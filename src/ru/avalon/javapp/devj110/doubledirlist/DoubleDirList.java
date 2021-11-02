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
        Object res = head.value;
        if (head != tail)
            head = head.next;
        else
            head = tail = null;
        return res;
    }

    // добавление значения в конец списка
    public void addToTail(Object value) {
        ListItem newItem = new ListItem(value);
        if (! isEmpty()) {
            tail.next = newItem;
            newItem.prev = tail;
            tail = newItem;
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
        Object res = tail.value;
        if (head != tail) {
            ListItem it = tail;
            it.prev = tail;
            tail.next = null;
        } else
            head = tail = null;
        return res;
    }

    // определение, содержит ли список заданное значение, или нет
    public boolean contains(Object value) {
        ListItem it = head;
        while (it != null) {
            if(it.checkValue(value)) {
                System.out.println("prev=" + it.prev.value);
                return true;
            }
            it.prev = it;
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
        it.prev = head;
        while (it != null) {
            if (it.checkValue(value)) {
                if (it.equals(tail))
                    removeFromTail();
                else {
                    head.prev.next = it.next;
                    it.prev = head.prev;
                }
                return;
            }
            head.prev = it;
            it = it.next;
        }

    }

    // *выполнение действия, заданного в параметре метода, для каждого значения из списка.

    // добавление всех значений заданного массива в начало списка; порядок значений должен сохраняться
    // — первое значение массива должно стать первым значением списка
    public void addArrayInHead(Object[] values) {
        for (int i = values.length; i> 0; i--) {
            addToHead(values[i-1]);
        }
    }

    // добавление всех значений заданного массива в конец списка; порядок значений должен сохраняться
    // — первое значение массива должно стать первым значением списка
    public void addArrayInTail(Object[] values) {
        for (int i=0; i< values.length; i++) {
            addToTail(values[i]);
        }
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


}
