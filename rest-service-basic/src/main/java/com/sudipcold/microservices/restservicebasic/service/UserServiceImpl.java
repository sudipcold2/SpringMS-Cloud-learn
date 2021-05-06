package com.sudipcold.microservices.restservicebasic.service;

import com.sudipcold.microservices.restservicebasic.model.User;
import com.sudipcold.microservices.restservicebasic.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public List<User> getAllUsers() {
        return userDao.findAllUsers();
    }

    @Override
    public User getUserByID(int id) {
        return userDao.findUser(id);
    }

    @Override
    public User saveUser(User user) {
        return userDao.saveUser(user);
    }

    @Override
    public User removeUser(int id) {
        return userDao.deleteUser(id);
    }
}
