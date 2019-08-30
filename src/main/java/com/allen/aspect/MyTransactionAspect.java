package com.allen.aspect;

import com.allen.annotaion.MyTransactional;
import com.allen.component.MyTransactionManager;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



/**
 * @author : allengent@163.com
 * @date : 2019/8/29 22:35
 * description :
 */


@Component
@Aspect
@Slf4j
public class MyTransactionAspect {

    @Autowired(required = false)
    private MyTransactionManager myTransactionManager;


    @Pointcut("execution(* com.allen.service.impl..*.*(..))")
    public void exeTransaction() {
        //
    }

    /**
     * 异常通知：给添加事务的方法回滚事务，当方法抛出异常时
     */
    @AfterThrowing("exeTransaction()")
    public void rollbackTransaction() {
        try {
            myTransactionManager.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * 环绕通知：给需要添加事务的方法，手动开启事务和提交事务
     * @param joinPoint
     * @throws Throwable
     */
    @Around("exeTransaction()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        Class<?> targetCls = joinPoint.getTarget().getClass();
        //获取即将要执行方法
        Method method = getInvokedMethod(targetCls, joinPoint);
        if (method == null) {
            joinPoint.proceed();
            return;
        }
        //判断执行方法是否有事务注解
        Annotation customTransAnno = method.getAnnotation(MyTransactional.class);
        if (customTransAnno == null) {
            log.info("方法上没有事务注解，直接开始执行方法...");

            joinPoint.proceed();
            return;
        }

        myTransactionManager.begin();
        joinPoint.proceed();

        myTransactionManager.commit();
    }

    private Method getInvokedMethod(Class targetCls, ProceedingJoinPoint pJoinPoint) {
        List<Class<? extends Object>> clazzList = new ArrayList<>();
        Object[] args = pJoinPoint.getArgs();
        for (Object arg : args) {
            clazzList.add(arg.getClass());
        }

        Class[] argsCls = (Class[]) clazzList.toArray(new Class[0]);

        String methodName = pJoinPoint.getSignature().getName();
        Method method = null;
        try {
            method = targetCls.getMethod(methodName, argsCls);
        } catch (NoSuchMethodException e) {
        }
        return method;
    }
}
