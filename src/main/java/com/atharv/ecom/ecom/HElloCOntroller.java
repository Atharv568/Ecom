package com.atharv.ecom.ecom;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HElloCOntroller {
    @GetMapping("/")
    public String greet(){
        return "Hey, this project is now secured!";
    }
}
