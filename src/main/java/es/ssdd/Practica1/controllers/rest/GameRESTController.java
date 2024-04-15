package es.ssdd.Practica1.controllers.rest;

import es.ssdd.Practica1.entities.Game;
import es.ssdd.Practica1.services.GameService;
import es.ssdd.Practica1.entities.Trial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequestMapping("/api/")
@RestController
public class GameRESTController {

    @Autowired
    private GameService gameService;

    @PostMapping("games")
    public ResponseEntity<Game> createGame(@RequestBody Game game){
        return ResponseEntity.status(201).body(gameService.createGame(game));
    }
    @GetMapping("games/{id}")
    public ResponseEntity<Game> readGame(@PathVariable long id){
        Game game = gameService.getGame(id);
        if(game == null)
            return ResponseEntity.status(404).build();
        return ResponseEntity.status(200).body(game);
    }
    @PutMapping("games/{id}")
    public ResponseEntity<Game> putGame(@PathVariable long id, @RequestBody Game updatedGame){
        Game game = gameService.putGame(id,updatedGame);
        if(game == null)
            return ResponseEntity.status(404).build();
        return ResponseEntity.status(200).body(game);
    }
    @DeleteMapping("games/{id}")
    public ResponseEntity<Game> deleteGame(@PathVariable long id){
        Game game = gameService.deleteGame(id);
        if(game == null)
            return ResponseEntity.status(404).build();
        return ResponseEntity.status(200).build();
    }
    @PatchMapping("games/{id}")
    public ResponseEntity<Game> patchGame(@PathVariable long id, @RequestBody Game updatedGame){
        Game game = gameService.patchGame(id,updatedGame);
        if(game == null)
            return ResponseEntity.status(404).build();
        return ResponseEntity.status(200).body(game);
    }
    @GetMapping("games")
    public ResponseEntity<Collection<Game>> getAllGame(){
        return ResponseEntity.status(200).body(gameService.getAllGame());
    }
    @GetMapping("games/{id}/trials")
    public ResponseEntity<Collection<Trial>> getAllTrials(@PathVariable long id){
        Collection<Trial> trials = gameService.getAllTrials(id);
        if(trials == null)
            return ResponseEntity.status(404).build();
        return ResponseEntity.status(200).body(trials);
    }
}
