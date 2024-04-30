package com.danilo.springbootarchitetureddd.infrastructure.utility;

import org.springframework.core.annotation.AliasFor;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Transactional
public @interface InterfaceUtility {

    @AliasFor(annotation = Transactional.class,attribute = "isolation")
    Isolation isolation() default Isolation.READ_COMMITTED;

    @AliasFor(annotation = Transactional.class,attribute = "rollbackFor")
    Class<Exception> rollbackFor() default Exception.class;

}
