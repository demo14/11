package com.example.kafka.controller;

import com.example.kafka.service.AccountFilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountFilterService service;

    @GetMapping("all")
    public ResponseEntity<Object> getAll() throws ExecutionException, InterruptedException {
        return new ResponseEntity<>(service.produce(), HttpStatus.OK);
    }
}
