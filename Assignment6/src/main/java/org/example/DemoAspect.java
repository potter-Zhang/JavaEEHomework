package org.example;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DemoAspect {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Before("execution(public * *(..))")
    public void controllerPointCut() {
        System.out.println("line here");
    }

    @Before("controllerPointCut()")
    public void beforePointCut(JoinPoint jp) {
        String info = "Calling method " + jp.getSignature();
        String argument = "";
        for (Object arg : jp.getArgs()) {
            argument += arg;
        }

        logger.info(info + argument);
        System.out.println(info + argument);
    }
}
