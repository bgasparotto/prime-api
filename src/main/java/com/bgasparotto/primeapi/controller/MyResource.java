package com.bgasparotto.primeapi.controller;

import com.bgasparotto.primeapi.service.SampleService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.Map;

@Path("myresource")
public class MyResource {
    private final SampleService sampleService;

    @Inject
    public MyResource(SampleService sampleService) {
        this.sampleService = sampleService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, String> getIt() {
        return Map.of("value", sampleService.print());
    }
}
