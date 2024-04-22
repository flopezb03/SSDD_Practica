package es.ssdd.Practica1.services;

import es.ssdd.Practica1.entities.CharacterInGame;
import es.ssdd.Practica1.entities.Game;
import es.ssdd.Practica1.entities.Trial;
import es.ssdd.Practica1.repositories.CharacterRepository;
import es.ssdd.Practica1.repositories.GameRepository;
import es.ssdd.Practica1.repositories.TrialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class TrialService {

    @Autowired
    private TrialRepository trialRepository;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private CharacterRepository characterRepository;

    public TrialService(){
    }
    public Trial createTrial(Trial trial){
        return trialRepository.save(trial);
    }
    public Trial deleteTrial(Long id){
        Optional<Trial> byId = trialRepository.findById(id);
        if (byId.isEmpty())
            return null;
        Trial trial = byId.get();
        trialRepository.deleteById(id);
        return trial;
    }

    public Collection<Trial> getAllTrials (){
        return trialRepository.findAll();
    }

    public Trial getTrial (Long id){
        Optional<Trial> byId = trialRepository.findById(id);
        return byId.orElse(null);
    }

    public Trial putTrial(long id, Trial trial){
        Optional<Trial> byId = trialRepository.findById(id);
        if (byId.isEmpty())
            return null;
        trial.setTrial_id(id);
        return trialRepository.save(trial);
    }

    public Trial patchTrial(Long id, Trial trial){
        Optional<Trial> byId = trialRepository.findById(id);
        if (byId.isEmpty())
            return null;
        Trial updateTrial = byId.get();
        if (trial.getChapter() != 0)
            updateTrial.setChapter(trial.getChapter());
        if (trial.getDecor() != null)
            updateTrial.setDecor(trial.getDecor());
        if (trial.getSummary() != null)
            updateTrial.setSummary(trial.getSummary());
        if (trial.getGame() != null)
            updateTrial.setGame(trial.getGame());
        return trialRepository.save(updateTrial);
    }

    public Collection<Game> getAllGames(){
        return gameRepository.findAll();
    }
    public Game getGame(Long id){
        Optional<Game> byId = gameRepository.findById(id);
        return byId.orElse(null);
    }
    public Collection<CharacterInGame> getAllCharacters(){
        return characterRepository.findAll();
    }
    public CharacterInGame getCharacter(Long id){
        Optional<CharacterInGame> byId = characterRepository.findById(id);
        return byId.orElse(null);
    }
    public Trial addCharacter(Long trial_id, Long character_id){
        Optional<Trial> trialOptional = trialRepository.findById(trial_id);
        if (trialOptional.isEmpty())
            return null;
        Optional<CharacterInGame> characterOptional = characterRepository.findById(character_id);
        if (characterOptional.isEmpty())
            return null;

        Trial trial = trialOptional.get();
        CharacterInGame character = characterOptional.get();

        if(trial.getParticipants().contains(character))
            return null;

        trial.getParticipants().add(character);
        //  Añadir del otro lado??? -> parece que no

        return trialRepository.save(trial);
    }
    public Trial removeCharacter(Long trial_id, Long character_id){
        Optional<Trial> trialOptional = trialRepository.findById(trial_id);
        if (trialOptional.isEmpty())
            return null;
        Optional<CharacterInGame> characterOptional = characterRepository.findById(character_id);
        if (characterOptional.isEmpty())
            return null;

        Trial trial = trialOptional.get();
        CharacterInGame character = characterOptional.get();

        if(!trial.getParticipants().contains(character))
            return null;

        trial.getParticipants().remove(character);

        return trialRepository.save(trial);
    }
}
