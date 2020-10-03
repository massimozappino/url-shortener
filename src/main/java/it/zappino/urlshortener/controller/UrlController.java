package it.zappino.urlshortener.controller;

import it.zappino.urlshortener.persistence.entity.ShortUrl;
import it.zappino.urlshortener.service.ShortUrlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/url")
public class UrlController {
    @Autowired
    ShortUrlService shortUrlService;

    @GetMapping
    ResponseEntity<List<ShortUrl>> getAllUrls() {
        return ResponseEntity.ok().body(shortUrlService.getAllUrls());
    }

    @PostMapping
    ResponseEntity<ShortUrl> createUrl(@RequestBody ShortUrl shortUrl) {
        return ResponseEntity.ok().body(shortUrlService.createShortUrl(shortUrl.getLongUrl()));
    }

    @DeleteMapping
    ResponseEntity<Void> deleteUrl(@RequestBody ShortUrl shortUrl) {
        shortUrlService.deleteShortUrl(shortUrl.getLink());
        return ResponseEntity.noContent().build();
    }

}
