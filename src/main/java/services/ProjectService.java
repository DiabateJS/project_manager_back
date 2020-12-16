package services;

import models.EtatEnum;
import models.Project;
import models.Task;

import javax.ejb.Stateless;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ProjectService {

    public static List<Project> getAllProjects()  {
        List<Project> projects = new ArrayList<Project>();
        Project project = null;
        Connection con = null;
        try{
            Class.forName(Constants.DRIVER);
            con = DriverManager.getConnection(Constants.URL_DATABASE,Constants.USERNAME,Constants.PASSWORD);
            Statement stmt = con.createStatement();
            ResultSet rs=stmt.executeQuery(Constants.SELECT_PROJECTS_QUERY);
            while(rs.next()) {
                project = new Project();
                project.setId(rs.getInt(1));
                project.setLibelle(rs.getString(2));
                project.setEtat(EtatEnum.valueOf(rs.getString(3)));
                project.setDescription(rs.getString(4));
                project.setTasks(new ArrayList<Task>());
                projects.add(project);
            }
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }
        finally {
            try{
                con.close();
            }catch(SQLException se){
                se.getStackTrace();
            }
        }

        // Ajout des tâches de projets
        for (Project p : projects){
            p.setTasks(TaskService.getProjectTasks(p.getId()));
        }

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
