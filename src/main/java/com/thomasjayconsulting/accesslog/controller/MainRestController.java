package com.thomasjayconsulting.accesslog.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MainRestController {

    @GetMapping(value="test1", produces = "application/json")
    public String getTest1() {
        return "{}";
    }

    @GetMapping("test2")
    public ResponseEntity<?> getTest2() {
        return ResponseEntity.notFound().header("Content-Type", "application/json").build();
    }


}
