package ws.document.server;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.Date;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT,use = SOAPBinding.Use.LITERAL)
public interface CurrentTimeInMoscowService {

    @WebMethod
    Date getDateAndTime();

    @WebMethod
    String repeatString(String input);
}
