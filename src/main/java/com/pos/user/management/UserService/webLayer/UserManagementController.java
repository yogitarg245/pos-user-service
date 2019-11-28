package com.pos.user.management.UserService.webLayer;

import com.pos.user.management.UserService.model.UserRegistration;
import com.pos.user.management.UserService.payload.UserCreationReponse;
import com.pos.user.management.UserService.service.IPosUserService;
import com.pos.user.management.UserService.validationImpl.PosValidationErrorHandling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/userapi/v1")
public class UserManagementController {

    @Autowired
    private PosValidationErrorHandling posValidationErrorHandling;
    @Autowired
    private IPosUserService posUserService;

    @PostMapping(value = "/registeruser")
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserRegistration userRegistrationRequest, BindingResult bindingResult) {

        ResponseEntity<?> responseEntity = posValidationErrorHandling.validateErrors(bindingResult);
        if (responseEntity != null) {
            return responseEntity;
        }
        UserCreationReponse userCreationReponse = posUserService.registerNewUser(userRegistrationRequest);

        return new ResponseEntity<>(userCreationReponse, HttpStatus.CREATED);
    }

    @GetMapping(value = "/activateUser/{token}")
    public ResponseEntity<?> confirmRegistration(@PathVariable @NotNull String token) {


        return new ResponseEntity<String>("User Activated Successfully", HttpStatus.OK);
    }
}