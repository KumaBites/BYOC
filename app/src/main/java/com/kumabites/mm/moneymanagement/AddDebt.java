package com.kumabites.mm.moneymanagement;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.kumabites.mm.R;

import java.util.List;
import java.util.regex.Pattern;

import MMENTITY.Debt;
import androidx.appcompat.app.AppCompatActivity;


public class AddDebt extends AppCompatActivity {


    private TextView debtName, debtAmount;
    private String oldCheck1,cSpinner,checkName, checkAmount;
    private  Spinner catergory;
    boolean booleanCheck;
    AppDatabase appDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_debt);
   catergory = findViewById(R.id.cSpinner);
        debtName = findViewById(R.id.dName);
        debtAmount = findViewById(R.id.amountDebt);
        appDatabase = AppDatabase.getDatabase(this);



    }

    //Stores new debt
    public void addDebt(View view) {
        checkName = debtName.getText().toString();
        checkAmount = debtAmount.getText().toString();
       booleanCheck = Pattern.matches("^[0-9]+$", checkAmount);


        if (checkName.isEmpty() || booleanCheck == false) {
            Toast.makeText(getBaseContext(), "A field is wrong!", Toast.LENGTH_SHORT).show();
            debtName.setText("");
            debtAmount.setText("");
        } else{
            cSpinner = catergory.getSelectedItem().toString();
            String DN = debtName.getText().toString();
            int DA = Integer.parseInt((debtAmount.getText().toString()));
            int DA2 = Math.abs(DA);
            List<Debt> checkDebtName = appDatabase.debtDao().getDebt(DN);

            for (Debt oldCheck : checkDebtName) {
                oldCheck1 = oldCheck.getDebt_name();
            }

            if (DN.equals(oldCheck1)) {
                Toast.makeText(getBaseContext(), "Debt name already exists", Toast.LENGTH_SHORT).show();
            } else {
                Debt debt = new Debt();
                debt.setDebt_name(DN);
                debt.setDebt_amount(DA2);
                debt.setUser_name(CurrentUser.getUsername());
                debt.setAmount_paid(0);
                debt.setRemaining(DA);
                debt.setCategoty(cSpinner);

                Toast.makeText(getBaseContext(), "Debt Saved!", Toast.LENGTH_LONG).show();
            }
        }
    }
        public void goBack (View view){

            Intent goBack = new Intent(this, MainPage.class);
            startActivity(goBack);
            finish();
        }
        @SuppressLint("MissingSuperCall")
        @Override
        public void onBackPressed ()
        {

        }

    }
