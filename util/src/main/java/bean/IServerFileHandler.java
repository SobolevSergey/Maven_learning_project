package bean;

import javax.ejb.Remote;
import java.io.File;

@Remote
public interface IServerFileHandler {
    void setFile(File file);

    File getFileByName(String name);

    void deleteFile(String fileName);
}
