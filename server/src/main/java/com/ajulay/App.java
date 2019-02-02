package com.ajulay;

import com.ajulay.api.controller.IControllerUI;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

public class App {

    public static void main(String[] args) throws Exception {
        @NotNull final SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        @NotNull final SeContainer seContainer = initializer.initialize();
        @NotNull final IControllerUI controllerUI = seContainer.select(IControllerUI.class).get();
        controllerUI.run();
        //TODO 39178,08. 331
    }

}
