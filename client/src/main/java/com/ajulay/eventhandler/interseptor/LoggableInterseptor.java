package com.ajulay.eventhandler.interseptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.Arrays;
import java.util.logging.Logger;

@Logable
@Interceptor
public class LoggableInterseptor {

    @AroundInvoke
    public Object intercept(InvocationContext ctx) throws Exception {
        System.out.println("In LoggingInterceptor");

        Logger logger = Logger.getLogger(ctx.getTarget().getClass().getName());
        logger.info("before call to " + ctx.getMethod() + " with args " + Arrays.toString(ctx.getParameters()));
        Object returnMe = ctx.proceed();
        logger.info("after call to " + ctx.getMethod() + " returned " + returnMe);
        return returnMe;
    }

}