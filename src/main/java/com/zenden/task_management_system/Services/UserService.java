package com.zenden.task_management_system.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.zenden.task_management_system.Classes.Role;
import com.zenden.task_management_system.Classes.User;
import com.zenden.task_management_system.Classes.DTO.UserDTO;
import com.zenden.task_management_system.Mapper.Mapper;
import com.zenden.task_management_system.Repositories.RoleRepository;
import com.zenden.task_management_system.Repositories.UserRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private Mapper mapper;

    @Autowired
    private EmailService emailService;

    public UserDTO createUser(UserDTO userDTO) {
        User user = mapper.map(userDTO);

        Optional<Role> roleOptional = roleRepository.findByName("ROLE_USER");
        if (roleOptional.isPresent()) {
            user.setRoles(List.of(roleOptional.get()));
        }
        else if (roleOptional.isEmpty()) {
            Role role = new Role();
            role.setName("ROLE_USER");
            roleRepository.saveAndFlush(role);
            user.setRoles(List.of(roleRepository.findByName("ROLE_USER").get()));
        }
        String subject = "Добро пожаловать в нашу систему управления мероприятиями";
        String message = "Дорогой " + userDTO.getUsername() + ",\n\nСпасибо за регистрацию!";
        emailService.sendEmail(userDTO.getEmail(), subject, message);
        return mapper.map(userRepository.save(mapper.map(userDTO)));
    }

    public UserDTO getUserById(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return mapper.map(user.get());
        }
        return null;
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        log.info("User: {}", user);
        if (user.isPresent()) {
            return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(), user.get().getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).toList());
        }
        throw new UsernameNotFoundException("User not found");
    }

}
