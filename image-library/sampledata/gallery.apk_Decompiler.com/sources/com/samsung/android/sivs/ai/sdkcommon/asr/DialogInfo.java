package com.samsung.android.sivs.ai.sdkcommon.asr;

import android.os.Parcel;
import android.os.Parcelable;
import i.C0212a;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DialogInfo implements Parcelable {
    public static final Parcelable.Creator<DialogInfo> CREATOR = new Parcelable.Creator<DialogInfo>() {
        public DialogInfo createFromParcel(Parcel parcel) {
            return new DialogInfo(parcel);
        }

        public DialogInfo[] newArray(int i2) {
            return new DialogInfo[i2];
        }
    };
    private final List<SpeechInfo> speakerInfos;
    private final List<Integer> speakerList;

    public DialogInfo() {
        this((Set<Integer>) Collections.EMPTY_SET);
    }

    public int describeContents() {
        return 0;
    }

    public List<SpeechInfo> getSpeakerInfos() {
        return this.speakerInfos;
    }

    public List<Integer> getSpeakerList() {
        return this.speakerList;
    }

    public void setSpeechInfos(List<SpeechInfo> list) {
        this.speakerInfos.clear();
        this.speakerInfos.addAll(list);
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("DialogInfo{speakerList=");
        sb2.append(this.speakerList);
        sb2.append(", speakerInfos=");
        return C0212a.r(sb2, this.speakerInfos, '}');
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeList(this.speakerList);
        parcel.writeList(this.speakerInfos);
    }

    public DialogInfo(Set<Integer> set) {
        LinkedList linkedList = new LinkedList();
        this.speakerList = linkedList;
        this.speakerInfos = new LinkedList();
        linkedList.addAll(set);
    }

    public DialogInfo(Parcel parcel) {
        LinkedList linkedList = new LinkedList();
        this.speakerList = linkedList;
        LinkedList linkedList2 = new LinkedList();
        this.speakerInfos = linkedList2;
        parcel.readList(linkedList, Integer.class.getClassLoader());
        parcel.readList(linkedList2, SpeechInfo.class.getClassLoader());
    }
}
