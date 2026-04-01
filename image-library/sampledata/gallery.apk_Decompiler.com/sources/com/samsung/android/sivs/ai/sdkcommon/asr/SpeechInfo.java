package com.samsung.android.sivs.ai.sdkcommon.asr;

import N2.j;
import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SpeechInfo implements Parcelable {
    public static final Parcelable.Creator<SpeechInfo> CREATOR = new Parcelable.Creator<SpeechInfo>() {
        public SpeechInfo createFromParcel(Parcel parcel) {
            return new SpeechInfo(parcel);
        }

        public SpeechInfo[] newArray(int i2) {
            return new SpeechInfo[i2];
        }
    };
    private final int endTime;
    private final int speaker;
    private final int startTime;

    public SpeechInfo(int i2, int i7, int i8) {
        this.speaker = i2;
        this.startTime = i7;
        this.endTime = i8;
    }

    public int describeContents() {
        return 0;
    }

    public int getEndTime() {
        return this.endTime;
    }

    public int getSpeaker() {
        return this.speaker;
    }

    public int getStartTime() {
        return this.startTime;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("SpeechInfo{speaker=");
        sb2.append(this.speaker);
        sb2.append(", startTime=");
        sb2.append(this.startTime);
        sb2.append(", endTime=");
        return j.e(sb2, this.endTime, '}');
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.speaker);
        parcel.writeInt(this.startTime);
        parcel.writeInt(this.endTime);
    }

    public SpeechInfo(Parcel parcel) {
        this.speaker = parcel.readInt();
        this.startTime = parcel.readInt();
        this.endTime = parcel.readInt();
    }
}
