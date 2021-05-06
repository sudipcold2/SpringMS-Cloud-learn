package com.sudipcold.microservices.restservicebasic.repository;

import com.sudipcold.microservices.restservicebasic.model.User;

import java.util.List;

public interface UserDao {

    List<User> findAllUsers();

    User findUser(int id);

    User saveUser(User user);

    User deleteUser(int id);

}
