package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import ru.itmentor.spring.boot_security.demo.dto.UserDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import ru.itmentor.spring.boot_security.demo.Util.UserValidator;
import ru.itmentor.spring.boot_security.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.services.RoleServices;
import ru.itmentor.spring.boot_security.demo.services.UserServices;

import java.util.List;


@RestController
@RequestMapping("/admin")
public class AdminController {
    private final UserServices userServices;
    private final UserValidator userValidator;


    @Autowired
    public AdminController(UserServices userServices, UserValidator userValidator) {
        this.userValidator = userValidator;
        this.userServices = userServices;
    }


    @GetMapping()
    public ResponseEntity<List<User>>index() {
        List<User> listusers = userServices.index();
        return ResponseEntity.ok().body(listusers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> show(@PathVariable("id") long id) {
        UserDTO userDTO = userServices.getUserDTO(id);
        return ResponseEntity.ok().body(userDTO);
    }


    @PostMapping("/add")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid UserDTO userDTO, BindingResult bindingResult) {
        userValidator.validate(userDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        userServices.register(userDTO);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }



    @PatchMapping("/{id}")
    public ResponseEntity<UserDTO> update(@RequestBody @Valid UserDTO userDTO, BindingResult bindingResult,
                                          @PathVariable("id") long id) {
        if (bindingResult.hasErrors()) {
            return new  ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        userServices.updateUserFromDTO(userDTO,id);
        return ResponseEntity.ok().body(userDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
        userServices.delete(id);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

}
