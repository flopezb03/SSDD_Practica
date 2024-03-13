package es.ssdd.Practica1.services;

import es.ssdd.Practica1.entities.Trial;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TrialService {
    Map<Trial,Long> trialMap = new ConcurrentHashMap<Trial,Long>();
    AtomicLong nextTrialId = new AtomicLong();

    public Trial createTrial(Trial trial){


        return trial;
    }


}
