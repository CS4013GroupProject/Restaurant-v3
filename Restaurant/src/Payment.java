import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class Payment extends TableReservation {
    private double amountDue;
    private LocalDate date;
    private double tip;


    public Payment(double amountDue, LocalDate date, double tip) throws FileNotFoundException {
        this.amountDue = amountDue;
        this.date = date;
        this.tip = tip;
    }

    private void writeToFile(String[] data) throws FileNotFoundException {

        try {
            FileWriter f = new FileWriter("Restaurant/src/payments.csv", true);
            for (String s : data) {
                f.write(s + ", ");
            }
            f.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("file not found");
        }

    }

    public void takePayment() throws FileNotFoundException {
        String[] data = { String.valueOf(this.amountDue), String.valueOf(this.date), String.valueOf(this.tip)};
        writeToFile(data);
    }


}