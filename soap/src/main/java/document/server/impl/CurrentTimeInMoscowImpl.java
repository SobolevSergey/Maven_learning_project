package document.server.impl;

import document.server.CurrentTimeInMoscowService;

import javax.jws.WebService;
import java.util.Date;

//Service Implementation
@WebService(endpointInterface = "document.server.CurrentTimeInMoscowService")
public class CurrentTimeInMoscowImpl implements CurrentTimeInMoscowService {

    @Override
    public Date getDateAndTime() {
        return new Date();
    }

    @Override
    public String repeatString(String input) {
        return input;
    }
}
