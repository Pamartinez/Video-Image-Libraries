package com.samsung.android.sdk.scs.ai.text.event;

import java.time.LocalDateTime;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Event {
    boolean allDay;
    String cyclicTime;
    LocalDateTime endDateTime;
    int endIndex;
    boolean hasDay;
    boolean hasMonth;
    boolean hasYear;
    boolean isRelative;
    String location;
    LocalDateTime startDateTime;
    int startIndex;
    String title;
    boolean untilFlag;

    public static Event create() {
        return new Event();
    }

    public String getCyclicTime() {
        return this.cyclicTime;
    }

    public LocalDateTime getEndDateTime() {
        return this.endDateTime;
    }

    public int getEndIndex() {
        return this.endIndex;
    }

    public String getLocation() {
        return this.location;
    }

    public LocalDateTime getStartDateTime() {
        return this.startDateTime;
    }

    public int getStartIndex() {
        return this.startIndex;
    }

    public String getTitle() {
        return this.title;
    }

    public boolean hasDay() {
        return this.hasDay;
    }

    public boolean hasMonth() {
        return this.hasMonth;
    }

    public boolean hasYear() {
        return this.hasYear;
    }

    public boolean isAllDay() {
        return this.allDay;
    }

    public boolean isRelative() {
        return this.isRelative;
    }

    public boolean isUntilFlag() {
        return this.untilFlag;
    }

    public void setAllDay(boolean z) {
        this.allDay = z;
    }

    public void setCyclicTime(String str) {
        this.cyclicTime = str;
    }

    public void setEndDateTime(LocalDateTime localDateTime) {
        this.endDateTime = localDateTime;
    }

    public void setEndIndex(int i2) {
        this.endIndex = i2;
    }

    public void setHasDay(boolean z) {
        this.hasDay = z;
    }

    public void setHasMonth(boolean z) {
        this.hasMonth = z;
    }

    public void setHasYear(boolean z) {
        this.hasYear = z;
    }

    public void setLocation(String str) {
        this.location = str;
    }

    public void setRelative(boolean z) {
        this.isRelative = z;
    }

    public void setStartDateTime(LocalDateTime localDateTime) {
        this.startDateTime = localDateTime;
    }

    public void setStartIndex(int i2) {
        this.startIndex = i2;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setUntilFlag(boolean z) {
        this.untilFlag = z;
    }
}
