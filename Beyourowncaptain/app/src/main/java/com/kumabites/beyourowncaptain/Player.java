package com.kumabites.beyourowncaptain;

public class Player {

    private static String name;
    private static int Health;
    private static String items;
    private static int Mana;
    private static double currentEventID;
    private static double nextEventID1;
    private static double nextEventID2;
    private static double nextEventID3;

    public static String getEventToast1() {
        return eventToast1;
    }

    public static void setEventToast1(String eventToast1) {
        Player.eventToast1 = eventToast1;
    }

    public static String getEventToast2() {
        return eventToast2;
    }

    public static void setEventToast2(String eventToast2) {
        Player.eventToast2 = eventToast2;
    }

    public static String getEventToast3() {
        return eventToast3;
    }

    public static void setEventToast3(String eventToast3) {
        Player.eventToast3 = eventToast3;
    }

    private static String eventToast1;
    private static String eventToast2;
    private static String eventToast3;

    public static double getCurrentEventID() {
        return currentEventID;
    }

    public static void setCurrentEventID(double currentEventID) {
        Player.currentEventID = currentEventID;
    }

    public static double getNextEventID1() {
        return nextEventID1;
    }

    public static void setNextEventID1(double nextEventID1) {
        Player.nextEventID1 = nextEventID1;
    }

    public static double getNextEventID2() {
        return nextEventID2;
    }

    public static void setNextEventID2(double nextEventID2) {
        Player.nextEventID2 = nextEventID2;
    }

    public static double getNextEventID3() {
        return nextEventID3;
    }

    public static void setNextEventID3(double nextEventID3) {
        Player.nextEventID3 = nextEventID3;
    }



    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Player.name = name;
    }

    public static int getHealth() {
        return Health;
    }

    public static void setHealth(int health) {
        Health = health;
    }

    public static String getItems() {
        return items;
    }

    public static void setItems(String items) {
        Player.items = items;
    }

    public static int getMana() {
        return Mana;
    }

    public static void setMana(int mana) {
        Mana = mana;
    }



}
