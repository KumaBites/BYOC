package com.kumabites.mm.moneymanagement.ViewDebt;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.kumabites.mm.R;
import com.kumabites.mm.moneymanagement.CurrentUser;
import com.kumabites.mm.moneymanagement.MainActivity;
import com.kumabites.mm.moneymanagement.MainPage.MainPage;
import com.kumabites.mm.moneymanagement.Pay.PayModel;

import java.util.ArrayList;
import java.util.List;

import MMENTITY.Debt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ViewDebt extends AppCompatActivity {
    private RecyclerView viewDebt;
    private List<PayModel> debtListArray;
    private String debtName,debtCategory,newDebtAmount, newDebtRemaining,newDebtPaid;
    private int debtAmount, debtPaid, debtRemaining;
    private com.kumabites.mm.moneymanagement.Delete.DeleteDebtAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_debt);
        viewDebt = findViewById(R.id.viewRecyclerView);
        viewDebt.setLayoutManager(new LinearLayoutManager(this));
        debtListArray = new ArrayList<>();
        List<Debt> getAllDebtList = MainActivity.appDatabase.debtDao().getAll(CurrentUser.getUsername());
        for (Debt debt : getAllDebtList) {
            debtName = debt.getDebt_name();
            debtAmount = debt.getDebt_amount();

            newDebtAmount = String.valueOf(debtAmount);

            debtPaid = debt.getAmount_paid();
            newDebtPaid =String.valueOf(debtPaid);

            debtRemaining = debt.getRemaining();
            newDebtRemaining =String.valueOf(debtRemaining);

            debtCategory = debt.getCategoty();

            debtListArray.add(new PayModel(debtName,newDebtAmount,newDebtRemaining,debtCategory,newDebtPaid));

        }


        mAdapter = new com.kumabites.mm.moneymanagement.Delete.DeleteDebtAdapter(debtListArray, this);
        viewDebt.setAdapter(mAdapter);








    }
    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed()
    {

    }

    public void goBack(View view){

        Intent goBack = new Intent(this, MainPage.class);
        startActivity(goBack);
        finish();
    }
}
