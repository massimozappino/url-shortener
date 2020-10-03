package it.zappino.urlshortener.service;

import com.google.common.collect.ImmutableList;
import it.zappino.urlshortener.lib.CodeGenerator;
import it.zappino.urlshortener.persistence.ShortUrlRepository;
import it.zappino.urlshortener.persistence.entity.ShortUrl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Slf4j
public class ShortUrlService {
    @Autowired
    private ShortUrlRepository shortUrlRepository;
    public static final String domain = "http://localhost:8080";

    public ShortUrl createShortUrl(String urlString) {
        ShortUrl shortUrl = new ShortUrl();
        shortUrl.setLongUrl(urlString);
        String code = generateCode();
        shortUrl.setCode(code);
        shortUrl.setLink(domain + "/" + code);

        return shortUrlRepository.save(shortUrl);
    }

    public List<ShortUrl> getAllUrls() {
        Iterable<ShortUrl> iterator = shortUrlRepository.findAll();
        return ImmutableList.copyOf(iterator);
    }

    public Optional<ShortUrl> getUrlByCode(String code) {
        return shortUrlRepository.findByCode(code);
    }

    public void deleteShortUrl(String link) {
        log.info("Delete link: " + link);
        Optional<ShortUrl> maybeShortUrl = shortUrlRepository.findByLink(link);
        maybeShortUrl.ifPresent(shortUrl -> shortUrlRepository.deleteById(shortUrl.getId()));
    }

    public Optional<ShortUrl> processRedirect(String code) {
        Optional<ShortUrl> maybeShortUrl = shortUrlRepository.findByCode(code);
        if (maybeShortUrl.isPresent()) {
            ShortUrl shortUrl = maybeShortUrl.get();
            shortUrl.setHits(shortUrl.getHits() + 1);
            return Optional.of(shortUrlRepository.save(shortUrl));
        }
        return maybeShortUrl;
    }

    public String generateCode() {
        return CodeGenerator.create();
    }
}
