package com.atharv.ecom.ecom;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HElloCOntroller {
    @GetMapping("/")
    public String greet(HttpServletRequest request){
        return "Hey, this project is now secured!" + request.getSession().getId();
    }
    @GetMapping("/csrf")
    public CsrfToken getCsrfTOken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");

    }
}


