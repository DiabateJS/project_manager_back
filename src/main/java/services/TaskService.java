package services;

import models.EtatEnum;
import models.Task;

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
}
