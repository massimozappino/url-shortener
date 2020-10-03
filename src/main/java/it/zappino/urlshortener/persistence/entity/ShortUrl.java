package it.zappino.urlshortener.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Entity
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShortUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    private String code;

    @JsonProperty("long_url")
    private String longUrl;


    @JsonProperty("short_url")
    private String shortUrl;
}
