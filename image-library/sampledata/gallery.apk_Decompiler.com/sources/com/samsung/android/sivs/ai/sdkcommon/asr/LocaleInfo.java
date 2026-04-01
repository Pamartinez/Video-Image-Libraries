package com.samsung.android.sivs.ai.sdkcommon.asr;

import android.os.Parcel;
import android.os.Parcelable;
import c0.C0086a;
import java.util.Locale;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LocaleInfo implements Parcelable {
    public static final Parcelable.Creator<LocaleInfo> CREATOR = new Parcelable.Creator<LocaleInfo>() {
        public LocaleInfo createFromParcel(Parcel parcel) {
            return new LocaleInfo(parcel);
        }

        public LocaleInfo[] newArray(int i2) {
            return new LocaleInfo[i2];
        }
    };
    private final String mDisplayName;
    private final Locale mLocale;
    private final int mOrder;

    public LocaleInfo(Locale locale, String str, int i2) {
        this.mLocale = locale;
        this.mDisplayName = str;
        this.mOrder = i2;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof LocaleInfo) || this.mLocale != ((LocaleInfo) obj).mLocale) {
            return false;
        }
        return true;
    }

    public String getDisplayName() {
        return this.mDisplayName;
    }

    public Locale getLocale() {
        return this.mLocale;
    }

    public int getOrder() {
        return this.mOrder;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.mLocale.toLanguageTag()});
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("LocaleInfo{Locale=");
        sb2.append(this.mLocale);
        sb2.append(", DisplayName='");
        sb2.append(this.mDisplayName);
        sb2.append("', Order='");
        return C0086a.l(sb2, this.mOrder, "'}");
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeSerializable(this.mLocale);
        parcel.writeString(this.mDisplayName);
        parcel.writeInt(this.mOrder);
    }

    public LocaleInfo(Parcel parcel) {
        this.mLocale = (Locale) parcel.readSerializable();
        this.mDisplayName = parcel.readString();
        this.mOrder = parcel.readInt();
    }
}
