import java.util.*;

public class Word_Map implements Map, Iterable
{
    private final static int INITCAP=11;  //initial capacity
    private int CURRCAP = INITCAP;   //current capacity
    private final static float LOADFACT = 0.75f;
    private Node [] table;
    /** head of linkedList, and last element that is added to linked list will be new head.*/
    private Node head =null;
    private int size=0;
    private int deletedSize=0;
    private static final Node DELETED = new Node(null, null);


    public Word_Map() {
        this.table = new Node[INITCAP];
    }

    @Override
    public Iterator iterator() {
        return new WordMapIterator();
    }

    static class Node {
        // complete this class according to the given structure in homework definition
        private String key;
        private Map<String, ArrayList<Integer> > val;
        private Node next;

        public Node(){/*intentionally empty.*/}

        /**
         * Full parameter constructor.
         * @param key string key
         * @param val refers to another hash map
         */
        public Node(String key, Map<String, ArrayList<Integer> > val){
            this.key = key;
            this.val = val;
        }

        @Override
        public String toString() {
            return "key = { "+ key +" }, value = { "+ val.toString() +" }";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;
            Node node = (Node) o;
            return Objects.equals(getKey(), node.getKey()) &&
                    Objects.equals(getVal(), node.getVal());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getKey(), getVal(), getNext());
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public Map<String, ArrayList<Integer>> getVal() {
            return val;
        }

        public void setVal(Map<String, ArrayList<Integer>> val) {
            this.val = val;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    private class WordMapIterator implements Iterator<Node> {
        Node currNode;
        Node preNode =null; // for remove method.

        public WordMapIterator() {
            this.currNode = head;
        }

        @Override
        public boolean hasNext() {
            return (!(currNode == null));
        }

        /**
         * @return next element in the iteration.
         * @throws NoSuchElementException if there is no next element.
         */
        @Override
        public Node next() throws NoSuchElementException {

            if(!hasNext()){
                throw new NoSuchElementException();
            }
            else{
                preNode = currNode;
                currNode = currNode.next;
                return preNode;
            }
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size==0);
    }

    @Override
    public boolean containsKey(Object key) {
        if(get(key)==null)
            return false;
        else
            return true;
    }

    @Override
    /*Use linked structure instead of table index
    to perform search operation effectively
     * */
    public boolean containsValue(Object value) {
        Node currNode;
        int i=0;
        while(table[i]==null){
            ++i;
        }

        currNode=table[i];
        while(currNode!= null && !(currNode.getVal()).equals(value)) {
            currNode = currNode.getNext();
        }

        if(currNode!=null)
            return true;
        else
            return false;
    }

    @Override
    public Object get(Object key) {
        int index = key.hashCode() % table.length;
        if(index < 0)
            index +=table.length;

        while(table[index]!=null && !(key.equals(table[index].getKey())) ){
            ++index;

            if(index==table.length)
                index=0;
        }

        if(table[index]==null)
            return null;
        else
            return table[index].getVal();
    }

    @SuppressWarnings("unchecked")
    @Override
    /*
    Use linear probing in case of collision
    * */
    public Object put(Object key, Object value) {
        int i=0;

        if(key==null)
            return null;

        i = key.hashCode() % table.length;
        if(i<0) {
            i += table.length;
        }

        while(table[i]!=null && !(table[i].getKey().equals(key)) && !(table[i].equals(DELETED))) {
            ++i;
            if(i==table.length)
                i=0;
        }

        if(table[i]== null || table[i].equals(DELETED)) {

            table[i]= new Node();
            table[i].setKey((String)key);
            if(value!=null)
                table[i].setVal((Map<String, ArrayList<Integer> >)value);

            ++size;

            // linked list last node(head) is updated.
            table[i].next = head;
            head = table[i];

            if( ((size+deletedSize)/table.length) > LOADFACT)
                rehash();

            return null;
        }
        else if(table[i].getKey().equals(key)){
            Object prev = table[i].getVal();

            if(value!=null)
                table[i].setVal((Map<String, ArrayList<Integer> >)value);

            return prev;
        }

        return null;
    }

    /**
     * Rehash the hashtable.
     */
    private void rehash(){

        Node [] old = table;

        //increasing capacity.
        CURRCAP = (CURRCAP*2)+1; // we prefer size to be and odd number
        table = new Node[CURRCAP];
        head = null;
        size =0;
        deletedSize =0;

        for(int i=0; i<old.length; ++i){
            if(old[i]!=null && !(old[i].equals(DELETED))){
                put(old[i].getKey(), old[i].getVal());
                ++size;
            }
        }
    }


    @Override
    /*You do not need to implement remove function
    * */
    public Object remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map m) {

        // if m is not an instance of Word_Map method does not work.
        if(!(m instanceof Word_Map)) {
            return;
        }

        Iterator itr = ((Word_Map)m).iterator();
        while(itr.hasNext()) {
            Node curr = (Word_Map.Node)itr.next();
            put(curr.getKey(), curr.getVal());
        }
    }

    @Override
    public void clear() {
        this.size = 0;
        head = null;
        this.deletedSize =0;
        this.CURRCAP = INITCAP;

        this.table = new Node[CURRCAP];
    }

    @Override
    /*Use linked structure instead of table index
    for efficiency
     * */
    public Set keySet() {
        HashSet<String> keys = new HashSet<>();
        Iterator itr = iterator();
        while(itr.hasNext()){
            keys.add(((Word_Map.Node)itr.next()).getKey());
        }
        return keys;
    }

    @Override
    /*Use linked structure instead of table index
    for efficiency
     * */
    public Collection values() {
        ArrayList<Map> vals = new ArrayList<>();
        Iterator itr = iterator();
        while(itr.hasNext()){
            vals.add(((Word_Map.Node)itr.next()).getVal());
        }
        return vals;
    }

    @Override
    /*You do not need to implement entrySet function
     * */
    public Set<Entry> entrySet() {
        return null;
    }
}
