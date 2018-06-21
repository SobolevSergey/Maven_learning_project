package weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.UserDto;
import model.User;
import service.UserService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import java.io.IOException;

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
        System.out.println("List of users has been requested");
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
        System.out.println(String.format("User %s has been requested", id));
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonInString = mapper.writeValueAsString(userService.getById(id));
            return Response.ok(jsonInString, MediaType.APPLICATION_JSON).build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @POST
    @Path("/update")
    @Consumes("application/json")
    public Response createProductInJSON(UserDto userDto) {

        String result = "User edited : " + userDto;
        System.out.println(result);
        userService.update(convertDtotoEntity(userDto));
        return Response.status(201).entity(result).build();
    }

    private User convertDtotoEntity(UserDto dto) {
        ObjectMapper mapper = new ObjectMapper();
        User result = null;
        try {
            String paramsString = mapper.writeValueAsString(dto);
            result = mapper.readValue(paramsString, User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
