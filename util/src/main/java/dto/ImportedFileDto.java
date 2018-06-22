package dto;

import java.io.Serializable;

public class ImportedFileDto implements Serializable {
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
