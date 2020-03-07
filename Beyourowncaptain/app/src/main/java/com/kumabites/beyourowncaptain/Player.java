package com.kumabites.beyourowncaptain;

public class Player {

    private static String name;
    private static int Health;
    private static String items;
    private static int Mana;
    private static boolean hygiene, breakfast, clothes, hair;


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

    public static boolean getHygiene() {
        return hygiene;
    }

    public static void setHygiene(boolean hygiene) {
        Player.hygiene = hygiene;
    }

    public static boolean getBreakfast() {
        return breakfast;
    }

    public  static void setBreakfast(boolean breakfast) {
        Player.breakfast = breakfast; }

    public static boolean isClothes() {
        return clothes;
    }

    public static void setClothes(boolean clothes) {
        Player.clothes = clothes;
    }

    public static boolean isHair() {
        return hair;
    }

    public static void setHair(boolean hair) {
        Player.hair = hair;
    }


}
