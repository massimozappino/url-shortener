package it.zappino.urlshortener.service;

import it.zappino.urlshortener.persistence.ShortUrlRepository;
import it.zappino.urlshortener.persistence.entity.ShortUrl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.*;

@SpringBootTest
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
class ShortShortUrlServiceTest {
    @Autowired
    private ShortUrlService shortUrlService;

    @Autowired
    private ShortUrlRepository shortUrlRepository;

    @Test
    void createUrl() {
        ShortUrl shortUrl = shortUrlService.createShortUrl("http://localhost");

        Optional<ShortUrl> maybeUrl = shortUrlRepository.findByCode(shortUrl.getCode());
        assertTrue(maybeUrl.isPresent());
        ShortUrl createdShortUrl = maybeUrl.get();

        assertEquals(6, createdShortUrl.getCode().length());
    }

    @Test
    void getAllUrl() {
        ShortUrl localhostUrl = shortUrlService.createShortUrl("http://localhost");
        ShortUrl googleUrl = shortUrlService.createShortUrl("https://google");
        ShortUrl amazonUrl = shortUrlService.createShortUrl("https://amazon");

        List<ShortUrl> allUrls = shortUrlService.getAllUrls();

        assertEquals(3, allUrls.size());
        assertEquals(localhostUrl, allUrls.get(0));
        assertEquals(googleUrl, allUrls.get(1));
        assertEquals(amazonUrl, allUrls.get(2));
    }

    @Test
    void getUrlByCode() {
        assertFalse(shortUrlService.getUrlByCode("void").isPresent());

        ShortUrl localhostUrl = shortUrlService.createShortUrl("http://localhost");

        Optional<ShortUrl> maybeShortUrl = shortUrlService.getUrlByCode(localhostUrl.getCode());
        if (maybeShortUrl.isPresent()) {
            assertEquals(localhostUrl, maybeShortUrl.get());
        } else {
            fail();
        }
    }

    @Test
    void deleteUrl() {
        ShortUrl shortUrl = shortUrlService.createShortUrl("http://localhost");

        assertEquals(1, shortUrlService.getAllUrls().size());

        shortUrlService.deleteShortUrl(shortUrl.getLink());
        assertEquals(0, shortUrlService.getAllUrls().size());
    }

    @Test
    void deleteDoesNotFailIfUrlDoesNotExists() {
        shortUrlService.deleteShortUrl("non existing link");
        assertEquals(0, shortUrlService.getAllUrls().size());
    }
}