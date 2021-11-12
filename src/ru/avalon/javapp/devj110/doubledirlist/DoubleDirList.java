package ru.avalon.javapp.devj110.doubledirlist;

public class DoubleDirList {

    private ListItem head;
    private ListItem tail;

    // добавление значения в начало списка
    public void addToHead(Object value) {
        ListItem newItem = new ListItem(value);
        if (! isEmpty()) {
            head.prev = newItem;
            newItem.next = head;
            head = newItem;
        } else
            head = tail = newItem;
    }

    // извлечение значения из начала списка без его удаления из списка
    public Object peekFromHead() {
        return ! isEmpty() ? head.value : null;
    }

    // извлечение значения из начала списка с удалением из списка
    public Object removeFromHead() {
        if (isEmpty())
            return null;
        ListItem res = head;
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
    public void addToTail(Object value) {
        ListItem newItem = new ListItem(value);
        if (! isEmpty()) {
            tail.next = newItem;
            newItem.prev = tail;
            tail = tail.next;
        } else
            head = tail = newItem;
    }


    // извлечение значения из конца списка без его удаления
    public Object peekFromTail() {
        return tail != null ? tail.value : null;
    }

    // извлечение значения из конца списка с удалением
    public Object removeFromTail() {
        if (isEmpty())
            return null;
        ListItem res = tail;
        if (head != tail) {
            tail = tail.prev;
            tail.next = null;
            res.prev = null;
        } else
            head = tail = null;
        return res.value;
    }

    // определение, содержит ли список заданное значение, или нет
    public boolean contains(Object value) {
        ListItem it = head;
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
    public void remove(Object value) {
        if (isEmpty())
            return;
        if (head.checkValue(value)) {
            removeFromHead();
            return;
        }
        ListItem it = head.next;
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
    public void addArrayInHead(Object[] values) {
        if (values == null)
            throw new IllegalArgumentException("Values is can't be null");
        for (int i = values.length; i> 0; i--) {
            addToHead(values[i-1]);
        }
    }

    // добавление всех значений заданного массива в конец списка; порядок значений должен сохраняться
    // — первое значение массива должно стать первым значением списка
    public void addArrayInTail(Object[] values) {
        if (values == null)
            throw new IllegalArgumentException("Values is can't be null");
        for (int i=0; i< values.length; i++) {
            addToTail(values[i]);
        }
    }

    //поглощение списка другим списком с добавлением значений второго в начало
    public void absorbingListToHead(DoubleDirList list){
        list.tail.next = head;
        head = list.head;
        list.head = list.tail = null;
        /*
        list.tail.next = head;
        head.prev = list.tail;
        list.tail = tail;
        head = tail = null;
         */
    }

    //поглощение списка другим списком с добавлением значений второго в конец
    public void absorbingListToTail(DoubleDirList list){
        tail.next = list.head;
        tail = list.tail;
        list.head.prev = tail.next;
        list.head = list.tail = null;
        /*
        list.head.prev = tail;
        tail.next = list.head;
        tail = list.tail;
        list.head = list.tail = null;

         */
    }


    // печать всех значений списка
    public void printAll() {
        ListItem it = head;
        while (it != null) {
            System.out.println(it.value);
            it = it.next;
        }
    }

    // печать всех значений списка в обратном порядке
    public void printAllRevers() {
        ListItem it = tail;
        while (it != null) {
            System.out.println(it.value);
            it = it.prev;
        }
    }

        // выполнение действия, заданного в параметре метода, для каждого значения из списка
    public String printAll(String str) {
        ListItem it = head;
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
        ListItem it = tail;
        int i = 1;
        while (it != null) {
            System.out.println(str + i + ": " + it.value);
            it = it.prev;
            i++;
        }
        return "";
    }


    private class ListItem {
        Object value;
        ListItem next;
        ListItem prev;

        public ListItem(Object value) {
            this.value = value;
        }

        boolean checkValue(Object value) {
            return value == null & this.value == null || value != null && value.equals(this.value);
        }
    }

}
