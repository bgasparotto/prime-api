package com.bgasparotto.primeapi.service;

import javax.inject.Inject;

public class SampleService {
    private final AnotherService anotherService;

    @Inject
    public SampleService(AnotherService anotherService) {
        this.anotherService = anotherService;
    }

    public String print() {
        return "Got that! with " + anotherService.printIt();
    }
}
