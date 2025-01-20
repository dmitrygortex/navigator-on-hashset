package Algorithms;


import java.util.Comparator;
import java.util.Iterator;

public class LinkedList<T> implements Iterable<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public LinkedList() {
        this.head = null;
        this.tail = null;
    }

    public LinkedList(T[] elements) {
        this();
        for (T element : elements) {
            addToTail(element);
            size++;
        }
    }

    public void addToTail(T data) {
        Node<T> newNode = new Node<>(data);
        size++;
        if (head == null) {
            head = newNode;
            tail = newNode;
        }
        else {
            newNode.previous = tail;
            tail.next = newNode;
            tail = newNode;
        }
    }

    public void addToHead(T data) {
        Node<T> newNode = new Node<>(data);
        size++;
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.previous = newNode;
            head = newNode;
        }
    }

    public T deleteFromHead() {
        size--;
        if (head == null) {
            return null;
        } else {
            T data = head.data;
            head = head.next;
            if (head != null) {
                head.previous = null;
            } else {
                tail = null;
            }
            return data;
        }
    }

    public T deleteFromTail() {
        size--;
        if (tail == null) {
            return null;
        } else {
            T data = tail.data;
            tail = tail.previous;
            if (tail != null) {
                tail.next = null;
            } else {
                head = null;
            }
            return data;
        }
    }

    public T replaceFirst(T oldData, T newData) {
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(oldData)) {
                T old = current.data;
                current.data = newData;
                return old;
            }
            current = current.next;
        }
        return null;
    }

    public T getW(T data) {
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(data)) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    public int getSize() {
        return size;
    }

    public T getHead() {
        if (head == null) {
            return null;
        } else {
            return head.data;
        }
    }

    public T getTail() {
        if (tail == null) {
            return null;
        } else {
            return tail.data;
        }
    }

    public void sortByComparator(Comparator<T> comparator) {
        Node<T> current = head;
        while (current!= null) {
            Node<T> next = current.next;
            while (next!= null) {
                if (comparator.compare(current.data, next.data) > 0) {
                    T temp = current.data;
                    current.data = next.data;
                    next.data = temp;
                }
                next = next.next;
            }
            current = current.next;
        }
    }

    @Override
    public String toString() {
        Node<T> current = head;
        String s = "";
        while (current != null) {
            s += current.data + " ";
            current = current.next;
        }
        return s;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (getSize() / 2 > index){
            Node<T> current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current.data;
        }
        else{
            //System.out.println("TEST PP");
            Node<T> current = tail;
            for (int i = getSize(); i > index; i--) {
                current = current.previous;
            }
            return current.data;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<T> {
        private Node<T> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T data = current.data;
            current = current.next;
            return data;
        }
    }

}
