package com.javarush.test.level15.lesson12.home05;

public class SubSolution extends Solution {
    public SubSolution(long l) {
        super(l);
    }

    public SubSolution(int i) {
        super(i);
    }

    public SubSolution(char c) {
        super(c);
    }

    private SubSolution(Long l){super(l);}
    private SubSolution(Integer i){super(i);}
    private SubSolution(Character c){super(c);}

    protected SubSolution(long[] l) {
        super(l);
    }

    protected SubSolution(int[] i) {
        super(i);
    }

    protected SubSolution(char[] c) {
        super(c);
    }

    SubSolution() {
    }

    SubSolution(Integer[] i) {
        super(i);
    }

    SubSolution(Character[] c) {
        super(c);
    }
}
