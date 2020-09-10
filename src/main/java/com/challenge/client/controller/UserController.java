package com.challenge.client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class UserController {

	
	@GetMapping("/noRegistry")
    public String noRegistry() {
        return "Você precisa está logado para acessar o sistema.";
    }

    @GetMapping("/login")
    public RedirectView restrictedAccess() {
    	return new RedirectView("/");
    }
    
}