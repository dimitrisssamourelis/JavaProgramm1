import java.util.*;

class Researcher extends Entry{
    private String name;
    private ArrayList<Paper> paperList;

    public Researcher(String name){
        this.name = name;
        paperList = new ArrayList<Paper>();
    }

    public void addPaper(Paper p){
        paperList.add(p);
    }

    public String toString(){
        return name;
    }

    public void display(){
        System.out.println("Researcher: "+name);
        System.out.println("Papers: ");
        for(int i=0;i<paperList.size();i++){
            Paper p = paperList.get(i);
            System.out.println("\t"+p.toString());
        }
    }

    public int computeScore(String text){
        int score = super.computeScore(text);

        if(text.equals(name.toLowerCase())){
            score += 100;
        }

        return score;
    }
}
