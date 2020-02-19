package MMENTITY;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//Name of table
@Entity(tableName = "debt_table")
public class Debt {


    //Columns

    @NonNull
    @PrimaryKey
    private String debt_name;
    private int debt_amount;
    private int remaining;


    private String categoty;
    private String user_name;
    private int amount_paid;

    //Getters and setters
    public int getDebt_amount() {
        return debt_amount;
    }

    public void setDebt_amount(int debt_amount) {
        this.debt_amount = debt_amount;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getAmount_paid() {
        return amount_paid;
    }

    public void setAmount_paid(int amount_paid) {
        this.amount_paid = amount_paid;
    }


    public String getDebt_name() {
        return debt_name;
    }

    public void setDebt_name(String debt_name) {
        this.debt_name = debt_name;
    }
    public String getCategoty() {
        return categoty;
    }

    public void setCategoty(String categoty) {
        this.categoty = categoty;
    }

}
