package com.kumabites.beyourowncaptain.Fantasy;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.kumabites.beyourowncaptain.ENTITY.Events;
import com.kumabites.beyourowncaptain.ENTITY.Fantasy_Enemy;
import com.kumabites.beyourowncaptain.ENTITY.Fantasy_Events;
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
    private List<Fantasy_Events> allStoryEventList;
    private List<Fantasy_Enemy> enemyEventList;
    private Fantasy_EventRecyclerViewAdapter eAdapter;
    private TextView descrption;
    private int enemyId, enemyCheck;
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
        enemyCheck = Fantasy_Player.getEnemyCheck();
        if(currentEventID == 0.0)
        {

        storyEndAlert();

        }
        else if(enemyCheck==1){
            Intent battle = new Intent(this, Fantasy_Battle.class);
            startActivity(battle);
            finish();

            }

        else {
            event.setLayoutManager(new LinearLayoutManager(this));
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            getFantasyEventCallable newEvent = new getFantasyEventCallable();
            Future<List<Fantasy_Events>> future = executorService.submit(newEvent);
            List<Fantasy_Events> result = null;
            try {
                result = future.get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }



            allStoryEventList =result;
            currentEventList = new ArrayList<>();

            for (Fantasy_Events EM : allStoryEventList) {
                Fantasy_Player.setNextEventID1(EM.getFantasyNextEventID1());
                Fantasy_Player.setNextEventID2(EM.getFantasyNextEventID2());
                Fantasy_Player.setNextEventID3(EM.getFantasyNextEventID3());
                Fantasy_Player.setEventToast1(EM.getFantasyEventToast1());
                Fantasy_Player.setEventToast2(EM.getFantasyEventToast2());
                Fantasy_Player.setEventToast3(EM.getFantasyEventToast3());

                enemyCheck = EM.getEnemyCheck();
                Fantasy_Player.setEnemyCheck(enemyCheck);
                enemyId = EM.getEnemyId();
                Fantasy_Enemy_Encounter.setEnemyId(enemyId);
                descrption.setText(EM.getFantasyEventDescription());
                String eventName = EM.getFantasyEventName();
                Double eventID = EM.getFantasyEventId();
                String eventDescription = EM.getFantasyEventDescription();
                String eventChoice1 = EM.getFantasyEventChoice1();
                String eventChoice2 = EM.getFantasyEventChoice2();
                String eventChoice3 = EM.getFantasyEventChoice3();
                double eventChoiceID1 = EM.getFantasyNextEventID1();
                double eventChoiceID2 = EM.getFantasyNextEventID2();
                double eventChoiceID3 = EM.getFantasyNextEventID3();
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
    private class getFantasyEventCallable implements Callable<List<Fantasy_Events>>

    {
        List<Fantasy_Events> rList;
        @Override
        public List<Fantasy_Events> call(){
           rList = eDatabase.fantasyDao().getSelectEvent(currentEventID);

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

