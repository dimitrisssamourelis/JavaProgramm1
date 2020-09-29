import java.util.*;

class Paper extends Entry{

    private String title;
    private int year;
    private ArrayList<Researcher> authorList;
    private Conference conf;

    public Paper(String title, int year){
        this.title = title;
        this.year = year;
        authorList = new ArrayList<Researcher>();
    }

    public void setConf(Conference conf){
        this.conf = conf;
    }
    public void addAuthor(Researcher r){
        authorList.add(r);
    }

    public String toString(){
        String str = "\""+title+"\". ";
        for(int i=0;i<authorList.size();i++){
            Researcher r = authorList.get(i);
            str = str+r.toString()+", ";
        }
        str = str+conf.toString() + " "+ year;

        return str;
    }

    public void display(){
        System.out.println("Paper: "+title);
        System.out.print("Autors: ");
        for(int i=0;i<authorList.size();i++){
            Researcher r = authorList.get(i);
            System.out.print(r.toString()+", ");
        }
        System.out.println();
        System.out.println("Conference: "+conf.toString() + " "+ year);
    }

    public int computeScore(String text){
        int score = super.computeScore(text);

        if(text.equals(title.toLowerCase())){
            score += 100;
        }
        else if(text.contains(title.toLowerCase())){
            score +=50;
        }

        return score;
    }
}
