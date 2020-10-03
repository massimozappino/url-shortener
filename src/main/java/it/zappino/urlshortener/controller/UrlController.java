package it.zappino.urlshortener.controller;

import it.zappino.urlshortener.persistence.entity.Url;
import it.zappino.urlshortener.service.UrlService;
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
    UrlService urlService;

    @PostMapping
    ResponseEntity<Url> createUrl(@Validated @RequestBody Url url) {
        return ResponseEntity.ok().body(urlService.createUrl(url.getUrl()));
    }

}
