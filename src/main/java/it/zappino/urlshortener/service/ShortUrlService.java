package it.zappino.urlshortener.service;

import it.zappino.urlshortener.lib.CodeGenerator;
import it.zappino.urlshortener.persistence.ShortUrlRepository;
import it.zappino.urlshortener.persistence.entity.ShortUrl;
import org.springframework.beans.factory.annotation.Autowired;

public class ShortUrlService {
    @Autowired
    private ShortUrlRepository shortUrlRepository;

    public ShortUrl createUrl(String urlString) {
        ShortUrl shortUrl = new ShortUrl();
        shortUrl.setLongUrl(urlString);
        shortUrl.setCode(CodeGenerator.create());

        return shortUrlRepository.save(shortUrl);
    }


}
