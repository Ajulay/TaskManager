package com.ajulay;

import com.ajulay.api.controller.IControllerUI;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

public class App {

    public static void main(String[] args) throws Exception {
        final SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        final SeContainer seContainer = initializer.initialize();
        final IControllerUI controllerUI = seContainer.select(IControllerUI.class).get();
        controllerUI.run();

        //TODO 39178,08. 331
    }

}
