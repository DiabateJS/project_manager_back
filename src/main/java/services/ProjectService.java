package services;

import models.EtatEnum;
import models.Project;
import models.Resultat;
import models.Task;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
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
                if (con != null){
                    con.close();
                }
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

    public static Resultat createProject(HttpServletRequest request){
        Resultat res = new Resultat();
        String libelle = request.getParameter("libelle");
        String description = request.getParameter("description");
        String etat = request.getParameter("etat");
        Connection con = null;
        try{
            Class.forName(Constants.DRIVER);
            con = DriverManager.getConnection(Constants.URL_DATABASE,Constants.USERNAME,Constants.PASSWORD);
            PreparedStatement preparedStm = con.prepareStatement(Constants.INSERT_PROJECT_QUERY);
            preparedStm.setString(1,libelle);
            preparedStm.setString(2,etat);
            preparedStm.setString(3,description);
            preparedStm.execute();
            con.close();
            res.setCode(Constants.SUCCES_CODE);
            res.setMessage("Ajout effectué avec succès en base");
        }catch(Exception e){
            e.printStackTrace();
            res.setMessage(e.getMessage());
            res.setCode(Constants.ERROR_CODE);
        }
        finally {
            try{
                if (con != null){
                    con.close();
                }
            }catch(SQLException se){
                se.getStackTrace();
                res.setMessage(se.getMessage());
                res.setCode(Constants.ERROR_CODE);
            }
        }

        return res;
    }

    private static Resultat deleteProjectTasks(int id){
        Resultat res = new Resultat();
        Connection con = null;
        //1- Suppression des taches du projet
        try {
            Class.forName(Constants.DRIVER);
            con = DriverManager.getConnection(Constants.URL_DATABASE,Constants.USERNAME,Constants.PASSWORD);
            PreparedStatement preparedStmt = con.prepareStatement(Constants.DELETE_PROJECT_TASK_QUERY);
            preparedStmt.setInt(1, id);
            preparedStmt.execute();
            con.close();
            res.setCode(Constants.SUCCES_CODE);
            res.setMessage("Taches du projet supprimées avec succes.");
        }catch(Exception e){
            e.printStackTrace();
            res.setMessage(res.getMessage() + e.getMessage());
            res.setCode(Constants.ERROR_CODE);
        }
        finally {
            try{
                if (con != null){
                    con.close();
                }
            }catch(SQLException se){
                se.getStackTrace();
                res.setMessage(se.getMessage());
                res.setCode(Constants.ERROR_CODE);
            }
        }
        return res;
    }

    private static Resultat deleteProjectOnly(int id){
        Resultat res = new Resultat();
        Connection con = null;
        try {
            Class.forName(Constants.DRIVER);
            con = DriverManager.getConnection(Constants.URL_DATABASE,Constants.USERNAME,Constants.PASSWORD);
            PreparedStatement preparedStmt = con.prepareStatement(Constants.DELETE_PROJECT_QUERY);
            preparedStmt.setInt(1, id);
            preparedStmt.execute();
            con.close();
            res.setCode(Constants.SUCCES_CODE);
            res.setMessage("Projet supprimés avec succes.");
        }catch(Exception e){
            e.printStackTrace();
            res.setMessage(e.getMessage());
            res.setCode(Constants.ERROR_CODE);
        }
        finally {
            try{
                if (con != null){
                    con.close();
                }
            }catch(SQLException se){
                se.getStackTrace();
                res.setMessage(se.getMessage());
                res.setCode(Constants.ERROR_CODE);
            }
        }
        return res;
    }

    public static Resultat deleteProject(HttpServletRequest request){
        Resultat resDeleteTasks = new Resultat();
        Resultat resDeleteProject = new Resultat();
        Resultat res = new Resultat();
        int id = Integer.parseInt(request.getParameter("id"));
        //1. Suppression des taches du projet
        resDeleteTasks = deleteProjectTasks(id);
        if (resDeleteTasks.getCode().equals(Constants.SUCCES_CODE)){
            //2- Suppression du projet
            resDeleteProject = deleteProjectOnly(id);
            res.setCode(Constants.SUCCES_CODE);
            res.setMessage("1-"+resDeleteTasks.getMessage() + "2-" + resDeleteProject.getMessage());
        }else{
            res.setCode(Constants.ERROR_CODE);
            res.setMessage(resDeleteTasks.getMessage());
        }
        return res;
    }

    public static Resultat updateProject(HttpServletRequest request){
        Resultat res = new Resultat();
        int id = Integer.parseInt(request.getParameter("id"));
        String libelle = request.getParameter("libelle");
        String etat = request.getParameter("etat");
        String description = request.getParameter("description");
        Connection con = null;
        try {
            Class.forName(Constants.DRIVER);
            con = DriverManager.getConnection(Constants.URL_DATABASE,Constants.USERNAME,Constants.PASSWORD);
            PreparedStatement preparedStmt = con.prepareStatement(Constants.UPDATE_PROJECT_QUERY);
            preparedStmt.setString(1, libelle);
            preparedStmt.setString(2, etat);
            preparedStmt.setString(3, description);
            preparedStmt.setInt(4, id);
            preparedStmt.execute();
            con.close();
            res.setCode(Constants.SUCCES_CODE);
            res.setMessage("Projet mis à jour avec succès.");
        }catch(Exception e){
            e.printStackTrace();
            res.setMessage(e.getMessage());
            res.setCode(Constants.ERROR_CODE);
        }
        finally {
            try{
                if (con != null){
                    con.close();
                }
            }catch(SQLException se){
                se.getStackTrace();
                res.setMessage(se.getMessage());
                res.setCode(Constants.ERROR_CODE);
            }
        }
        return res;
    }
}
