package com.muecode.demo.helloworld.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/default")
public interface HelloworldOperations {
  
  @GetMapping(value = "/greeting")
  public ResponseEntity<String> greeting();

}
