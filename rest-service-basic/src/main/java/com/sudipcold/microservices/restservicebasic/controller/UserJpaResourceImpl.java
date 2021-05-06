package com.sudipcold.microservices.restservicebasic.controller;

import com.sudipcold.microservices.restservicebasic.exception.UserNotFoundException;
import com.sudipcold.microservices.restservicebasic.model.Post;
import com.sudipcold.microservices.restservicebasic.model.User;
import com.sudipcold.microservices.restservicebasic.repository.PostJpaRepository;
import com.sudipcold.microservices.restservicebasic.repository.UserJpaRepository;
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
import java.util.Optional;

@RestController
public class UserJpaResourceImpl {

    @Autowired
    UserJpaRepository userJpaRepository;

    @Autowired
    PostJpaRepository postJpaRepository;

    @RequestMapping(method = RequestMethod.GET, path = "/jpa/users")
    public List<User> getAllUsers(){
        return userJpaRepository.findAll();
    }

    @GetMapping(path = "/jpa/users/{id}")
    public EntityModel<User> getUser(@PathVariable int id){
        Optional<User> user = userJpaRepository.findById(id);
        if(!user.isPresent()){
            throw new UserNotFoundException("User not found");
        }

        EntityModel<User> resource = EntityModel.of(user.get());
        WebMvcLinkBuilder linkTo =
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers());

        resource.add(linkTo.withRel("all-users"));

        return resource;
    }

    @PostMapping(path="/jpa/users")
    public ResponseEntity<Object> saveUser(@Valid @RequestBody User user){
        User createdUser = userJpaRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/jpa/users/{id}")
    public void deleteUser(@PathVariable int id){
        userJpaRepository.deleteById(id);
    }

    @GetMapping(path = "/jpa/users/{id}/posts")
    public List<Post> getAllPostsForUser(@PathVariable int id) {
        Optional<User> user = userJpaRepository.findById(id);
        if(!user.isPresent()){
            throw new UserNotFoundException("User not found");
        }

        return user.get().getPosts();
    }

    @PostMapping(path="/jpa/users/{id}/posts")
    public ResponseEntity<Object> savePost(@PathVariable int id, @RequestBody Post post){
        Optional<User> user = userJpaRepository.findById(id);
        if(!user.isPresent())
            throw new UserNotFoundException("User not found");

        post.setUser(user.get());

        Post savedPost = postJpaRepository.save(post);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId()).toUri();

        return ResponseEntity.created(location).build();
    }


}
