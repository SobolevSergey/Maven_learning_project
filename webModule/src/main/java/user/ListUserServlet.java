package user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.UserDto;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@WebServlet(urlPatterns = "/users/", loadOnStartup = 1,
        asyncSupported = true)
public class ListUserServlet extends HttpServlet {
   /* private UserService userService;

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

    */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<UserDto> result = Collections.emptyList();
        String responseString = RequestProcessor
                .sendGetRequestToUserRestService(RequestProcessor.userRestServiceBaseUrl + "list");
        ObjectMapper mapper = new ObjectMapper();
        try {
            result = Arrays.asList(mapper.readValue(responseString, UserDto[].class));
        } catch (JsonProcessingException e) {
            System.out.println("Error while processing response");
            e.printStackTrace();
        }

        request.setAttribute("users", result);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }

    public void init(ServletConfig config) {
        System.out.println("ListUser servlet has been initialized");
    }
}
