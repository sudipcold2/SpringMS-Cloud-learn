package com.sudipcold.microservices.restservicebasic.testcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private MessageSource messageSource;

//    @GetMapping(path = "/test/testLang")
//    public TestBean testBean(@RequestHeader(name = "Accept-Language", required = false) Locale locale){
//        return new TestBean(messageSource.getMessage("good.morning.message", null, locale));
//    }

    @GetMapping(path = "/test/testLang")
    public TestBean testBean(){
        return new TestBean(messageSource.getMessage("good.morning.message",
                null, LocaleContextHolder.getLocale()));
    }
}
