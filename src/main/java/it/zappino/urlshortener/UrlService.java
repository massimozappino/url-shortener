package it.zappino.urlshortener;

import it.zappino.urlshortener.persistence.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UrlService {
    @Autowired
    private UrlRepository urlRepository;
}
