package com.kumabites.beyourowncaptain.Fantasy;

import com.kumabites.beyourowncaptain.ENTITY.Fantasy_Enemy;

import java.util.Random;

public class Encounter {
    private Random player_number, enemy_number;
    private int player_health, player_attack, player_defense, player_added_attack, player_added_defense;
    private int enemy_health, enemy_attack, enemy_defense, enemy_added_attack, enemy_added_defense;
    private int player_total_attack, player_total_defense, enemy_total_defense, enemy_total_attack;
    private  int battle_result;

    public void playerAttacks(){
        player_number = new Random();
        enemy_number = new Random();
        player_added_attack = player_number.nextInt(1-6);
        enemy_added_defense = enemy_number.nextInt(1-6);
        player_total_attack = Fantasy_Player.getAttack() + player_added_attack;
        enemy_total_defense= Fantasy_Player.getDefense() + enemy_added_defense;
        battle_result = enemy_total_defense - player_total_attack;
        Fantasy_Enemy.setEnemy_health(Fantasy_Enemy.getEnemy_health() - Math.abs(battle_result));

    }

    public void enemyAttacks(){
        player_number = new Random();
        enemy_number = new Random();
        player_added_defense = player_number.nextInt(1-6);
        enemy_added_attack= enemy_number.nextInt(1-6);
        player_total_defense = Fantasy_Player.getDefense() + player_added_defense;
        enemy_total_attack= Fantasy_Enemy.getEnemy_attack() + enemy_added_attack;
        battle_result = player_total_defense - enemy_total_attack;
        Fantasy_Player.setHeath(Fantasy_Player.getHeath() - Math.abs(battle_result));


    }
}
