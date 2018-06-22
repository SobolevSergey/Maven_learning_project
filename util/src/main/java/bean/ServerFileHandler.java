package bean;

import javax.ejb.Remote;
import javax.ejb.Stateful;
import java.io.File;
import java.util.HashMap;

@Stateful(mappedName = "ServerFileHandler",name = "ServerFileHandlerName")
@Remote(IServerFileHandler.class)
public class ServerFileHandler implements IServerFileHandler {
    public static String uploadedFilesPath = "./uploaded/";

    private HashMap<String, String> loadedFilesMap = new HashMap<>();

    public void setFile(File file) {
        loadedFilesMap.put(file.getName(), file.getPath());
    }

    public File getFileByName(String name) {
        return new File(loadedFilesMap.get(name));
    }

    public void deleteFile(String fileName) {
        loadedFilesMap.remove(fileName);
    }
}
