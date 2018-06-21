package user;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(urlPatterns = "/delete/*", loadOnStartup = 4,
        asyncSupported = true)
public class DeleteUserServlet extends HttpServlet {
  /*  private UserService userService;

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
    */

    public void init(ServletConfig config) {
        System.out.println("DeleteUser servlet has been initialized");
    }
}
