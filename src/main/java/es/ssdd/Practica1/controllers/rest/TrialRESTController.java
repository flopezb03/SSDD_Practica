package es.ssdd.Practica1.controllers.rest;

import es.ssdd.Practica1.entities.CharacterInGame;
import es.ssdd.Practica1.entities.Trial;
import es.ssdd.Practica1.services.TrialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequestMapping("/api/")
@RestController
public class TrialRESTController {
    @Autowired
    TrialService trialService;

    @GetMapping("trials")
    public ResponseEntity<Collection<Trial>> getAllTrials(){
        return ResponseEntity.ok(trialService.getAllTrials());
    }

    @GetMapping("trials/{id}")
    public ResponseEntity<Trial> getTrial(@PathVariable long id){
        Trial trial = trialService.getTrial(id);
        if(trial == null)
            return  ResponseEntity.notFound().build();
        return ResponseEntity.ok(trial);
    }

    @PostMapping("trials")
    public ResponseEntity<Trial> createTrial(@RequestBody Trial trial){
        return ResponseEntity.status(201).body(trialService.createTrial(trial));
    }

    @DeleteMapping("trials/{id}")
    public ResponseEntity<Trial> deleteTrial(@PathVariable long id){
        Trial delete = trialService.deleteTrial(id);
        if (delete == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(delete);
    }

    @PutMapping("trials/{id}")
    public ResponseEntity<Trial> putTrial(@PathVariable long id, @RequestBody Trial trial){
        Trial updated = trialService.putTrial(id,trial);
        if (trial == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.status(200).body(updated);
    }

    @PatchMapping("trials/{id}")
    public ResponseEntity<Trial> patchTrial(@PathVariable long id, @RequestBody Trial trial){
        Trial updated = trialService.patchTrial(id,trial);
        if (trial == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.status(200).body(updated);
    }

    @GetMapping("trials/{id}/characters")
    public ResponseEntity<Collection<CharacterInGame>> getCharacters(@PathVariable long id){
        Trial trial = trialService.getTrial(id);
        if(trial == null)
            return  ResponseEntity.notFound().build();
        return ResponseEntity.ok(trial.getParticipants());
    }
    @PostMapping("trials/{id}/characters")
    public ResponseEntity<Trial> addCharacter(@PathVariable long id, @RequestBody CharacterInGame character){
        Trial trial = trialService.addCharacter(id,character.getIdChar());
        if(trial == null)
            return  ResponseEntity.notFound().build();
        return ResponseEntity.ok(trial);
    }
    @DeleteMapping("trials/{id}/characters")
    public ResponseEntity<Trial> removeCharacter(@PathVariable long id, @RequestBody CharacterInGame character){
        Trial trial = trialService.removeCharacter(id,character.getIdChar());
        if(trial == null)
            return  ResponseEntity.notFound().build();
        return ResponseEntity.ok(trial);
    }
}
