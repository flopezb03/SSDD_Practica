package es.ssdd.Practica1.services;

import es.ssdd.Practica1.entities.Game;
import es.ssdd.Practica1.entities.Trial;
import es.ssdd.Practica1.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;


    public Game createGame(Game game){
        return gameRepository.save(game);
    }
    public Game getGame(long id){
        Optional<Game> byId = gameRepository.findById(id);
        return byId.orElse(null);
    }
    public Game updateGame(long id, Game updatedGame){
        Optional<Game> byId = gameRepository.findById(id);
        if(byId.isPresent()) {
            updatedGame.setId(id);
            return gameRepository.save(updatedGame);
        }
        return null;
    }
    public Game deleteGame(long id){
        Optional<Game> byId = gameRepository.findById(id);
        if(byId.isPresent()) {
            Game game = byId.get();
            gameRepository.deleteById(id);
            return game;
        }
        return null;
    }
    public Game patchGame(long id, Game updatedGame){
        Optional<Game> gameOp = gameRepository.findById(id);
        if(gameOp.isEmpty())
            return null;
        Game game = gameOp.get();
        if(updatedGame.getName() != null)
            game.setName(updatedGame.getName());
        if(updatedGame.getReleaseYear() != null)
            game.setReleaseYear(updatedGame.getReleaseYear());
        if(updatedGame.getDuration() != null)
            game.setDuration(updatedGame.getDuration());
        return gameRepository.save(game);

    }
    public Collection<Game> getAllGame(){
        return gameRepository.findAll();
    }
    public Collection<Trial> getAllTrials(long id){
        Optional<Game> game = gameRepository.findById(id);
        if(game.isEmpty())
            return null;

        return game.get().getTrials();
    }
}
