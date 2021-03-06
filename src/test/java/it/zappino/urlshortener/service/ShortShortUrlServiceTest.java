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
        ShortUrl shortUrl = shortUrlService.createShortUrl("http://google.com");

        Optional<ShortUrl> maybeUrl = shortUrlRepository.findByCode(shortUrl.getCode());
        assertTrue(maybeUrl.isPresent());
        ShortUrl createdShortUrl = maybeUrl.get();

        assertEquals(6, createdShortUrl.getCode().length());
    }

    @Test
    void getAllUrl() {
        ShortUrl wikipediatUrl = shortUrlService.createShortUrl("http://wikipedia.com");
        ShortUrl googleUrl = shortUrlService.createShortUrl("https://google.com");
        ShortUrl amazonUrl = shortUrlService.createShortUrl("https://amazon.com");

        List<ShortUrl> allUrls = shortUrlService.getAllUrls();

        assertEquals(3, allUrls.size());
        assertEquals(wikipediatUrl, allUrls.get(0));
        assertEquals(googleUrl, allUrls.get(1));
        assertEquals(amazonUrl, allUrls.get(2));
    }

    @Test
    void getUrlByCode() {
        assertFalse(shortUrlService.getUrlByCode("void").isPresent());

        ShortUrl localhostUrl = shortUrlService.createShortUrl("http://google.com");

        Optional<ShortUrl> maybeShortUrl = shortUrlService.getUrlByCode(localhostUrl.getCode());
        if (maybeShortUrl.isPresent()) {
            assertEquals(localhostUrl, maybeShortUrl.get());
        } else {
            fail();
        }
    }

    @Test
    void deleteUrl() {
        ShortUrl shortUrl = shortUrlService.createShortUrl("http://google.com");

        assertEquals(1, shortUrlService.getAllUrls().size());

        shortUrlService.deleteShortUrl(shortUrl.getLink());
        assertEquals(0, shortUrlService.getAllUrls().size());
    }

    @Test
    void processRedirect() {
        ShortUrl shortUrl = shortUrlService.createShortUrl("http://google.com");
        assertEquals(0, shortUrl.getHits());
        shortUrlService.processRedirect(shortUrl.getCode());
        Optional<ShortUrl> maybeShortUrl = shortUrlService.processRedirect(shortUrl.getCode());

        assertTrue(maybeShortUrl.isPresent());
        assertEquals(2, maybeShortUrl.get().getHits());
    }

    @Test
    void processRedirectForNotExistUrl() {
        Optional<ShortUrl> maybeShortUrl = shortUrlService.processRedirect("void");
        assertFalse(maybeShortUrl.isPresent());
    }

    @Test
    void deleteDoesNotFailIfUrlDoesNotExists() {
        shortUrlService.deleteShortUrl("non existing link");
        assertEquals(0, shortUrlService.getAllUrls().size());
    }
}