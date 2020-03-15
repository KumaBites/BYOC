package com.kumabites.beyourowncaptain.ENTITY;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class Fantasy_Enemy {

    public static int getEnemyId() {
        return enemyId;
    }

    public static void setEnemyId(int enemyId) {
        Fantasy_Enemy.enemyId = enemyId;
    }

    public static int getEnemy_health() {
        return enemy_health;
    }

    public static void setEnemy_health(int enemy_health) {
        Fantasy_Enemy.enemy_health = enemy_health;
    }

    public static int getEnemy_attack() {
        return enemy_attack;
    }

    public static void setEnemy_attack(int enemy_attack) {
        Fantasy_Enemy.enemy_attack = enemy_attack;
    }

    public static int getEnemy_defense() {
        return enemy_defense;
    }

    public static void setEnemy_defense(int enemy_defense) {
        Fantasy_Enemy.enemy_defense = enemy_defense;
    }

    @PrimaryKey
    private static int enemyId;
    private static int enemy_health;
    private static int enemy_attack;
    private static int enemy_defense;

}