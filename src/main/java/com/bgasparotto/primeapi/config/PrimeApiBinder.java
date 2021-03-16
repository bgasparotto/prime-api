package com.bgasparotto.primeapi.config;

import com.bgasparotto.primeapi.service.SampleService;
import jakarta.inject.Singleton;
import org.glassfish.jersey.internal.inject.AbstractBinder;

public class PrimeApiBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bindAsContract(SampleService.class).in(Singleton.class);
    }
}
