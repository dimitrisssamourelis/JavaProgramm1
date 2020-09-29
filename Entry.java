import java.util.*;
abstract class Entry{
    private HashMap<String, Integer> tokenMap;

    public Entry(){
        tokenMap = new HashMap<String, Integer>();
    }

    public abstract void display();

    public void addText(String text){
        text = text.toLowerCase();
        String[] tokens = text.split(" ");

        for(int i=0;i<tokens.length;i++){
            if(tokenMap.containsKey(tokens[i])){
                int number = tokenMap.get(tokens[i]);
                tokenMap.put(tokens[i], number+1);
            }
            else{
                tokenMap.put(tokens[i], 1);
            }
        }
    }

    public Set<String> getTokens(){
        return tokenMap.keySet();
    }

    public int computeScore(String text){
        text = text.toLowerCase();
        String[] tokens = text.split(" ");

        int score = 0;

        for(int i=0;i<tokens.length;i++){
            if(tokenMap.containsKey(tokens[i])){
                int number = tokenMap.get(tokens[i]);
                score += number;
            }
        }

        return score;
    }
}
