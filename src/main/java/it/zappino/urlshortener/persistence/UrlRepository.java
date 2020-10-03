package it.zappino.urlshortener.persistence;

import it.zappino.urlshortener.persistence.entity.Url;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UrlRepository extends CrudRepository<Url, Long> {

    Optional<Url> findByCode(String code);
}
