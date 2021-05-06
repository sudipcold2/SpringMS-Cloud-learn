package com.sudipcold.microservices.restservicebasic.service;

import com.sudipcold.microservices.restservicebasic.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserByID(int id);

    User saveUser(User user);

    User removeUser(int id);

}
