package com.testProject.exception;

public class FileUnzipException extends Exception {

    public FileUnzipException() {
    }

    public FileUnzipException(String var1) {
        super(var1);
    }

    public FileUnzipException(String var1, Throwable var2) {
        super(var1, var2);
    }

    public FileUnzipException(Throwable var1) {
        super(var1);
    }

    protected FileUnzipException(String var1, Throwable var2, boolean var3, boolean var4) {
        super(var1, var2, var3, var4);
    }
}
