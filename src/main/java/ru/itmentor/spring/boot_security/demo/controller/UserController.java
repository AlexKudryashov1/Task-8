package ru.itmentor.spring.boot_security.demo.controller;


import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.dto.UserDTO;
import ru.itmentor.spring.boot_security.demo.model.User;


@RestController
@RequestMapping("/users")
public class UserController {
    private final ModelMapper modelMapper;

    public UserController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public ResponseEntity<UserDTO> show(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(modelMapper.map(user, UserDTO.class));
    }

}

