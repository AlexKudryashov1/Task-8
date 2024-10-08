package ru.itmentor.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserServices extends UserDetailsService {
    List<User> index();

    User show(Long id);

    User findByUsername(String username);

    void save (User user);

    void delete(Long id);

    void update(User user, Long id);

    void register(User User);

}
