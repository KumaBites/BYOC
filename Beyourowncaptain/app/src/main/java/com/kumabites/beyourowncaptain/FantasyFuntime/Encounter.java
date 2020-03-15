package com.kumabites.beyourowncaptain.FantasyFuntime;

import java.util.Random;

public class Encounter {
    private Random player_number, enemy_number;
    private int player_health, player_attack, player_defense, player_added_attack, player_added_defense;
    private int enemy_health, enemy_attack, enemy_defense, enemy_added_attack, enemy_added_defense;
    private int player_total_attack, player_total_defense;

    public void playerAttacks(){
        player_number = new Random();
        enemy_number = new Random();
        player_added_attack = player_number.nextInt(1-6);
        enemy_added_defense = enemy_number.nextInt(1-6);


    }

    public void enemyDefense(){}
}
