package com.kumabites.beyourowncaptain.GettingReady;

public class EventModel {

    private String eventName;
    private Double eventID;
    private String eventDescription;
    private String eventChoice1;
    private String eventChoice2;
    private String eventChoice3;
    private double eventChoiceID1;
    private double eventChoiceID2;
    private double eventChoiceID3;
    public Double getEventID() {
        return eventID;
    }

    public void setEventID(Double eventID) {
        this.eventID = eventID;
    }

    public double getEventChoiceID1() {
        return eventChoiceID1;
    }

    public void setEventChoiceID1(double eventChoiceID1) {
        this.eventChoiceID1 = eventChoiceID1;
    }

    public double getEventChoiceID2() {
        return eventChoiceID2;
    }

    public void setEventChoiceID2(double eventChoiceID2) {
        this.eventChoiceID2 = eventChoiceID2;
    }

    public double getEventChoiceID3() {
        return eventChoiceID3;
    }

    public void setEventChoiceID3(double eventChoiceID3) {
        this.eventChoiceID3 = eventChoiceID3;
    }



    public EventModel(String eventName, double eventID, String eventDescription, double eventChoiceID1, String eventChoice1, double eventChoiceID2, String eventChoice2, double eventChoiceID3, String eventChoice3){
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventChoice1 = eventChoice1;
        this.eventChoice2 = eventChoice2;
        this.eventChoice3 = eventChoice3;
        this.eventChoiceID1 = eventChoiceID1;
        this.eventChoiceID2 = eventChoiceID2;
        this.eventChoiceID3 = eventChoiceID3;

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
