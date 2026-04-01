package com.samsung.android.sivs.ai.sdkcommon.tts;

import Ad.C0720a;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TtsPackageInfo implements Parcelable {
    public static final Parcelable.Creator<TtsPackageInfo> CREATOR = new Parcelable.Creator<TtsPackageInfo>() {
        public TtsPackageInfo createFromParcel(Parcel parcel) {
            return new TtsPackageInfo(parcel);
        }

        public TtsPackageInfo[] newArray(int i2) {
            return new TtsPackageInfo[i2];
        }
    };
    private Bundle mExtras;
    private final String mPackageName;
    private final List<TtsSpeakerInfo> mSpeakerList;
    private final TtsPackageType mType;

    public TtsPackageInfo(String str, TtsPackageType ttsPackageType, List<TtsSpeakerInfo> list) {
        this.mPackageName = str;
        this.mType = ttsPackageType;
        this.mSpeakerList = (List) Optional.ofNullable(list).orElseGet(new C0720a(12));
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof TtsPackageInfo)) {
            TtsPackageInfo ttsPackageInfo = (TtsPackageInfo) obj;
            if (this.mType != ttsPackageInfo.mType || !this.mPackageName.equals(ttsPackageInfo.mPackageName)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public Bundle getExtras() {
        return (Bundle) Optional.ofNullable(this.mExtras).orElse(Bundle.EMPTY);
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public List<TtsSpeakerInfo> getSpeakerInfo() {
        return this.mSpeakerList;
    }

    public TtsPackageType getType() {
        return this.mType;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.mPackageName + this.mType});
    }

    public void setExtras(Bundle bundle) {
        if (bundle != null) {
            this.mExtras = bundle;
        }
    }

    public String toString() {
        return "TtsPackageInfo{mPackageName='" + this.mPackageName + "', mSpeakerList=" + this.mSpeakerList.size() + ", mType=" + this.mType + ", mExtras=" + this.mExtras + '}';
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.mPackageName);
        parcel.writeString(this.mType.toString());
        parcel.writeList(this.mSpeakerList);
        parcel.writeBundle((Bundle) Optional.ofNullable(this.mExtras).orElse(Bundle.EMPTY));
    }

    public TtsPackageInfo(Parcel parcel) {
        this.mPackageName = parcel.readString();
        this.mType = TtsPackageType.valueOf(parcel.readString());
        ArrayList arrayList = new ArrayList();
        this.mSpeakerList = arrayList;
        parcel.readList(arrayList, TtsSpeakerInfo.class.getClassLoader());
        this.mExtras = parcel.readBundle();
    }
}
