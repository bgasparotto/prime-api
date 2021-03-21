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

/**
 * Jersey is tied to HK2 which uses the ServiceLocator as its DI container, whereas Guice uses the Injector as the DI
 * container. The bridge enables Guice components to also be discovered via the ServiceLocator (`javax.inject.Inject`),
 * and Jersey controllers (`javax.ws.rs.Path`) to be discovered via the Injector (`com.google.inject.Inject`). However,
 * one can just use `javax.inject.Inject` for standardisation with this config.
 */
public class GuiceBridgeFeature implements Feature {

    @Override
    public boolean configure(FeatureContext context) {
        InjectionManager injectionManager = InjectionManagerProvider.getInjectionManager(context);
        ServiceLocator hk2ServiceLocator = injectionManager.getInstance(ServiceLocator.class);

        GuiceBridge.getGuiceBridge().initializeGuiceBridge(hk2ServiceLocator);
        GuiceIntoHK2Bridge guiceBridge = hk2ServiceLocator.getService(GuiceIntoHK2Bridge.class);

        Injector guiceInjector = Guice.createInjector(new GuiceInjectorModule());
        guiceBridge.bridgeGuiceInjector(guiceInjector);

        return true;
    }
}
