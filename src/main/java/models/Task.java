package models;

public class Task {
    private int Id;
    private String libelle;
    private int estimation;
    private String description;
    private EtatEnum etat;
    private String user;

    public Task() {
    }

    public Task(int id, String libelle, int estimation, String description, EtatEnum etat, String user) {
        Id = id;
        this.libelle = libelle;
        this.estimation = estimation;
        this.description = description;
        this.etat = etat;
        this.user = user;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getEstimation() {
        return estimation;
    }

    public void setEstimation(int estimation) {
        this.estimation = estimation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EtatEnum getEtat() {
        return etat;
    }

    public void setEtat(EtatEnum etat) {
        this.etat = etat;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Task{" +
                "Id=" + Id +
                ", libelle='" + libelle + '\'' +
                ", estimation=" + estimation +
                ", description='" + description + '\'' +
                ", etat=" + etat +
                ", user='" + user + '\'' +
                '}';
    }
}

