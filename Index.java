import java.util.*;

class Index{
    private HashMap<String, HashSet<Entry>> index;

    public Index(){
        index = new HashMap<String, HashSet<Entry>>();
    }

    public void indexDB(Database database){
        ArrayList<Entry> entries = database.getEntries();

        for(int i=0;i<entries.size();i++){
            Entry entry = entries.get(i);
            Set<String> tokens = entry.getTokens();
            for(String token : tokens){
                if(index.containsKey(token)){
                    HashSet<Entry> setEntry = index.get(token);
                    setEntry.add(entry);
                }
                else{
                    HashSet<Entry> setEntry = new HashSet<Entry>();
                    setEntry.add(entry);
                    index.put(token,setEntry);
                }
            }
        }
    }

    public void printIndex(){
        Set<String> keys = index.keySet();

        for(String key : keys){
            HashSet<Entry> entries = index.get(key);
            System.out.println("Token:"+key);
            int i=1;
            for(Entry entry : entries){
                System.out.print(i+". ");
                entry.display();
                i++;
            }
            System.out.println("----");
        }
    }

    public HashSet<Entry> retreive(String word){
        return index.get(word);
    }

    public static void main(String args[]){

        if(args.length != 1){
            System.out.println("Give the right arguments");
            System.exit(0);
        }
        Database db = new Database();
        System.out.println();
        db.createDB(args[0]);
       // db.printDB();
       Index index = new Index();
       index.indexDB(db);
       index.printIndex();
    }
}
