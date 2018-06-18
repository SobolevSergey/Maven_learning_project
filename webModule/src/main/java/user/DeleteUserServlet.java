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

@WebServlet(urlPatterns = "/delete/*", loadOnStartup = 4,
        asyncSupported = true)
public class DeleteUserServlet extends HttpServlet {
    private UserService userService;

    @Inject
    public DeleteUserServlet(@Named("baseUserService") UserService userService) {
        this.userService = userService;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String loginParam = request.getParameter("login");
        if(loginParam != null)
        {
            userService.deleteById(loginParam);
        }
        response.sendRedirect("users/");
    }

    public void init(ServletConfig config) {
        System.out.println("DeleteUser servlet has been initialized");
    }
}
