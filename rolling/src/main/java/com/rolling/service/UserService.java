package com.rolling.service;

import java.util.List;

import com.rolling.entity.User;
import com.rolling.dto.UserDto;

public interface UserService {
    UserDto createUser(User user);

    UserDto getUserById(Long id);

    UserDto getUserByUsername(String username);

    List<UserDto> getAllUsers();

    UserDto updateUser(Long id, User userDetails);

    void deleteUser(Long id);
}