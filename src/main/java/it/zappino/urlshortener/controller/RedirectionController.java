package it.zappino.urlshortener.controller;

import it.zappino.urlshortener.persistence.entity.Url;
import it.zappino.urlshortener.service.UrlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.NoHandlerFoundException;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;

@Slf4j
@RestController
public class RedirectionController {
    @Autowired
    UrlService urlService;

//
//    @GetMapping(path = "/{code}")
//    public @ResponseBody
//    void redirect(@PathVariable String code, HttpServletResponse response) throws IOException {
//        log.info(code);
//        response.sendRedirect("http://repubblica.it");
//    }

}
