package com.bgasparotto.primeapi.config;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.InjectionManagerProvider;
import org.glassfish.jersey.internal.inject.InjectionManager;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

public class GuiceBridgeFeature implements Feature {

    @Override
    public boolean configure(FeatureContext context) {
        InjectionManager injectionManager = InjectionManagerProvider.getInjectionManager(context);
        ServiceLocator serviceLocator = injectionManager.getInstance(ServiceLocator.class);

        GuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);
        GuiceIntoHK2Bridge guiceBridge = serviceLocator.getService(GuiceIntoHK2Bridge.class);

        Injector injector = Guice.createInjector(new GuiceInjectorModule());
        guiceBridge.bridgeGuiceInjector(injector);

        return true;
    }
}
