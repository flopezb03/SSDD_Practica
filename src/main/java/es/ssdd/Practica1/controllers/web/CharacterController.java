package es.ssdd.Practica1.controllers.web;
import es.ssdd.Practica1.entities.CharacterInGame;
import es.ssdd.Practica1.services.CharacterService;
import es.ssdd.Practica1.util.ErrorMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/startMenu")
public class CharacterController {
    @Autowired
    CharacterService charService;
    ErrorMessageHandler errorMessageHandler = new ErrorMessageHandler();

    //Read operations
    @GetMapping("/characters")
    public String showAllCharacters(Model model){
        model.addAttribute("characters",charService.getAllCharacter());
        return "characters";
    }

    @GetMapping("/characters/details/{id}")
    public String showCharacter(@PathVariable Long id,Model model){
        CharacterInGame charToShow = charService.getCharacter(id);
        if(charToShow == null)
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"Character with id "+id+" not found");
        else {
            model.addAttribute("character", charToShow);
            return "character-details";
        }
    }

    //Create operation
    @GetMapping("/characters/create")
    public String createCharacterForm(Model model){
        model.addAttribute("character",new CharacterInGame());
        return "character-create";
    }
    @PostMapping("/characters/create")
    public String addCharacter(CharacterInGame character){
        charService.createCharacter(character);
        return "redirect:/startMenu/characters";
    }

    //Delete operation
    @GetMapping("/characters/delete/{id}")
    public String deleteCharacter(@PathVariable Long id){
        CharacterInGame charToDelete = charService.deleteCharacter(id);
        if(charToDelete == null)
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"Character with id "+id+" not found");
        return "redirect:/startMenu/characters";
    }

    //Update operations
    @GetMapping("/characters/update/{id}")
    public String updateCharacterForm(@PathVariable Long id, Model model){
        CharacterInGame charToEdit = charService.getCharacter(id);
        if (charToEdit == null)
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"Character with id "+id+" not found");
        else {
            model.addAttribute("character",charToEdit);
            return "character-update";
        }
    }

    @PostMapping("/characters/update/{id}")
    public String updateCharacter(@PathVariable Long id, CharacterInGame character){
        character.setIdChar(id);
        charService.putCharacter(id,character);
        return "redirect:/startMenu/characters";
    }
    @ExceptionHandler({ResponseStatusException.class})
    public ModelAndView handleException(ResponseStatusException ex){
        return errorMessageHandler.errorMessage("Error "+ex.getStatusCode().value()+": "+ex.getReason(),"/startMenu/characters");
    }

    @GetMapping("/characters/details/{id}/trials")
    public String showTrialsManager(Model model, @PathVariable Long id){
        CharacterInGame character = charService.getCharacter(id);
        if(character == null)
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"Character with id "+id+" not found");
        model.addAttribute("character", character);
        return "character-trialManager";
    }

    @PostMapping("characters/details/{char_id}/trials/add")
    public String addTrial(@PathVariable Long char_id, Long trial_id){
        if (charService.getCharacter(char_id) == null)
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"Character with id "+ char_id +" not found");
        if (charService.getTrial(trial_id) == null)
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"Trial with id "+trial_id+" not found");
        if (charService.addTrial(char_id,trial_id) == null)
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"An error occurred while adding trial to character");
        return "redirect:/startMenu/characters/details/"+char_id+"/trials";
    }

    @PostMapping("characters/details/{char_id}/trials/remove")
    public String removeTrial(@PathVariable Long char_id, Long trial_id){
        CharacterInGame character = charService.getCharacter(char_id);
        if (character == null)
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"Character with id "+ char_id +" not found");
        if (charService.getTrial(trial_id) == null)
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"Trial with id "+trial_id+" not found");
        if (charService.removeTrial(char_id, trial_id) == null)
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"An error occurred while removing trial from character");

        return "redirect:/startMenu/characters/details/"+char_id+"/trials";
    }
}
