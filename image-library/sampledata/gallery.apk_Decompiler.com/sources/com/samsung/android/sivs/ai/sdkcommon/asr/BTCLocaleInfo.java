package com.samsung.android.sivs.ai.sdkcommon.asr;

import A4.I;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sivs.ai.sdkcommon.tts.TtsPackageInfo;
import com.samsung.android.sivs.ai.sdkcommon.tts.TtsSpeakerInfo;
import i.C0212a;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BTCLocaleInfo extends LocaleInfo {
    public static final Parcelable.Creator<LocaleInfo> CREATOR = new Parcelable.Creator<LocaleInfo>() {
        public BTCLocaleInfo createFromParcel(Parcel parcel) {
            return new BTCLocaleInfo(parcel);
        }

        public BTCLocaleInfo[] newArray(int i2) {
            return new BTCLocaleInfo[i2];
        }
    };
    private final TtsSpeakerInfo defaultSpeaker;
    private final List<TtsPackageInfo> ttsPackage;
    private final List<TtsSpeakerInfo> ttsSpeaker;

    public BTCLocaleInfo(Locale locale, String str, int i2, TtsSpeakerInfo ttsSpeakerInfo) {
        super(locale, str, i2);
        this.ttsSpeaker = new LinkedList();
        this.ttsPackage = new LinkedList();
        this.defaultSpeaker = ttsSpeakerInfo;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$getDefaultPackages$0(TtsPackageInfo ttsPackageInfo) {
        return ttsPackageInfo.getSpeakerInfo().contains(this.defaultSpeaker);
    }

    public int describeContents() {
        return super.describeContents();
    }

    public List<TtsPackageInfo> getDefaultPackages() {
        return (List) this.ttsPackage.stream().filter(new I(20, this)).distinct().collect(Collectors.toList());
    }

    public TtsSpeakerInfo getDefaultSpeaker() {
        return this.defaultSpeaker;
    }

    public List<TtsPackageInfo> getTtsPackages() {
        return this.ttsPackage;
    }

    public List<TtsSpeakerInfo> getTtsSpeakers() {
        return this.ttsSpeaker;
    }

    public void setPackageInfo(List<TtsPackageInfo> list) {
        if (list != null) {
            this.ttsPackage.clear();
            this.ttsPackage.addAll(list);
        }
    }

    public void setSpeakerInfo(List<TtsSpeakerInfo> list) {
        if (list != null) {
            this.ttsSpeaker.clear();
            this.ttsSpeaker.addAll(list);
        }
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("BTCLocaleInfo{localeInfo=");
        sb2.append(super.toString());
        sb2.append(", defaultSpeaker=");
        sb2.append(this.defaultSpeaker);
        sb2.append(", speaker=");
        sb2.append(this.ttsSpeaker);
        sb2.append(", package=");
        return C0212a.r(sb2, this.ttsPackage, '}');
    }

    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeParcelable(this.defaultSpeaker, 0);
        parcel.writeList(this.ttsSpeaker);
        parcel.writeList(this.ttsPackage);
    }

    public BTCLocaleInfo(Parcel parcel) {
        super(parcel);
        LinkedList linkedList = new LinkedList();
        this.ttsSpeaker = linkedList;
        LinkedList linkedList2 = new LinkedList();
        this.ttsPackage = linkedList2;
        Class<TtsSpeakerInfo> cls = TtsSpeakerInfo.class;
        this.defaultSpeaker = (TtsSpeakerInfo) parcel.readParcelable(cls.getClassLoader());
        parcel.readList(linkedList, cls.getClassLoader());
        parcel.readList(linkedList2, TtsPackageInfo.class.getClassLoader());
    }
}
