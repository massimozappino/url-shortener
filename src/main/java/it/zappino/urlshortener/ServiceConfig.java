package it.zappino.urlshortener;

import it.zappino.urlshortener.service.ShortUrlService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
    @Bean
    public ShortUrlService urlService() {
        return new ShortUrlService();
    }
}
