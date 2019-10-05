package com.kumabites.mm.moneymanagement.CreateDebt;

import MMENTITY.Debt;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.kumabites.mm.R;
import com.kumabites.mm.moneymanagement.CurrentUser;
import com.kumabites.mm.moneymanagement.MainActivity;
import com.kumabites.mm.moneymanagement.MainPage;


import java.util.List;


public class AddDebt extends AppCompatActivity {
    private TextView debtName, debtAmount;
    private String oldCheck1,cSpinner;
    private  Spinner catergory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_debt);
   catergory = (Spinner)findViewById(R.id.cSpinner);
        debtName = (TextView) findViewById(R.id.dName);
        debtAmount = (TextView) findViewById(R.id.amountDebt);

    }

    //Stores new debt
    public void addDebt(View view) {
        cSpinner = catergory.getSelectedItem().toString();
        String DN = debtName.getText().toString();
        int DA = Integer.parseInt((debtAmount.getText().toString()));
        int DA2 = Math.abs(DA);
        List<Debt> checkDebtName = MainActivity.appDatabase.debtDao().getDebt(DN);

        for(Debt oldCheck : checkDebtName) {
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
            MainActivity.appDatabase.debtDao().insertNew(debt);
            Toast.makeText(getBaseContext(), "Debt Saved!", Toast.LENGTH_LONG).show();
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
