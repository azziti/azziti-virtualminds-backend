package com.virtualminds.test.backend.controllers;

import com.virtualminds.test.backend.entities.User;
import com.virtualminds.test.backend.services.UserService;
import com.virtualminds.test.backend.shared.requests.RegisterRequest;
import com.virtualminds.test.backend.utils.error_managment.exceptions.PasswordMismatchException;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    //new account
    @PostMapping("/register")
    public User register(@RequestBody @Valid RegisterRequest registerRequest) throws InvocationTargetException, PasswordMismatchException, IllegalAccessException {

        if (!registerRequest.getPassword().equals(registerRequest.getRepassword()))
            throw new PasswordMismatchException("le mot de passe et la confirmation doivent etre identique !");


        User user = new User();
        BeanUtils.copyProperties(user, registerRequest);
        userService.saveUser(user);
        userService.AddRoleToUser(user.getUsername(), "USER");
        return user;
    }


}

