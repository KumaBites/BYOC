package com.kumabites.mm.moneymanagement.Pay;

import MMENTITY.Debt;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.kumabites.mm.R;
import com.kumabites.mm.moneymanagement.CurrentUser;
import com.kumabites.mm.moneymanagement.MainActivity;
import com.kumabites.mm.moneymanagement.MainPage;


import java.util.ArrayList;
import java.util.List;

public class PayDebt extends AppCompatActivity {
    ListView viewDebt;
    private ArrayList<String> stringDebt = new ArrayList<>();
    private String debtName,debtAmount, debtPaid,debtRemaining,checkDebtName,debtCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_debt);
        final Intent payDebt = new Intent(this, confirmPay.class);

        viewDebt = (ListView) findViewById(R.id.payList);
        List<Debt> getAllDebtList = MainActivity.appDatabase.debtDao().getAll(CurrentUser.getUsername());
        for(Debt debt : getAllDebtList)
        {
            debtName = debt.getDebt_name();
            stringDebt.add("Debt Name: "+debtName);
            debtAmount = String.valueOf(debt.getDebt_amount());
            stringDebt.add("Debt Amount: "+(debtAmount));
            debtPaid = String.valueOf((debt.getAmount_paid()));
            stringDebt.add("How much debt Paid: "+(debtPaid));
            debtRemaining = String.valueOf(debt.getRemaining());
            stringDebt.add("Debt Remaining: "+(debtRemaining));
            debtCategory = debt.getCategoty();
            stringDebt.add("Debt Category is: " + debtCategory);
            stringDebt.add("");
        }
        ArrayAdapter viewDebtAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, stringDebt);
        viewDebt.setAdapter(viewDebtAdapter);
        viewDebt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String position = stringDebt.get(i);
                position = position.substring(11);
                position = position.trim();
                List<Debt> checkDebtInput = MainActivity.appDatabase.debtDao().getDebt(position);
                for(Debt debt : checkDebtInput)
                {
                    checkDebtName = debt.getDebt_name();

                    if (position.equals(checkDebtName)) {
                        payDebt.putExtra("Debt Name",position);
                        startActivity(payDebt);
                        finish();

                    }
                    else if (position == null){
                        Toast.makeText(getBaseContext(), "Sorry can not find debt!", Toast.LENGTH_SHORT).show();
                    }
                    else {Toast.makeText(getBaseContext(), "Sorry can not find debt!", Toast.LENGTH_SHORT).show();}

                }
            }


        });
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
