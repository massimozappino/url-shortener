package it.zappino.urlshortener.persistence.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UrlTest {

    @Test
    void toJsonWithoutId() throws JsonProcessingException {
        Url url = new Url();
        url.setId(1L);
        url.setUrl("http://localhost");
        url.setCode("abcDEF");

        assertEquals("{\"url\":\"http://localhost\",\"code\":\"abcDEF\"}",
                (new ObjectMapper().writeValueAsString(url)));
    }
}