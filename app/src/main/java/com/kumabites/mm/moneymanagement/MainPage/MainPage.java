package com.kumabites.mm.moneymanagement.MainPage;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.kumabites.mm.R;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainPage extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MainPageViewAdapter mAdapter;
    private List<DebtModel> debtListArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        //getting the recyclerview from xml
        mRecyclerView = findViewById(R.id.idRecycleMainPage);
        //mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Populate the products
        debtListArray = new ArrayList<>();
        debtListArray.add(new DebtModel("New Debt"));
        debtListArray.add(new DebtModel("Delete Debt"));
        debtListArray.add(new DebtModel("Pay Debt"));
        debtListArray.add(new DebtModel("View Debt"));
        debtListArray.add(new DebtModel("Log Out"));


        //set adapter to recyclerview
        mAdapter = new MainPageViewAdapter(debtListArray,this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {

    }
}