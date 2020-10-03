package it.zappino.urlshortener.lib;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CodeGeneratorTest {

    @Test
    public void generateAStringWithRightLength() {
        assertEquals(6, CodeGenerator.create().length());
    }

    @Test
    public void actualCodeIsDifferentFromPrevious() {
        String firstCode = CodeGenerator.create();
        String secondCode = CodeGenerator.create();
        assertNotEquals(firstCode, secondCode);
    }

}