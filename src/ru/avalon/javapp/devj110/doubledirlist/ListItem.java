package ru.avalon.javapp.devj110.doubledirlist;

public class ListItem {
    Object value;
    public ListItem next;
    public ListItem prev;

    public ListItem(Object value) {
        this.value = value;
    }

    boolean checkValue(Object value) {
        return value == null & this.value == null || value != null && value.equals(this.value);
    }
}
