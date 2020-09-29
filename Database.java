import java.util.*;
import java.io.*;

class Database {

    private ArrayList<Entry> entries;
    private HashMap<String, Researcher> researchers;
    private HashMap<String, Conference> conferences;

    public Database(){
        entries = new ArrayList<Entry>();
        researchers = new HashMap<String, Researcher>();
        conferences = new HashMap<String, Conference>();
    }

    public ArrayList<Entry> getEntries(){
        return entries;
    }

    public void createDB(String filename){
        try{
            FileInputStream inputStream = new FileInputStream(filename);
            Scanner input = new Scanner(inputStream);

            while(input.hasNextLine()){
                String title = input.nextLine().trim();

                String authors = input.nextLine();

                String name = input.nextLine().trim();

                int year = input.nextInt();

                if(input.hasNextLine())
                    input.nextLine();//gia to \n pou afhnei o .nextInt()

                Paper p = new Paper(title, year);
                entries.add(p);
                p.addText(title+" "+name + " "+year);
                if(conferences.containsKey(name)){
                    Conference conf = conferences.get(name);
                    p.setConf(conf);
                    conf.addPaper(p);
                }
                else{
                    Conference conf = new Conference(name);
                    p.setConf(conf);
                    conf.addPaper(p);
                    entries.add(conf);
                    conferences.put(name, conf);
                    conf.addText(name);
                }
                String[] authorsSplit = authors.split(",");
                for(int i=0;i<authorsSplit.length;i++){
                    String authorName = authorsSplit[i].trim();
                    p.addText(authorName);
                    if(researchers.containsKey(authorName)){
                        Researcher r = researchers.get(authorName);
                        r.addPaper(p);
                        p.addAuthor(r);
                        r.addText(title);
                        r.addText(name);
                    }
                    else{
                        Researcher r = new Researcher(authorName);
                        r.addPaper(p);
                        p.addAuthor(r);
                        entries.add(r);
                        researchers.put(authorName, r);
                        r.addText(authorName);
                        r.addText(title);
                        r.addText(name);
                    }
                }
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
            System.exit(0);
        }
    }

    public void printDB(){
        for(int i=0;i<entries.size();i++){
            Entry e = entries.get(i);
            e.display();
            System.out.println("----");
        }
    }

    public static void main(String args[]){

        if(args.length != 1){
            System.out.println("Give the right arguments");
            System.exit(0);
        }
        Database db = new Database();
        System.out.println();
        db.createDB(args[0]);
        db.printDB();

    }
}
