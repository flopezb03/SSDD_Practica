package es.ssdd.Practica1.controllers.web;

import es.ssdd.Practica1.entities.Game;
import es.ssdd.Practica1.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GameController {

    @Autowired
    GameService gameService;

    @GetMapping("/games")
    public String getAllGame(Model model){
        model.addAttribute("games", gameService.getAllGame());
        return "games";
    }
    @GetMapping("/games/create")
    public String createGameForm(){
        return "game-create";
    }
    @PostMapping("/games/create")
    public String createGame(Game game){
        gameService.createGame(game);
        return "redirect:/games";
    }
    @GetMapping("/games/details/{id}")
    public String getGame(Model model, @PathVariable long id){
        Game game = gameService.readGame(id);
        if(game == null)
            //return "redirect:/error";
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"Videojuego con id "+id+" no encontrado");
        model.addAttribute("game",game);
        return "game-details";
    }
    @GetMapping("/games/delete/{id}")
    public String deleteGame(@PathVariable long id){
        Game game = gameService.deleteGame(id);
        if(game == null)
            //return "redirect:/error";
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"Videojuego con id "+id+" no encontrado");
        return "redirect:/games";
    }
    @GetMapping("/games/update/{id}")
    public String updateGameForm(Model model, @PathVariable long id){
        Game game = gameService.readGame(id);
        if(game == null)
            return "redirect:/error";
        model.addAttribute("game",game);
        return "game-update";
    }
    @PostMapping("/games/update/{id}")
    public String updateGame(@PathVariable long id, Game updatedGame){
        Game game = gameService.updateGame(id,updatedGame);
        if(game == null)
            return "redirect:/error";
        return "redirect:/games/details/"+id;
    }

    @ExceptionHandler({ResponseStatusException.class})
    public ModelAndView handleException(ResponseStatusException ex){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("message-error");
        modelAndView.addObject("message",ex.getReason());
        modelAndView.addObject("redirect","/games");
        return modelAndView;
    }
}
