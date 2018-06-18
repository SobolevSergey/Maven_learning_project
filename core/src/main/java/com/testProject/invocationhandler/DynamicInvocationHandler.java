package com.testProject.invocationhandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class DynamicInvocationHandler implements InvocationHandler {

    private static Logger logger = Logger.getLogger("DynamicInvocationHandlerLogger");

    private Object target;

    private final Map<String, Integer> methodsInvocationCount = new HashMap<>();

    public DynamicInvocationHandler(Object target) {
        this.target = target;
    }


    @Override
    public Object invoke(Object o, Method method, Object[] args) {
        String methodName = method.getName();
        countMethodUsages(methodName);
        Object result = null;
        long start, elapsed;
        try {
            start = System.nanoTime();
            result = method.invoke(target, args);
            elapsed = System.nanoTime() - start;
            logger.info(String.format("Executing %s with params %s finished in %d ns", methodName, Arrays.toString(args),
                    elapsed));
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            throw new RuntimeException("unexpected invocation exception: " + ex.getMessage());
        }
        return result;
    }

    private void countMethodUsages(String methodName) {
        Integer count = methodsInvocationCount.get(methodName);
        if (count == null) {
            count = 0;
        }
        count++;
        methodsInvocationCount.put(methodName, count);
    }
}
