package com.hei.project2p1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class    LoginController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // Assurez-vous que vous avez un fichier HTML nommé "login.html" dans le dossier des templates
    }

    @GetMapping("/index")
    public String showHomePage() {
        return "index"; // Assurez-vous que vous avez un fichier HTML nommé "index.html" dans le dossier des templates
    }

    // Cette méthode sera appelée après une connexion réussie
    @GetMapping("/home")
    public RedirectView redirectToHomePage() {
        return new RedirectView("/"); // Rediriger vers le chemin "/"
    }
}
