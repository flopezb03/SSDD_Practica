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
        /*CharacterInGame char1 = new CharacterInGame("Edgar","Gutierrez Pleite","Low marks","The shiniest star","math",1.6d);
        charRepository.save(char1);
        CharacterInGame char2 = new CharacterInGame("Keqing","Liyue","Lazy people","Ganyu","Sword master",1.5d);
        charRepository.save(char2);*/
    }

    //CRUD opetarions

    //Create operation(C)
    public CharacterInGame createCharacter(CharacterInGame character){
        boolean alreadyExists = charRepository.findAll().contains(character);
        if (!alreadyExists) {
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
        Optional<CharacterInGame> charToUpdate = charRepository.findById(id);
        if(charToUpdate.isEmpty())
            return null;
        else{
            Set<Trial> charTrials = character.getTrialsParticipated();
            Trial trialI;
            for(Trial trial:charTrials){
                trialI = trialRepository.getById(trial.getTrial_id());
                trialI.getParticipants().add(character);
                trialRepository.save(trialI);
            }
            charRepository.save(character);
            return charToUpdate.get();
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
            Set<Trial> charTrials = charToChange.getTrialsParticipated();
            Trial trialI;
            for(Trial trial:charTrials){
                trialI = trialRepository.getById(trial.getTrial_id());
                trialI.getParticipants().add(charToChange);
                trialRepository.save(trialI);
            }
            charRepository.save(charToChange);
            return charToChange;
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
            Set<Trial> charTrials = characterD.getTrialsParticipated();
            Trial trialI;
            for(Trial trial:charTrials){
                trialI = trialRepository.getById(trial.getTrial_id());
                trialI.getParticipants().remove(characterD);
                trialRepository.save(trialI);
            }
            charRepository.deleteById(id);
            return charToDelete.get();
        }
    }
}
