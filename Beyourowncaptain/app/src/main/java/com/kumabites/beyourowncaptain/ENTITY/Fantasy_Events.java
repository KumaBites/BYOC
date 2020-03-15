package com.kumabites.beyourowncaptain.ENTITY;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Fantasy_Events {

    @PrimaryKey
    private double fantasyEventId;
    private String fantasyEventName;
    private String fantasyEventDescription;
    private double fantasyNextEventID1;
    private String fantasyEventChoice1;
    private double fantasyNextEventID2;
    private String fantasyEventChoice2;
    private double fantasyNextEventID3;
    private String fantasyEventChoice3;
    private String fantasyEventToast1;
    private String fantasyEventToast2;
    private String fantasyEventToast3;

    public double getFantasyEventId() {
        return fantasyEventId;
    }

    public void setFantasyEventId(double fantasyEventId) {
        this.fantasyEventId = fantasyEventId;
    }

    public String getFantasyEventName() {
        return fantasyEventName;
    }

    public void setFantasyEventName(String fantasyEventName) {
        this.fantasyEventName = fantasyEventName;
    }

    public String getFantasyEventDescription() {
        return fantasyEventDescription;
    }

    public void setFantasyEventDescription(String fantasyEventDescription) {
        this.fantasyEventDescription = fantasyEventDescription;
    }

    public double getFantasyNextEventID1() {
        return fantasyNextEventID1;
    }

    public void setFantasyNextEventID1(double fantasyNextEventID1) {
        this.fantasyNextEventID1 = fantasyNextEventID1;
    }

    public String getFantasyEventChoice1() {
        return fantasyEventChoice1;
    }

    public void setFantasyEventChoice1(String fantasyEventChoice1) {
        this.fantasyEventChoice1 = fantasyEventChoice1;
    }

    public double getFantasyNextEventID2() {
        return fantasyNextEventID2;
    }

    public void setFantasyNextEventID2(double fantasyNextEventID2) {
        this.fantasyNextEventID2 = fantasyNextEventID2;
    }

    public String getFantasyEventChoice2() {
        return fantasyEventChoice2;
    }

    public void setFantasyEventChoice2(String fantasyEventChoice2) {
        this.fantasyEventChoice2 = fantasyEventChoice2;
    }

    public double getFantasyNextEventID3() {
        return fantasyNextEventID3;
    }

    public void setFantasyNextEventID3(double fantasyNextEventID3) {
        this.fantasyNextEventID3 = fantasyNextEventID3;
    }

    public String getFantasyEventChoice3() {
        return fantasyEventChoice3;
    }

    public void setFantasyEventChoice3(String fantasyEventChoice3) {
        this.fantasyEventChoice3 = fantasyEventChoice3;
    }

    public String getFantasyEventToast1() {
        return fantasyEventToast1;
    }

    public void setFantasyEventToast1(String fantasyEventToast1) {
        this.fantasyEventToast1 = fantasyEventToast1;
    }

    public String getFantasyEventToast2() {
        return fantasyEventToast2;
    }

    public void setFantasyEventToast2(String fantasyEventToast2) {
        this.fantasyEventToast2 = fantasyEventToast2;
    }

    public String getFantasyEventToast3() {
        return fantasyEventToast3;
    }

    public void setFantasyEventToast3(String fantasyEventToast3) {
        this.fantasyEventToast3 = fantasyEventToast3;
    }



}
