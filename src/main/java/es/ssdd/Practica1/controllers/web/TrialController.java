package es.ssdd.Practica1.controllers.web;

import es.ssdd.Practica1.entities.Trial;
import es.ssdd.Practica1.services.TrialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/startMenu")
@Controller
public class TrialController {

    @Autowired
    private TrialService trialService;

    @GetMapping("/trials")
    public String showAllTrials (Model model){
        model.addAttribute("ltrials", trialService.getAllTrials());
        return "trials";
    }

    @GetMapping("/trials/create")
    public String addTrialForm(Model model){
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
        model.addAttribute("trial", trial);
        return "trial-details";
    }

    @GetMapping("/trials/delete/{id}")
    public String deleteTrial (@PathVariable long id){
        Trial deleted = trialService.deleteTrial(id);
        if (deleted == null)
            return "redirect:/error";
        return "redirect:/startMenu/trials";
    }
    @GetMapping ("/trials/update/{id}")
    public String showUpdateForm(Model model, @PathVariable long id){
        Trial toUpdate = trialService.getTrial(id);
        if (toUpdate == null)
            return "redirect:/error";
        model.addAttribute("trial", toUpdate);
        return "trial-update";
    }
    @PostMapping("/trials/update/{id}")
    public String updateTrial (@PathVariable long id, Trial trial){
        Trial toUpdate = trialService.updateTrial(id, trial);
        if (toUpdate == null)
            return "redirect:/error";
        return "redirect:/startMenu/trials";
    }


}
