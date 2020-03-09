package com.minhaz.dsa;

public class LinkedListCustom {

    public Node head;
    public Node tail;
    public int size;

    public class Node{
        public int val;
        public Node next;
        public Node(int val){
            this.val = val;
            this.next = null;
        }
    }

}
