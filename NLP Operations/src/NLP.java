import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class NLP
{
    public Word_Map wmap;

    /*You should not use File_Map class in this file since only word hash map is aware of it.
    In fact, you can define the File_Map class as a nested class in Word_Map,
     but for easy evaluation we defined it separately.
     If you need to access the File_Map instances, write wrapper methods in Word_Map class.
    * */

    public NLP(){
        wmap = new Word_Map();
    }

    /**
     * Reads the dataset from the given dir and created a word map
     * @param dir dataset directory
     * @throws FileNotFoundException if file could not find
     */
    void readDataset(String dir) throws FileNotFoundException {
        File f = new File(dir);
        File [] fileNames = f.listFiles();

        if(fileNames==null)
            return;

        for (int i = 0; i < fileNames.length; i++) {

            Scanner sc = new Scanner(fileNames[i]);
            int wordFilePos =0;
            while(sc.hasNext()){
                String s = sc.next();

                String fileName = fileNames[i].toString().replace(dir+"/","");
                String word = (s.trim().replaceAll("\\p{Punct}", "").replaceAll("\\s+", "")).trim();
                addWordMap(word, fileName, wordFilePos);

                ++wordFilePos;

            }
        }

    }

    @SuppressWarnings("unchecked")
    private void addWordMap(String word, String fileName, int wordFilePos) {

        // creating File_Map and ArrayList as key and value
        File_Map fileMap;
        ArrayList<Integer> arrlst;

        if(!(wmap.containsKey(word)) ){

            fileMap = new File_Map();
            arrlst = new ArrayList<>();
            // adding wordPos to array list
            arrlst.add(wordFilePos);

            // put operation for Word_Map
            wmap.put(word, fileMap);
            // put operation for File_Map
            fileMap.put(fileName, arrlst);
        }
        else {
            fileMap = (File_Map)wmap.get(word);
            if((fileMap.containsKey(fileName))){
                ((ArrayList<Integer>)fileMap.get(fileName)).add(wordFilePos);
            }
            else {
                arrlst = new ArrayList<>();
                arrlst.add(wordFilePos);
                fileMap.put(fileName, arrlst);
            }
        }
    }

    /**Finds all the bigrams starting with the given word*/
    @SuppressWarnings("unchecked")
    List<String> bigrams(String word){

        HashSet<String> bigramSet = new HashSet<>();
        File_Map file_map = (File_Map)wmap.get(word);

        Iterator fmItr = file_map.fnames.iterator();
        while(fmItr.hasNext()){ // scan every file that contains the word

            String fileName = (String)fmItr.next();
            Iterator posItr = ((ArrayList<Integer>)(file_map.get(fileName))).iterator();

            while(posItr.hasNext()){ // scan every position of current file for the word

                Integer currPos =(Integer) posItr.next();
                Iterator itr= wmap.iterator();

                while(itr.hasNext()){ // scan every word that contained by wmap for bi-gram match

                    Word_Map.Node currNode = (Word_Map.Node)itr.next();
                    if(!(currNode.getKey().equals(word)) && currNode.getVal().containsKey(fileName)){
                        Iterator searchArrItr = currNode.getVal().get(fileName).iterator();

                        while(searchArrItr.hasNext()){
                            if((Integer) searchArrItr.next() == (currPos+1)){
                                bigramSet.add(word+" "+currNode.getKey());
                            }
                        }
                    }
                }
            }
        }

        return new ArrayList<>(bigramSet);
    }

    /**
     *Calculates the tfIDF value of the given word for the given file */
    @SuppressWarnings("unchecked")
    float tfIDF(String word, String fileName) {

        float tf, idf;
        float numOfTimeOccurrTheWordInFile, totalWordInFile, totalFileNum, fileNumsContainsTheWord;

        if(!(wmap.containsKey(word)))
            return 0;

        numOfTimeOccurrTheWordInFile = ((ArrayList<Integer>)((File_Map)wmap.get(word)).get(fileName)).size();
        totalWordInFile = findTotalWords(fileName);
        totalFileNum = findTotalDocuments();
        fileNumsContainsTheWord = ((File_Map)wmap.get(word)).size();

        tf = (numOfTimeOccurrTheWordInFile/ totalWordInFile);
        idf = (float) (Math.log(totalFileNum/fileNumsContainsTheWord));

        return (tf*idf);
    }

    /**
     * Finds total words number in a file.
     * @param fileName corresponding file to be searched
     * @return total words number in the given file
     */
    private int findTotalWords(String fileName){

        int totalWord=0;
        Iterator itr = wmap.iterator();
        while (itr.hasNext()){
            Word_Map.Node curr = (Word_Map.Node)itr.next();
            if(curr.getVal().containsKey(fileName))
                totalWord += curr.getVal().get(fileName).size();
        }

        return totalWord;
    }

    /**
     * Returns total number of dataset files numbers
     * @return number of files in the dataset.
     */
    private int findTotalDocuments(){

        HashSet<String> doc = new HashSet<>();
        Iterator itr = wmap.iterator();
        while(itr.hasNext()){
            Word_Map.Node curr = (Word_Map.Node)itr.next();
            doc.addAll(curr.getVal().keySet());
        }
        return doc.size();
    }



    /*Print the WordMap by using its iterator*/
    @SuppressWarnings("unchecked")
    public  void printWordMap() {

        Iterator itr = wmap.iterator();
        while (itr.hasNext()){
            Word_Map.Node node = (Word_Map.Node)itr.next();
            File_Map flMap = (File_Map) node.getVal();

            // word map information is being printed.
            System.out.println("Word_Map-key {"+node.getKey()+"}, Word_Map-value {"+node.getVal()+"}\n" +
                                    "\t File_Map-entrySet: ");
            // File map is being printed.
            for(File_Map.Entry e :flMap.entrySet()){
                System.out.println("\t\t"+e);
            }

        }
    }

}
