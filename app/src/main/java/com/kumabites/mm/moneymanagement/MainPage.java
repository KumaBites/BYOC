package com.kumabites.mm.moneymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;


import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.kumabites.mm.R;
import com.kumabites.mm.moneymanagement.CreateDebt.AddDebt;
import com.kumabites.mm.moneymanagement.Delete.DeleteDebt;
import com.kumabites.mm.moneymanagement.Pay.PayDebt;

import java.util.ArrayList;

public class MainPage extends AppCompatActivity {
    private EditText wMessage;
    private ListView listView;
    private String addDebt,viewDebt,deleteDebt,payDebt, debtMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);


        //takes value from static variable stored at log in and displays to user
        wMessage = (EditText) findViewById(R.id.welcomeMessage);
        wMessage.setText("Welcome user " + CurrentUser.getUsername());


        //creates list to display to user
        listView = (ListView) findViewById(R.id.listView);
        final ArrayList<String> mainArrayList = new ArrayList<>();
        mainArrayList.add("Add Debt");
        mainArrayList.add("View Debt");
        mainArrayList.add("Delete Debt");
        mainArrayList.add("Pay Debt");
        mainArrayList.add("Debt Main");


        //Creates intents to use when item selected form the list
        final Intent addD = new Intent(this, AddDebt.class);
        final Intent viewD = new Intent(this,ViewDebt.class);
        final Intent delD = new Intent(this, DeleteDebt.class);
        final Intent payD = new Intent(this, PayDebt.class);
        final Intent DebtD = new Intent (this, DebtMain.class);

        //store string inside variables to be compared later with onClicklistener
        addDebt = "Add Debt";
        viewDebt = "View Debt";
        deleteDebt = "Delete Debt";
        payDebt = "Pay Debt";
        debtMain = "Debt Main";



        //Creates adapter for the array
        ArrayAdapter mainAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mainArrayList);

        listView.setAdapter(mainAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String position = mainArrayList.get(i);

                Toast.makeText(getBaseContext(), position, Toast.LENGTH_SHORT).show();
                if (position.equals(addDebt)) {
                    startActivity(addD);
                    finish();
                }
                else if (position.equals(viewDebt)){
                    startActivity(viewD);
                    finish();
                }
                else if (position.equals(deleteDebt)){
                    startActivity(delD);
                        finish();}
                else if(position.equals(payDebt)){
                    startActivity(payD);
                    finish();
                }
                else if(position.equals(debtMain)){
                    startActivity(DebtD);
                    finish();
                }
            }

        });
    }
    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed()
    {

    }
    public void goBack(View view){

        Intent goBack = new Intent(this,MainActivity.class);
        startActivity(goBack);
        CurrentUser.setUsername("");
        Toast.makeText(getBaseContext(),"You have successfully logged out!",Toast.LENGTH_SHORT).show();
        finish();
    }

}




