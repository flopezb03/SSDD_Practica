package es.ssdd.Practica1.services;

import es.ssdd.Practica1.entities.CharacterInGame;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CharacterService {
    //CharacterService Attributes
    private final Map<Long, CharacterInGame> characterMap = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    public CharacterService(){
        CharacterInGame char1 = new CharacterInGame("Edgar","Gutierrez Pleite","Low marks","The shiniest star","math",1.6d);
        Long id = idGenerator.getAndIncrement();
        char1.setIdChar(id);
        characterMap.put(id,char1);
        CharacterInGame char2 = new CharacterInGame("Keqing","Liyue","Lazy people","Ganyu","Sword master",1.5d);
        id = idGenerator.getAndIncrement();
        char2.setIdChar(id);
        characterMap.put(id,char2);
    }

    //CRUD opetarions

    //Create operation(C)
    public CharacterInGame createCharacter(CharacterInGame character){
        if (!characterMap.containsValue(character)) {
            Long idChar = idGenerator.getAndIncrement();
            character.setIdChar(idChar);
            characterMap.put(idChar, character);
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
        return characterMap.get(id);
    }

    public Collection<CharacterInGame> getAllCharacter(){
        return characterMap.values();
    }

    //Update operations
    /*putCharacter for complete update of all attributes
      patchCharacter for partial update of some attributes
    */
    public CharacterInGame putCharacter(Long id, CharacterInGame character){
        if (characterMap.containsKey(id)){
            character.setIdChar(id);
            characterMap.put(id,character);
            return character;
        }
        else
            return null;
    }
    public CharacterInGame patchCharacter(Long id, CharacterInGame character){
        if (characterMap.containsKey(id)){
            CharacterInGame charToChange = characterMap.get(id);
            if (!(character.getName()==null))
                charToChange.setName(character.getName());
            if (!(character.getSurname()==null))
                charToChange.setSurname(character.getSurname());
            if (!(character.getHeight() == 0.0d))
                charToChange.setHeight(character.getHeight());
            if (!(character.getDislike()==null))
                charToChange.setDislike(character.getDislike());
            if(!(character.getLike()==null))
                charToChange.setLike(character.getLike());
            if(!(character.getTalent()==null))
                charToChange.setTalent(character.getTalent());
            return charToChange;
        }
        else
            return null;
    }

    //Delete operation(D)
    public CharacterInGame deleteCharacter(Long id){
        return characterMap.remove(id);
    }
}
