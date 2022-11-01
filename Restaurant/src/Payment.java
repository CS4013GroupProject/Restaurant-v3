import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class Payment extends TableReservation {
    private double amountDue;
    private LocalDate date;
    
    public Payment(int amountDue, LocalDate date) {
        this.amountDue = amountDue;
        this.date = date;
    }
    
    private void writeToFile(String[] data) throws FileNotFoundException {
        
        try {
            FileWriter f = new FileWriter("src/payments.csv", true);
            for(String s : data) {
                f.write(s + ", ");
            }
            f.write("\n");
        } catch(IOException e) {
            e.printStackTrace();
        }
        
    }
    
    public void takePayment() throws FileNotFoundException {
        String[] data = {String.valueOf(this.getTableNumber()), String.valueOf(this.amountDue)};
        writeToFile(data);
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Payment a = new Payment(100, LocalDate.now());
        a.takePayment();
        //a.takeTip();
    }
}