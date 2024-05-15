package net.cambium.examples.rest;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TimeController.
 * 
 *  Simple example REST service that we plan on securing with a custom security mechanism.  
 *
 * @author Baruch Speiser, Cambium.
 */
@RestController
@RequestMapping("/api/time")
public class TimeController {
  
  @GetMapping
  public String now() {
    Date now = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");
    String result = formatter.format(now);
    //No need to get fancy with JSON Serializers, just use a simple string interpolation, the payload is small enough
    return String.format("{ \"time\" : \"%s\" }", result);
  }
 
}
