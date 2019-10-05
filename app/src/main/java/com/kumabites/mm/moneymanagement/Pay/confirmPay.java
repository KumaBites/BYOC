package com.kumabites.mm.moneymanagement.Pay;

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


import java.util.List;

public class confirmPay extends AppCompatActivity {
    private String newDebtName;
    private TextView payDebt, confirmPay, debtName;
    private int pText, absolutePText, debtRemain, debtPay;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_pay);
        debtName = (TextView) findViewById(R.id.debtName);
        confirmPay= (TextView)findViewById(R.id.confirmPay);
        Intent confirm = getIntent();


        newDebtName = confirm.getStringExtra("Debt Name");
        debtName.setText("How much do you want to pay to debt : " + newDebtName + "?");


    }

    public void payDebt(View view) {
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
                List<Debt> getOldDebt = MainActivity.appDatabase.debtDao().getDebt(newDText);

                for(Debt oldDebt : getOldDebt)
                {

                    debtRemain = oldDebt.getRemaining();
                    debtRemain = debtRemain - absolutePText;
                    oldDebt.setRemaining(debtRemain);

                    debtPay = oldDebt.getAmount_paid();
                    debtPay = debtPay + pText;
                    oldDebt.setAmount_paid(debtPay);
                    MainActivity.appDatabase.debtDao().updateDebt(oldDebt);

                }

                Toast.makeText(getBaseContext(),"Updated successfully",Toast.LENGTH_SHORT).show();
                Intent newPay = new Intent(this, PayDebt.class);
                startActivity(newPay);
                finish();
            }
        }
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
}

