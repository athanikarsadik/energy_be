package com.energy.service;

import com.energy.model.User;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    List<User> getAllUsers();

}
