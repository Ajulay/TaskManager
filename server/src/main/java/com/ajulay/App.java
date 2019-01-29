package com.ajulay;

import com.ajulay.controller.ControllerUI;

public class App {

    public static void main(String[] args) throws Exception {
        final ControllerUI controllerUI = new ControllerUI();
        controllerUI.run();
//        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
//        initializer.addBeanClasses(IControllerUI.class);
//        SeContainer seContainer = initializer.initialize();
//        IControllerUI controllerUI1 = seContainer.select(IControllerUI.class).get();
//        System.out.println(controllerUI1.getCommands().size());
        // 39178,08.
    }

}
