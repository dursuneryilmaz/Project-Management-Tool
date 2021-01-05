package com.dursuneryilmaz.duscrumtool.controller;

import com.dursuneryilmaz.duscrumtool.domain.User;
import com.dursuneryilmaz.duscrumtool.model.request.*;
import com.dursuneryilmaz.duscrumtool.model.response.*;
import com.dursuneryilmaz.duscrumtool.security.jwt.JwtTokenProvider;
import com.dursuneryilmaz.duscrumtool.service.RequestValidationService;
import com.dursuneryilmaz.duscrumtool.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    // register user
    @PostMapping(path = "/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRegisterRequestModel userRegisterRequestModel,
                                        BindingResult bindingResult) {
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

    //get user
    @GetMapping(path = "/{userId}")
    public UserResponseModel getUser(@PathVariable String userId) {
        // get users
        User user = userService.getUserByUserId(userId);
        UserResponseModel userResponseModel = new UserResponseModel();
        BeanUtils.copyProperties(user, userResponseModel);
        return userResponseModel;
    }

    // update user
    @PutMapping(path = "/{userId}")
    public UserResponseModel updateUser(@PathVariable String userId, @RequestBody UserRequestModel userDetail) {

        User user = userService.getUserByUserId(userId);
        user.setFirstName(userDetail.getFirstName());
        user.setLastName(userDetail.getLastName());
        User updatedUser = userService.updateUser(userId, user);

        UserResponseModel userResponseModel = new UserResponseModel();
        BeanUtils.copyProperties(updatedUser, userResponseModel);
        return userResponseModel;
    }

    // delete user
    @DeleteMapping(path = "/{userId}")
    public OperationModel deleteUser(@PathVariable String userId) {
        OperationModel operationModel = new OperationModel();
        operationModel.setOperationName(OperationName.DELETE.name());
        userService.deleteUser(userId);
        operationModel.setOperationStatus(OperationStatus.SUCCESS.name());
        return operationModel;
    }

    // user email verification
    @GetMapping(path = "/email-verification")
    public OperationModel verifyEmailToken(@RequestParam(value = "token") String token) {
        OperationModel operationModel = new OperationModel();
        operationModel.setOperationName(OperationName.VERIFY_EMAIL.name());
        boolean isVerified = userService.verifyEmailToken(token);
        if (isVerified) {
            operationModel.setOperationStatus(OperationStatus.SUCCESS.name());
        } else {
            operationModel.setOperationStatus(OperationStatus.ERROR.name());
        }
        return operationModel;
    }

    @PostMapping(path = "/password-reset-request")
    public OperationModel requestPasswordReset(@RequestBody UserResetPasswordRequestModel passwordResetRequestModel) {
        OperationModel operationModel = new OperationModel();
        operationModel.setOperationName(OperationName.PASSWORD_RESET_REQUEST.name());

        boolean isEmailSent = userService.requestPasswordReset(passwordResetRequestModel.getEmail());
        if (isEmailSent) {
            operationModel.setOperationStatus(OperationStatus.SUCCESS.name());
        } else {
            operationModel.setOperationStatus(OperationStatus.ERROR.name());
        }
        return operationModel;
    }

    @PostMapping(path = "/password-reset")
    public OperationModel passwordReset(@RequestBody UserNewPasswordRequestModel newPasswordRequestModel) {
        OperationModel operationModel = new OperationModel();

        boolean operationResult = userService.resetPassword(
                newPasswordRequestModel.getToken(),
                newPasswordRequestModel.getPassword());

        operationModel.setOperationName(OperationName.PASSWORD_RESET.name());
        operationModel.setOperationStatus(OperationStatus.ERROR.name());

        if (operationResult) {
            operationModel.setOperationStatus(OperationStatus.SUCCESS.name());
        }
        return operationModel;
    }
}
