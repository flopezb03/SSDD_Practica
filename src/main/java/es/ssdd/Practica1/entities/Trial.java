package es.ssdd.Practica1.entities;

public class Trial {
    private Long trial_id;
    private String decor;
    private String resumen;     //Traducir

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

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }
}
