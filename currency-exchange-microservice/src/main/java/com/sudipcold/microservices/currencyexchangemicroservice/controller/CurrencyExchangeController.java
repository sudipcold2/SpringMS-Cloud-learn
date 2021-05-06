package com.sudipcold.microservices.currencyexchangemicroservice.controller;

import com.sudipcold.microservices.currencyexchangemicroservice.model.CurrencyExchange;
import com.sudipcold.microservices.currencyexchangemicroservice.repository.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepository;

    @Autowired
    private Environment environment;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to){
        CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromAndTo(from, to);

        if(currencyExchange == null){
            throw new RuntimeException("Unable to find data for " + from + " and " + to);
        }

        String port = environment.getProperty("local.server.port");

        currencyExchange.setEnvironment(port);
        return currencyExchange;
    }

}
