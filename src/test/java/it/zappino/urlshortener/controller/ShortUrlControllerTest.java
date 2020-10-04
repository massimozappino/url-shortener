package it.zappino.urlshortener.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.zappino.urlshortener.persistence.entity.ShortUrl;
import it.zappino.urlshortener.service.ShortUrlService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static it.zappino.urlshortener.controller.ControllerHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ShortUrlControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private ShortUrlService service;

    @Test
    public void createNewShortUrl() throws Exception {
        doReturn("abcDEF").when(service).generateCode();

        MvcResult mvcResult = doPost(mockMvc, "/api/url", "{\"url\":\"http://google.com\"}",
                status().is(200)).andReturn();

        ShortUrl shortUrl = new ObjectMapper().readValue(getResponseBody(mvcResult), ShortUrl.class);
        assertNull(shortUrl.getId());
        assertEquals("http://google.com", shortUrl.getLongUrl());
        assertEquals("http://localhost:8080/abcDEF", shortUrl.getLink());
    }

    @Test
    public void createNewInvalidShortUrl() throws Exception {
        MvcResult mvcResult = doPost(mockMvc, "/api/url", "{\"url\":\"http://localhost/post\"}",
                status().is(400)).andReturn();

        assertEquals("{\"url\":\"Invalid url\"}", getResponseBody(mvcResult));
    }

    @Test
    public void getAllShortUrls() throws Exception {
        doReturn("abcDEF").when(service).generateCode();

        service.createShortUrl("https://google.com");
        service.createShortUrl("https://amazon.com");

        MvcResult mvcResult = doGet(mockMvc, "/api/url", status().is(200)).andReturn();

        assertEquals("[" +
                        "{\"link\":\"http://localhost:8080/abcDEF\",\"hits\":0,\"long_url\":\"https://google.com\"}," +
                        "{\"link\":\"http://localhost:8080/abcDEF\",\"hits\":0,\"long_url\":\"https://amazon.com\"}]",
                getResponseBody(mvcResult));
    }

    @Test
    public void deleteExistingUrl() throws Exception {
        ShortUrl shortUrl = service.createShortUrl("https://google.com");
        List<ShortUrl> allUrls = service.getAllUrls();
        assertEquals(1, allUrls.size());

        MvcResult mvcResult = doDelete(mockMvc, "/api/url", "{\"link\":\"" + shortUrl.getLink() + "\"}",
                status().is(204)).andReturn();
        assertEquals("", getResponseBody(mvcResult));
        assertEquals(0, service.getAllUrls().size());
    }
}
