package com.wimdeblauwe.springissue29120;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {

    @GetMapping(value = "/", consumes = "application/merge-patch+json")
    public void test() {
        throw new RuntimeException("fake");
    }
}
