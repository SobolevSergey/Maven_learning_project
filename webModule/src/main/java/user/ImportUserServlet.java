package user;

import bean.ServerFileHandler;
import dto.ImportedFileDto;

import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = "/users/importuser", loadOnStartup = 5,
        asyncSupported = true)
public class ImportUserServlet extends HttpServlet {

    public ImportUserServlet() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("multiPartFile");
        String fileName = getFileName(filePart);
        if (fileName.endsWith(".xml")) {
            InputStream fileContent = filePart.getInputStream();
            byte[] buffer = new byte[fileContent.available()];
            fileContent.read(buffer);
            File destination = new File(ServerFileHandler.uploadedFilesPath + fileName);
            OutputStream outStream = new FileOutputStream(destination);
            outStream.write(buffer);
           // serverFileHandler.setFile(destination);

            ImportedFileDto fileDto = new ImportedFileDto();
            fileDto.setFileName(fileName);


            String resultString = RequestProcessor
                    .sendPostRequestToUserRestService(RequestProcessor.userRestServiceBaseUrl + "import", fileDto);

        }

        response.sendRedirect("/webModule/users/");
    }

    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().contains("filename"))
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
        }
        return null;
    }

    public void init(ServletConfig config) {
        System.out.println("ImportUser servlet has been initialized");
    }

}
