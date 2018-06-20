package document.client;

import document.server.CurrentTimeInMoscowService;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class CurrentTimeInMoscowClient {

    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:9991/ws/time?wsdl");

        //1st argument service URI, refer to wsdl document above
        //2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://impl.server.document.ws/", "CurrentTimeInMoscowImplService");

        Service service = Service.create(url, qname);

        CurrentTimeInMoscowService timeInMoscowService = service.getPort(CurrentTimeInMoscowService.class);

        Date result = timeInMoscowService.getDateAndTime();
        System.out.println(result);
        System.out.println(timeInMoscowService.repeatString("REPEAT ME!"));

    }
}
