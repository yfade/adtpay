package com.pay.test;

public class Test {
    private static AbcModel abcModel;

    /*public Test(AbcModel abcModel) {
        this.abcModel = abcModel;
    }*/

    public void testAbc(){
        int a=abcModel.getNum();
    }

    public static void main(String[] args) {
        abcModel.getNum();
    }
}
