package contollers;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Project;
import models.Resultat;
import models.User;
import services.Constants;
import services.ProjectService;
import services.UserService;
import utils.Helper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="userServlet", urlPatterns = "/user")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Helper.fixHeaders(resp);
        resp.setContentType("application/json");
        int idUser = Integer.parseInt(req.getParameter("id"));
        User user = UserService.getUserById(idUser);
        ObjectMapper mapper = new ObjectMapper();
        resp.getWriter().write(mapper.writeValueAsString(user));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Helper.fixHeaders(resp);
        resp.setContentType("application/json");
        Resultat res = UserService.createUser(req);
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
        Resultat res = UserService.deleteUser(req);
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
        Resultat res = UserService.updateUser(req);
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
