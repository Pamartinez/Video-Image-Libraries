package com.samsung.android.sivs.ai.sdkcommon.translation;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class d extends Binder implements e {
    static final int TRANSACTION_onFailure = 2;
    static final int TRANSACTION_onSuccess = 1;

    public d() {
        attachInterface(this, "com.samsung.android.sivs.ai.sdkcommon.translation.INeuralTranslationCallback");
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [com.samsung.android.sivs.ai.sdkcommon.translation.e, java.lang.Object, com.samsung.android.sivs.ai.sdkcommon.translation.c] */
    public static e asInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.samsung.android.sivs.ai.sdkcommon.translation.INeuralTranslationCallback");
        if (queryLocalInterface != null && (queryLocalInterface instanceof e)) {
            return (e) queryLocalInterface;
        }
        ? obj = new Object();
        obj.f1722a = iBinder;
        return obj;
    }

    public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i7) {
        Object obj;
        if (i2 >= 1 && i2 <= 16777215) {
            parcel.enforceInterface("com.samsung.android.sivs.ai.sdkcommon.translation.INeuralTranslationCallback");
        }
        if (i2 != 1598968902) {
            if (i2 == 1) {
                Parcelable.Creator creator = Bundle.CREATOR;
                if (parcel.readInt() != 0) {
                    obj = creator.createFromParcel(parcel);
                } else {
                    obj = null;
                }
                onSuccess((Bundle) obj);
                parcel2.writeNoException();
            } else if (i2 != 2) {
                return super.onTransact(i2, parcel, parcel2, i7);
            } else {
                onFailure(parcel.readInt());
                parcel2.writeNoException();
            }
            return true;
        }
        parcel2.writeString("com.samsung.android.sivs.ai.sdkcommon.translation.INeuralTranslationCallback");
        return true;
    }

    public IBinder asBinder() {
        return this;
    }
}
