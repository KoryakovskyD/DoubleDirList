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



        // содержит ли список значение
        System.out.println("содержит ли список значение");
        if (lst.contains("666"))
            System.out.println("Содержит");
        if(!lst.contains("BBB"))
            System.out.println("Не содержит");
        System.out.println();


        // удаление элементов в начале и конце
        System.out.println("удаление элементов в начале и конце");
        lst.removeFromHead();
        lst.removeFromTail();
        lst.printAll();
        System.out.println();


        // удаление заданного элемента
        System.out.println("удаление заданного элемента");
        lst.remove("222");
        lst.remove("444");
        lst.printAll();
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
        System.out.println("печать массива в обратном порядке");
        lst.printAllRevers();
        System.out.println();

        // выполнение действия, заданного в параметре метода, для каждого значения из списка
        System.out.println("выполнение действия, заданного в параметре метода, для каждого значения из списка");
        Consumer<String> printer = x -> System.out.println(x.toUpperCase());
        printer.accept(lst.printAll("Строка"));
        System.out.println();

        // выполнение действия, заданного в параметре метода, для каждого значения из списка  в обратном порядке
        System.out.println("выполнение действия, заданного в параметре метода, для каждого значения из списка в обратном порядке");
        Consumer<String> printer1 = x -> System.out.println(x.toUpperCase());
        printer1.accept(lst.printAllRevers("Строка"));
        System.out.println();


        // поглощение списка другим списком с добавлением значений второго в начало
        // первого списка; после поглощения второй список должен очищаться;
        DoubleDirList lst1 = new DoubleDirList();





        // добавление элементов в начало и конец
        System.out.println("поглощение списка другим списком с добавлением значений второго в начало");
        lst1.addToHead("Head1");
        lst1.addToTail("Head2");
        lst1.addToTail("Head3");
        lst.absorbingListToHead(lst1);
        lst.printAll();
        System.out.println();


        DoubleDirList lst2 = new DoubleDirList();
        // поглощение списка другим списком с добавлением значений второго в конец
        //первого списка; после поглощения второй список должен очищаться;
        System.out.println("поглощение списка другим списком с добавлением значений второго в конец");
        lst2.addToHead("End1");
        lst2.addToTail("End2");
        lst2.addToTail("End3");
        lst.absorbingListToTail(lst2);

        lst.printAll();

        // проверка на пустоту списков
        System.out.println("проверка на пустоту списков");
        lst1.printAll();
        lst2.printAll();
        System.out.println();


        System.out.println("-------------------------------");
        DoubleDirList l1 = new DoubleDirList(),
                l2 = new DoubleDirList();

        l1.addToHead("111");
        l1.addToTail("444");
        l2.addToHead("222");

        l1.absorbingListToTail(l2);
        l1.printAllRevers();
        System.out.println("++");
        l1.printAll();


        System.out.println("-------------------------------");
        DoubleDirList l3 = new DoubleDirList(),
                l4 = new DoubleDirList();

        l3.addToHead("111");
        l3.addToTail("444");
        l4.addToHead("222");

        l3.absorbingListToHead(l4);
        l3.printAllRevers();
        System.out.println("++");
        l3.printAll();



    }
}
