package com.testProject.exception;

public class FileCopyException extends Exception {

    public FileCopyException() {
    }

    public FileCopyException(String var1) {
        super(var1);
    }

    public FileCopyException(String var1, Throwable var2) {
        super(var1, var2);
    }

    public FileCopyException(Throwable var1) {
        super(var1);
    }

    protected FileCopyException(String var1, Throwable var2, boolean var3, boolean var4) {
        super(var1, var2, var3, var4);
    }
}
