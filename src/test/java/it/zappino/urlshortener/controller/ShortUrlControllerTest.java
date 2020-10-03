package it.zappino.urlshortener.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.zappino.urlshortener.persistence.entity.ShortUrl;
import it.zappino.urlshortener.service.ShortUrlService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
public class ShortUrlControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShortUrlService service;

    @Test
    public void createNewShortUrl() throws Exception {
        ShortUrl expectedShortUrl = aUrl();
        when(service.createUrl(any())).thenReturn(expectedShortUrl);

        ObjectMapper objectMapper  = new ObjectMapper();
        String jsonContent = objectMapper.writeValueAsString(expectedShortUrl);
        System.out.println(jsonContent);
        doPost(mockMvc, "/api/url", "{\"url\":\"http://localhost/post\"}",
                status().is(200))
                .andExpect(content().json(jsonContent));
    }


    private ShortUrl aUrl() {
        ShortUrl shortUrl = new ShortUrl();
        shortUrl.setLongUrl("http://localhost/post");
        shortUrl.setCode("abcDEF");
        return shortUrl;
    }
}
