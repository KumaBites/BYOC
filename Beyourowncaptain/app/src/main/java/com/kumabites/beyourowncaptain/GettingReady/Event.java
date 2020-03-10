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
import com.kumabites.beyourowncaptain.EventsDatabase;
import com.kumabites.beyourowncaptain.Player;
import com.kumabites.beyourowncaptain.R;
import com.kumabites.beyourowncaptain.Story_Select;
import com.kumabites.beyourowncaptain.DAO.EventsDao;

import java.util.ArrayList;
import java.util.List;


public class Event extends AppCompatActivity {
    private RecyclerView event;
    private List<EventModel> currentEventList;
    private List<Events> allStoryEventList;
    private EventRecyclerViewAdapter eAdapter;
    private TextView descrption;
    private double currentEventID ,nextID, nextID2,nextID3;
    EventsDatabase eDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        event = findViewById(R.id.EventRecyclerView);
        descrption = findViewById(R.id.Description);
        currentEventID = Player.getCurrentEventID();
        eDatabase = EventsDatabase.getDatabase(this);
        currentEventID = Player.getCurrentEventID();
        event.setLayoutManager(new LinearLayoutManager(this));


        allStoryEventList = eDatabase.eventsDao().getSelectEvent(currentEventID);
        currentEventList= new ArrayList<>();

        for(Events EM : allStoryEventList)
        {
               Player.setNextEventID1(EM.getNextEventID1());
               Player.setNextEventID2(EM.getNextEventID2());
               Player.setNextEventID3(EM.getNextEventID3());
            String eventName = EM.getEventName();
            Double eventID = EM.getEventId();
            String eventDescription = EM.getEventDescription();
            String eventChoice1 = EM.getEventChoice1();
            String eventChoice2 = EM.getEventChoice2();
            String eventChoice3 =  EM.getEventChoice3();
            double eventChoiceID1 = EM.getNextEventID1();
            double eventChoiceID2 = EM.getNextEventID2();
            double eventChoiceID3 =EM.getNextEventID3();
            currentEventList.add(new EventModel(eventName,eventID,eventDescription,eventChoiceID1,eventChoice1,eventChoiceID2,eventChoice2,eventChoiceID3,eventChoice3));
        }

        eAdapter = new EventRecyclerViewAdapter(currentEventList, this);
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

