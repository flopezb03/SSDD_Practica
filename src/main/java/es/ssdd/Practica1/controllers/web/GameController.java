package es.ssdd.Practica1.controllers.web;

import es.ssdd.Practica1.entities.Game;
import es.ssdd.Practica1.util.ErrorMessageHandler;
import es.ssdd.Practica1.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/startMenu")
public class GameController {

    @Autowired
    GameService gameService;
    ErrorMessageHandler errorMessageHandler = new ErrorMessageHandler();

    @GetMapping("/games")
    public String showAllGame(Model model){
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
        return "redirect:/startMenu/games";
    }
    @GetMapping("/games/details/{id}")
    public String showGame(Model model, @PathVariable long id){
        Game game = gameService.getGame(id);
        if(game == null)
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"Videogame with id "+id+" not found");
        model.addAttribute("game",game);
        return "game-details";
    }
    @GetMapping("/games/delete/{id}")
    public String deleteGame(@PathVariable long id){
        Game game = gameService.deleteGame(id);
        if(game == null)
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"Videogame with id "+id+" not found");
        return "redirect:/startMenu/games";
    }
    @GetMapping("/games/update/{id}")
    public String updateGameForm(Model model, @PathVariable long id){
        Game game = gameService.getGame(id);
        if(game == null)
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"Videogame with id "+id+" not found");
        model.addAttribute("game",game);
        return "game-update";
    }
    @PostMapping("/games/update/{id}")
    public String updateGame(@PathVariable long id, Game updatedGame){
        Game game = gameService.putGame(id,updatedGame);
        if(game == null)
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"Videogame with id "+id+" not found");
        return "redirect:/startMenu/games/details/"+id;
    }

    @ExceptionHandler({ResponseStatusException.class})
    public ModelAndView handleException(ResponseStatusException ex){
        return errorMessageHandler.errorMessage(ex.getReason(),"/startMenu/games");
    }
}
