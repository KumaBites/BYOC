package com.kumabites.beyourowncaptain.Fantasy;

public class Fantasy_Enemy_Encounter {

    private static int enemyId;
    private static String enemy_name;
    private static int enemy_health;
    private static int enemy_attack;
    private static int enemy_defense;
    private static double nextEventId;
    public static int getEnemyId() {
        return enemyId;
    }

    public static void setEnemyId(int enemyId) {
        Fantasy_Enemy_Encounter.enemyId = enemyId;
    }

    public static String getEnemy_name() {
        return enemy_name;
    }

    public static void setEnemy_name(String enemy_name) {
        Fantasy_Enemy_Encounter.enemy_name = enemy_name;
    }

    public static int getEnemy_health() {
        return enemy_health;
    }

    public static void setEnemy_health(int enemy_health) {
        Fantasy_Enemy_Encounter.enemy_health = enemy_health;
    }

    public static int getEnemy_attack() {
        return enemy_attack;
    }

    public static void setEnemy_attack(int enemy_attack) {
        Fantasy_Enemy_Encounter.enemy_attack = enemy_attack;
    }

    public static int getEnemy_defense() {
        return enemy_defense;
    }

    public static void setEnemy_defense(int enemy_defense) {
        Fantasy_Enemy_Encounter.enemy_defense = enemy_defense;
    }

    public static double getNextEventId() {
        return nextEventId;
    }

    public static void setNextEventId(double nextEventId) {
        Fantasy_Enemy_Encounter.nextEventId = nextEventId;
    }


}

