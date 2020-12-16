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
    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String SELECT_PROJECTS_QUERY = "select * from projet";
    private static String URL_DATABASE = "jdbc:mysql://localhost/project_manager?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
    private static String USERNAME = "root";
    private static String PASSWORD = "";

    public static List<Project> getAllProjects()  {
        List<Project> projects = new ArrayList<Project>();
        Project project = new Project();
        Connection con = null;
        try{
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL_DATABASE,USERNAME,PASSWORD);
            Statement stmt = con.createStatement();
            ResultSet rs=stmt.executeQuery(SELECT_PROJECTS_QUERY);
            while(rs.next()) {
                project = new Project();
                project.setId(rs.getInt(1));
                project.setLibelle(rs.getString(2));
                project.setEtat(EtatEnum.valueOf(rs.getString(3)));
                project.setDescription(rs.getString(4));
                List<Task> tasks = new ArrayList<Task>();
                Task task1 = new Task(1,"Creation du dépôt du projet sous GitHub.",1,"Creer le repository sous GitHub", EtatEnum.A_FAIRE);
                tasks.add(task1);
                project.setTasks(tasks);
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
