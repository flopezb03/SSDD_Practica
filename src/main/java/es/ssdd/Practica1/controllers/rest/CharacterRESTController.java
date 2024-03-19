package es.ssdd.Practica1.controllers.rest;

import es.ssdd.Practica1.entities.CharacterInGame;
import es.ssdd.Practica1.services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/characters")
public class CharacterRESTController {
    @Autowired private CharacterService charService;

    @GetMapping
    public ResponseEntity<Collection<CharacterInGame>> getAllCharacters(){
        Collection<CharacterInGame> characters = charService.getAllCharacter();
        return ResponseEntity.status(200).body(characters);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CharacterInGame> getCharacter(@PathVariable Long id){
        CharacterInGame searchedChar = charService.getCharacter(id);
        if (searchedChar== null){
            return ResponseEntity.status(404).build();
        }
        else{
            return ResponseEntity.status(200).body(searchedChar);
        }
    }

    @PostMapping
    public ResponseEntity<CharacterInGame> createCharacter(@RequestBody CharacterInGame character){
        CharacterInGame charSaved = charService.createCharacter(character);
        if (charSaved == null){
            return ResponseEntity.status(400).build();
        }else{
            return ResponseEntity.status(201).body(charSaved);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CharacterInGame> deleteCharacter(@PathVariable Long id){
        CharacterInGame charDeleted = charService.deleteCharacter(id);
        if (charDeleted == null){
            return ResponseEntity.notFound().build();
        }else
            return ResponseEntity.ok().body(charDeleted);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CharacterInGame> putCharacter(@PathVariable Long id, @RequestBody CharacterInGame character){
        CharacterInGame updatedChar = charService.putCharacter(id,character);
        if (updatedChar== null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok().body(updatedChar);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CharacterInGame> patchCharacter(@PathVariable Long id, @RequestBody CharacterInGame character){
        CharacterInGame partialUpdatedChar = charService.patchCharacter(id,character);
        if(partialUpdatedChar==null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok().body(partialUpdatedChar);
    }




}
