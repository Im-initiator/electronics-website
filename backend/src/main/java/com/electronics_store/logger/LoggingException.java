package com.electronics_store.logger;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingException {

    public static final Logger LOGGER = LoggerFactory.getLogger(LoggingServiceAspect.class);

    @Pointcut("execution(* com.electronics_store.exception.GlobalExceptionHandler.*(..))")
    public void pointCut() {}

    @Around("pointCut()")
    public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getTarget().getClass().getName();
        //       String methodName = joinPoint.getTarget().getClass().getInterfaces()[0].getName();
        Object[] args = joinPoint.getArgs();
        //       LOGGER.info("class " + className + " in method " + methodName + " BEGIN...\n");
        for (Object arg : args) {
            Throwable ex = (Throwable) arg;
            StringWriter stringWriter = new StringWriter();
            ex.printStackTrace(new PrintWriter(stringWriter));
            LOGGER.error("Exception: " + stringWriter.toString());
        }
        //        LOGGER.info("class " + className + " in method " + methodName + " END.\n");

        return joinPoint.proceed();
    }
}
