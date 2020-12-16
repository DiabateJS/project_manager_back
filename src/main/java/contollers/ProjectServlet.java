package contollers;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Project;
import services.ProjectService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="projectServlet",urlPatterns = "/project")
public class ProjectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin","*");
        resp.setContentType("application/json");
        int idProject = Integer.parseInt(req.getParameter("id"));
        Project project = ProjectService.getProjectById(idProject);
        ObjectMapper mapper = new ObjectMapper();
        resp.getWriter().write(mapper.writeValueAsString(project));
    }
}
