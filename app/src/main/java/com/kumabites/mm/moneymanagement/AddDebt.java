package com.kumabites.mm.moneymanagement;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.kumabites.mm.R;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.regex.Pattern;

import MMENTITY.Debt;
import androidx.appcompat.app.AppCompatActivity;


public class AddDebt extends AppCompatActivity {


    private TextView debtName, debtAmount;
    private String oldCheck1,cSpinner,checkName, checkAmount;
    private  Spinner catergory;
    boolean booleanCheck;
    private List<Debt> oldUserList;
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
    public void addDebt(View view)throws ExecutionException, InterruptedException {
        checkName = debtName.getText().toString();
        checkAmount = debtAmount.getText().toString();
       booleanCheck = Pattern.matches("^[0-9]+$", checkAmount);


        if (checkName.isEmpty() || booleanCheck == false) {
            Toast.makeText(getBaseContext(), "A field is wrong!", Toast.LENGTH_SHORT).show();
            debtName.setText("");
            debtAmount.setText("");
        }
        else if(!checkName.isEmpty() && !booleanCheck == false){
            cSpinner = catergory.getSelectedItem().toString();
            String DN = debtName.getText().toString();
            int DA = Integer.parseInt((debtAmount.getText().toString()));
            int DA2 = Math.abs(DA);
            getOldDebtFuture oFuture = new getOldDebtFuture();
            oldUserList = oFuture.result;
            List<Debt> checkDebtName = oldUserList;
            if (!checkDebtName.isEmpty()) {
                for (Debt oldCheck : checkDebtName) {
                    oldCheck1 = oldCheck.getDebt_name();
                    if (oldCheck1.equals(DN)) {
                        Toast.makeText(getBaseContext(), "Debt name already exists", Toast.LENGTH_SHORT).show();
                        break;
                    }     else{
                        Debt debt = new Debt();
                        debt.setDebt_name(DN);
                        debt.setDebt_amount(DA2);
                        debt.setUser_name(CurrentUser.getUsername());
                        debt.setAmount_paid(0);
                        debt.setRemaining(DA);
                        debt.setCategoty(cSpinner);
                        new insertDebtAsync(debt).execute();
                        Toast.makeText(getBaseContext(), "Debt Saved!", Toast.LENGTH_SHORT).show();
                        break;
                    }

                }

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

        private class insertDebtAsync extends AsyncTask<Void,Void,Void> {
            private Debt insertDebt;
            private AppDatabase insertDatabase;

            public insertDebtAsync(Debt newDebt) {
                this.insertDebt = newDebt;
                insertDatabase = AppDatabase.getDatabase(AddDebt.this);
            }

            @Override
            protected Void doInBackground(Void... params) {

                insertDatabase.debtDao().insertNew(insertDebt);
                return null;
            }


        }

    private class getOldDebtCallable implements Callable<List<Debt>>
    {

        List<Debt> rList;
        @Override
        public List<Debt> call(){
            AppDatabase app = AppDatabase.getDatabase(AddDebt.this);
            rList = app.debtDao().getAllDebt();
            return rList;

        }
    }

    private class getOldDebtFuture {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        getOldDebtCallable newC = new getOldDebtCallable();

        private getOldDebtFuture() throws ExecutionException, InterruptedException {
        }

        Future<List<Debt>> future = executorService.submit(newC);
        List<Debt> result = future.get();
    }

    }
