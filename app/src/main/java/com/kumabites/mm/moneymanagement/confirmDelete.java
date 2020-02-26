package com.kumabites.mm.moneymanagement;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
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

public class confirmDelete extends AppCompatActivity {
private String deleteName,deleteDebtName;
private TextView confirmText;
    final AppDatabase appDatabase = AppDatabase.getDatabase(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_delete);
        confirmText = findViewById(R.id.confirmD);
        Intent confirm = getIntent();


        deleteName = confirm.getStringExtra("Debt Name");
        confirmText.setText("Do you want to delete debt : "+deleteName+"?");

    }
    public void debtDeleted(View view ){
        confirmDeleteDebt();
    }
    public void confirmD() throws ExecutionException, InterruptedException {
        getDebtFuture getDebt = new getDebtFuture();
        List<Debt> getDeleteList = getDebt.result ;
        for(Debt debtDelete : getDeleteList){
            deleteDebtName = debtDelete.getDebt_name();
        }
        final Intent finishDelete = new Intent(this, DeleteDebt.class);
        new deleteOneAsyncTask(deleteDebtName).execute();
        Toast.makeText(this, "Successfully Deleted!!", Toast.LENGTH_SHORT).show();
        startActivity(finishDelete);
        finish();
    }
    private void confirmDeleteDebt() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Delete Action!");
        builder.setMessage("You are about to delete the debt. Do you really want to proceed ?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    confirmD();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "You've changed your mind to delete the debt", Toast.LENGTH_SHORT).show();
            }
        });

        builder.show();
    }
    public void back(View back) {
        final Intent goBack = new Intent(this, DeleteDebt.class);
        startActivity(goBack);
        finish();

    }
    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed()
    {

    }
 private class deleteOneAsyncTask extends AsyncTask<Void,Void,Void> {
        private String debtName;
        private AppDatabase db;
        public deleteOneAsyncTask(String debt){
            this.debtName = debt;
        }
     @Override
     protected Void doInBackground(Void... params) {

         db = AppDatabase.getDatabase(confirmDelete.this);
         db.debtDao().deleteOne(debtName);
         return null;
     }
 }
    private class getDebtCallable implements Callable<List<Debt>>

    {
        List<Debt> rList;
        @Override
        public List<Debt> call(){
            AppDatabase app = AppDatabase.getDatabase(confirmDelete.this);
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
