package ru.itmentor.spring.boot_security.demo.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.itmentor.spring.boot_security.demo.dto.UserDTO;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.services.UserServices;

@Component
public class UserValidator implements Validator {

    private final UserServices userServices;

    @Autowired
    public UserValidator(UserServices userServices) {
        this.userServices = userServices;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDTO userDTO = (UserDTO) target;
        if (userServices.findByUsername(userDTO.getUsername()) != null) {
            errors.rejectValue("username", "", "User exist");

        }
    }
}
