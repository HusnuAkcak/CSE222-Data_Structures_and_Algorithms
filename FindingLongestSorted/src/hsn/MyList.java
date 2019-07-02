package hsn;

import java.util.Objects;

public class MyList {

    private Node head, tail; // head and tail of list
    private int length=0;

    /**
     * No parameter constructor.
     */
    public MyList(){/*intentionally empty*/}

    /**
     * Takes array of integers and initialize the list.
     * @param arr content of list
     */
    public MyList(int [] arr) {
        for (int data : arr) {
            append(data);
        }
    }

    /**
     * Copy constructor of MyList
     * @param newList the list that will be copied
     */
    public MyList(MyList newList){
        Node curr = newList.getHead();
        this.length=0;
        while (curr!=null){
            append(curr.getData());
            curr = curr.getNext();
        }
    }

    /**
     * Appends give data to the linked list.
     * @param data an integer number
     */
    public void append(int data){
        if(head==null){
            this.head = new Node(data);
            this.tail = this.head;
            this.length =1;
        }
        else {
            tail.setNext(new Node(data));
            tail = tail.getNext();
            ++this.length;
        }
    }

    public void print(){
        Node curr = head;
        while(curr!=null) {
            System.out.print(curr.getData()+ " ");
            curr = curr.getNext();
        }
        System.out.println();
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getTail() {
        return tail;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    /**
     * Finds last longest sorted sublist in the linked list and returns it.
     */
    public MyList itrLongestSortedSublist(){
        Node curr=head;
        MyList subList , preSubList;

        subList = new MyList();
        preSubList = new MyList();

        while(curr!=null){
            if(subList.getTail()== null || subList.getTail().getData()<=curr.getData()){
                subList.append(curr.data);
            }
            else {
                if(subList.getLength() >= preSubList.getLength()){
                    preSubList = subList;
                }
                subList = new MyList();
                subList.append(curr.data);
            }
            curr = curr.getNext();
        }

        if(subList.getLength() >= preSubList.getLength())
            return subList;

        return preSubList;
    }

    public MyList wrapRecLongestSortedSublist(){
        MyList sublist, longestSublist;

        sublist = new MyList();
        longestSublist = new MyList();

        return recFindLongSorted(head, sublist, longestSublist);
    }

    private MyList recFindLongSorted(Node head, MyList sublist, MyList longestSublist){
        if(head==null) {
            if(sublist.getLength()>=longestSublist.getLength())
                return sublist;
            else
                return longestSublist;
        }
        // there are still some element to add to sublist from the main list
        else if(sublist.getTail()==null || head.getData() >= sublist.getTail().getData() ) {
            // append current head to sublist
            sublist.append(head.getData());
        }
        else {
            if(sublist.getLength() >= longestSublist.getLength()){
                longestSublist = sublist;
            }
            sublist = new MyList();
            sublist.append(head.getData());
        }
        return recFindLongSorted(head.getNext(), sublist, longestSublist);
    }

    class Node{
        private int data;
        private Node next;

        private Node(int data, Node next) {
            this(data);
            this.next = next;
        }

        private Node(int data) {
            this.data = data;
        }

        private Node(Node n){
            this(n.getData(), n.getNext());
        }

        private int getData() {
            return data;
        }

        private void setData(int data) {
            this.data = data;
        }

        private Node getNext() {
            return next;
        }

        private void setNext(Node next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof MyList.Node)) return false;
            Node node = (Node) o;
            return getData() == node.getData();
        }

        @Override
        public int hashCode() {
            return Objects.hash(getData());
        }

    }
}
