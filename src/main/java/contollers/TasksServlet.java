package contollers;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Project;
import models.Task;
import services.ProjectService;
import services.TaskService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name="tasksServlet", urlPatterns = "/tasks")
public class TasksServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        int idProject = Integer.parseInt(req.getParameter("id"));
        List<Task> tasks = TaskService.getProjectTasks(idProject);
        ObjectMapper mapper = new ObjectMapper();
        resp.getWriter().write(mapper.writeValueAsString(tasks));
    }
}
