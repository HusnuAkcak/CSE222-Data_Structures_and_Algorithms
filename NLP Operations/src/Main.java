import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String [] args) {
        NLP nlp = new NLP();
        try{
            nlp.readDataset("dataset");
        }
        catch (FileNotFoundException fe){
            System.out.println(fe.toString());
            return;
        }

        nlpTest(nlp);
    }

    /**
     * Test Nlp classes operations.
     */
    public static void nlpTest(NLP nlp){
        try{
            Scanner sc = new Scanner(new File("src/input.txt"));
            while (sc.hasNextLine()){
                String key = sc.next();
                if(key.equals("bigram")){
                    String bigram = sc.next();
                    ArrayList<String> arrLst = (ArrayList<String>) nlp.bigrams(bigram);

                    System.out.println("Bigram ("+bigram+") : ");
                    System.out.print("[");
                    int i;
                    for(i=0; i<arrLst.size()-1; ++i){
                        System.out.print(arrLst.get(i)+", " );

                        if((i+1)%9==0)
                            System.out.print("\n");
                    }
                    System.out.println(arrLst.get(i)+"]\n");
                }
                else if(key.equals("tfidf")){
                    String fileName, word;
                    word = sc.next();
                    fileName =sc.next();
                    System.out.println("TF-IDF ("+word+", "+fileName+") :");
                    System.out.println(nlp.tfIDF(word, fileName) +"\n");
                }
                else {
                    System.out.println("THERE IS A PROBLEM WITH \"input.txt\" FILE'S FORMAT.");
                }
            }
        }
        catch (FileNotFoundException fe){
            System.out.println(fe.toString());
        }
    }

    @SuppressWarnings("unchecked")
    public static void testFM(){

        File_Map fm1 = new File_Map();

        ArrayList<Integer> al1 = new ArrayList();
        for(int i=1; i<4; ++i)
            al1.add(i);

        System.out.println("fm1.isEmpty = "+fm1.isEmpty());
        System.out.println("fm1.put(K,V): key = File1, value = "+al1+" is put");
        fm1.put("File1", al1);
        ArrayList<Integer> al2 = new ArrayList<>(al1);
        al2.add(34);
        System.out.println("fm1.put(K,V): key = File2, value = "+al2+" is put");
        fm1.put("File2", al2);
        System.out.println("----------------------------------------------------");

        System.out.println("fm1.isEmpty = "+fm1.isEmpty());
        System.out.println("fm1.size() = " + fm1.size());
        System.out.println("----------------------------------------------------");

        System.out.println("fm1.containsKey(\"File1\") returns "+ fm1.containsKey("File1"));
        System.out.println("fm1.containsValue("+al1+") returns "+ fm1.containsValue(al1));
        System.out.println("----------------------------------------------------");

        System.out.println("fm1.get(\"File1\") = " +fm1.get("File1"));
        System.out.println("fm1.get(\"File2\") = " +fm1.get("File2"));
        System.out.println("----------------------------------------------------");

        System.out.println("fm1.putAll(Map) method is being tested.");
        // to test putAll method fm2 is prepared.
        File_Map fm2 = new File_Map();
        ArrayList<Integer> tempAl = new ArrayList<>();
        tempAl.add(101);
        tempAl.add(102);
        fm2.put("File3", tempAl);
        tempAl = new ArrayList<>();
        tempAl.add(33);
        tempAl.add(22);
        fm2.put("File4", tempAl);
        System.out.println("Before fm1.putAll(fm2), fm1.size() = "+fm1.size()+", fm2.size() = "+fm2.size());
        fm1.putAll(fm2);
        System.out.println("After fm1.putAll(fm2), fm1.size() = "+fm1.size()+", fm2.size() = "+fm2.size());
        System.out.println("------------------------------------------------------");

        // keySet test
        System.out.println("fm1.keySet() method is being tested.");
        HashSet<String> keys = (HashSet<String>) fm1.keySet();
        Iterator itr = keys.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
        System.out.println("-----------------------------------------------------");

        // values test
        System.out.println("fm1.values() method is being tested.");
        ArrayList< ArrayList<Integer> > values = (ArrayList<ArrayList<Integer>>)fm1.values();
        itr = values.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
        System.out.println("-----------------------------------------------------");

        // entrySet test
        System.out.println("fm1.entrySet() method is being tested.");
        HashSet<File_Map.Entry > entries = (HashSet<File_Map.Entry>) fm1.entrySet();
        itr = entries.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
        System.out.println("-----------------------------------------------------");

        // remove test
        System.out.println("fm1.remove(\"File2\") is called.");
        fm1.remove("File2");
        System.out.println("fm1.entrySet() results...");
        entries = (HashSet<File_Map.Entry>)fm1.entrySet();
        itr = entries.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
        System.out.println("-----------------------------------------------------");

        // clear test
        System.out.println("fm1.clear() is being tested.");
        System.out.println("before clear : fm1.size() = " + fm1.size());
        fm1.clear();
        System.out.println("after clear : fm1.size() = " + fm1.size());
        System.out.println("-----------------------------------------------------");


    }

    @SuppressWarnings("unchecked")
    public static void testWM(){

        Word_Map wm1 = new Word_Map();
        HashMap<String, Integer> hm1 = new HashMap<>();

        System.out.println("wm1.isEmpty() = "+wm1.isEmpty());

        for(int i=0; i<9; ++i) {
            hm1.put("00"+i, i);
        }
        String word = "word1";

        System.out.println("wm1.put(K,V): key = "+word+", value = "+hm1+" is put");
        wm1.put(word, hm1);
        System.out.println("------------------------------------------------------");
        System.out.println("wm1.isEmpty() = "+wm1.isEmpty());
        System.out.println("wm1.size() = "+wm1.size());
        System.out.println("------------------------------------------------------");

        System.out.println("wm1.putAll(Word_Map<String, Map>) method is tested.");
        // Word_Map2(wm2) is prepared to examine putAll(Map<>) method of Word_Map
        HashMap<String, Integer> tempMap = new HashMap<>();
        tempMap.put("temp", 1001);
        Word_Map wm2 = new Word_Map();
        for(int i=0; i<3; ++i){
            wm2.put("~:"+i, tempMap);
        }

        wm1.putAll(wm2); // word map2 is put word map1
        System.out.println("wm1.size() = "+wm1.size());
        System.out.println("------------------------------------------------------");

        if(wm1.containsKey(word))
            System.out.println("wm1.containsKey("+word+") returns true");
        else
            System.out.println("wm1.containsKey("+word+") returns false");

        if(wm1.containsValue(hm1))
            System.out.println("wm1.containsValue("+hm1+") returns true");
        else
            System.out.println("wm1.containsValue("+hm1+") returns false");

        System.out.println("------------------------------------------------------");

        System.out.println("wm1.get("+word+") result: "+ wm1.get(word));
        System.out.println("------------------------------------------------------");

        System.out.println("wm1 Word_Map is being iterated...");
        Iterator itr = wm1.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
        System.out.println("------------------------------------------------------");

        System.out.println("wm1.keySet() is being tested.");
        HashSet<String> keys = (HashSet<String>) wm1.keySet();
        Iterator keyItr = keys.iterator();
        while(keyItr.hasNext()){
            System.out.println(keyItr.next());

        }
        System.out.println("------------------------------------------------------");

        System.out.println("wm1.values() is being tested.");
        ArrayList<Map> values = (ArrayList<Map>) wm1.values();
        System.out.println("wm1.iterator() is being tested.");
        Iterator valItr = values.iterator();
        while(valItr.hasNext()){
            System.out.println(valItr.next());
        }
        System.out.println("------------------------------------------------------");

        System.out.println("vm1.clear() is being tested.");
        wm1.clear();
        System.out.println("wm1.size() = "+ wm1.size());
        itr = wm1.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
        System.out.println("-------------------------------------------------------");


    }

}

