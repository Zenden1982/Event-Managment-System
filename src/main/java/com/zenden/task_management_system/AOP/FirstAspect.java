package com.zenden.task_management_system.AOP;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class FirstAspect {
    
    @Pointcut("within(com.zenden.task_management_system.Services.*Service)")
    public void isService() {

    }

    @Pointcut("within(com.zenden.task_management_system.Controllers.*Controller)")
    public void isController() {
        
    }

    @Pointcut("this(org.springframework.stereotype.Repository)")
    public void repositoryPointCut() {

    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void getMappingPointCut() {

    }

    @Pointcut("isController() && @args(org.springframework.web.bind.annotation.RequestBody,..)")
    public void postMappingPointCut() {

    }

    @Pointcut("bean(*Service)")
    public void beanPointCut() {
    }

    @Pointcut("execution(public * com.zenden.task_management_system.Services.*Service.*(long))")
    public void controllerPointCut() {
    }

    @Before("controllerPointCut()")
    public void beforeControllerAdvice() {
        log.info("Before Controller Advice");
    }
}
