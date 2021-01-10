package com.dursuneryilmaz.duscrumtool.controller;

import com.dursuneryilmaz.duscrumtool.domain.Role;
import com.dursuneryilmaz.duscrumtool.domain.User;
import com.dursuneryilmaz.duscrumtool.model.response.OperationModel;
import com.dursuneryilmaz.duscrumtool.service.RequestValidationService;
import com.dursuneryilmaz.duscrumtool.service.RoleService;
import com.dursuneryilmaz.duscrumtool.service.UserService;
import com.dursuneryilmaz.duscrumtool.shared.enums.OperationName;
import com.dursuneryilmaz.duscrumtool.shared.enums.OperationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {
    @Autowired
    RoleService roleService;
    @Autowired
    RequestValidationService requestValidationService;
    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<?> createRole(@Valid @RequestBody Role role,
                                        BindingResult bindingResult, Principal principal) {
        Role superAdmin = roleService.getRoleByName("ROLE_SUPER_ADMIN");
        if (userService.getUserByEmail(principal.getName()).getRoleSet().contains(superAdmin)) {
            ResponseEntity<?> errorMap = requestValidationService.mapValidationErrors(bindingResult);
            if (errorMap != null) return errorMap;
            return new ResponseEntity<Role>(roleService.createRole(role), HttpStatus.CREATED);
        }
        return requestValidationService.getAccessDeniedResponseEntity();
    }

    // delete product
    @DeleteMapping(path = "/{roleId}")
    public ResponseEntity<OperationModel> deleteProductByProductId(@PathVariable String roleId, Principal principal) {
        OperationModel operationModel = new OperationModel();
        Role superAdmin = roleService.getRoleByName("ROLE_SUPER_ADMIN");
        if (userService.getUserByEmail(principal.getName()).getRoleSet().contains(superAdmin)) {
            if (roleService.deleteRoleById(roleId)) {
                operationModel.setOperationName(OperationName.DELETE.name());
                operationModel.setOperationStatus(OperationStatus.SUCCESS.name());
                return new ResponseEntity<>(operationModel, HttpStatus.OK);
            }
        }
        return requestValidationService.getAccessDeniedResponseEntity();
    }

}
