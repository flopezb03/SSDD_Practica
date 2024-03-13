package es.ssdd.Practica1.services;

import es.ssdd.Practica1.entities.Trial;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TrialService {
    Map<Long,Trial> trialMap = new ConcurrentHashMap<>();
    AtomicLong nextTrialId = new AtomicLong();

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
        trial.setTrial_id(id);
        return trialMap.put(id,trial);
    }


}
