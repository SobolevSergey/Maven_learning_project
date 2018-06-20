package weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import service.UserService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

@Path("/users")
public class UserRestService {
    private UserService userService;

    public UserRestService() {

    }

    @Inject
    public UserRestService(@Named("baseUserService") UserService userService) {
        this.userService = userService;
    }

    @Path("/list")
    @GET
    @Produces("application/json")
    public Response getUserList(@Context Request request) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonInString = mapper.writeValueAsString(userService.getAll());
            return Response.ok(jsonInString, MediaType.APPLICATION_JSON).build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @Path("{id}")
    @GET
    @Produces("application/json")
    public Response getUser(@Context Request request,
                            @PathParam("id") String id) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonInString = mapper.writeValueAsString(userService.getById(id));
            return Response.ok(jsonInString, MediaType.APPLICATION_JSON).build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
}
