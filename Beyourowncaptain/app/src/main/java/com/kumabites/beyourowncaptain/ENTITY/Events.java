package com.kumabites.beyourowncaptain.ENTITY;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Events {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int eventId;
    private String eventName;
    private String eventDescription;
    private String eventChoice1;
    private String eventChoice2;
    private String eventChoice3;

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventChoice1() {
        return eventChoice1;
    }

    public void setEventChoice1(String eventChoice1) {
        this.eventChoice1 = eventChoice1;
    }

    public String getEventChoice2() {
        return eventChoice2;
    }

    public void setEventChoice2(String eventChoice2) {
        this.eventChoice2 = eventChoice2;
    }

    public String getEventChoice3() {
        return eventChoice3;
    }

    public void setEventChoice3(String eventChoice3) {
        this.eventChoice3 = eventChoice3;
    }
}
