package it.zappino.urlshortener.lib;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UrlValidatorTest {
    @Test
    void validUrl() {
        CustomUrlValidator customUrlValidator = new CustomUrlValidator();
        assertTrue(customUrlValidator.isValid("https://google.com", null));
        assertTrue(customUrlValidator.isValid("https://mysite.com/page?param1=value1&param2=value2", null));

        assertFalse(customUrlValidator.isValid("http://localhost", null));
        assertFalse(customUrlValidator.isValid("localhost", null));
        assertFalse(customUrlValidator.isValid("ftp://localhost", null));
    }

}