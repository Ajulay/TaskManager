package com.ajulay;

import com.ajulay.api.IController;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

public class ClientApp {

    public static void main(String[] args) throws Exception {
        @NotNull final SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        @NotNull final SeContainer seContainer = initializer.initialize();
        @NotNull final IController controllerUI = seContainer.select(IController.class).get();
        controllerUI.run();
    }

}
