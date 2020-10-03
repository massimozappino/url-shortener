package it.zappino.urlshortener;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
    @Bean
    public UrlService urlService() {
        return new UrlService();
    }
}
