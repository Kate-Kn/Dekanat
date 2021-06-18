public class main {
    public static void main(String[] args) throws Exception {
        deleteStatements.deleteDatabase();
        checker.checkAndCreate();
        //mandatory line
        Database.connect();
        MyParser parser = new MyParser("new.pdf");
        MyParser parser3 = new MyParser("dc_good.pdf");
        MyParser parser55 = new MyParser("unix_badamount.pdf");
        MyParser parser5 = new MyParser("unix_good.pdf");
        MyParser parser6 = new MyParser("dc_good.pdf");
        MyParser parser7= new MyParser("holes.pdf");

        MyParser parser8 = new MyParser("nitbad.pdf");
          MyParser parser88 = new MyParser("nitgoodF.pdf");
          MyParser parser9 = new MyParser("Бігунець.pdf");

//
    }
}
