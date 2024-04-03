package es.ssdd.Practica1.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer releaseYear;
    private Integer duration;

    @OneToMany(mappedBy = "game",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Trial> trials = new HashSet<>();

    public Game(){}
    public Game(long id, String name, int releaseYear, int duration) {
        this.id = id;
        this.name = name;
        this.releaseYear = releaseYear;
        this.duration = duration;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
