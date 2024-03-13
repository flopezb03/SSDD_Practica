package es.ssdd.Practica1.entities;

public class Trial {
    private long trial_id;
    private String decor;
    private String summary;
    private int chapter;

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
}
