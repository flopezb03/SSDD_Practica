package es.ssdd.Practica1.services;

import es.ssdd.Practica1.entities.Trial;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TrialService {
    Map<Long,Trial> trialMap = new HashMap<>();
    AtomicLong nextTrialId = new AtomicLong();

    public TrialService(){
        Trial t1 = new Trial();
        t1.setChapter(1);
        t1.setDecor("Bright colors");
        t1.setSummary("Oh no!");
        long id = nextTrialId.getAndIncrement();
        t1.setTrial_id(id);
        trialMap.put(id,t1);

        Trial t2 = new Trial();
        t2.setChapter(1);
        t2.setDecor("Factory environment");
        t2.setSummary("Mamma mia!");
        id = nextTrialId.getAndIncrement();
        t2.setTrial_id(id);
        trialMap.put(id,t2);
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

    public Trial updateTrial(Long id, Trial trial){
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
