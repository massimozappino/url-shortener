package it.zappino.urlshortener.controller;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class ControllerHelper {

    public static ResultActions doPost(MockMvc mockMvc, String resource, String body, ResultMatcher expectation) throws Exception {
        return mockMvc.perform(post(resource)
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andDo(print())
                .andExpect(expectation);
    }
    public static ResultActions doGet(MockMvc mockMvc, String resource, ResultMatcher expectation) throws Exception {
        return mockMvc.perform(get(resource)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(expectation);
    }

    public static ResultActions doDelete(MockMvc mockMvc, String resource, String body, ResultMatcher expectation) throws Exception {
        return mockMvc.perform(delete(resource)
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andDo(print())
                .andExpect(expectation);
    }

    public static String getResponseBody(MvcResult mvcResult) throws UnsupportedEncodingException {
        return mvcResult.getResponse().getContentAsString();
    }

}
