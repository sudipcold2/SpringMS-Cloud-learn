package com.sudipcold.microservices.limitsmicroservice.controller;

import com.sudipcold.microservices.limitsmicroservice.configuration.Configuration;
import com.sudipcold.microservices.limitsmicroservice.model.Limits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {

    @Autowired
    Configuration configuration;

    @GetMapping("/limits")
    public Limits retrieveLimits(){
        return new Limits(configuration.getMinimum(), configuration.getMaximum());
    }
}
