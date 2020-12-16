package contollers;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Task;
import services.TaskService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="webServlet",urlPatterns = "/task")
public class TaskServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin","*");
        int idProject = Integer.parseInt(req.getParameter("idProject"));
        int idTask = Integer.parseInt(req.getParameter("idTask"));
        Task task = TaskService.getProjectTask(idProject, idTask);
        ObjectMapper mapper = new ObjectMapper();
        resp.getWriter().write(mapper.writeValueAsString(task));
    }
}
