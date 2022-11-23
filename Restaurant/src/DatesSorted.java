import java.time.LocalDate;

public class DatesSorted {
    private LocalDate d;
    private double amount;

    public DatesSorted(LocalDate d, double amount){
        this.d = d;
        this.amount = amount;
    }
    public LocalDate getDate(){
        return d;
    }
    public double getTotal(){
        return amount;
    }
}
