package com.javarush.test.level20.lesson10.home03;

import java.io.*;

public class test {
    public static void main(String[] args) throws Exception{
        Solution sol=new Solution();
        Solution.A b = sol.new B("B");
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        ObjectOutputStream os=new ObjectOutputStream(baos);
        os.writeObject(b);
        os.close();
        ObjectInputStream is = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
        Solution.A bb = (Solution.B)is.readObject();
    }
}
