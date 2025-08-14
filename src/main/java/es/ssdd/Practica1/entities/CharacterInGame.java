package es.ssdd.Practica1.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class CharacterInGame {
    //Attributes of the class
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChar;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "trial_participants",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "trial_id")
    )
    @JsonIgnore
    private Set<Trial> trialsParticipated = new HashSet<>();

    //The combination of name & surname could be used as alternative key in second practice
    private String name;
    private String surname;

    //Other information attributes
    private String dislike; //A thing the char dislikes
    private String fav; //A thing the char likes
    private String talent;

    private double height;

    //Constructors
    public CharacterInGame(){}
    public CharacterInGame(String name, String surname, String dislike, String fav, String talent, double height) {
        this.name = name;
        this.surname = surname;
        this.dislike = dislike;
        this.fav = fav;
        this.talent = talent;
        this.height = height;
    }

    public CharacterInGame(Set<Trial> trialsParticipated, String name, String surname, String dislike, String fav, String talent, double height) {
        this.trialsParticipated = trialsParticipated;
        this.name = name;
        this.surname = surname;
        this.dislike = dislike;
        this.fav = fav;
        this.talent = talent;
        this.height = height;
    }

    //Getter and setter methods


    public Long getIdChar() {
        return idChar;
    }

    public void setIdChar(Long idChar) {
        this.idChar = idChar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDislike() {
        return dislike;
    }

    public void setDislike(String dislike) {
        this.dislike = dislike;
    }

    public String getFav() {
        return fav;
    }

    public void setFav(String fav) {
        this.fav = fav;
    }

    public String getTalent() {
        return talent;
    }

    public void setTalent(String talent) {
        this.talent = talent;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    //Equals Method
    @Override
    public boolean equals(Object char2){
        if (char2 == null)
            return false;
        else if (CharacterInGame.class.equals(char2.getClass())){
            CharacterInGame charEq = (CharacterInGame) char2;
            return (this.getName().equals(charEq.getName()) && this.getSurname().equals(charEq.getSurname()));
        }//This block states two character are the same if they have same name and username
        else
            return false;
    }

    public Set<Trial> getTrialsParticipated() {
        return trialsParticipated;
    }

    public void setTrialsParticipated(Set<Trial> trialsParticipated) {
        this.trialsParticipated = trialsParticipated;
    }
}
