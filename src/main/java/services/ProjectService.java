package services;

import models.EtatEnum;
import models.Project;
import models.Task;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ProjectService {
    public static List<Project> getAllProjects(){
        List<Project> projects = new ArrayList<Project>();
        Project project = new Project();
        project.setId(1);
        project.setLibelle("Gestion de Projets");
        project.setDescription("Application de gestion de projets");
        List<Task> tasks = new ArrayList<Task>();
        Task task1 = new Task(1,"Creation du dépôt du projet sous GitHub.",1,"Creer le repository sous GitHub", EtatEnum.A_FAIRE);
        tasks.add(task1);
        Task task2 = new Task(2,"Intégrer le template au projet.",1,"Template Angular Admin 9",EtatEnum.A_FAIRE);
        tasks.add(task2);
        Task task3 = new Task(2,"Créer la structure de données permanente en Json.",1,"Structure de données provisoire.",EtatEnum.A_FAIRE);
        tasks.add(task3);
        project.setTasks(tasks);
        project.setEtat(EtatEnum.EN_COURS);
        projects.add(project);

        Project project2 = new Project();
        project2.setId(2);
        project2.setLibelle("Plateforme de transaction monétaire");
        project2.setDescription("Application de gestion des transactions en ligne via mobile money, crypto monaie ou banque");
        List<Task> tasks2 = new ArrayList<Task>();
        project2.setTasks(tasks2);
        project2.setEtat(EtatEnum.A_FAIRE);

        projects.add(project2);
        return projects;
    }

    public static Project getProjectById(int id){
        List<Project> projects = getAllProjects();
        Project project = null;
        for (Project proj : projects){
            if (proj.getId() == id){
                project = proj;
            }
        }
        return project;
    }
}
