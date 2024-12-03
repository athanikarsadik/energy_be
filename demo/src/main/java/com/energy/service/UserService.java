package com.energy.service;

import com.energy.model.User;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    List<User> getAllUsers();

    User createUser(User user);

    User updateUser(Long userId, User updatedUser);

    void deleteUser(Long userId);

}
