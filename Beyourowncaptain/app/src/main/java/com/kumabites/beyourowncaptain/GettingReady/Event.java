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


import com.kumabites.beyourowncaptain.ENTITY.Events;
import com.kumabites.beyourowncaptain.Player;
import com.kumabites.beyourowncaptain.R;
import com.kumabites.beyourowncaptain.Story_Select;

import java.util.ArrayList;
import java.util.List;


public class Event extends AppCompatActivity {
    private RecyclerView event;
    private List<EventModel> currrentEventList;
    private List<Events> allStoryEventList;
    private EventRecyclerViewAdapter eAdapter;
    private TextView descrption;
    private double currentEventID ,nextID, nextID2,nextID3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        event = findViewById(R.id.EventRecyclerView);
        descrption = findViewById(R.id.Description);
        currentEventID = Player.getCurrentEventID();


        event.setLayoutManager(new LinearLayoutManager(this));
        descrption.setText("What to do with hygiene");
        allStoryEventList = new ArrayList<>();
        currrentEventList = new ArrayList<>();
        allStoryEventList.add(new Events( 1.0, "Alan 1.0",  "What to do with alan", 1.1 , "Kiss Him", 1.2,"Snuggle Him",1.3, "Shag him"));
        allStoryEventList.add(new Events( 1.1, "Alan 1.1",  "What to do with 1.1", 1.1 , "Kiss Him", 1.2,"Snuggle Him",1.3, "Shag him"));
       allStoryEventList.add(new Events( 1.2, "Alan 1.2", "What to do with 1.2", 2.1 , "Kiss Him", 2.2,"Snuggle Him",2.3, "Shag him"));
       allStoryEventList.add(new Events( 1.3, "Alan 1.3", "What to do with 1.3", 3.1 , "Kiss Him", 3.2,"Snuggle Him",3.3, "Shag him"));
        for(Events EM : allStoryEventList)
        {
            double eventIdcheck;
            eventIdcheck = EM.getEventID();
            if(eventIdcheck == currentEventID)
            {
                currrentEventList.add(new Event)

            }
        }
        eAdapter = new EventRecyclerViewAdapter(currrentEventList, this);
        event.setAdapter(eAdapter);


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

