package it.zappino.urlshortener.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.zappino.urlshortener.persistence.UrlRepository;
import it.zappino.urlshortener.persistence.entity.Url;
import it.zappino.urlshortener.service.UrlService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static it.zappino.urlshortener.controller.ControllerHelper.doPost;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UrlController.class)
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//@AutoConfigureMockMvc
public class UrlControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UrlService service;

    @Test
    public void createNewUrl() throws Exception {
        Url expectedUrl = aUrl();
        when(service.createUrl(any())).thenReturn(expectedUrl);

        ObjectMapper objectMapper  = new ObjectMapper();
        String jsonContent = objectMapper.writeValueAsString(expectedUrl);
        System.out.println(jsonContent);
        doPost(mockMvc, "/api/url", "{\"url\":\"http://localhost/post\"}",
                status().is(200))
                .andExpect(content().json(jsonContent));
    }

    private Url aUrl() {
        Url url= new Url();
        url.setUrl("http://localhost/post");
        url.setCode("abcDEF");
        return url;
    }
}
