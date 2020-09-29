class SearchEngine{

    public static void main(String args[]){

        if(args.length != 1){
            System.out.println("Give the right arguments");
            System.exit(0);
        }
        Database db = new Database();
        System.out.println();
        db.createDB(args[0]);

       Index index = new Index();
       index.indexDB(db);
       //index.printIndex();

        QueryProcessor queryProcessor = new QueryProcessor(index);

        queryProcessor.run();

    }
}
