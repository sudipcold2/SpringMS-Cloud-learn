package com.sudipcold.microservices.restservicebasic.repository;

import com.sudipcold.microservices.restservicebasic.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostJpaRepository extends JpaRepository<Post, Integer> {
}
