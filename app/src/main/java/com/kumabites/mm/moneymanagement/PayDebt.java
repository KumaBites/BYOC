package com.kumabites.mm.moneymanagement;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.kumabites.mm.R;

import java.util.ArrayList;
import java.util.List;

import MMENTITY.Debt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PayDebt extends AppCompatActivity {


    private String debtName,debtCategory,newDebtAmount, newDebtRemaining,newDebtPaid;
    private int debtAmount, debtPaid, debtRemaining;
    private RecyclerView mRecyclerView;
    private PayDebtAdapter mAdapter;
    private List<PayModel> debtListArray;
    final AppDatabase appDatabase = AppDatabase.getDatabase(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_debt);
        mRecyclerView = findViewById(R.id.payViewRecycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        debtListArray = new ArrayList<>();
        List<Debt> getAllDebtList = appDatabase.debtDao().getAll(CurrentUser.getUsername());

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


        mAdapter = new PayDebtAdapter(debtListArray, this);
        mRecyclerView.setAdapter(mAdapter);


    }




    public void goBack(View view){

        Intent goBack = new Intent(this, MainPage.class);
        startActivity(goBack);
        finish();
    }
    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed()
    {

    }
}
