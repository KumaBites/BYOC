package com.kumabites.mm.moneymanagement;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.kumabites.mm.R;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import MMENTITY.Debt;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class confirmPay extends AppCompatActivity {
    private String newDebtName;
    private TextView payDebt, confirmPay, debtName;
    private int pText, absolutePText, debtRemain, debtPay;
    final AppDatabase appDatabase = AppDatabase.getDatabase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_pay);
        debtName = findViewById(R.id.debtName);
        confirmPay= findViewById(R.id.confirmPay);
        payDebt = findViewById(R.id.confirmName);
        Intent confirm = getIntent();
        newDebtName = confirm.getStringExtra("Debt Name");
        debtName.setText("How much are you paying off ");
        payDebt.setText(newDebtName+" ?");

    }
    public void confirmedPay(View view){
        confirmPayDialog();

    }
    public void payDebt() {
        String newDText =  newDebtName;
        String newPText = confirmPay.getText().toString();
        if (newPText.equals("")) {
            Toast.makeText(getBaseContext(), "Incorrect details try again!", Toast.LENGTH_SHORT).show();
        }
        else if(newDText.equals("0")){
            Toast.makeText(getBaseContext(), "Incorrect details try again!", Toast.LENGTH_SHORT).show();}
        else{
            pText = Integer.parseInt(newPText);
            if (pText == 0 || newDText.equals("")) {
                Toast.makeText(getBaseContext(), "Incorrect details try again!", Toast.LENGTH_SHORT).show();
            } else {
                absolutePText = Math.abs(pText);

                List<Debt> getOldDebt = appDatabase.debtDao().getDebt(newDText);

                for(Debt oldDebt : getOldDebt)
                {

                    debtRemain = oldDebt.getRemaining();
                    debtRemain = debtRemain - absolutePText;
                    oldDebt.setRemaining(debtRemain);

                    debtPay = oldDebt.getAmount_paid();
                    debtPay = debtPay + pText;
                    oldDebt.setAmount_paid(debtPay);
                    appDatabase.debtDao().updateDebt(oldDebt);

                }

                Toast.makeText(getBaseContext(),"Updated successfully",Toast.LENGTH_SHORT).show();
                Intent newPay = new Intent(this, PayDebt.class);
                startActivity(newPay);
                finish();
            }
        }
    }

    private void confirmPayDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Payment");
        builder.setMessage("You are about to pay a debt. Do you really want to proceed ?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                payDebt();


            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "You've cancelled payment", Toast.LENGTH_SHORT).show();
            }
        });

        builder.show();
    }

    public void goBack(View view){
        Intent goBack = new Intent(this,PayDebt.class);
        startActivity(goBack);
        finish();
    }


    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed()
    {

    }
    private class getDebtCallable implements Callable<List<Debt>>
    private String debtnameCallable;
    List<Debt> rList;
    private getDebtCallable(String debt){
        this.debtnameCallable = debt;
    }
    {

        @Override
        public List<Debt> call(){
            AppDatabase app = AppDatabase.getDatabase(confirmPay.this);
            rList = app.debtDao().getDebt(debt);
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

