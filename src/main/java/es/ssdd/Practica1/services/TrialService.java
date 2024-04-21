package es.ssdd.Practica1.services;

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
        return trialRepository.save(updateTrial);
    }

    public Collection<Game> getAllGames(){
        return gameRepository.findAll();
    }
    public Game getGame(Long id){
        Optional<Game> byId = gameRepository.findById(id);
        return byId.orElse(null);
    }
}
