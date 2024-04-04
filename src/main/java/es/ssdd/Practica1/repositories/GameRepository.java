package es.ssdd.Practica1.repositories;

import es.ssdd.Practica1.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game,Long> {
}
