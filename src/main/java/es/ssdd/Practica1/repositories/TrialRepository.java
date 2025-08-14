package es.ssdd.Practica1.repositories;

import es.ssdd.Practica1.entities.Trial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrialRepository extends JpaRepository<Trial,Long> {
}
