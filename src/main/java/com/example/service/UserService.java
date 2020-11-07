package com.example.service;

import com.example.pojo.User;

public interface UserService {

    User checkUser(String username, String password);
}
