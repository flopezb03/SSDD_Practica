package es.ssdd.Practica1.entities;

public class Game {

    private long id;
    private String name;
    private int releaseYear;
    private int duration;

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

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
