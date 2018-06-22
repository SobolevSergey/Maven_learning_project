package weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.ImportedFileDto;
import dto.ManyUsersDto;
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
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.net.URL;

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
    public Response updateUser(UserDto userDto) {

        String result = "User edited : " + userDto;
        System.out.println(result);
        userService.update(convertDtotoEntity(userDto));
        return Response.status(201).entity(result).build();
    }

    @Path("/delete/{id}")
    @GET
    @Produces("application/json")
    public Response deleteUser(@Context Request request,
                               @PathParam("id") String id) {
        System.out.println(String.format("Delete user %s has been requested", id));
        boolean result = userService.deleteById(id);
        if (result) {
            return Response.ok(String.format("User %s deleted", id)).build();
        } else {
            return Response.ok(String.format("User %s not deleted", id)).build();
        }
    }

    @POST
    @Path("/import")
    @Consumes("application/json")
    public int importUsers(ImportedFileDto fileDto) {
        System.out.println("Importing USERS");
        int countOfImportedUsers = 0;
        File uploadedFile = new File("./uploaded/" + fileDto.getFileName());
        if (uploadedFile.exists()) {
            boolean isValid = false;
            try {
                URL schemaFile = new URL("http://proxy.external.example.com/usersToImport.xsd");
                SchemaFactory factory =
                        SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
                Schema schema = factory.newSchema(schemaFile);
                Validator validator = schema.newValidator();
                validator.validate(new StreamSource(uploadedFile));
                isValid = true;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            if (isValid) {
                ManyUsersDto users = null;
                try {
                    JAXBContext schoolContext = JAXBContext.newInstance(ManyUsersDto.class);
                    Unmarshaller unmarshaller = schoolContext.createUnmarshaller();
                    users = (ManyUsersDto) unmarshaller.unmarshal(uploadedFile);
                } catch (JAXBException e) {
                    e.printStackTrace();
                }
                if (users != null && users.getUsers() != null) {
                    for (UserDto user : users.getUsers()) {
                        if (userService.getById(user.getLogin()) == null) {
                            userService.save(convertDtotoEntity(user));
                            countOfImportedUsers++;
                        }
                    }
                }
            }
        }
        return countOfImportedUsers;
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
