package ru.itmentor.spring.boot_security.demo.services;

import org.modelmapper.ModelMapper;
import ru.itmentor.spring.boot_security.demo.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.repositories.RoleRepositories;
import ru.itmentor.spring.boot_security.demo.repositories.UserRepositories;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class UserServicesimpl implements UserServices {
    private final UserRepositories userRepositories;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final RoleServices roleServices;

    @Autowired
    public UserServicesimpl(UserRepositories userRepositories, PasswordEncoder passwordEncoder, ModelMapper modelMapper, RoleServices roleServices) {
        this.userRepositories = userRepositories;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.roleServices = roleServices;
    }


    @Override
    public List<User> index() {
        return userRepositories.findAll();
    }

    @Override
    public User show(Long id) {
        return userRepositories.findById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepositories.findByUsername(username);
    }

    @Transactional
    @Override
    public void save(User user) {
        userRepositories.save(user);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        userRepositories.deleteById(id);
    }

    @Transactional
    @Override
    public void update(User user, Long id) {
        user.setId(id);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepositories.save(user);
    }
    @Transactional
    @Override
    public void updateUserFromDTO(UserDTO userDTO, Long id) {
        User user = userDtoToUser(userDTO);
        user.setId(id);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepositories.save(user);

    }

    @Transactional
    @Override
    public void register(UserDTO userDTO) {
        if (userDTO.getPassword() == null || userDTO.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        User user = userDtoToUser(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepositories.save(user);
    }

    @Override
    public UserDTO getUserDTO(Long id) {
        User user = userRepositories.findById(id);
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepositories.findByUsernameAndFetchLazyRelationEagerly(username);
    }

    private User userDtoToUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        Set<Role> roles = new HashSet<>();
        for (var role : userDTO.getRoles()) {
            roles.add(roleServices.findByName(role));
        }
        user.setRoles(roles);
        return user;
    }
}
