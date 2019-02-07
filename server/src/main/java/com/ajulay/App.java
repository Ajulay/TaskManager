package com.ajulay;

import com.ajulay.api.controller.IControllerUI;
import com.ajulay.hibernate.PersistenceJPAConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) throws Exception {
//        @NotNull final SeContainerInitializer initializer = SeContainerInitializer.newInstance();
//        @NotNull final SeContainer seContainer = initializer.initialize();
//        @NotNull final IControllerUI controllerUI = seContainer.select(IControllerUI.class).get();
//        controllerUI.run();
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(PersistenceJPAConfig.class);
        // ctx.scan("com.ajulay");
        // ctx.refresh();
        IControllerUI controller = ctx.getBean(IControllerUI.class);
        controller.run();
    }

}
