package it.zappino.urlshortener.service;

import it.zappino.urlshortener.persistence.UrlRepository;
import it.zappino.urlshortener.persistence.entity.Url;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class UrlServiceTest {
    @Autowired
    private UrlService urlService;

    @Autowired
    private UrlRepository urlRepository;

    @Test
    void createUrl() {
        Url url = urlService.createUrl("http://localhost");

        Optional<Url> maybeUrl = urlRepository.findByCode(url.getCode());
        assertTrue(maybeUrl.isPresent());
        Url createdUrl = maybeUrl.get();

        assertEquals(6, createdUrl.getCode().length());
    }

}