package es.ssdd.Practica1.repositories;

import es.ssdd.Practica1.entities.CharacterInGame;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CharacterRepository extends JpaRepository<CharacterInGame,Long> {
}
