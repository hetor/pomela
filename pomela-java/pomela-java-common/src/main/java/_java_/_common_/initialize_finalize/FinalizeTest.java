package _java_._common_.initialize_finalize;

public class FinalizeTest {
    public static void main(String[] args) {
        Book novel = new Book(true);
        novel.checkIn();
        new Book(true);
        System.gc();
    }

    private static class Book {

        boolean checkedOut = false;

        Book(boolean checkedOut) {
            this.checkedOut = checkedOut;
        }

        void checkIn() {
            checkedOut= false;
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println("invoke method finalize before gc");
            if(checkedOut) {
                System.err.println("Error:checked out");
                super.finalize();
            }
        }
    }
}
