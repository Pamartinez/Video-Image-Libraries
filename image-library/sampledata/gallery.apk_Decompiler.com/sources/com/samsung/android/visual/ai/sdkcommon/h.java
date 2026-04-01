package com.samsung.android.visual.ai.sdkcommon;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class h extends Binder implements i {
    static final int TRANSACTION_onError = 2;
    static final int TRANSACTION_onSuccess = 1;

    /* JADX WARNING: type inference failed for: r0v2, types: [com.samsung.android.visual.ai.sdkcommon.i, com.samsung.android.visual.ai.sdkcommon.g, java.lang.Object] */
    public static i asInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.samsung.android.visual.ai.sdkcommon.IDownloadCallback");
        if (queryLocalInterface != null && (queryLocalInterface instanceof i)) {
            return (i) queryLocalInterface;
        }
        ? obj = new Object();
        obj.f4185a = iBinder;
        return obj;
    }

    public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i7) {
        if (i2 >= 1 && i2 <= 16777215) {
            parcel.enforceInterface("com.samsung.android.visual.ai.sdkcommon.IDownloadCallback");
        }
        if (i2 != 1598968902) {
            if (i2 == 1) {
                onSuccess(parcel.readString());
            } else if (i2 != 2) {
                return super.onTransact(i2, parcel, parcel2, i7);
            } else {
                onError(parcel.readString(), parcel.readString());
            }
            return true;
        }
        parcel2.writeString("com.samsung.android.visual.ai.sdkcommon.IDownloadCallback");
        return true;
    }

    public IBinder asBinder() {
        return this;
    }
}
