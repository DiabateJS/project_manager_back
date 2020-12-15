package models;

import java.util.List;

public class Project {
    public int id;
    public String libelle;
    public String description;
    public List<Task> tasks;
    public EtatEnum etat;

    public Project() {
    }

    public Project(int id, String libelle, String description, List<Task> tasks, EtatEnum etat) {
        this.id = id;
        this.libelle = libelle;
        this.description = description;
        this.tasks = tasks;
        this.etat = etat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public EtatEnum getEtat() {
        return etat;
    }

    public void setEtat(EtatEnum etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                ", description='" + description + '\'' +
                ", tasks=" + tasks +
                ", etat=" + etat +
                '}';
    }

}
