package it.zappino.urlshortener.persistence;

import it.zappino.urlshortener.persistence.entity.ShortUrl;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ShortUrlRepository extends CrudRepository<ShortUrl, Long> {

    Optional<ShortUrl> findByCode(String code);
}
