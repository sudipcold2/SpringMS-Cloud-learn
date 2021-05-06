package com.sudipcold.microservices.restservicebasic.repository;

import com.sudipcold.microservices.restservicebasic.model.User;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    public static List<User> allUsers = new ArrayList<>();
    public static int idCounter = 4;

    static {
        allUsers.add(new User(1, "Sudip", LocalDate.of(1995,1,18)));
        allUsers.add(new User(2,"Rimi", LocalDate.of(1995,8, 7)));
        allUsers.add(new User(3, "Pankuri", LocalDate.of(1975,2,9)));
    }


    @Override
    public List<User> findAllUsers() {
        return allUsers;
    }

    @Override
    public User findUser(int id) {

        for(User user : allUsers){
            if(user.getId() == id){
                return user;
            }
        }

        return null;
    }

    @Override
    public User saveUser(User user) {
        if(user.getId() == 0){
            user.setId(idCounter++);
        }

        allUsers.add(user);
        return user;
    }

    @Override
    public User deleteUser(int id) {
        Iterator<User> itr = allUsers.iterator();
        while (itr.hasNext()){
            User user = itr.next();
            if(user.getId() == id){
                itr.remove();
                return user;
            }
        }

        return null;
    }
}
