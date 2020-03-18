package com.kumabites.beyourowncaptain.Fantasy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.kumabites.beyourowncaptain.ENTITY.Fantasy_Enemy;
import com.kumabites.beyourowncaptain.EventsDatabase;
import com.kumabites.beyourowncaptain.R;
import com.kumabites.beyourowncaptain.Story_Select;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Fantasy_Battle extends AppCompatActivity {
    private Random player_number, enemy_number;
    private int player_health, player_attack, player_defense, player_added_attack, player_added_defense;
    private int enemy_health, enemy_attack, enemy_defense, enemy_added_attack, enemy_added_defense;
    private int player_total_attack, player_total_defense, enemy_total_defense, enemy_total_attack;
    private int pbattle_result, eBattle_result;
    private  int enemyId;
    private double nextEventId;
    private TextView pHealth, pAttack, pDefense, eHealth, eAttack,eDefense ,pName,eName;
    EventsDatabase eDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fantasy_battle);
        eDatabase = EventsDatabase.getDatabase(this);

        pName =findViewById(R.id.playerNameView);
        pHealth = findViewById(R.id.pHealthView);
        pAttack =findViewById(R.id.pAttack);
        pDefense = findViewById(R.id.pDefense);

        eName =findViewById(R.id.eNameView);
        eHealth =findViewById(R.id.eHealthView);
        eAttack =findViewById(R.id.eAttackView);
        eDefense =findViewById(R.id.eDefenseView);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        getEnemyCallable newEvent = new getEnemyCallable();
        Future<List<Fantasy_Enemy>> future = executorService.submit(newEvent);
        List<Fantasy_Enemy> result = null;
        try {
            result = future.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Fantasy_Enemy> enemyEventList = result;
        for(Fantasy_Enemy newEnemy : enemyEventList){
            Fantasy_Enemy_Encounter.setEnemyId(newEnemy.getEnemyId());
            Fantasy_Enemy_Encounter.setEnemy_attack(newEnemy.getEnemy_attack());
            Fantasy_Enemy_Encounter.setEnemy_health(newEnemy.getEnemy_health());
            Fantasy_Enemy_Encounter.setEnemy_defense(newEnemy.getEnemy_defense());
            Fantasy_Enemy_Encounter.setEnemy_name(newEnemy.getEnemy_name());
            Fantasy_Enemy_Encounter.setNextEventId(newEnemy.getNextEventId());
        }
        pName.setText(Fantasy_Player.getName());
        player_health = Fantasy_Player.getHeath();
        player_attack = Fantasy_Player.getAttack();
        player_defense = Fantasy_Player.getDefense();
        nextEventId = Fantasy_Enemy_Encounter.getNextEventId();

        eName.setText(Fantasy_Enemy_Encounter.getEnemy_name());
        enemy_health = Fantasy_Enemy_Encounter.getEnemy_health();
        enemy_attack = Fantasy_Enemy_Encounter.getEnemy_attack();
        enemy_defense= Fantasy_Enemy_Encounter.getEnemy_defense();

        pHealth.setText(String.valueOf(player_health));
        pAttack.setText(String.valueOf(player_attack));
        pDefense.setText(String.valueOf(player_defense));

        eHealth.setText(String.valueOf(enemy_health));
        eAttack.setText(String.valueOf(enemy_attack));
        eDefense.setText(String.valueOf(enemy_defense));

    }
    public void commenceAttack(View view) {
        playerAttacks();
        enemyAttacks();
        battleCheck();


    }


    public void playerAttacks() {
        player_number = new Random();
        enemy_number = new Random();
        player_added_attack = player_number.nextInt(6);
        enemy_added_defense = enemy_number.nextInt(6);
        player_total_attack = Fantasy_Player.getAttack() + player_added_attack;
        enemy_total_defense = Fantasy_Enemy_Encounter.getEnemy_defense() + enemy_added_defense;
        pbattle_result = enemy_total_defense - player_total_attack;
        enemy_health = enemy_health - Math.abs(pbattle_result);
        Fantasy_Enemy_Encounter.setEnemy_health(enemy_health);
        eHealth.setText(String.valueOf(Fantasy_Enemy_Encounter.getEnemy_health()));

    }

    public void enemyAttacks() {
        player_number = new Random();
        enemy_number = new Random();
        player_added_defense = player_number.nextInt(6);
        enemy_added_attack = enemy_number.nextInt(6);
        player_total_defense = player_defense + player_added_defense;
        enemy_total_attack = enemy_attack + enemy_added_attack;
        eBattle_result = player_total_defense - enemy_total_attack;
        player_health = player_health - Math.abs(eBattle_result);
        Fantasy_Player.setHeath(player_health);
        pHealth.setText(String.valueOf(Fantasy_Player.getHeath()));
  }
  public void battleCheck(){
      final Intent died = new Intent(this, Story_Select.class);
      final Intent survive = new Intent(this, Fantasy_Event.class);
      final int eHCheck = Fantasy_Enemy_Encounter.getEnemy_health();
      final int pHCheck =Fantasy_Player.getHeath();

      if ((eHCheck > 0 && pHCheck > 0)){
          playerAttacks();
          enemyAttacks();
          AlertDialog.Builder builder = new AlertDialog.Builder(this);
          builder.setMessage("You did " + pbattle_result + " damage but the " + Fantasy_Enemy_Encounter.getEnemy_name() + " did " + eBattle_result + "damage!");
          builder.setCancelable(false);
          builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {
              }
          });
          builder.show();
      }
         else if (pHCheck <= 0) {

          AlertDialog.Builder diedBuilder = new AlertDialog.Builder(this);
          diedBuilder.setMessage("You have died!!! Oh well luck was one your side. Why not try again?");
          diedBuilder.setCancelable(false);
          diedBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {
                  Fantasy_Player.setCurrentEventID(nextEventId);
                  startActivity(died);
                  finish();

              }
          });
          diedBuilder.show();
      }
      else if(eHCheck <= 0){

          AlertDialog.Builder wonBuilder = new AlertDialog.Builder(this);
          wonBuilder.setMessage("You have won!! Congratulations! One with the adventure");
          wonBuilder.setCancelable(false);
          wonBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {
                  Fantasy_Player.setEnemyCheck(0);
                  startActivity(survive);
                  finish();

              }
          });
          wonBuilder.show();
      }



  }
    private class getEnemyCallable implements Callable<List<Fantasy_Enemy>>
    {
        List<Fantasy_Enemy> rList;
        @Override
        public List<Fantasy_Enemy> call(){
            rList = eDatabase.fantasyEnemyDao().getSelectEvent(Fantasy_Enemy_Encounter.getEnemyId());


            return rList;

        }
    }
}
