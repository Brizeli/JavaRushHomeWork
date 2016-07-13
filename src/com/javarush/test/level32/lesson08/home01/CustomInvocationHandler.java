package com.javarush.test.level32.lesson08.home01;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Next on 10.05.2016.
 */
public class CustomInvocationHandler implements InvocationHandler{
    SomeInterfaceWithMethods interfaceWithMethods;

    public CustomInvocationHandler(SomeInterfaceWithMethods interfaceWithMethods) {
        this.interfaceWithMethods = interfaceWithMethods;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        System.out.println(methodName +" in");
        Object invoke = method.invoke(interfaceWithMethods, args);
        System.out.println(methodName +" out");
        return invoke;
    }
}
