package es.ssdd.Practica1.util;

import es.ssdd.Practica1.entities.CharacterInGame;
import es.ssdd.Practica1.entities.Game;
import es.ssdd.Practica1.entities.Trial;
import es.ssdd.Practica1.repositories.CharacterRepository;
import es.ssdd.Practica1.repositories.GameRepository;
import es.ssdd.Practica1.repositories.TrialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private TrialRepository trialRepository;
    @Autowired
    private CharacterRepository characterRepository;

    @Override
    public void run(String... args) throws Exception {
        Game game1 = new Game("Game1",2000,1);
        Game game2 = new Game("Game2",2000,1);
        Game game3 = new Game("Game3",2000,1);
        Game game4 = new Game("Game4",2000,1);

        gameRepository.save(game1);
        gameRepository.save(game2);
        gameRepository.save(game3);
        gameRepository.save(game4);

        Trial trial1 = new Trial(1,"dec","Trial1");
        Trial trial2 = new Trial(1,"dec","Trial2");
        Trial trial3 = new Trial(1,"dec","Trial3");
        Trial trial4 = new Trial(1,"dec","Trial4");

        trialRepository.save(trial1);
        trialRepository.save(trial2);
        trialRepository.save(trial3);
        trialRepository.save(trial4);

        CharacterInGame char1 = new CharacterInGame("Char1","s","d","f","t",1);
        CharacterInGame char2 = new CharacterInGame("Char2","s","d","f","t",1);
        CharacterInGame char3 = new CharacterInGame("Char3","s","d","f","t",1);
        CharacterInGame char4 = new CharacterInGame("Char4","s","d","f","t",1);

        characterRepository.save(char1);
        characterRepository.save(char2);
        characterRepository.save(char3);
        characterRepository.save(char4);
    }
}
