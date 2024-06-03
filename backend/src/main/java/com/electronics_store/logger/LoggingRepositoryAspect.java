package com.electronics_store.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingRepositoryAspect {
    // log by slf4j
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingRepositoryAspect.class);

    @Pointcut("execution(* com.electronics_store.repository.*.*(..))")
    public void pointCut() {}

    @Before("pointCut()")
    public void showMethod(JoinPoint joinPoint) {
        String interfaceName =
                joinPoint.getTarget().getClass().getInterfaces()[0].getName();
        String methodName = joinPoint.getSignature().getName();
        LOGGER.info("SQL FROM " + interfaceName + " in method " + methodName + " \n");
    }
}
