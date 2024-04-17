package com.first.Service;

import com.first.entity.User;

public interface UserService {
    User getUserById(Long userId);
    User saveUser(User user);

}