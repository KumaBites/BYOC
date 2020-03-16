package com.kumabites.beyourowncaptain.Fantasy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.kumabites.beyourowncaptain.R;
import com.kumabites.beyourowncaptain.Story_Select;

import java.util.Random;

public class Fantasy_Battle extends AppCompatActivity {
    private Random player_number, enemy_number;
    private int player_health, player_attack, player_defense, player_added_attack, player_added_defense;
    private int enemy_health, enemy_attack, enemy_defense, enemy_added_attack, enemy_added_defense;
    private int player_total_attack, player_total_defense, enemy_total_defense, enemy_total_attack;
    private int battle_result;
    private double nextEventId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fantasy_battle);

        player_health = Fantasy_Player.getHeath();
        player_attack = Fantasy_Player.getAttack();
        player_defense = Fantasy_Player.getDefense();
        nextEventId = Fantasy_Enemy_Encounter.getNextEventId();

        enemy_health = Fantasy_Enemy_Encounter.getEnemy_health();
        enemy_attack = Fantasy_Enemy_Encounter.getEnemy_attack();
        enemy_defense= Fantasy_Enemy_Encounter.getEnemy_defense();

    }
    public void commenceAttack(View view) {
        final Intent died = new Intent(this, Story_Select.class);
        final Intent survive = new Intent(this,Fantasy_Event.class);
        if (player_health <= 0) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("You have died!!! Oh well luck was one your side. Why not try again?");
            builder.setCancelable(false);
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Fantasy_Player.setCurrentEventID(nextEventId);
                    startActivity(died);
                    finish();

                }
            });
            builder.show();
        }
        else if(enemy_health <= 0){

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("You have won!! Congratulations! One with the adventure");
            builder.setCancelable(false);
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(survive);
                    finish();

                }
            });
            builder.show();
        }
        else{
            playerAttacks();
            enemyAttacks();
        }
    }


    public void playerAttacks() {
        player_number = new Random();
        enemy_number = new Random();
        player_added_attack = player_number.nextInt(1 - 6);
        enemy_added_defense = enemy_number.nextInt(1 - 6);
        player_total_attack = player_attack + player_added_attack;
        enemy_total_defense = enemy_defense + enemy_added_defense;
        battle_result = enemy_total_defense - player_total_attack;
        enemy_health = enemy_health - Math.abs(battle_result);

    }

    public void enemyAttacks() {
        player_number = new Random();
        enemy_number = new Random();
        player_added_defense = player_number.nextInt(1 - 6);
        enemy_added_attack = enemy_number.nextInt(1 - 6);
        player_total_defense = player_defense + player_added_defense;
        enemy_total_attack = enemy_attack + enemy_added_attack;
        battle_result = player_total_defense - enemy_total_attack;
        player_health = player_health - Math.abs(battle_result);
    }

}
