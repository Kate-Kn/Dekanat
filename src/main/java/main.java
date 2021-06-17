public class main {
    public static void main(String[] args) throws Exception {
//        deleteStatements.deleteDatabase();
        checker.checkAndCreate();
        //mandatory line
        Database.connect();
        MyParser parser = new MyParser("new.pdf");
        MyParser parser3 = new MyParser("dc_good.pdf");
        MyParser parser4 = new MyParser("infoposhuk_good.pdf");
        MyParser parser5 = new MyParser("unix_good.pdf");
        MyParser parser6 = new MyParser("dc_good.pdf");
        MyParser parser7= new MyParser("holes.pdf");
        MyParser parser8 = new MyParser("nitbad.pdf");
        MyParser parser9 = new MyParser("Бігунець.pdf");
        MyParser parser10 = new MyParser("infoposhuk_wrong_amount.pdf");
    //    MyParser parser11 = new MyParser("C:\\Users\\Owner\\Documents\\Downloads\\Vidomosti_pdf\\Vidomosti_pdf\\Магістри_Іспит.pdf");

    }
}
