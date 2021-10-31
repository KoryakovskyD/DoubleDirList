package ru.avalon.javapp.devj110.doubledirlist;

public class Main {
    public static void main(String[] args) {
        DoubleDirList lst = new DoubleDirList();
        lst.addToHead("111");
        lst.addToTail("333");
        lst.addToTail("222");
        lst.addToTail("444");
        lst.printAll();
        System.out.println();

        /*
        lst.addToHead("AAA");
        lst.printAll();
        System.out.println();

        lst.remove("222");
        lst.remove("444");
        lst.printAll();
        System.out.println();

        lst.removeFromHead();
        lst.removeFromTail();
        lst.printAll();
        System.out.println();

         */
    }
}
