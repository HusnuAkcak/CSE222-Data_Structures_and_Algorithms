package hsn;

public class Main {

    public static void main(String[] arg){
        int [] arr = {1, 9, 2, 7, 8, 9, 20, 13};
//        int [] arr = {1, 9, 2, 7, 8, 9, 20, 13, 14, 15, 16, 17, 18, 11, 12, 21, 22, 23, 24};
        MyList l1 = new MyList(arr);
        MyList l2;

//        l2=l1.itrLongestSortedSublist();
        l2=l1.wrapRecLongestSortedSublist();
        l1.print();
        l2.print();
    }
}
