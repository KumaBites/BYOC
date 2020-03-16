package com.kumabites.beyourowncaptain.Fantasy;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.kumabites.beyourowncaptain.ENTITY.Events;
import com.kumabites.beyourowncaptain.EventsDatabase;
import com.kumabites.beyourowncaptain.R;
import com.kumabites.beyourowncaptain.Story_Select;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class Fantasy_Event extends AppCompatActivity {
    private RecyclerView event;
    private List<Fantasy_EventModel> currentEventList;
    private List<Events> allStoryEventList;
    private Fantasy_EventRecyclerViewAdapter eAdapter;
    private TextView descrption;
    private int enemyId;
    private double currentEventID ,nextID, nextID2,nextID3;
    EventsDatabase eDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        event = findViewById(R.id.EventRecyclerView);
        descrption = findViewById(R.id.Description);
        currentEventID = Fantasy_Player.getCurrentEventID();
        eDatabase = EventsDatabase.getDatabase(this);
        currentEventID = Fantasy_Player.getCurrentEventID();
        if(currentEventID == 0.0)
        {

        storyEndAlert();

        }
        else if(enemyId==1){
            Intent battle = new Intent(this, Fantasy_Battle.class);
            startActivity(battle);
            finish();
        }

        else {
            event.setLayoutManager(new LinearLayoutManager(this));
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            getEventCallable newEvent = new getEventCallable();
            Future<List<Events>> future = executorService.submit(newEvent);
            List<Events> result = null;
            try {
                result = future.get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            allStoryEventList =result;
            currentEventList = new ArrayList<>();

            for (Events EM : allStoryEventList) {
                Fantasy_Player.setNextEventID1(EM.getNextEventID1());
                Fantasy_Player.setNextEventID2(EM.getNextEventID2());
                Fantasy_Player.setNextEventID3(EM.getNextEventID3());
                Fantasy_Player.setEventToast1(EM.getEventToast1());
                Fantasy_Player.setEventToast2(EM.getEventToast2());
                Fantasy_Player.setEventToast3(EM.getEventToast3());
                enemyId =Fantasy_Player.getEnemyId();

                descrption.setText(EM.getEventDescription());
                String eventName = EM.getEventName();
                Double eventID = EM.getEventId();
                String eventDescription = EM.getEventDescription();
                String eventChoice1 = EM.getEventChoice1();
                String eventChoice2 = EM.getEventChoice2();
                String eventChoice3 = EM.getEventChoice3();
                double eventChoiceID1 = EM.getNextEventID1();
                double eventChoiceID2 = EM.getNextEventID2();
                double eventChoiceID3 = EM.getNextEventID3();
                currentEventList.add(new Fantasy_EventModel(eventName, eventID, eventDescription, eventChoiceID1, eventChoice1, eventChoiceID2, eventChoice2, eventChoiceID3, eventChoice3));
            }

            eAdapter = new Fantasy_EventRecyclerViewAdapter(currentEventList, this);
            event.setAdapter(eAdapter);

        }
    }
    private void storyEndAlert() {
        final Intent finish = new Intent(this, Story_Select.class);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("You have completed Alan's dilemma! Why not try one of the other stories?");
        builder.setCancelable(false);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(finish);
                finish();

            }
        });
        builder.show();
    }
    private class getEventCallable implements Callable<List<Events>>

    {
        List<Events> rList;
        @Override
        public List<Events> call(){
           rList = eDatabase.eventsDao().getSelectEvent(currentEventID);

            return rList;

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

