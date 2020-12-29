package contollers;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Resultat;
import services.Constants;
import services.UserService;
import utils.Helper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="userAuthServlet", urlPatterns = "/auth")
public class UserAuthServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Helper.fixHeaders(resp);
        resp.setContentType("application/json");
        Resultat res = UserService.isAuth(req);
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
