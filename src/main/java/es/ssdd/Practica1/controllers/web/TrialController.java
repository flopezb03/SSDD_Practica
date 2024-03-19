package es.ssdd.Practica1.controllers.web;

import es.ssdd.Practica1.entities.Trial;
import es.ssdd.Practica1.services.TrialService;
import es.ssdd.Practica1.util.ErrorMessageHandler;
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

    @GetMapping("/trials")
    public String showAllTrials (Model model){
        model.addAttribute("ltrials", trialService.getAllTrials());
        return "trials";
    }

    @GetMapping("/trials/create")
    public String createTrialForm(Model model){
        model.addAttribute("trial", new Trial());
        return "trial-create";
    }

    @PostMapping("/trials/create")
    public String createTrial (Model model, Trial trial){
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
        Trial toUpdate = trialService.updateTrial(id, trial);
        if (toUpdate == null)
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"Trial with id "+id+" not found");
        return "redirect:/startMenu/trials";
    }
    @ExceptionHandler({ResponseStatusException.class})
    public ModelAndView handleException(ResponseStatusException ex){
        return errorMessageHandler.errorMessage(ex.getReason(),"/startMenu/trials");
    }

}
