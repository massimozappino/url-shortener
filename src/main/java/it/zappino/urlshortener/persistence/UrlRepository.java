package it.zappino.urlshortener.persistence;

import it.zappino.urlshortener.persistence.entity.Url;
import org.springframework.data.repository.CrudRepository;

public interface UrlRepository extends CrudRepository<Url, Long> {
}
