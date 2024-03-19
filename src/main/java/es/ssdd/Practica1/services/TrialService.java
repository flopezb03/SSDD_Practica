package es.ssdd.Practica1.services;

import es.ssdd.Practica1.entities.Trial;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TrialService {
    Map<Long,Trial> trialMap = new HashMap<>();
    AtomicLong nextTrialId = new AtomicLong();

    public TrialService(){
    }
    public Trial createTrial(Trial trial){
        long id = nextTrialId.getAndIncrement();
        trial.setTrial_id(id);
        return trialMap.put(id,trial);
    }
    public Trial deleteTrial(Long id){
        return trialMap.remove(id);
    }

    public Collection<Trial> getAllTrials (){
        return trialMap.values();
    }

    public Trial getTrial (Long id){
        return trialMap.get(id);
    }

    public Trial putTrial(long id, Trial trial){
        Trial updatedTrial = trialMap.get(id);
        if(updatedTrial == null)
            return null;
        updatedTrial.setTrial_id(id);
        trialMap.put(id,updatedTrial);
        return trial;
    }

    public Trial patchTrial(Long id, Trial trial){
        Trial updateTrial = trialMap.get(id);
        if (updateTrial == null)
            return null;
        if (trial.getChapter() != 0)
            updateTrial.setChapter(trial.getChapter());
        if (trial.getDecor() != null)
            updateTrial.setDecor(trial.getDecor());
        if (trial.getSummary() != null)
            updateTrial.setSummary(trial.getSummary());
        return updateTrial;
    }
}
