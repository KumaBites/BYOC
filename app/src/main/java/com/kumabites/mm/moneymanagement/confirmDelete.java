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
    public void confirmD(){

        List<Debt> getDeleteList = appDatabase.debtDao().getDebt(deleteName);
        for(Debt debtDelete : getDeleteList){
            deleteDebtName = debtDelete.getDebt_name();
        }
        final Intent finishDelete = new Intent(this, DeleteDebt.class);
        appDatabase.debtDao().deleteOne(deleteDebtName);
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
            confirmD();
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

}
