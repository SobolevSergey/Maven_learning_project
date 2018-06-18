package com.testProject;

import com.testProject.base.classes.FirstLessonObject;
import com.testProject.base.interfaces.HasObjectFullInfo;
import com.testProject.customClassLoader.MyClassLoader;
import com.testProject.exception.FileCopyException;
import com.testProject.exception.FileUnzipException;
import com.testProject.exception.FileZipException;
import com.testProject.exception.ProcessLoadClassException;
import com.testProject.invocationhandler.DynamicInvocationHandler;
import com.testProject.workWithFiles.FileWorker;
import org.junit.internal.builders.JUnit4Builder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

public class Main {

    public static void main(String[] args) throws ProcessLoadClassException {
        // Урок 1: Простая программа по созданию в цикле набора объектов и вывода ее на экран в консоли.
        // Работа со строками, работа с массивами
        FirstLessonObject[] objects = new FirstLessonObject[10];
        for (int i = 0; i < objects.length; i++) {
            FirstLessonObject object = new FirstLessonObject("Object_" + i);
            objects[i] = object;
            System.out.println(String.format("%s created!", object.getName()));
        }

        // Урок 3: использование анонимных классов
        HasObjectFullInfo anonymousObjectInfo = () -> "I have no info about any object";
        System.out.printf("\nGet info from anonymus object. result: %s\n", anonymousObjectInfo.getObjectFullInfo());

        // Урок 3: Создание своего classloader. Загрузка и работа с классами через свой classloader.
        MyClassLoader loader = new MyClassLoader();
        Class<?> loadedClass;
        try {
            loadedClass = loader.loadClass("com.testProject.load.ClassToLoad");
            Object instance = loadedClass.newInstance();
            System.out.println("\nReading methods of loaded class:");
            // Урок 6 Программа reflection динамический вызов метода, распечатка методов класса.
            for (Method m : instance.getClass().getDeclaredMethods()) { //getMethods вернет методы родителей
                System.out.printf("method name:%s, return type: %s, parameter count:%s\n",
                        m.getName(), m.getReturnType(), m.getParameterCount());
            }
            System.out.println("All methods are read\n");
            System.out.println("Executing his methods\n");
            loadedClass.getMethod("sayHelloFiveTimes", String.class).invoke(instance, "MyClass");
            loadedClass.getMethod("saySomething", String.class).invoke(null, "Text1234556");
            System.out.println("Executing ended\n");
        } catch (Exception e) {
            // Урок 5: Программа с демонстрацией работы с исключениями. Построение цепочки исключений.
            throw new ProcessLoadClassException("Ошибка при работе с загружаемыми классами", e);
        }


        Map<String, Integer> numberMap = new HashMap<>();
        ArrayList<Integer> numberArrayList = new ArrayList<>();
        LinkedList<Integer> numberLinkedList = new LinkedList<>();
        Stack<Integer> numberStack = new Stack<>();
        Queue<Integer> numberQueue = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            numberMap.put(String.valueOf(i), i);
            numberArrayList.add(i);
            numberLinkedList.addLast(i);
            numberStack.push(i);
            numberQueue.offer(i);
        }
        //---------------------Queue

        System.out.println("Queue:");
        while (numberQueue.size() > 0) {
            System.out.print(numberQueue.poll() + " ");
        }
        //-----------------------Stack

        System.out.println("\nStack:");
        while (numberStack.size() > 0) {
            System.out.print(numberStack.pop() + " ");
        }
        //-----------------------LinkedList

        System.out.println("\nOriginal contents of linkedList: " + numberLinkedList);
        // Remove elements from the linked list.
        numberLinkedList.remove(7);
        numberLinkedList.remove(2);
        System.out.println("Contents of list after deletion: "
                + numberLinkedList);
        // Remove first and last elements.
        numberLinkedList.removeFirst();
        numberLinkedList.removeLast();
        System.out.println("list after deleting first and last: "
                + numberLinkedList);
        // Get and set a value.
        Integer val = numberLinkedList.get(2);
        numberLinkedList.set(2, val + 9);
        System.out.println("list after change: " + numberLinkedList);

        // ---------------ArrayList

        System.out.println("\nContents of arrayList: " + numberArrayList);
        numberArrayList.remove(9);
        numberArrayList.remove(3);
        System.out.println("Size of arrayList after deletions: "
                + numberArrayList.size());
        System.out.println("Contents of arrayList: " + numberArrayList);
        List<Integer> removeElements = new ArrayList<>();
        removeElements.add(1);
        removeElements.add(5);
        removeElements.add(0);
        numberArrayList.removeAll(removeElements);
        System.out.println("Size of arrayList after removeAll: "
                + numberArrayList.size());
        System.out.println("Contents of arrayList after removeAll: "
                + numberArrayList);

        System.out.print("\nClass PATH: ");
        System.out.println(System.getProperty("java.class.path"));
        System.out.println("Class PATH END\n");


        System.out.println(JUnit4Builder.class.getName());

        //Чтение из конфига
        Properties prop = new Properties();
        String filename = "core.properties";
        try (InputStream input = Main.class.getClassLoader().getResourceAsStream(filename)) {
            if (input == null) {
                System.out.println("Unable to find " + filename);
                return;
            }
            //load a properties file from class path, inside static method
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(prop.getProperty("mainProjectProperty"));

        // Урок 6: Программа использование proxy класса для подмены реализации класса, лог вызовов к методам класса.
        Map mapProxyInstance = (Map) Proxy.newProxyInstance(
                Main.class.getClassLoader(), new Class[]{Map.class},
                new DynamicInvocationHandler(new HashMap<>()));
        mapProxyInstance.put("hello", "world");

        // Урок 6: Программа копирования файла
        String filePath = "./resources/originalFile.txt";
        String destination = "./resources/destinationFile.txt";
        try {
            FileWorker.copyFile(filePath, destination);
        } catch (FileCopyException e) {
            e.printStackTrace();
        }

        // Урок 6: Программа архивирования файлов с использованием потоков
        String dirPathToZip = "./resources";
        String filePathToZip = "./resources/originalFile.txt";
        String emptyDirectory = "./resources/testEmptyDir";
        File zippedDir = null;
        File zippedFile = null;
        File zippedEmptyDir = null;
        try {
            zippedDir = FileWorker.zipFile(dirPathToZip, "./resources", "dirArch");
            zippedFile = FileWorker.zipFile(filePathToZip, null, null);
         //   zippedEmptyDir = FileWorker.zipFile(emptyDirectory, null, null);
        } catch (FileZipException e) {
            e.printStackTrace();
        }
        File unzippedDir = null;
        File unzippedFile = null;
        // Разархивирование
        if (zippedDir != null) {
            try {
                unzippedDir = FileWorker.unzipFile(zippedDir.getPath(), "./unzippedArchives");
                System.out.printf("Unzipped directory is able by path:%s\n", unzippedDir.getPath());
            } catch (FileUnzipException e) {
                e.printStackTrace();
            }
        }
        if (zippedFile != null) {
            try {
                unzippedFile = FileWorker.unzipFile(zippedFile.getPath(), "./unzippedArchives");
                System.out.printf("Unzipped file is able by path:%s\n", unzippedFile.getPath());
            } catch (FileUnzipException e) {
                e.printStackTrace();
            }
        }
        if (zippedEmptyDir != null) {
            try {
                FileWorker.unzipFile(zippedEmptyDir.getPath(), "./unzippedArchives");
            } catch (FileUnzipException e) {
                e.printStackTrace();
            }
        }

    }
}
