package user;

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

@WebServlet(urlPatterns = "/users/", loadOnStartup = 1,
        asyncSupported = true)
public class ListUserServlet extends HttpServlet {
    private UserService userService;

    @Inject
    public ListUserServlet(@Named("baseUserService") UserService userService) {
        this.userService = userService;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        //response.getWriter().println("Hello");
        request.setAttribute("users", userService.getAll());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }

    public void init(ServletConfig config) {
        System.out.println("ListUser servlet has been initialized");
    }
}
