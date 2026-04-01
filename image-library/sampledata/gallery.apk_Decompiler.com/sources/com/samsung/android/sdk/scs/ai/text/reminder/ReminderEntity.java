package com.samsung.android.sdk.scs.ai.text.reminder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ReminderEntity {
    int endIndex;
    Place place;
    int startIndex;
    State state;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum Place {
        HOME,
        WORK,
        SCHOOL,
        CAR
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum State {
        GET_IN,
        GET_OUT
    }

    public ReminderEntity(Place place2, State state2, int i2, int i7) {
        this.place = place2;
        this.state = state2;
        this.startIndex = i2;
        this.endIndex = i7;
    }

    public int getEndIndex() {
        return this.endIndex;
    }

    public Place getPlace() {
        return this.place;
    }

    public int getStartIndex() {
        return this.startIndex;
    }

    public State getState() {
        return this.state;
    }

    public void setEndIndex(int i2) {
        this.endIndex = i2;
    }

    public void setPlace(Place place2) {
        this.place = place2;
    }

    public void setStartIndex(int i2) {
        this.startIndex = i2;
    }

    public void setState(State state2) {
        this.state = state2;
    }
}
