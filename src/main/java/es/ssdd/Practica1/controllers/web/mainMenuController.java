package es.ssdd.Practica1.controllers.webControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class mainMenuController {
    @GetMapping
    public String getMainMenu(){
        return "mainMenu";
    }

}
