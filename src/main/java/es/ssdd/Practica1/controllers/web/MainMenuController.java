package es.ssdd.Practica1.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainMenuController {
    @GetMapping("/startMenu")
    public String getMainMenu(){
        return "mainMenu";
    }

}
