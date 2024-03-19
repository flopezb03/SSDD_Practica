package es.ssdd.Practica1.services;

import es.ssdd.Practica1.entities.Game;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class GameService {

    private final Map<Long, Game> games= new HashMap();
    private final AtomicLong nextId = new AtomicLong();



    public Game createGame(Game game){
        long id = nextId.getAndIncrement();
        game.setId(id);
        games.put(id,game);
        return game;
    }
    public Game getGame(long id){
        return games.get(id);
    }
    public Game putGame(long id, Game updatedGame){
        Game oldGame = games.get(id);
        if(oldGame == null)
            return null;
        updatedGame.setId(id);
        games.put(id,updatedGame);
        return updatedGame;
    }
    public Game deleteGame(long id){
        if(games.containsKey(id))
            return games.remove(id);
        return null;
    }
    public Game patchGame(long id, Game updatedGame){
        Game game = games.get(id);
        if(game == null)
            return null;
        if(updatedGame.getName() != null)
            game.setName(updatedGame.getName());
        if(updatedGame.getReleaseYear() != null)
            game.setReleaseYear(updatedGame.getReleaseYear());
        if(updatedGame.getDuration() != null)
            game.setDuration(updatedGame.getDuration());
        return game;

    }
    public Collection<Game> getAllGame(){
        return games.values();
    }
}
