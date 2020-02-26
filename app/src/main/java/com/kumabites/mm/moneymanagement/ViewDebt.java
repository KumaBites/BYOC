package com.kumabites.mm.moneymanagement;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.kumabites.mm.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import MMENTITY.Debt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ViewDebt extends AppCompatActivity {
    private RecyclerView viewDebt;
    private List<PayModel> debtListArray;
    private String debtName,debtCategory,newDebtAmount, newDebtRemaining,newDebtPaid;
    private int debtAmount, debtPaid, debtRemaining;
    private DeleteDebtAdapter mAdapter;

    final AppDatabase appDatabase = AppDatabase.getDatabase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_debt);
        viewDebt = findViewById(R.id.viewRecyclerView);
        viewDebt.setLayoutManager(new LinearLayoutManager(this));
        debtListArray = new ArrayList<>();
        getDebtFuture getDebt = null;
        try {
            getDebt = new getDebtFuture();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Debt> getAllDebtList = getDebt.result;
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


        mAdapter = new DeleteDebtAdapter(debtListArray, this);
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
    private class getDebtCallable implements Callable<List<Debt>>

    {
        List<Debt> rList;
        @Override
        public List<Debt> call(){
            AppDatabase app = AppDatabase.getDatabase(ViewDebt.this);
            rList = app.debtDao().getAllDebt();
            return rList;

        }
    }

    private class getDebtFuture {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        getDebtCallable newC = new getDebtCallable();

        private getDebtFuture() throws ExecutionException, InterruptedException {
        }

        Future<List<Debt>> future = executorService.submit(newC);
        List<Debt> result = future.get();
    }
}
