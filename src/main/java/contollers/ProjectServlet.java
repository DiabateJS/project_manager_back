package contollers;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Project;
import models.Resultat;
import services.Constants;
import services.ProjectService;
import utils.Helper;

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
        Helper.fixHeaders(resp);
        resp.setContentType("application/json");
        int idProject = Integer.parseInt(req.getParameter("id"));
        Project project = ProjectService.getProjectById(idProject);
        ObjectMapper mapper = new ObjectMapper();
        resp.getWriter().write(mapper.writeValueAsString(project));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Helper.fixHeaders(resp);
        resp.setContentType("application/json");
        Resultat res = ProjectService.createProject(req);
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
        Resultat res = ProjectService.deleteProject(req);
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
        Resultat res = ProjectService.updateProject(req);
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
