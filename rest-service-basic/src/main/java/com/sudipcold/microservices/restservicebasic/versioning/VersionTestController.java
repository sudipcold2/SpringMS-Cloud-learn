package com.sudipcold.microservices.restservicebasic.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Factors to decide
 *  1. URI pollution
 *  2. Misuse of HTTP headers
 *  3. Caching
 *  4. Can we execute the request on the browser
 *  5. API documentation
 *
 *  No perfect solution
 */
@RestController
public class VersionTestController {

    /**
     * method 1
     * URI versioning
     */
    @GetMapping(path = "/v1/person")
    public PersonV1 getPersonV1() {
        return new PersonV1("Sudip Ghosh");
    }

    @GetMapping(path = "/v2/person")
    public PersonV2 getPersonV2() {
        return new PersonV2(new Name("Sudip", "Ghosh"));
    }

    /**
     * method 2
     * Query parameter versioning
     */
    @GetMapping(value = "/person/param", params = "version=1")
    public PersonV1 getPersonParamsV1() {
        return new PersonV1("Sudip Ghosh");
    }

    @GetMapping(value = "/person/param", params = "version=2")
    public PersonV2 getPersonParamsV2() {
        return new PersonV2(new Name("Sudip", "Ghosh"));
    }

    /**
     * method 3
     * Header versioning
     */
    @GetMapping(value = "/person/header", headers = "X-API-VERSION=1")
    public PersonV1 getPersonHeadersV1() {
        return new PersonV1("Sudip Ghosh");
    }

    @GetMapping(value = "/person/header", headers = "X-API-VERSION=2")
    public PersonV2 getPersonHeadersV2() {
        return new PersonV2(new Name("Sudip", "Ghosh"));
    }

    /**
     * method 4
     * mime type versioning
     */
    @GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v1+json")
    public PersonV1 getPersonProducesV1() {
        return new PersonV1("Sudip Ghosh");
    }

    @GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v2+json")
    public PersonV2 getPersonProduces2() {
        return new PersonV2(new Name("Sudip", "Ghosh"));
    }
}
