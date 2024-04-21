package es.ssdd.Practica1.controllers.web;

import es.ssdd.Practica1.entities.CharacterInGame;
import es.ssdd.Practica1.entities.Game;
import es.ssdd.Practica1.entities.Trial;
import es.ssdd.Practica1.services.TrialService;
import es.ssdd.Practica1.util.ErrorMessageHandler;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/startMenu")
@Controller
public class TrialController {

    @Autowired
    private TrialService trialService;
    ErrorMessageHandler errorMessageHandler = new ErrorMessageHandler();
    HttpServletRequest request;

    @GetMapping("/trials")
    public String showAllTrials (Model model){
        model.addAttribute("ltrials", trialService.getAllTrials());
        return "trials";
    }

    @GetMapping("/trials/create")
    public String createTrialForm(Model model){
        model.addAttribute("trial", new Trial());
        model.addAttribute("gameList", trialService.getAllGames());
        return "trial-create";
    }

    @PostMapping("/trials/create")
    public String createTrial (Trial trial, Long game_id){
        Game game = trialService.getGame(game_id);
        trial.setGame(game);
        trialService.createTrial(trial);
        return "redirect:/startMenu/trials";
    }


    @GetMapping("/trials/details/{id}")
    public String showTrial (Model model, @PathVariable long id) {
        Trial trial = trialService.getTrial(id);
        if(trial == null)
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"Trial with id "+id+" not found");
        model.addAttribute("trial", trial);
        return "trial-details";
    }

    @GetMapping("/trials/delete/{id}")
    public String deleteTrial (@PathVariable long id){
        Trial deleted = trialService.deleteTrial(id);
        if (deleted == null)
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"Trial with id "+id+" not found");
        return "redirect:/startMenu/trials";
    }
    @GetMapping ("/trials/update/{id}")
    public String updateTrialForm(Model model, @PathVariable long id){
        Trial toUpdate = trialService.getTrial(id);
        if (toUpdate == null)
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"Trial with id "+id+" not found");
        model.addAttribute("trial", toUpdate);
        return "trial-update";
    }
    @PostMapping("/trials/update/{id}")
    public String updateTrial (@PathVariable long id, Trial trial){
        Trial toUpdate = trialService.putTrial(id, trial);
        if (toUpdate == null)
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"Trial with id "+id+" not found");
        return "redirect:/startMenu/trials";
    }
    @ExceptionHandler({ResponseStatusException.class})
    public ModelAndView handleException(ResponseStatusException ex){
        return errorMessageHandler.errorMessage("Error "+ex.getStatusCode().value()+": "+ex.getReason(),"/startMenu/trials");
    }



    @GetMapping("/trials/details/{id}/characters")
    public String showCharactersManager(Model model, @PathVariable long id){
        Trial trial = trialService.getTrial(id);
        if(trial == null)
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"Trial with id "+id+" not found");
        model.addAttribute("trial", trial);


        return "trial-characterManager";
    }
    @PostMapping("/trials/details/{trial_id}/characters/add")
    public String addCharacter(@PathVariable long trial_id, Long character_id){
        if(trialService.getTrial(trial_id) == null)
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"Trial with id "+trial_id+" not found");
        if(trialService.getCharacter(character_id) == null)
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"Character with id "+character_id+" not found");

        if(trialService.addCharacter(trial_id,character_id)==null)
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"An error occurred while adding character to trial");

        return "redirect:/startMenu/trials/details/"+trial_id+"/characters";
    }

    @PostMapping("/trials/details/{trial_id}/characters/remove")
    public String removeCharacter(@PathVariable long trial_id, Long character_id){
        if(trialService.getTrial(trial_id) == null)
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"Trial with id "+trial_id+" not found");
        if(trialService.getCharacter(character_id) == null)
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"Character with id "+character_id+" not found");

        if(trialService.removeCharacter(trial_id,character_id)==null)
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"An error occurred while removing character to trial");

        return "redirect:/startMenu/trials/details/"+trial_id+"/characters";
    }
}
