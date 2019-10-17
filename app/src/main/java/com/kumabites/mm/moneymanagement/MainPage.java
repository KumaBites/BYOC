package com.kumabites.mm.moneymanagement;
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
        mRecyclerView = (RecyclerView)findViewById(R.id.idRecycleMainPage);
        //mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Populate the products
        debtListArray = new ArrayList<>();
        debtListArray.add(new DebtModel("Mango"));
        debtListArray.add(new DebtModel("Pineapple"));


        //set adapter to recyclerview
        mAdapter = new MainPageViewAdapter(debtListArray,this);
        mRecyclerView.setAdapter(mAdapter);
    }
}