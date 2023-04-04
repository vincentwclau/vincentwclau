package com.muecode.demo.helloworld.service.impl;

import org.springframework.stereotype.Service;
import com.muecode.demo.helloworld.service.HelloworldService;

@Service
public class HelloworldServiceImpl implements HelloworldService {

  @Override
  public String greetingService() {
    return "helloworld!";
  }

}
