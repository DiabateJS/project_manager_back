package services;

import models.Project;
import models.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskService {
    public static List<Task> getProjectTasks(int idProject){
        List<Task> tasks = new ArrayList<Task>();
        Project project = ProjectService.getProjectById(idProject);
        if (project != null){
            tasks = project.getTasks();
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
