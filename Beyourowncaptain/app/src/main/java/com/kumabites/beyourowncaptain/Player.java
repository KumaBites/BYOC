package com.kumabites.beyourowncaptain;

public class Player {

    private static String name;
    private static int Health;
    private static String items;
    private static int Mana;
    private static boolean hygiene, breakfast, clothes, hair;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return Health;
    }

    public void setHealth(int health) {
        Health = health;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public int getMana() {
        return Mana;
    }

    public void setMana(int mana) {
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

    public boolean isClothes() {
        return clothes;
    }

    public void setClothes(boolean clothes) {
        this.clothes = clothes;
    }

    public boolean isHair() {
        return hair;
    }

    public void setHair(boolean hair) {
        this.hair = hair;
    }


}
