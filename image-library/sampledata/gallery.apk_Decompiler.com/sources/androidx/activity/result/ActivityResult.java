package androidx.activity.result;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ActivityResult implements Parcelable {
    public static final Parcelable.Creator<ActivityResult> CREATOR = new Parcelable.Creator<ActivityResult>() {
        public ActivityResult createFromParcel(Parcel parcel) {
            return new ActivityResult(parcel);
        }

        public ActivityResult[] newArray(int i2) {
            return new ActivityResult[i2];
        }
    };
    private final Intent mData;
    private final int mResultCode;

    public ActivityResult(int i2, Intent intent) {
        this.mResultCode = i2;
        this.mData = intent;
    }

    public static String resultCodeToString(int i2) {
        if (i2 == -1) {
            return "RESULT_OK";
        }
        if (i2 != 0) {
            return String.valueOf(i2);
        }
        return "RESULT_CANCELED";
    }

    public int describeContents() {
        return 0;
    }

    public Intent getData() {
        return this.mData;
    }

    public int getResultCode() {
        return this.mResultCode;
    }

    public String toString() {
        return "ActivityResult{resultCode=" + resultCodeToString(this.mResultCode) + ", data=" + this.mData + '}';
    }

    public void writeToParcel(Parcel parcel, int i2) {
        int i7;
        parcel.writeInt(this.mResultCode);
        if (this.mData == null) {
            i7 = 0;
        } else {
            i7 = 1;
        }
        parcel.writeInt(i7);
        Intent intent = this.mData;
        if (intent != null) {
            intent.writeToParcel(parcel, i2);
        }
    }

    public ActivityResult(Parcel parcel) {
        this.mResultCode = parcel.readInt();
        this.mData = parcel.readInt() == 0 ? null : (Intent) Intent.CREATOR.createFromParcel(parcel);
    }
}
