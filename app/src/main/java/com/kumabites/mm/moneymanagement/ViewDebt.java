package com.kumabites.mm.moneymanagement;

import MMENTITY.Debt;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.kumabites.mm.R;

import java.util.ArrayList;
import java.util.List;

public class ViewDebt extends AppCompatActivity {
    ListView viewDebt;
    private ArrayList<String> stringDebt = new ArrayList<>();
    private String debtName, debtAmount, debtPaid, debtRemaining, debtCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_debt);
        viewDebt = (ListView) findViewById(R.id.viewDebt);
        List<Debt> getAllDebtList = MainActivity.appDatabase.debtDao().getAll(CurrentUser.getUsername());
        for (Debt debt : getAllDebtList) {
            debtName = debt.getDebt_name();
            stringDebt.add("Debt Name: " + debtName);
            debtAmount = String.valueOf(debt.getDebt_amount());
            stringDebt.add("Debt Amount: " + (debtAmount));
            debtPaid = String.valueOf((debt.getAmount_paid()));
            stringDebt.add("How much debt Paid: " + (debtPaid));
            debtRemaining = String.valueOf(debt.getRemaining());
            stringDebt.add("Debt Remaining: " + (debtRemaining));
            debtCategory = debt.getCategoty();
            stringDebt.add("Debt Category is: " + debtCategory);
            stringDebt.add("");
        }


        ArrayAdapter viewDebtAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, stringDebt);
        viewDebt.setAdapter(viewDebtAdapter);


    }

    public void goBack(View view) {

        Intent goBack = new Intent(this, MainPage.class);
        startActivity(goBack);
        finish();
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {

    }

}
