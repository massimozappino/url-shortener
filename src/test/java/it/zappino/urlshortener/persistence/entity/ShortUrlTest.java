package it.zappino.urlshortener.persistence.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShortUrlTest {

    @Test
    void toJsonWithoutId() throws JsonProcessingException {
        ShortUrl shortUrl = new ShortUrl();
        shortUrl.setId(1L);
        shortUrl.setLongUrl("http://localhost");
        shortUrl.setCode("abcDEF");

        assertEquals("{\"code\":\"abcDEF\",\"long_url\":\"http://localhost\"}",
                (new ObjectMapper().writeValueAsString(shortUrl)));
    }
}