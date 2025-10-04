package com.example.productservice01.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/say")
public class SampleController {

  @GetMapping("/hello/{name}/{times}")
  public String sayHello(@PathVariable("name") String username, @PathVariable("times") int time) {
    return "Hello " + username + "! It is " + time + "'O Clock!";
  }

  @GetMapping("/bye")
  public String sayBye() {
    return "Bye World!";
  }
}
