package com.navinfo.xd.xd.es.migrate.bean;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Inherited
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.SOURCE)
public @interface Description {
	String value() default "";
}