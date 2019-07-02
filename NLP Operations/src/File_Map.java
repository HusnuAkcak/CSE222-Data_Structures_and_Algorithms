import java.util.*;

public class File_Map implements Map
{
    /*
    For this hashmap, you will use arraylists which will provide easy but costly implementation.
    * */
    ArrayList<String> fnames;
    ArrayList< ArrayList<Integer> > occurrences;
    private final static int INITCAP=101;  //initial capacity

    public File_Map(){
        fnames = new ArrayList<>(INITCAP);
        occurrences = new ArrayList<>(INITCAP);
    }

    @Override
    public int size() {
        if(fnames == null)
            return 0;
        return fnames.size();
    }

    @Override
    public boolean isEmpty() {
        return fnames.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        if(!(key instanceof String))
            return false;

        return fnames.contains(key);
    }

    @Override
    public boolean containsValue(Object value) {
        if(!(value instanceof ArrayList))
            return false;

        return values().contains(value);
    }

    @Override
    public Object get(Object key) {
        if(!(key instanceof String))
            return null;

        int index = fnames.indexOf(key);
        if(index < 0) // if element is NOT FOUND index must be (-1)
            return null;
        else
            return occurrences.get(index);
    }

    @SuppressWarnings("unchecked")
    @Override
    /*Each put operation will extend the occurrence list*/
    public Object put(Object key, Object value) {

        if(!(key instanceof String) || !(value instanceof ArrayList))
            return null;

        if(containsKey(key)){
            Object oldVal = occurrences.get(fnames.indexOf(key));
            occurrences.set(fnames.indexOf(key), (ArrayList<Integer>)value);
            return oldVal;
        }
        else {
            fnames.add((String)key);
            occurrences.add((ArrayList<Integer>)value);
            return null;
        }
    }

    @Override
    public Object remove(Object key) {

        if(!(key instanceof String))
            return null;

        int index = fnames.indexOf(key);
        if(index < 0) // if there is no such key.
            return null;
        else{
            occurrences.remove(index);
            fnames.remove(index);
            return key;
        }
    }

    @Override
    public void putAll(Map m) {

        // if give map is not fit to File_Map class
        if(!(m instanceof File_Map)) {
            return;
        }

        File_Map fm = (File_Map)m;

        for(int i=0; i<(fm.fnames.size()); ++i){
            put(fm.fnames.get(i), fm.occurrences.get(i));
        }
    }

    @Override
    public void clear() {
        fnames = null;
        occurrences = null;
    }

    @Override
    public Set keySet() {
        return new HashSet<>(fnames);
    }

    @Override
    public Collection values() {
        return new ArrayList<>(occurrences);
    }

    @Override
    public Set<Entry> entrySet() {
        Entry curr;
        HashSet<Entry> entryS = new HashSet<>();

        for(int i=0; i<fnames.size(); ++i) {
            entryS.add(new Entry(fnames.get(i), occurrences.get(i)));
        }
        return entryS;
    }

    public static class Entry {
        private String fname;
        private ArrayList<Integer> occurrence;

        public Entry(String fname, ArrayList<Integer> occurrence) {
            this.fname = fname;
            this.occurrence = new ArrayList<>(occurrence);
        }

        @Override
        public String toString() {
            return "fname {"+ fname +"}, occurrences {"+ occurrence.toString()+"}";
        }

        public String getFname() {
            return fname;
        }

        public void setFname(String fname) {
            this.fname = fname;
        }

        public ArrayList<Integer> getOccurrence() {
            return new ArrayList<Integer>(occurrence);
        }

        public void setOccurrence(ArrayList<Integer> occurrence) {
            this.occurrence.addAll(occurrence);
        }
    }
}
