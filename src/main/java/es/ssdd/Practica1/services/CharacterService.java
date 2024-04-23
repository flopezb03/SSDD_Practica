package es.ssdd.Practica1.services;

import es.ssdd.Practica1.entities.CharacterInGame;
import es.ssdd.Practica1.entities.Trial;
import es.ssdd.Practica1.repositories.CharacterRepository;
import es.ssdd.Practica1.repositories.TrialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Service
public class CharacterService {
    //CharacterService Attributes
    @Autowired
    private CharacterRepository charRepository;

    @Autowired
    private TrialRepository trialRepository;

    public CharacterService(){
    }

    //CRUD opetarions

    //Create operation(C)
    public CharacterInGame createCharacter(CharacterInGame character){
        boolean alreadyExists = charRepository.findAll().contains(character);
        if (!alreadyExists && !(character == null)) {
            Set<Trial> charTrials = character.getTrialsParticipated();
            Trial trialI;
            for(Trial trial:charTrials){
                trialI = trialRepository.getById(trial.getTrial_id());
                trialI.getParticipants().add(character);
                trialRepository.save(trialI);
            }
            charRepository.save(character);
            return (character);
        }else{
            return null;
        }
    }

    /*Read operations(R)
    getCharacter to read a single character
    getAllCharacters to read every character in the map
    * */

    public CharacterInGame getCharacter(Long id){
        Optional<CharacterInGame> charToGet = charRepository.findById(id);
        return  charToGet.orElse(null);
    }


    public Collection<CharacterInGame> getAllCharacter(){
        return charRepository.findAll();
    }

    //Update operations
    /*putCharacter for complete update of all attributes
      patchCharacter for partial update of some attributes
    */
    public CharacterInGame putCharacter(Long id, CharacterInGame character){
        character.setIdChar(id);
        Optional<CharacterInGame> charToUpdate = charRepository.findById(id);
        if(charToUpdate.isEmpty())
            return null;
        else{
            return charRepository.save(character);
        }

    }
    public CharacterInGame patchCharacter(Long id, CharacterInGame character){
        Optional<CharacterInGame> charToPatch = charRepository.findById(id);
        if (charToPatch.isPresent()){
            CharacterInGame charToChange = charToPatch.get();
            if (!(character.getName()==null))
                charToChange.setName(character.getName());
            if (!(character.getSurname()==null))
                charToChange.setSurname(character.getSurname());
            if (!(character.getHeight() == 0.0d))
                charToChange.setHeight(character.getHeight());
            if (!(character.getDislike()==null))
                charToChange.setDislike(character.getDislike());
            if(!(character.getFav()==null))
                charToChange.setFav(character.getFav());
            if(!(character.getTalent()==null))
                charToChange.setTalent(character.getTalent());

            return charRepository.save(charToChange);
        }
        else
            return null;
    }

    //Delete operation(D)
    public CharacterInGame deleteCharacter(Long id){
        Optional<CharacterInGame> charToDelete = charRepository.findById(id);
        if(charToDelete.isEmpty())
            return null;
        else{
            CharacterInGame characterD = charToDelete.get();
            Set<Trial> trialsParticipated = characterD.getTrialsParticipated();
            Trial trialI;
            for(Trial trial:trialsParticipated){
                trialI = trialRepository.getById(trial.getTrial_id());
                trialI.getParticipants().remove(characterD);
                trialRepository.save(trialI);
            }
            charRepository.deleteById(id);
            return charToDelete.get();
        }
    }

    //Operations with Trials due to the relationship
    public Collection<Trial> getAllTrials(){
        return trialRepository.findAll();
    }

    public Trial getTrial(Long idTrial){
        Optional<Trial> trial = trialRepository.findById(idTrial);
        return trial.orElse(null);
    }

    public CharacterInGame addTrial(Long idChar,Long idTrial){
        Trial trial = getTrial(idTrial);
        CharacterInGame character = getCharacter(idChar);
        if(trial== null || character == null)
            return null;
        if(character.getTrialsParticipated().contains(trial))
            return null;
        character.getTrialsParticipated().add(trial);
        return charRepository.save(character);
    }

    public CharacterInGame removeTrial(Long idChar,Long idTrial){
        Trial trial = getTrial(idTrial);
        CharacterInGame character = getCharacter(idChar);
        if(trial== null || character == null)
            return null;
        if (!character.getTrialsParticipated().contains(trial))
            return null;
        character.getTrialsParticipated().remove(trial);
        return charRepository.save(character);
    }
}
