package ru.avalon.javapp.devj110.doubledirlist;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleDirList<T> implements Iterable<T>{

    private ListItem<T> head;
    private ListItem<T> tail;

    // добавление значения в начало списка
    public void addToHead(T value) {
        ListItem<T> newItem = new ListItem<>(value);
        if (! isEmpty()) {
            head.prev = newItem;
            newItem.next = head;
            head = newItem;
        } else
            head = tail = newItem;
    }

    // извлечение значения из начала списка без его удаления из списка
    public T peekFromHead() {
        return ! isEmpty() ? head.value : null;
    }

    // извлечение значения из начала списка с удалением из списка
    public T removeFromHead() {
        if (isEmpty())
            return null;
        ListItem<T> res = head;
        if (head != tail) {
            head = head.next;
            res.next = null;
            head.prev = null;
        }
        else
            head = tail = null;
        return res.value;
    }

    // добавление значения в конец списка
    public void addToTail(T value) {
        ListItem<T> newItem = new ListItem<>(value);
        if (! isEmpty()) {
            tail.next = newItem;
            newItem.prev = tail;
            tail = tail.next;
        } else
            head = tail = newItem;
    }


    // извлечение значения из конца списка без его удаления
    public T peekFromTail() {
        return tail != null ? tail.value : null;
    }

    // извлечение значения из конца списка с удалением
    public T removeFromTail() {
        if (isEmpty())
            return null;
        ListItem<T> res = tail;
        if (head != tail) {
            tail = tail.prev;
            tail.next = null;
            res.prev = null;
        } else
            head = tail = null;
        return res.value;
    }

    // определение, содержит ли список заданное значение, или нет
    public boolean contains(T value) {
        ListItem<T> it = head;
        while (it != null) {
            if(it.checkValue(value)) return true;
            it = it.next;
        }
        return false;
    }

    // определение, является ли список пустым, или нет
    public boolean isEmpty() {
        return head == null;
    }



    // *удаление заданного значения из списка; если значения в списке нет, то ничего происходить не должно
    public void remove(T value) {
        if (isEmpty())
            return;
        if (head.checkValue(value)) {
            removeFromHead();
            return;
        }
        ListItem<T> it = head.next;
        while (it != null) {
            if (it.checkValue(value)) {
                if (it == tail)
                    removeFromTail();
                else {
                    it.prev.next = it.next;
                    it.next.prev = it.prev;
                    it.next = it.prev = null;
                }
                return;
            }
            it = it.next;
        }

    }

    // добавление всех значений заданного массива в начало списка; порядок значений должен сохраняться
    // — первое значение массива должно стать первым значением списка
    public void addArrayInHead(T[] values) {
        if (values == null)
            throw new IllegalArgumentException("Values is can't be null");
        for (int i = values.length; i> 0; i--) {
            addToHead(values[i-1]);
        }
    }

    // добавление всех значений заданного массива в конец списка; порядок значений должен сохраняться
    // — первое значение массива должно стать первым значением списка
    public void addArrayInTail(T[] values) {
        if (values == null)
            throw new IllegalArgumentException("Values is can't be null");
        for (int i=0; i< values.length; i++) {
            addToTail(values[i]);
        }
    }

    //поглощение списка другим списком с добавлением значений второго в начало
    public void absorbingListToHead(DoubleDirList list){
        list.tail.next = head;
        head.prev = list.tail;
        head = list.head;
        list.head = list.tail = null;
    }

    //поглощение списка другим списком с добавлением значений второго в конец
    public void absorbingListToTail(DoubleDirList list){
        list.head.prev = tail;
        tail.next = list.head;
        tail = list.tail;
        list.head = list.tail = null;
    }

    public void forEach(Consumer<? super T> consumer) {
        ListItem<T> it = head;
        while (it != null) {
            consumer.accept(it.value);
            it = it.next;
        }
    }


    // печать всех значений списка
    public void printAll() {
        ListItem<T> it = head;
        while (it != null) {
            System.out.println(it.value);
            it = it.next;
        }
    }

    // печать всех значений списка в обратном порядке
    public void printAllRevers() {
        ListItem<T> it = tail;
        while (it != null) {
            System.out.println(it.value);
            it = it.prev;
        }
    }

    // выполнение действия, заданного в параметре метода, для каждого значения из списка
    public String printAll(String str) {
        ListItem<T> it = head;
        int i = 1;
        while (it != null) {
            System.out.println(str + i + ": " + it.value);
            it = it.next;
            i++;
        }
        return "";
    }

    // печать всех значений списка в обратном порядке
    public String printAllRevers(String str) {
        ListItem<T> it = tail;
        int i = 1;
        while (it != null) {
            System.out.println(str + i + ": " + it.value);
            it = it.prev;
            i++;
        }
        return "";
    }

    private ListItem<T> findItem(T value) {
        ListItem<T> it = head;
        while(it != null) {
            if(it.checkValue(value))
                return it;
            it = it.next;
        }
        return null;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator<>(head);
    }

    public Iterable<T> after(T val) {
        return () -> new ListIterator<>(findItem(val).next);
    }

    public Iterable<T> before(T val) {
        return () -> new BeforeListIterator<>(head, val);
    }

    public Iterable<T> between(T val1, T val2) {
        return () -> new BeforeListIterator<>(findItem(val1).next, val2);
    }

    public Iterable<T> reverse() {
        return () -> new AfterListIterator<>(tail, null);
    }

    public Iterable<T> reverseAfter(T val) {
        return () -> new AfterListIterator<>(tail, val);
    }

    public Iterable<T> reverseBefore(T val) {
        return () -> new AfterListIterator<>(findItem(val).prev, null);
    }

    public Iterable<T> reverseBetween(T val1, T val2) {
        return () -> new AfterListIterator<>(findItem(val2).prev, val1);
    }


    private static class ListItem<V> {
        V value;
        ListItem<V> next;
        ListItem<V> prev;

        public ListItem(V value) {
            this.value = value;
        }

        boolean checkValue(V value) {
            return value == null & this.value == null || value != null && value.equals(this.value);
        }
    }


    private static class ListIterator<V> implements Iterator<V> {
        private ListItem<V> nextNode;

        public ListIterator(ListItem<V> startNode) {
            this.nextNode = startNode;
        }

        @Override
        public boolean hasNext() {
            return nextNode != null;
        }

        @Override
        public V next() {
            if(nextNode == null)
                throw new NoSuchElementException();
            V res = nextNode.value;
            nextNode = nextNode.next;
            return res;
        }
    }

    private static class BeforeListIterator<V> implements Iterator<V> {
        private ListItem<V> nextNode;
        private V finishVal;

        public BeforeListIterator(ListItem<V> startNode, V finishVal) {
            this.nextNode = startNode;
            this.finishVal = finishVal;
        }

        @Override
        public boolean hasNext() {
            return nextNode != null && !nextNode.checkValue(finishVal);
        }

        @Override
        public V next() {
            if (nextNode == null || nextNode.checkValue(finishVal))
                throw new NoSuchElementException();
            V res = nextNode.value;
            nextNode = nextNode.next;
            return res;
        }
    }

        private static class AfterListIterator<V> implements Iterator<V> {
            private ListItem<V> nextNode;
            private V finishVal;

            public AfterListIterator(ListItem<V> startNode, V finishVal) {
                this.nextNode = startNode;
                this.finishVal = finishVal;
            }

            @Override
            public boolean hasNext() {
                return nextNode != null && !nextNode.checkValue(finishVal);
            }

            @Override
            public V next() {
                if (nextNode == null || nextNode.checkValue(finishVal))
                    throw new NoSuchElementException();
                V res = nextNode.value;
                nextNode = nextNode.prev;
                return res;
            }
        }
}
