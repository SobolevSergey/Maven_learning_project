package com.testProject.customClassLoader;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class MyClassLoader extends ClassLoader {

    /**
     * Loads a given class from .class file just like
     * the default ClassLoader. This method could be
     * changed to load the class over network from some
     * other server or from the database.
     *
     * @param name Full class name
     */
    private Class<?> getClass(String name)
            throws ClassNotFoundException {
        // We are getting a name
        // and we have to convert it into the .class file name
        // like project/package/ClassToLoad.class
        String file = name.replace('.', File.separatorChar)
                + ".class";
        byte[] b;
        try {
            // This loads the byte code data from the file
            b = loadClassData(file);
            // defineClass is inherited from the ClassLoader class
            // and converts the byte array into a Class
            Class<?> c = defineClass(name, b, 0, b.length);
            if (c == null) {
                throw new ClassNotFoundException(String.format("Class %s not found", name));
            }
            resolveClass(c);
            return c;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Every request for a class passes through this method.
     * If the requested class is in "com.testProject.load" package,
     * it will load it using the
     * {@link MyClassLoader#getClass()} method.
     * If not, it will use the super.loadClass() method
     * which in turn will pass the request to the parent.
     *
     * @param name Full class name
     */
    @Override
    public Class<?> loadClass(String name)
            throws ClassNotFoundException {
        System.out.println("loading class '" + name + "'");
        Class<?> result;
        if (name.startsWith("com.testProject")) {
            result = getClass(name);
        } else {
            result = super.loadClass(name);
        }
        if (result != null && result.getClassLoader() != null) {
            System.out.printf("Class %s loaded by classloader: %s \n", result.getName(),
                    result.getClassLoader().getClass().getName());
        }
        return result;
    }

    /**
     * Loads a given file (presumably .class) into a byte array.
     * The file should be accessible as a resource, for example
     * it could be located on the classpath.
     *
     * @param name File name to load
     * @return Byte array read from the file
     * @throws IOException Is thrown when there
     *                     was some problem reading the file
     */
    private byte[] loadClassData(String name) throws IOException {
        // Opening the file

        byte buff[];
        try (InputStream stream = getClass().getClassLoader().getResourceAsStream(name);
             DataInputStream in = new DataInputStream(stream)) {
            int size = stream.available();
            buff = new byte[size];
            // Reading the binary data
            in.readFully(buff);
        }
        return buff;
    }
}
