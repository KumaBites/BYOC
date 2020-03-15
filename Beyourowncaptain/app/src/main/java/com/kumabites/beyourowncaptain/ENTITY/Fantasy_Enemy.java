package com.kumabites.beyourowncaptain.ENTITY;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class Fantasy_Enemy {
    @PrimaryKey
    private int enemyId;
    private int enemy_health;
    private int enemy_attack;
    private int enemy_defense;

    public int getEnemyId() {
        return enemyId;
    }

    public void setEnemyId(int enemyId) {
        this.enemyId = enemyId;
    }

    public int getEnemy_health() {
        return enemy_health;
    }

    public void setEnemy_health(int enemy_health) {
        this.enemy_health = enemy_health;
    }

    public int getEnemy_attack() {
        return enemy_attack;
    }

    public void setEnemy_attack(int enemy_attack) {
        this.enemy_attack = enemy_attack;
    }

    public int getEnemy_defense() {
        return enemy_defense;
    }

    public void setEnemy_defense(int enemy_defense) {
        this.enemy_defense = enemy_defense;
    }
}