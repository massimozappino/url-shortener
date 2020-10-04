package it.zappino.urlshortener.controller.request;

import it.zappino.urlshortener.lib.ValidUrl;
import lombok.Data;

@Data
public class FullUrl {
    @ValidUrl
    private String url;
}
