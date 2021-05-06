package com.sudipcold.microservices.restservicebasic.controller;

import com.sudipcold.microservices.restservicebasic.exception.UserNotFoundException;
import com.sudipcold.microservices.restservicebasic.model.User;
import com.sudipcold.microservices.restservicebasic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserResourceImpl {

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET, path = "/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping(path = "/users/{id}")
    public EntityModel<User> getUser(@PathVariable int id){
        User user = userService.getUserByID(id);
        if(user == null){
            throw new UserNotFoundException("User not found");
        }

        EntityModel<User> resource = EntityModel.of(user);
        WebMvcLinkBuilder linkTo =
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers());

        resource.add(linkTo.withRel("all-users"));

        return resource;
    }

    @PostMapping(path="/users")
    public ResponseEntity<Object> saveUser(@Valid @RequestBody User user){
        User createdUser = userService.saveUser(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/users/{id}")
    public void deleteUser(@PathVariable int id){
        User user = userService.removeUser(id);
        if(user == null){
            throw new UserNotFoundException("User not found");
        }
    }

}
