package es.ssdd.Practica1.util;

import es.ssdd.Practica1.entities.CharacterInGame;
import es.ssdd.Practica1.entities.Game;
import es.ssdd.Practica1.entities.Trial;
import es.ssdd.Practica1.repositories.CharacterRepository;
import es.ssdd.Practica1.repositories.GameRepository;
import es.ssdd.Practica1.repositories.TrialRepository;
import es.ssdd.Practica1.services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private TrialRepository trialRepository;
    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private CharacterService charService;

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

        trial1.setGame(game1);
        trial2.setGame(game2);
        trial3.setGame(game3);
        trial4.setGame(game3);

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
        //Cambios Edgar intentando enlazar entidades
        Set<CharacterInGame> charactersTrial1 = new HashSet<>();
        charactersTrial1.add(char1);
        charactersTrial1.add(char2);
        trial1.setParticipants(charactersTrial1);
        trialRepository.save(trial1);
        //characterRepository.delete(char1); //Si se hace asi error por violacion de restriccion de integridad referencial
        /*CharacterInGame char1x = characterRepository.findById(char1.getIdChar()).get();
        Collection<Trial> trialsChar1 = char1x.getTrialsParticipated();
        for(Trial juicio:trialsChar1){
            System.out.println(juicio.getTrial_id());
        }
        charService.deleteCharacter(char1.getIdChar());*/
        //Try 2
       /* CharacterInGame char1x = characterRepository.getById(char1.getIdChar());
        Set<Trial> trialsChar1 = new HashSet<>();
        trialsChar1.add(trial1);
        char1x.setTrialsParticipated(trialsChar1);
        characterRepository.save(char1x);
        CharacterInGame char1y = characterRepository.getById(char1x.getIdChar());
        Collection<Trial> trialsSavedChar1 = char1y.getTrialsParticipated();
        System.out.println(trialsSavedChar1);*/
    }
}
