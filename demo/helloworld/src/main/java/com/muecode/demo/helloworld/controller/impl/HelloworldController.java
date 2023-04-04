package com.muecode.demo.helloworld.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.muecode.demo.helloworld.controller.HelloworldOperations;
import com.muecode.demo.helloworld.service.HelloworldService;

@RestController
@RequestMapping(value = "/demo/api/v1")
public class HelloworldController implements HelloworldOperations {

  @Autowired
  HelloworldService helloworldService;

  @Override
  public ResponseEntity<String> greeting() {
    String greetingMessage = helloworldService.greetingService();
    return ResponseEntity.ok().body(greetingMessage);
  }

}
