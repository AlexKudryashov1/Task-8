package ru.itmentor.spring.boot_security.demo.controller;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.Util.UserValidator;
import ru.itmentor.spring.boot_security.demo.dto.UserDTO;
import ru.itmentor.spring.boot_security.demo.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import ru.itmentor.spring.boot_security.demo.services.RoleServices;
import ru.itmentor.spring.boot_security.demo.services.UserServices;

import java.util.List;
import java.util.Set;


@RestController
public class AuthController {

    private final UserValidator userValidator;
    private final UserServices userServices;

    @Autowired
    public AuthController(UserValidator userValidator, UserServices userServices) {
        this.userValidator = userValidator;;
        this.userServices = userServices;
    }


    @PostMapping("/registration")
    public ResponseEntity<UserDTO> registration(@RequestBody @Valid UserDTO userDTO, BindingResult bindingResult) {
        userValidator.validate(userDTO, bindingResult);
        if (bindingResult.hasErrors()) {
           return new  ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        userServices.register(userDTO);
        return ResponseEntity.ok().body(userDTO);
    }
}
