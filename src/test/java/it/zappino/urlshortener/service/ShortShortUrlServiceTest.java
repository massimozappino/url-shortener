package it.zappino.urlshortener.service;

import it.zappino.urlshortener.persistence.ShortUrlRepository;
import it.zappino.urlshortener.persistence.entity.ShortUrl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ShortShortUrlServiceTest {
    @Autowired
    private ShortUrlService shortUrlService;

    @Autowired
    private ShortUrlRepository shortUrlRepository;

    @Test
    void createUrl() {
        ShortUrl shortUrl = shortUrlService.createUrl("http://localhost");

        Optional<ShortUrl> maybeUrl = shortUrlRepository.findByCode(shortUrl.getCode());
        assertTrue(maybeUrl.isPresent());
        ShortUrl createdShortUrl = maybeUrl.get();

        assertEquals(6, createdShortUrl.getCode().length());
    }

}