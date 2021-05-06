package com.sudipcold.microservices.restservicebasic.repository;

import com.sudipcold.microservices.restservicebasic.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Integer> {
}
