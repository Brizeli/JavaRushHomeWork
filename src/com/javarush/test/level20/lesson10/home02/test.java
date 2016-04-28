package com.javarush.test.level20.lesson10.home02;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class test {
    public static void main(String[] args) throws Exception{
        Solution sol=new Solution();
        Solution.A b=sol.new B();
        System.out.println(b.getClass().getName());
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        ObjectOutputStream os=new ObjectOutputStream(baos);
        os.writeObject(b);
        os.close();
        sol.getOriginalObject(new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray())));
    }
}
