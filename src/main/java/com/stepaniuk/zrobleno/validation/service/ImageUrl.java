package com.stepaniuk.zrobleno.validation.service;

import jakarta.validation.Constraint;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

@Target({ElementType.TYPE_USE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {})
@Length(min = 1, max = 2048)
@URL
public @interface ImageUrl {

}
