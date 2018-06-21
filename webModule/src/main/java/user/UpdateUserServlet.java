package user;

import dto.UserDto;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@WebServlet(urlPatterns = "/update/*", loadOnStartup = 3, asyncSupported = true)
public class UpdateUserServlet extends HttpServlet {
    //    private UserService userService;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
/*
    @Inject
    public UpdateUserServlet(@Named("baseUserService") UserService userService) {
        this.userService = userService;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String birthDateString = request.getParameter("birthDate");
        Date birthDate = new Date();
        if (birthDateString != null) {
            try {
                birthDate = dateFormat.parse(birthDateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (Objects.nonNull(login) && Objects.nonNull(password) && Objects.nonNull(birthDate)) {
            User user = new User();
            user.setLogin(login);
            user.setPassword(password);
            user.setBirthDate(birthDate);
            user.setActive(true);
            userService.update(user);
        }
        response.sendRedirect("users/");
    }
    */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String birthDateString = request.getParameter("birthDate");
        Date birthDate = new Date();
        if (birthDateString != null) {
            try {
                birthDate = dateFormat.parse(birthDateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (Objects.nonNull(login) && Objects.nonNull(password) && Objects.nonNull(birthDate)) {
            UserDto user = new UserDto();
            user.setLogin(login);
            user.setPassword(password);
            user.setBirthDate(birthDate);
            user.setActive(true);
            String resultString = RequestProcessor
                    .sendPostRequestToUserRestService(RequestProcessor.userRestServiceBaseUrl + "update", user);
        }
        response.sendRedirect("users/");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public void init(ServletConfig config) {
        System.out.println("UpdateUser servlet has been initialized");
    }
}
