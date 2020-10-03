package it.zappino.urlshortener.controller;

import it.zappino.urlshortener.persistence.entity.ShortUrl;
import it.zappino.urlshortener.service.ShortUrlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

@Slf4j
@RestController
@RequestMapping("/api/url")
public class UrlController {
    @Autowired
    ShortUrlService shortUrlService;

    @PostMapping
    ResponseEntity<ShortUrl> createUrl(@Validated @RequestBody ShortUrl shortUrl) {
        return ResponseEntity.ok().body(shortUrlService.createUrl(shortUrl.getLongUrl()));
    }

}
