package services;

import models.EtatEnum;
import models.Resultat;
import models.Task;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskService {
    public static List<Task> getProjectTasks(int idProject){
        List<Task> tasks = new ArrayList<Task>();
        Task task = null;
        Connection con = null;
        try{
            Class.forName(Constants.DRIVER);
            con = DriverManager.getConnection(Constants.URL_DATABASE,Constants.USERNAME,Constants.PASSWORD);
            Statement stmt = con.createStatement();
            String sql = Constants.SELECT_PROJECTS_TASK_QUERY+idProject;
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()) {
                task = new Task();
                task.setId(rs.getInt(1));
                task.setLibelle(rs.getString(2));
                task.setEstimation(rs.getInt(3));
                task.setDescription(rs.getString(4));
                task.setEtat(EtatEnum.valueOf(rs.getString(5)));
                tasks.add(task);
            }
            con.close();
        }catch(Exception e){
            e.getStackTrace();
        }
        finally {
            try{
                con.close();
            }catch(SQLException se){
                se.getStackTrace();
            }
        }
        return tasks;
    }

    public static Task getProjectTask(int idProject, int idTask){
        Task task = null;
        List<Task> projectTasks = getProjectTasks(idProject);
        for (Task t : projectTasks){
            if (t.getId() == idTask){
                task = t;
            }
        }
        return task;
    }

    public static Resultat createTask(HttpServletRequest req){
        Resultat res = new Resultat();
        int idProjet = Integer.parseInt(req.getParameter("idProjet"));
        String libelle = req.getParameter("libelle");
        String etat = req.getParameter("etat");
        int estimation = Integer.parseInt(req.getParameter("estimation"));
        String description = req.getParameter("description");
        String sql = Constants.INSERT_PROJECT_TASK_QUERY + "('"+libelle+"',"+estimation+",'"+description+"','"+etat+"',"+idProjet+")";
        Connection con = null;
        try{
            Class.forName(Constants.DRIVER);
            con = DriverManager.getConnection(Constants.URL_DATABASE,Constants.USERNAME,Constants.PASSWORD);
            Statement stmt = con.createStatement();
            stmt.execute(sql);
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
                con.close();
            }catch(SQLException se){
                se.getStackTrace();
                res.setMessage(se.getMessage());
                res.setCode(Constants.ERROR_CODE);
            }
        }

        return res;
    }

    public static Resultat updateTask(HttpServletRequest req){
        Resultat res = new Resultat();
        int idProjet = Integer.parseInt(req.getParameter("idProjet"));
        int id = Integer.parseInt(req.getParameter("id"));
        String libelle = req.getParameter("libelle");
        String etat = req.getParameter("etat");
        int estimation = Integer.parseInt(req.getParameter("estimation"));
        String description = req.getParameter("description");
        String sql = Constants.UPDATE_TASK_QUERY+ "libelle = '"+libelle+"' ,estimation = "+estimation+", etat = '"+etat+"' , description = '"+description+"' ";
        sql += " where id = "+id+" and idProjet = "+idProjet;
        Connection con = null;
        try{
            Class.forName(Constants.DRIVER);
            con = DriverManager.getConnection(Constants.URL_DATABASE,Constants.USERNAME,Constants.PASSWORD);
            Statement stmt = con.createStatement();
            stmt.execute(sql);
            con.close();
            res.setCode(Constants.SUCCES_CODE);
            res.setMessage("Mise à jour effectué avec succès en base");
        }catch(Exception e){
            e.printStackTrace();
            res.setMessage(e.getMessage());
            res.setCode(Constants.ERROR_CODE);
        }
        finally {
            try{
                con.close();
            }catch(SQLException se){
                se.getStackTrace();
                res.setMessage(se.getMessage());
                res.setCode(Constants.ERROR_CODE);
            }
        }
        return res;
    }

    public static Resultat deleteTask(HttpServletRequest req){
        Resultat res = new Resultat();
        int idProjet =  Integer.parseInt(req.getParameter("idProjet"));
        int idTask = Integer.parseInt(req.getParameter("id"));
        String sql = Constants.DELETE_TASK_QUERY+ idProjet + " and id = "+idTask;
        Connection con = null;
        try{
            Class.forName(Constants.DRIVER);
            con = DriverManager.getConnection(Constants.URL_DATABASE,Constants.USERNAME,Constants.PASSWORD);
            Statement stmt = con.createStatement();
            stmt.execute(sql);
            con.close();
            res.setCode(Constants.SUCCES_CODE);
            res.setMessage("Mise à jour effectué avec succès en base");
        }catch(Exception e){
            e.printStackTrace();
            res.setMessage(e.getMessage());
            res.setCode(Constants.ERROR_CODE);
        }
        finally {
            try{
                con.close();
            }catch(SQLException se){
                se.getStackTrace();
                res.setMessage(se.getMessage());
                res.setCode(Constants.ERROR_CODE);
            }
        }
        return res;
    }
}
