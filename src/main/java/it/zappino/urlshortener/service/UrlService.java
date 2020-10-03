package it.zappino.urlshortener.service;

import it.zappino.urlshortener.lib.CodeGenerator;
import it.zappino.urlshortener.persistence.UrlRepository;
import it.zappino.urlshortener.persistence.entity.Url;
import org.springframework.beans.factory.annotation.Autowired;

public class UrlService {
    @Autowired
    private UrlRepository urlRepository;

    public Url createUrl(String urlString) {
        Url url = new Url();
        url.setUrl(urlString);
        url.setCode(CodeGenerator.create());

        return urlRepository.save(url);
    }


}
