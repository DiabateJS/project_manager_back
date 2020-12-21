package contollers;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Project;
import models.User;
import services.ProjectService;
import services.UserService;
import utils.Helper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name="usersServlet", urlPatterns = "/users")
public class UsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Helper.fixHeaders(resp);
        resp.setContentType("application/json");
        List<User> users = UserService.getAllUsers();
        ObjectMapper mapper = new ObjectMapper();
        resp.getWriter().write(mapper.writeValueAsString(users));
    }
}
