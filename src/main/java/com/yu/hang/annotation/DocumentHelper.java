package com.yu.hang.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DocumentHelper {

	/**
	 * 
	 * @Description 说明
	 * @return
	 */
	String documentation() default "";

	/**
	 * 
	 * @Description 例子
	 */
	String example() default "";
}
