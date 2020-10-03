package it.zappino.urlshortener.controller;

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

import static it.zappino.urlshortener.controller.ControllerHelper.doGet;
import static it.zappino.urlshortener.controller.ControllerHelper.getResponseBody;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class RedirectionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private ShortUrlService service;

    @Test
    public void processNotFoundLink() throws Exception {
        MvcResult mvcResult = doGet(mockMvc, "/non-existing",
                status().is(404)).andReturn();
        assertEquals("", getResponseBody(mvcResult));
    }

    @Test
    public void processRedirectLinkToUrl() throws Exception {
        ShortUrl shortUrl = service.createShortUrl("http://google.com");
        doGet(mockMvc, "/" + shortUrl.getCode(),
                status().is(302))
                .andExpect(redirectedUrl("http://google.com"));
    }
}