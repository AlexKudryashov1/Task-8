package ru.itmentor.spring.boot_security.demo.services;

import ru.itmentor.spring.boot_security.demo.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserServices extends UserDetailsService {
    List<User> index();

    User show(Long id);

    User findByUsername(String username);

    void save (User user);

    void delete(Long id);
    void update(User user, Long id);

    void updateUserFromDTO(UserDTO userDTO, Long id);

    void register(UserDTO userDTO);
    UserDTO getUserDTO(Long id);

}
