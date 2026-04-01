package com.samsung.android.gallery.module.abstraction;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FlipCoverSavedData implements Parcelable {
    public static final Parcelable.Creator<FlipCoverSavedData> CREATOR = new Parcelable.Creator<FlipCoverSavedData>() {
        public FlipCoverSavedData createFromParcel(Parcel parcel) {
            return new FlipCoverSavedData(parcel);
        }

        public FlipCoverSavedData[] newArray(int i2) {
            return new FlipCoverSavedData[i2];
        }
    };
    public final String location;
    public final int position;
    public final Uri uri;

    public FlipCoverSavedData(Uri uri2, int i2, String str) {
        this.uri = uri2;
        this.position = i2;
        this.location = str;
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("FlipCoverSavedData{");
        sb2.append(this.uri);
        sb2.append(ArcCommonLog.TAG_COMMA);
        sb2.append(this.position);
        sb2.append(ArcCommonLog.TAG_COMMA);
        return C0212a.p(sb2, this.location, "}");
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeParcelable(this.uri, i2);
        parcel.writeInt(this.position);
        parcel.writeString(this.location);
    }

    public FlipCoverSavedData(Parcel parcel) {
        this.uri = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.position = parcel.readInt();
        this.location = parcel.readString();
    }
}
