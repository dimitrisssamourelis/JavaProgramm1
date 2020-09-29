import java.util.*;

class QueryProcessor{
    private Index index;
    private String query;
    private int[] scores;

    QueryProcessor(Index index){
        this.index = index;
    }

    public String[] getQuery(){
        Scanner input = new Scanner(System.in);
        System.out.println("Give the query: ");

        query = input.nextLine();
        query = query.toLowerCase();
        String[] tokens = query.split(" ");
        return tokens;
    }

    public ArrayList<Entry> getEntries(String[] tokens){

        HashMap<String, HashSet<Entry>> entries = new HashMap<String, HashSet<Entry>>();

        for(int i=0;i<tokens.length;i++){
            System.out.println(tokens[i]);
             HashSet<Entry> tokenEntries = index.retreive(tokens[i]);
             entries.put(tokens[i], tokenEntries);
        }

        HashSet<Entry> firstEntries = entries.get(tokens[0]);
        ArrayList<Entry> entriesInAll = new ArrayList<Entry>();

        for(Entry e : firstEntries){
            int counter = 0;
            for(int i=0;i<tokens.length;i++){
                HashSet<Entry> tokenEntries = index.retreive(tokens[i]);
                if(tokenEntries.contains(e)){
                    counter++;
                }
            }
            if(counter == tokens.length){
                entriesInAll.add(e);
            }
        }
        return entriesInAll;
    }

    public ArrayList<Entry> sortEntries(ArrayList<Entry> entriesInAll){
        scores = new int[entriesInAll.size()];

        for(int i=0;i<entriesInAll.size();i++){
            Entry entry = entriesInAll.get(i);
            scores[i] = entry.computeScore(query);
        }

        for(int i=0;i<entriesInAll.size();i++){
            for(int j=0;j<entriesInAll.size()-1;j++){
                if(scores[j]<scores[j+1]){
                    int temp = scores[j];
                    scores[j] = scores[j+1];
                    scores[j+1] = temp;

                    Entry entry = entriesInAll.get(j);
                    Entry entry1 = entriesInAll.get(j+1);

                    entriesInAll.set(j, entry1);
                    entriesInAll.set(j+1, entry);
                }
            }
        }

        return entriesInAll;
    }

    public void display(ArrayList<Entry> entriesInAll){
        for(int i=0;i<entriesInAll.size();i++){
            Entry entry = entriesInAll.get(i);
            System.out.println("Result "+(i+1) +"(Score: "+scores[i] +")");
            entry.display();
        }
    }

    public void run(){
        String[] tokens = getQuery();
        ArrayList<Entry> entries = getEntries(tokens);
        entries = sortEntries(entries);
        display(entries);
    }
}
