package com.wxjfkg.payment.validate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.sf.oval.configuration.annotation.Constraint;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Constraint(checkWith = ValidMethodCheck.class)
public @interface ValidMethod {

	String message() default "";
	
	String methodName();
	
	String[] profiles() default {};
	
	Class<?> parameterType();
}
