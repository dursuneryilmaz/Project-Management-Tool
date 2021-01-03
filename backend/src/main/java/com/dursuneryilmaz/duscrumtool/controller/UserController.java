package com.dursuneryilmaz.duscrumtool.controller;

import com.dursuneryilmaz.duscrumtool.domain.User;
import com.dursuneryilmaz.duscrumtool.model.request.UserLoginRequestModel;
import com.dursuneryilmaz.duscrumtool.model.request.UserRegisterRequestModel;
import com.dursuneryilmaz.duscrumtool.model.response.UserLoginResponseModel;
import com.dursuneryilmaz.duscrumtool.model.response.UserResponseModel;
import com.dursuneryilmaz.duscrumtool.security.SecurityConstants;
import com.dursuneryilmaz.duscrumtool.security.jwt.JwtTokenProvider;
import com.dursuneryilmaz.duscrumtool.service.RequestValidationService;
import com.dursuneryilmaz.duscrumtool.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    RequestValidationService requestValidationService;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody UserLoginRequestModel loginRequest, BindingResult result) {
        ResponseEntity<?> errorMap = requestValidationService.mapValidationErrors(result);
        if (errorMap != null) return errorMap;

        User user = userService.getUserByEmail(loginRequest.getEmail());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                        //  user.getAuthorities() -> ok for roles ?
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = SecurityConstants.TOKEN_PREFIX + jwtTokenProvider.generateToken(authentication);
        // find way to object mapping to reduce code lines
        UserLoginResponseModel userLoginResponseModel = new UserLoginResponseModel(jwt, true);
        userLoginResponseModel.setUserId(user.getUserId());
        userLoginResponseModel.setFirstName(user.getFirstName());
        userLoginResponseModel.setLastName(user.getLastName());
        userLoginResponseModel.setEmail(user.getEmail());

        return ResponseEntity.ok(userLoginResponseModel);
    }

    @PostMapping(path = "/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRegisterRequestModel userRegisterRequestModel, BindingResult bindingResult) {
        ResponseEntity<?> errorMap = requestValidationService.mapValidationErrors(bindingResult);
        if (errorMap != null) return errorMap;

        User user = new User();
        user.setEncryptedPassword(userRegisterRequestModel.getPassword());
        BeanUtils.copyProperties(userRegisterRequestModel, user);
        User savedUser = userService.createUser(user);

        UserResponseModel userResponseModel = new UserResponseModel();
        BeanUtils.copyProperties(savedUser, userResponseModel);

        return new ResponseEntity<UserResponseModel>(userResponseModel, HttpStatus.CREATED);
    }
}
