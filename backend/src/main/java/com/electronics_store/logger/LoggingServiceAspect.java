package com.electronics_store.logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingServiceAspect {

    public static final Logger LOGGER = LoggerFactory.getLogger(LoggingServiceAspect.class);

    @Pointcut("execution(* com.electronics_store.service.impl.*.*(..))")
    public void pointCut() {}

    @Around("pointCut()")
    public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();
        LOGGER.info("class " + className + " in method " + methodName + " BEGIN...\n");
        Object result = joinPoint.proceed();
        LOGGER.info("class " + className + " in method " + methodName + " END.\n");
        return result;
    }
}
