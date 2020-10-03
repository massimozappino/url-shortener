package it.zappino.urlshortener.lib;

import org.apache.commons.lang3.RandomStringUtils;

public class CodeGenerator {

    public static final int length = 6;

    public static String create() {
        return RandomStringUtils.randomAlphanumeric(length);
    }
}
