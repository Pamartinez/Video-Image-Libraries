package com.samsung.android.scs.ai.sdkcommon.asr;

import B8.b;
import android.os.Parcel;
import android.os.Parcelable;
import i.C0212a;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    private final List<Integer> speakerList;
    private final List<SpeechInfo> speechInfos;

    public DialogInfo() {
        this((Set<Integer>) Collections.EMPTY_SET);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getSpeechInfosById$0(int i2, SpeechInfo speechInfo) {
        if (speechInfo.getSpeaker() == i2) {
            return true;
        }
        return false;
    }

    public int describeContents() {
        return 0;
    }

    public List<Integer> getSpeakerList() {
        return this.speakerList;
    }

    public List<SpeechInfo> getSpeechInfos() {
        return this.speechInfos;
    }

    public List<SpeechInfo> getSpeechInfosById(int i2) {
        return (List) this.speechInfos.stream().filter(new b(i2, 14)).collect(Collectors.toList());
    }

    public void setSpeechInfos(List<SpeechInfo> list) {
        this.speechInfos.clear();
        this.speechInfos.addAll(list);
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("DialogInfo{speakerList=");
        sb2.append(this.speakerList);
        sb2.append(", speechInfos=");
        return C0212a.r(sb2, this.speechInfos, '}');
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeList(this.speakerList);
        parcel.writeList(this.speechInfos);
    }

    public DialogInfo(Set<Integer> set) {
        LinkedList linkedList = new LinkedList();
        this.speakerList = linkedList;
        this.speechInfos = new LinkedList();
        linkedList.addAll(set);
    }

    public DialogInfo(Parcel parcel) {
        LinkedList linkedList = new LinkedList();
        this.speakerList = linkedList;
        LinkedList linkedList2 = new LinkedList();
        this.speechInfos = linkedList2;
        parcel.readList(linkedList, Integer.class.getClassLoader());
        parcel.readList(linkedList2, SpeechInfo.class.getClassLoader());
    }
}
