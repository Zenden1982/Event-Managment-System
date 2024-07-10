package com.zenden.task_management_system.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zenden.task_management_system.Classes.DTO.UserDTO;
import com.zenden.task_management_system.Classes.User;
import com.zenden.task_management_system.Mapper.Mapper;
import com.zenden.task_management_system.Repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Mapper mapper;

    public UserDTO createUser(UserDTO userDTO) {
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

}
