package es.ssdd.Practica1.services;

import es.ssdd.Practica1.entities.Game;
import es.ssdd.Practica1.entities.Trial;
import es.ssdd.Practica1.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;
    //private final Map<Long, Game> games= new HashMap();
    //private final AtomicLong nextId = new AtomicLong();


    public Game createGame(Game game){
        return gameRepository.save(game);
        /*long id = nextId.getAndIncrement();
        game.setId(id);
        games.put(id,game);
        return game;*/
    }
    public Game getGame(long id){
        Optional<Game> byId = gameRepository.findById(id);
        return byId.orElse(null);
        //return games.get(id);
    }
    public Game putGame(long id, Game updatedGame){
        Optional<Game> byId = gameRepository.findById(id);
        if(byId.isPresent()) {
            updatedGame.setId(id);
            return gameRepository.save(updatedGame);
        }
        return null;
        /*Game oldGame = games.get(id);
        if(oldGame == null)
            return null;
        updatedGame.setId(id);
        games.put(id,updatedGame);
        return updatedGame;*/
    }
    public Game deleteGame(long id){
        Optional<Game> byId = gameRepository.findById(id);
        if(byId.isPresent()) {
            Game game = byId.get();
            gameRepository.deleteById(id);
            return game;
        }
        return null;
        /*if(games.containsKey(id))
            return games.remove(id);
        return null;*/
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
        /*Game game = games.get(id);
        if(game == null)
            return null;
        if(updatedGame.getName() != null)
            game.setName(updatedGame.getName());
        if(updatedGame.getReleaseYear() != null)
            game.setReleaseYear(updatedGame.getReleaseYear());
        if(updatedGame.getDuration() != null)
            game.setDuration(updatedGame.getDuration());
        return game;*/

    }
    public Collection<Game> getAllGame(){
        return gameRepository.findAll();
        //return games.values();
    }
    public Collection<Trial> getAllTrials(long id){
        Optional<Game> game = gameRepository.findById(id);
        if(game.isEmpty())
            return null;

        return game.get().getTrials();
    }
}
