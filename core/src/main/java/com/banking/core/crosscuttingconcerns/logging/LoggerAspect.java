package com.banking.core.crosscuttingconcerns.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import com.banking.core.crosscuttingconcerns.logging.annotations.LoggableMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Component
public class LoggerAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggerAspect.class);

    @Around("@within(com.banking.core.crosscuttingconcerns.logging.annotations.LoggableService) || " +
            "@annotation(com.banking.core.crosscuttingconcerns.logging.annotations.LoggableMethod)")
    public Object logMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        // Get method description if LoggableMethod annotation is present
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LoggableMethod loggableMethod = method.getAnnotation(LoggableMethod.class);
        String description = loggableMethod != null ? loggableMethod.description() : "";

        if (!description.isEmpty()) {
            logger.info("Executing {}.{} - {} with parameters: {}", 
                className, methodName, description, Arrays.toString(args));
        } else {
            logger.info("Executing {}.{} with parameters: {}", 
                className, methodName, Arrays.toString(args));
        }

        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();

        logger.info("Completed {}.{} in {} ms", className, methodName, (endTime - startTime));
        return result;
    }

    @AfterThrowing(pointcut = "@within(com.banking.core.crosscuttingconcerns.logging.annotations.LoggableService) || " +
                            "@annotation(com.banking.core.crosscuttingconcerns.logging.annotations.LoggableMethod)", 
                  throwing = "exception")
    public void logException(JoinPoint joinPoint, Exception exception) {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        
        logger.error("Exception in {}.{}: {} - {}", 
            className, 
            methodName, 
            exception.getClass().getSimpleName(), 
            exception.getMessage());
    }
} 