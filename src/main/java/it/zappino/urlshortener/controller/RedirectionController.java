package it.zappino.urlshortener.controller;

import it.zappino.urlshortener.persistence.entity.ShortUrl;
import it.zappino.urlshortener.service.ShortUrlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


@Slf4j
@RestController
public class RedirectionController {
    @Autowired
    ShortUrlService shortUrlService;

    @GetMapping(path = "/{code}")
    public @ResponseBody
    void redirect(@PathVariable String code, HttpServletResponse response) throws IOException {
        log.info("Processing redirect for code: " + code);

        Optional<ShortUrl> maybeShortUrl = shortUrlService.processRedirect(code);
        if (maybeShortUrl.isPresent()) {
            ShortUrl shortUrl = maybeShortUrl.get();
            response.sendRedirect(shortUrl.getLongUrl());
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

}
