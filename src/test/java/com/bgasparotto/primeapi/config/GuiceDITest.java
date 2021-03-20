package com.bgasparotto.primeapi.config;

import com.bgasparotto.primeapi.controller.MyResource;
import com.bgasparotto.primeapi.service.SampleService;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.assertNotNull;

public class GuiceDITest {
    private final Injector injector = Guice.createInjector(new GuiceInjectorModule());

    @Inject
    private SampleService injectedService;

    @Inject
    private MyResource injectedResource;

    @Before
    public void setup() {
        injector.injectMembers(this);
    }

    @Test
    public void shouldInjectGuiceComponent() {
        assertNotNull(injectedService);
    }

    @Test
    public void shouldInjectHK2Component() {
        assertNotNull(injectedResource);
    }
}
