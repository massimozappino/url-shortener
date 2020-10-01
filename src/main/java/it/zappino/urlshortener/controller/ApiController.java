package it.zappino.urlshortener.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {

//    private static final Logger LOG = LoggerFactory.getLogger(AliveController.class);

    @GetMapping
    public @ResponseBody
    ResponseEntity alive() {

        return ResponseEntity.ok().body("{\"status\":\"OK\"}");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getUrl(@PathVariable String id) {

        if (url == null) {
            Error error = new Error("id", id, "No such key exists");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }

        return ResponseEntity.ok(url);
    }

}