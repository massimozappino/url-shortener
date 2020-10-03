package it.zappino.urlshortener.lib;

import org.apache.commons.validator.routines.UrlValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomUrlValidator implements ConstraintValidator<ValidUrl, String> {
    @Override
    public boolean isValid(String url, ConstraintValidatorContext context) {
        String[] schemes = {"http", "https"};
        UrlValidator urlValidator = new UrlValidator(schemes);
        return urlValidator.isValid(url);
    }
}
