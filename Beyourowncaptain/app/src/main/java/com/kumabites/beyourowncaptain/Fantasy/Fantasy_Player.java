package com.kumabites.beyourowncaptain.Fantasy;

public class Fantasy_Player {

    private static String name;
    private static double currentEventID;
    private static double nextEventID1;
    private static double nextEventID2;
    private static double nextEventID3;
    private static String eventToast1;
    private static String eventToast2;
    private static String eventToast3;
    private static int heath;
    private static int attack;
    private static int defense;

    public static int getEnemyId() {
        return enemyId;
    }

    public static void setEnemyId(int enemyId) {
        Fantasy_Player.enemyId = enemyId;
    }

    private static int enemyId;
    public static int getHeath() {
        return heath;
    }

    public static void setHeath(int heath) {
        Fantasy_Player.heath = heath;
    }

    public static int getDefense() {
        return defense;
    }

    public static void setDefense(int defense) {
        Fantasy_Player.defense = defense;
    }

    public static int getAttack() {
        return attack;
    }

    public static void setAttack(int attack) {
        Fantasy_Player.attack = attack;
    }



    public static String getEventToast1() {
        return eventToast1;
    }

    public static void setEventToast1(String eventToast1) {
        Fantasy_Player.eventToast1 = eventToast1;
    }

    public static String getEventToast2() {
        return eventToast2;
    }

    public static void setEventToast2(String eventToast2) {
        Fantasy_Player.eventToast2 = eventToast2;
    }

    public static String getEventToast3() {
        return eventToast3;
    }

    public static void setEventToast3(String eventToast3) {
        Fantasy_Player.eventToast3 = eventToast3;
    }



    public static double getCurrentEventID() {
        return currentEventID;
    }

    public static void setCurrentEventID(double currentEventID) {
        Fantasy_Player.currentEventID = currentEventID;
    }

    public static double getNextEventID1() {
        return nextEventID1;
    }

    public static void setNextEventID1(double nextEventID1) {
        Fantasy_Player.nextEventID1 = nextEventID1;
    }

    public static double getNextEventID2() {
        return nextEventID2;
    }

    public static void setNextEventID2(double nextEventID2) {
        Fantasy_Player.nextEventID2 = nextEventID2;
    }

    public static double getNextEventID3() {
        return nextEventID3;
    }

    public static void setNextEventID3(double nextEventID3) {
        Fantasy_Player.nextEventID3 = nextEventID3;
    }



    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Fantasy_Player.name = name;
    }




}
