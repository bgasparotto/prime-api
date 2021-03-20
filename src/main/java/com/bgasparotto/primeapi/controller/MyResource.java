package com.bgasparotto.primeapi.controller;

import com.bgasparotto.primeapi.service.AnotherService;
import com.bgasparotto.primeapi.service.SampleService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Map;

@Path("myresource")
public class MyResource {
    private final SampleService sampleService;
    private final AnotherService anotherService;

    @Inject
    public MyResource(SampleService sampleService, AnotherService anotherService) {
        this.sampleService = sampleService;
        this.anotherService = anotherService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, String> getIt() {
        return Map.of("value", sampleService.print() + " and " + anotherService.printIt());
    }
}
