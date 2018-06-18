package user;

import model.User;
import service.UserService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

@WebServlet(urlPatterns = "/edit/*",loadOnStartup = 2, asyncSupported = true)
public class EditFormUserServlet extends HttpServlet {
    private UserService userService;

    @Inject
    public EditFormUserServlet(@Named("baseUserService") UserService userService) {
        this.userService = userService;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String loginParam = request.getParameter("login");
        if(loginParam != null)
        {
          request.setAttribute("user", userService.getById(loginParam));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/editUser.jsp");
        dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    public void init(ServletConfig config) {
        System.out.println("EditUser servlet has been initialized");
    }
}
