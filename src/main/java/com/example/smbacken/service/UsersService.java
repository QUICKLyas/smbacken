package com.example.smbacken.service;

import com.example.smbacken.javabean.Users;

public interface UsersService {
    public boolean isUserExist(String phone);
    public Users findUser(String phone);
    public void addUserPhone(Users user);
}
