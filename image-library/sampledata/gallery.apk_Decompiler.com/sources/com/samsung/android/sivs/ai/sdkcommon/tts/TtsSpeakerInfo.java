package com.samsung.android.sivs.ai.sdkcommon.tts;

import android.os.Parcel;
import android.os.Parcelable;
import i.C0212a;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TtsSpeakerInfo implements Parcelable {
    public static final Parcelable.Creator<TtsSpeakerInfo> CREATOR = new Parcelable.Creator<TtsSpeakerInfo>() {
        public TtsSpeakerInfo createFromParcel(Parcel parcel) {
            return new TtsSpeakerInfo(parcel);
        }

        public TtsSpeakerInfo[] newArray(int i2) {
            return new TtsSpeakerInfo[i2];
        }
    };
    private final String displayName;
    private final String id;

    public TtsSpeakerInfo(String str, String str2) {
        this.id = str;
        this.displayName = str2;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof TtsSpeakerInfo)) {
            return false;
        }
        return this.id.equals(((TtsSpeakerInfo) obj).id);
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public String getId() {
        return this.id;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.id});
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("Speaker {id='");
        sb2.append(this.id);
        sb2.append("', Name='");
        return C0212a.p(sb2, this.displayName, "'}");
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.id);
        parcel.writeString(this.displayName);
    }

    public TtsSpeakerInfo(Parcel parcel) {
        this.id = parcel.readString();
        this.displayName = parcel.readString();
    }
}
