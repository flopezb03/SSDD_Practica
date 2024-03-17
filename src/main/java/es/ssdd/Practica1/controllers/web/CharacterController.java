package es.ssdd.Practica1.controllers.webControllers;
import es.ssdd.Practica1.entities.CharacterInGame;
import es.ssdd.Practica1.services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/startMenu/characters")
public class CharacterController {
    @Autowired
    CharacterService charService;

    //Read operations
    @GetMapping
    public String showAllCharacters(Model model){
        model.addAttribute("characters",charService.getAllCharacter());
        return "showAllCharacters";
    }

    @GetMapping("/{id}")
    public String showCharacter(@PathVariable Long id,Model model){
        CharacterInGame charToShow = charService.getCharacter(id);
        if(charToShow == null)
            return "redirect: /startMenu/characters";
        else {
            model.addAttribute("character", charToShow);
            return "showCharacter";
        }
    }

    //Create operation
    @GetMapping("/addCharacter")
    public String showAddCharForm(Model model){
        model.addAttribute("character",new CharacterInGame());
        return "addCharForm";
    }
    @PostMapping("/addCharacter")
    public String addCharacter(CharacterInGame character){
        charService.createCharacter(character);
        return "redirect:/startMenu/characters";
    }

    //Delete operation
    @GetMapping("/{id}/delete")
    public String deleteCharacter(@PathVariable Long id){
        charService.deleteCharacter(id);
        return "redirect:/startMenu/characters";
    }

    //Update operations
    @GetMapping("/{id}/put")
    public String showPutCharacterForm(@PathVariable Long id,Model model){
        CharacterInGame charToEdit = charService.getCharacter(id);
        if (charToEdit == null)
            return "redirect:/startMenu/characters";
        else {
            model.addAttribute("character",charToEdit);
            return "putCharForm";
        }
    }

    @PostMapping("/{id}/put")
    public String putCharacter(@PathVariable Long id,CharacterInGame character){
        character.setIdChar(id);
        charService.putCharacter(id,character);
        return "redirect:/startMenu/characters";
    }


}
