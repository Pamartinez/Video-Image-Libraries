package com.samsung.android.sivs.ai.sdkcommon.translation;

import B3.a;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LanguageDirection implements Parcelable {
    public static final Parcelable.Creator<LanguageDirection> CREATOR = new a(22);
    private String sourceLanguage;
    private String targetLanguage;

    public /* synthetic */ LanguageDirection(Parcel parcel, int i2) {
        this(parcel);
    }

    private void readFromParcel(Parcel parcel) {
        this.sourceLanguage = parcel.readString();
        this.targetLanguage = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            LanguageDirection languageDirection = (LanguageDirection) obj;
            if (!Objects.equals(this.sourceLanguage, languageDirection.sourceLanguage) || !Objects.equals(this.targetLanguage, languageDirection.targetLanguage)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public String getSourceLanguage() {
        return this.sourceLanguage;
    }

    public String getTargetLanguage() {
        return this.targetLanguage;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.sourceLanguage, this.targetLanguage});
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.sourceLanguage);
        parcel.writeString(this.targetLanguage);
    }

    public LanguageDirection(String str, String str2) {
        this.sourceLanguage = str;
        this.targetLanguage = str2;
    }

    private LanguageDirection(Parcel parcel) {
        readFromParcel(parcel);
    }
}
