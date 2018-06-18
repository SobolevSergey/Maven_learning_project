package com.testProject.workWithFiles;

import com.testProject.exception.FileCopyException;
import com.testProject.exception.FileUnzipException;
import com.testProject.exception.FileZipException;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class FileWorker {

    public static final String fileSeparator = System.getProperty("file.separator");

    public static boolean copyFile(String filePath, String destination) throws FileCopyException {
        File afile = new File(filePath);
        File bfile = new File(destination);
        if (!afile.exists()) {
            throw new FileCopyException(String.format("Incorrect source: %s", filePath));
        }
        try (InputStream inStream = new FileInputStream(afile); OutputStream outStream = new FileOutputStream(bfile)) {
            byte[] buffer = new byte[1024];
            int length;
            //copy the file content in bytes
            while ((length = inStream.read(buffer)) > 0) {
                outStream.write(buffer, 0, length);
            }
        } catch (IOException e) {
            throw new FileCopyException(String.format("Error while copying file: %s to %s", filePath, destination), e);
        }
        System.out.println("File is copied successful!");
        return bfile.exists();
    }

    public static File zipFile(String filePath, String destination, String archiveName) throws FileZipException {
        File fileToZip = new File(filePath);
        if (!fileToZip.exists()) {
            throw new FileZipException(String.format("Incorrect source %s", fileToZip.getName()));
        }
        String nameArchive = (archiveName == null || archiveName.isEmpty())
                ? (fileToZip.getName() + "Compressed")
                : archiveName;

        if (destination == null || destination.isEmpty()) {
            destination = "."+fileSeparator;
        }

        if (!destination.endsWith(fileSeparator)) {
            destination += fileSeparator;
        }
        String destinationFullName = destination + nameArchive + ".zip";
        try (FileOutputStream fos = new FileOutputStream(destinationFullName);
             ZipOutputStream zipOut = new ZipOutputStream(fos)) {
            zip(fileToZip, fileToZip.getName(), zipOut, destinationFullName);
            return new File(destinationFullName);
        } catch (Exception e) {
            throw new FileZipException(String.format("Error while zipping file %s", fileToZip.getName()), e);
        }
    }

    private static void zip(File fileToZip, String fileName, ZipOutputStream zipOut, String destinationFullName) throws IOException {
        if (fileToZip.isHidden()) {
            return;
        }
        if (fileToZip.isDirectory())
        {
            if(fileToZip.listFiles() != null
                    && fileToZip.listFiles().length > 0)
            {
                for (File childFile : fileToZip.listFiles()) {
                    if (!destinationFullName.equals(childFile.toString())) {
                        zip(childFile, fileName + fileSeparator + childFile.getName(), zipOut, childFile.toString());
                    }
                }
            }
            ZipEntry zipEntry = new ZipEntry(fileName+fileSeparator);
            zipOut.putNextEntry(zipEntry);
            return;
        }

        try (FileInputStream fis = new FileInputStream(fileToZip)) {
            ZipEntry zipEntry = new ZipEntry(fileName);
            zipOut.putNextEntry(zipEntry);
            byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
        }
    }

    public static File unzipFile(String filePath, String destination) throws FileUnzipException {
        byte[] buffer = new byte[1024];
        if (destination == null || destination.isEmpty()) {
            destination = "." + fileSeparator;
        }

        if (!destination.endsWith(fileSeparator)) {
            destination += fileSeparator;
        }


        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(filePath))) {
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null && !zipEntry.isDirectory()) {
                String fileName = zipEntry.getName();
                String fullDirectoryPath = destination + fileName;
                fullDirectoryPath = fullDirectoryPath.substring(0, fullDirectoryPath.lastIndexOf(fileSeparator));
                File resultDir = new File(fullDirectoryPath);
                boolean fileCreated = resultDir.mkdirs();
                if (fileCreated) {
                    System.out.printf("Created directories for path: %s\n", fullDirectoryPath);
                }
                File newFile = new File(destination + fileName);
                FileOutputStream fos = new FileOutputStream(newFile);
                int length;
                while ((length = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, length);
                }
                fos.close();
                zipEntry = zis.getNextEntry();
            }
        } catch (IOException e) {
            throw new FileUnzipException(String.format("Error while unzipping file %s", filePath), e);
        }
        return new File(destination);
    }
}
