package com.hei.project2p1.controller;

import com.hei.project2p1.modele.Utilisateur;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class AuthentificationController {

    private Utilisateur utilisateur = new Utilisateur("defaultUser", "defaultPassword");


    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,@RequestParam("password") String password, Model model) {
        if (utilisateur.getUsername().equals(username) && utilisateur.getPassword().equals(password)) {
            return "redirect:/";
        } else {
            model.addAttribute("error", "Identifiant ou mot de passe incorrect.");
            return "redirect:/";
        }
    }

    @GetMapping("/index")
    public String showIndexPage() {
        return "index";
    }

    @GetMapping("/logout")
    public RedirectView logout() {
        return new RedirectView("/login");
    }
}
