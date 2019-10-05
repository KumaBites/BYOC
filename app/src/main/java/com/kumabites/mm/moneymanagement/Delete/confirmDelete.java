package com.kumabites.mm.moneymanagement.Delete;

import MMENTITY.Debt;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.kumabites.mm.R;
import com.kumabites.mm.moneymanagement.MainActivity;
import com.kumabites.mm.moneymanagement.Pay.PayDebt;


import java.util.List;

public class confirmDelete extends AppCompatActivity {
private String confirmDelete, deleteName,deleteDebtName;
private TextView confirmText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_delete);
        confirmText = (TextView)findViewById(R.id.confirmD);
        Intent confirm = getIntent();


        deleteName = confirm.getStringExtra("Debt Name");
        confirmText.setText("Do you want to delete debt : "+deleteName+"?");

    }

    public void confirmD(View view){

        List<Debt> getDeleteList = MainActivity.appDatabase.debtDao().getDebt(deleteName);
        for(Debt debtDelete : getDeleteList){
            deleteDebtName = debtDelete.getDebt_name();
        }
        final Intent finishDelete = new Intent(this, DeleteDebt.class);
        MainActivity.appDatabase.debtDao().deleteOne(deleteDebtName);
        Toast.makeText(this, "Successfully Deleted!!", Toast.LENGTH_SHORT).show();
        startActivity(finishDelete);
        finish();
    }
    public void back(View back) {
        final Intent goBack = new Intent(this, PayDebt.class);
        startActivity(goBack);
        finish();

    }
    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed()
    {

    }

}
