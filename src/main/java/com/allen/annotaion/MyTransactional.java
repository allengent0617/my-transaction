package com.allen.annotaion;

import org.springframework.transaction.annotation.Propagation;

import java.lang.annotation.*;


/**
 * @author : allengent@163.com
 * @date : 2019/8/29 20:21
 * description :
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyTransactional {

    String value() default "";

    Propagation propagation() default Propagation.REQUIRED;
}
