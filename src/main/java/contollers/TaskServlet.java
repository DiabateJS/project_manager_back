package contollers;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Resultat;
import models.Task;
import services.Constants;
import services.TaskService;
import utils.Helper;

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
        Helper.fixHeaders(resp);
        resp.setContentType("application/json");
        int idProject = Integer.parseInt(req.getParameter("idProject"));
        int idTask = Integer.parseInt(req.getParameter("idTask"));
        Task task = TaskService.getProjectTask(idProject, idTask);
        ObjectMapper mapper = new ObjectMapper();
        resp.getWriter().write(mapper.writeValueAsString(task));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Helper.fixHeaders(resp);
        resp.setContentType("application/json");
        Resultat res = TaskService.createTask(req);
        ObjectMapper mapper = new ObjectMapper();
        resp.getWriter().write(mapper.writeValueAsString(res));
        int status = res.getCode().equals(Constants.SUCCES_CODE) ? HttpServletResponse.SC_OK
                : HttpServletResponse.SC_BAD_REQUEST;
        resp.setStatus(status);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Helper.fixHeaders(resp);
        resp.setContentType("application/json");
        Resultat res = TaskService.updateTask(req);
        ObjectMapper mapper = new ObjectMapper();
        resp.getWriter().write(mapper.writeValueAsString(res));
        int status = res.getCode().equals(Constants.SUCCES_CODE) ? HttpServletResponse.SC_OK
                : HttpServletResponse.SC_BAD_REQUEST;
        resp.setStatus(status);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Helper.fixHeaders(resp);
        resp.setContentType("application/json");
        Resultat res = TaskService.deleteTask(req);
        ObjectMapper mapper = new ObjectMapper();
        resp.getWriter().write(mapper.writeValueAsString(res));
        int status = res.getCode().equals(Constants.SUCCES_CODE) ? HttpServletResponse.SC_OK
                : HttpServletResponse.SC_BAD_REQUEST;
        resp.setStatus(status);
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Helper.fixHeaders(resp);
        resp.setStatus(HttpServletResponse.SC_ACCEPTED);
    }
}
