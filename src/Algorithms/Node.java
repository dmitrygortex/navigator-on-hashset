package Algorithms;

public class Node<T> {

    Node previous;
    Node next;
    T data;

    //basic node
    Node(T data, Node previous, Node next){
        this.previous = previous;
        this.next = next;
        this.data = data;
    }

    //single node
    Node(T data){
        this.previous = null;
        this.next = null;
        this.data = data;
    }
}