package it.zappino.urlshortener.controller;

import it.zappino.urlshortener.persistence.entity.ShortUrl;
import it.zappino.urlshortener.service.ShortUrlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    ResponseEntity<ShortUrl> createUrl(@Valid @RequestBody ShortUrl shortUrl) {
        return ResponseEntity.ok().body(shortUrlService.createShortUrl(shortUrl.getLongUrl()));
    }

    @DeleteMapping
    ResponseEntity<Void> deleteUrl(@RequestBody ShortUrl shortUrl) {
        shortUrlService.deleteShortUrl(shortUrl.getLink());
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
