package ru.avalon.javapp.devj110.doubledirlist;

public class DoubleDirList {

    private ListItem head;
    private ListItem tail;

    // добавление значения в начало списка
    public void addToHead(Object value) {
        if (! isEmpty()) {
            ListItem newItem = new ListItem(value);
            newItem.next = head;
            newItem.prev = null;
            head = newItem;
        } else
            head = tail = new ListItem(value);
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
        if (tail != null) {
            tail.next = new ListItem(value);
            tail = tail.next;
        } else
            head = tail = new ListItem(value);
    }


    public boolean isEmpty() {
        return head == null;
    }

    // печать всех значений списка
    public void printAll() {
        ListItem it = head;
        while (it != null) {
            System.out.println(it.value);
            it = it.next;
        }
    }



    private static class ListItem {
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
