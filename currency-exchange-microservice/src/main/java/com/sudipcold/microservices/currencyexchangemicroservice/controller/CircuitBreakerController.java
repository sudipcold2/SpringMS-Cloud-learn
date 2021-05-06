package com.sudipcold.microservices.currencyexchangemicroservice.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {

    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    @GetMapping("/sample-api")
    @Retry(name = "sample-api", fallbackMethod = "fallbackMethodForSampleApi")
    public String sampleApi(){

        logger.info("Sample API call received");
        ResponseEntity<String> response =
                new RestTemplate().getForEntity("http://localhost:8080/scr-webclient", String.class);

        return response.getBody();
    }

    public String fallbackMethodForSampleApi(Exception e){
        logger.info("fallback for sample api 1 happened and exception message is "+ e.getMessage());
        return "fallback for sample api 1";
    }

    @GetMapping("/sample-api2")
    @CircuitBreaker(name = "default", fallbackMethod = "sampleAPI2FallbackMethod")
    public String sampleApi2(){

        logger.info("Sample API call 2 received");
        ResponseEntity<String> response =
                new RestTemplate().getForEntity("http://localhost:8080/scr-webclient", String.class);

        return response.getBody();
    }

    public String sampleAPI2FallbackMethod(Exception e){
        logger.info("fallback for sample api 2 happened and exception message is "+ e.getMessage());
        return "fallback for sample api 2";
    }

    @GetMapping("/rate-limiter")
    @RateLimiter(name = "rate-limit-instance")
    public String rateLimiter(){
        logger.info("Rate Limiter request received");
        return "Rate Limiting";
    }

    @GetMapping("/bulkhead-test")
    @Bulkhead(name = "bulkHeadInstance")
    public String bulkHead() throws InterruptedException {
        logger.info("Rate Limiter request received");
        Thread.sleep(5000);
        return "Rate Limiting";
    }
}
