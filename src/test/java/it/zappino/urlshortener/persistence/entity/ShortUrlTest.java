package it.zappino.urlshortener.persistence.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShortUrlTest {

    @Test
    void toJsonWithoutId() throws JsonProcessingException {
        ShortUrl shortUrl = aShortUrl();

        assertEquals("{\"link\":\"http://localhost:8080/abcDEF\",\"hits\":0,\"long_url\":\"http://localhost\"}",
                (new ObjectMapper().writeValueAsString(shortUrl)));
    }

    @Test
    void getLink() {
        ShortUrl shortUrl = aShortUrl();
        assertEquals("http://localhost:8080/abcDEF", shortUrl.getLink());
    }

    private ShortUrl aShortUrl() {
        ShortUrl shortUrl = new ShortUrl();
        shortUrl.setId(1L);
        shortUrl.setLongUrl("http://localhost");
        shortUrl.setCode("abcDEF");
        shortUrl.setLink("http://localhost:8080/abcDEF");
        return shortUrl;
    }
}