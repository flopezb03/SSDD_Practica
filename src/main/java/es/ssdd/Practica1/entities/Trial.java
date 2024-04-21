package es.ssdd.Practica1.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Trial {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long trial_id;
    private int chapter;
    private String decor;
    private String summary;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToMany
    @JoinTable(
            name = "trial_participants",
            joinColumns = @JoinColumn(name = "trial_id"),
            inverseJoinColumns = @JoinColumn(name = "character_id")
    )
    private Set<CharacterInGame> participants = new HashSet<>();
    public Trial() {
    }

    public Long getTrial_id() {
        return trial_id;
    }

    public void setTrial_id(Long trial_id) {
        this.trial_id = trial_id;
    }

    public String getDecor() {
        return decor;
    }

    public void setDecor(String decor) {
        this.decor = decor;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getChapter() {
        return chapter;
    }

    public void setChapter(int chapter) {
        this.chapter = chapter;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Set<CharacterInGame> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<CharacterInGame> participants) {
        this.participants = participants;
    }
}
