package com.sreejith.providerservice;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MyController {

    @GetMapping("/welcome/{input}")
    public ResponseEntity<?> giveWelcome(@PathVariable("input") String input)
    {
        return ResponseEntity.status(HttpStatus.OK).body("Welcome ==> "+input);
    }
}
