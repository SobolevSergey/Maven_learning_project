package rest.weather;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import java.text.SimpleDateFormat;
import java.util.Date;

@Path("/weather")
public class WeatherTestService {

    @GET
    @Produces("application/json")
    public Response getWeather(@Context Request request,
                               @DefaultValue("___") @QueryParam("city") String city,
                               @DefaultValue("___") @QueryParam("date") String dateString) {
        Weather weather = new Weather();
        weather.setCity("Дубна");
        weather.setDate(new Date());
        weather.setCaption("Зубодробительный холод и слякоть");
        weather.setMaxTemp("+1");

        JsonObjectBuilder objectBuilder = Json.createObjectBuilder()
                .add("city", weather.getCity())
                .add("caption", weather.getCaption())
                .add("maxTemp", weather.getMaxTemp())
                .add("date", new SimpleDateFormat()
                        .format(weather.getDate()));
        return Response.ok(objectBuilder.build(), MediaType.APPLICATION_JSON).build();
    }
}
