package hsn;

import java.util.EmptyStackException;

public class MyStack<T>{
    /** Top of stack*/
    private Node top;

    /**
     * Tell if stack is empty or not.
     * @return Returns true if stack is empty, false otherwise.
     */
    public boolean empty(){
        return (top==null);
    }

    /**
     * Returns peak of the stack's data but does not remove it.
     * @return data of peak node of stack
     * @throws EmptyStackException if stack is empty
     */
    public T peak() throws EmptyStackException{
        if(!empty()){
            return  top.getData();
        }
        else {
            throw new EmptyStackException();
        }
    }

    /**
     * Returns peak of stack's data and remove peak node
     * @return data of peak node
     * @throws EmptyStackException if there is no element in stack
     */
    public T pop() throws EmptyStackException{
        T topData;
        if(empty())
            throw new EmptyStackException();

        topData=top.getData();
        top=top.getNext();

        return topData;
    }

    /**
     * Creates a new node and put it top of the stack
     * @param data data of new node
     */
    public void push(T data){
        top =new Node(data,top);
    }

    /**
     * if given data is stored in any node of stack returns its distance from top as 1-based position,
     * it there is no such data returns -1.
     * @param data the data desired to find
     * @return  returns 1-based position from top, returns -1 if there is no such element
     */
    public int search(T data){
        Node curr = top;        // search starts from top
        boolean found = false;  // controls if element is found or not
        int distance=1;         // distance of found element from top

        while (curr!=null && !found){
            if(curr.getData()==data)
                found=true;

            curr=curr.next;
            ++distance;
        }
        if(found)
            return distance;
        else
            return -1;
    }

    class Node {
        private T data;
        private Node next;

        /**
         * Two parameter constructor.
         * @param data  data of current node
         * @param next  reference to next node
         */
        Node(T data, Node next) {
            this(data);
            this.next = next;
        }

        /**
         * One parameter constructor.
         * @param data  data of current node
         */
        Node(T data){
            this.data=data;
        }

        /**
         * No parameter constructor.
         */
        Node(){ /*Intentionally empty*/ }

        public T getData() {
            return data;
        }

        public Node getNext() {
            return next;
        }

        public void setData(T data) {
            this.data = data;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
