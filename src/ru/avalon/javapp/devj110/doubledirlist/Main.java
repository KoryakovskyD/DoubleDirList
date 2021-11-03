package ru.avalon.javapp.devj110.doubledirlist;

public class Main {
    public static void main(String[] args) {
        DoubleDirList lst = new DoubleDirList();

        // добавление элементов в начало и конец
        System.out.println("добавление элементов в начало и конец");
        lst.addToHead("222");
        lst.addToTail("333");
        lst.addToHead("111");
        lst.addToTail("444");
        lst.addToTail("555");
        lst.addToTail("666");
        lst.addToTail("777");
        lst.addToHead("AAA");
        lst.addToHead("DDD");
        lst.printAll();
        System.out.println();

        // удаление элементов в начале и конце
        System.out.println("удаление элементов в начале и конце");
        lst.removeFromHead();
        lst.removeFromTail();
        lst.printAll();
        System.out.println();


        // содержит ли список значение
        System.out.println("содержит ли список значение");
        if (lst.contains("666"))
            System.out.println("Содержит");
        if(!lst.contains("BBB"))
            System.out.println("Не содержит");
        System.out.println();


        // удаление заданного элемента
        System.out.println("удаление заданного элемента");
        lst.remove("222");
        lst.remove("444");
        lst.printAll();
        System.out.println();


        // печать массива в обратном порядке
        System.out.println("печать массива в обратном порядке");
        lst.printAllRevers();
        System.out.println();


        // добавление массива в начало
        System.out.println("добавление массива в начало");
        lst.addArrayInHead(new String[] {"first", "second","third"});
        lst.printAll();
        System.out.println();

        // добавление массива в конец
        System.out.println("добавление массива в конец");
        lst.addArrayInTail(new String[] {"first_end", "second_end","third_end"});
        lst.printAll();
        System.out.println();

        // печать массива в обратном порядке
        //lst.printAllRevers();

    }
}
