package com.kumabites.beyourowncaptain.GettingReady;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.kumabites.beyourowncaptain.Player;
import com.kumabites.beyourowncaptain.R;
import com.kumabites.beyourowncaptain.Story_Select;

import java.util.ArrayList;
import java.util.List;


public class Event extends AppCompatActivity {
    private RecyclerView event;
    private List<EventModel> eventList;
    private EventRecyclerViewAdapter eAdapter;
    private TextView descrption;
    boolean hygenie, breakfast, clothes, hair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        event = findViewById(R.id.EventRecyclerView);
        descrption = findViewById(R.id.Description);
        hygenie = Player.getHygiene();
        breakfast = Player.getBreakfast();
        clothes = Player.isClothes();
        hair = Player.isHair();
        if (hygenie == false)
        {

            event.setLayoutManager(new LinearLayoutManager(this));
            descrption.setText("What to do with hygiene");
            eventList= new ArrayList<>();
            eventList.add(new EventModel("Alan","Husband","Kiss Him","Snuggle Him","Shag him"));
            eAdapter = new EventRecyclerViewAdapter(eventList,this);
            event.setAdapter(eAdapter);
            Player.setHygiene(true);

        }
        else if(hygenie == true && breakfast == false) {
            event.setLayoutManager(new LinearLayoutManager(this));
            descrption.setText("What to do with alan breakfast");
            eventList = new ArrayList<>();
            eventList.add(new EventModel("Alan", "Husband", "Kiss Him", "Snuggle Him", "Shag him"));
            eAdapter = new EventRecyclerViewAdapter(eventList, this);
            event.setAdapter(eAdapter);
            Player.setBreakfast(true);
        }
        else if(hygenie == true  && breakfast == true && clothes == false) {
            event.setLayoutManager(new LinearLayoutManager(this));
            descrption.setText("What to do with alan clothes");
            eventList = new ArrayList<>();
            eventList.add(new EventModel("Alan", "Husband", "Kiss Him", "Snuggle Him", "Shag him"));
            eAdapter = new EventRecyclerViewAdapter(eventList, this);
            event.setAdapter(eAdapter);
            Player.setClothes(true);
        }
        else if(hygenie == true  && breakfast == true && clothes == true && hair == false) {
            event.setLayoutManager(new LinearLayoutManager(this));
            descrption.setText("What to do with alan hair");
            eventList = new ArrayList<>();
            eventList.add(new EventModel("Alan", "Husband", "Kiss Him", "Snuggle Him", "Shag him"));
            eAdapter = new EventRecyclerViewAdapter(eventList, this);
            event.setAdapter(eAdapter);
            Player.setHair(true);
        }
        else if(hygenie == true  && breakfast == true && clothes == true && hair == true) {
            event.setLayoutManager(new LinearLayoutManager(this));
            descrption.setText("Adventure finished");
            eventList = new ArrayList<>();
            eventList.add(new EventModel("Alan", "Husband", "Kiss Him", "Snuggle Him", "Shag him"));
            eAdapter = new EventRecyclerViewAdapter(eventList, this);
            event.setAdapter(eAdapter);
            Player.setClothes(true);
            Intent intent = new Intent(this,Story_Select.class);
            startActivity(intent);
        }
    }


    public void quit (View view){

        Intent quit = new Intent(this,Story_Select.class);
        startActivity(quit);
        finish();

    }
    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {

    }
}
