package user;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

class RequestProcessor {
    static final String userRestServiceBaseUrl = "http://localhost:8080/rest/users/";

    static String sendGetRequestToUserRestService(String url) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

        connection.setRequestMethod("GET");
        if (connection.getResponseCode() != 200) {
            System.out.println(String.format("Error while sending GET request to \"%s\"", url));
        }
        return readResultFromBuffer(connection);
    }

    static String sendPostRequestToUserRestService(String url, Object params) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        connection.setRequestMethod("POST");

        ObjectMapper mapper = new ObjectMapper();
        String paramsString = mapper.writeValueAsString(params);

        OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
        wr.write(paramsString);
        wr.flush();

        return readResultFromBuffer(connection);
    }

    private static String readResultFromBuffer(HttpURLConnection connection) {
        StringBuilder responseString = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                responseString.append(line);
            }
        } catch (IOException ex) {
            System.out.println("Error while reading request: \n");
            ex.printStackTrace();
        }
        return responseString.toString();
    }
}
