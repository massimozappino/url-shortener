package it.zappino.urlshortener.lib;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CustomUrlValidator.class)
public @interface ValidUrl {
    String message() default "Invalid url";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
