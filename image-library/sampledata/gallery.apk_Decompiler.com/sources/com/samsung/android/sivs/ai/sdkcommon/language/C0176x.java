package com.samsung.android.sivs.ai.sdkcommon.language;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.samsung.android.sivs.ai.sdkcommon.language.x  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C0176x extends Binder implements B {
    static final int TRANSACTION_onComplete = 3;
    static final int TRANSACTION_onError = 2;
    static final int TRANSACTION_onNext = 1;

    /* JADX WARNING: type inference failed for: r0v2, types: [com.samsung.android.sivs.ai.sdkcommon.language.w, com.samsung.android.sivs.ai.sdkcommon.language.B, java.lang.Object] */
    public static B asInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.samsung.android.sivs.ai.sdkcommon.language.ILlmServiceObserver");
        if (queryLocalInterface != null && (queryLocalInterface instanceof B)) {
            return (B) queryLocalInterface;
        }
        ? obj = new Object();
        obj.f1720a = iBinder;
        return obj;
    }

    public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i7) {
        Object obj;
        if (i2 >= 1 && i2 <= 16777215) {
            parcel.enforceInterface("com.samsung.android.sivs.ai.sdkcommon.language.ILlmServiceObserver");
        }
        if (i2 != 1598968902) {
            if (i2 == 1) {
                onNext(parcel.readString());
                parcel2.writeNoException();
            } else if (i2 == 2) {
                Parcelable.Creator creator = Bundle.CREATOR;
                if (parcel.readInt() != 0) {
                    obj = creator.createFromParcel(parcel);
                } else {
                    obj = null;
                }
                onError((Bundle) obj);
                parcel2.writeNoException();
            } else if (i2 != 3) {
                return super.onTransact(i2, parcel, parcel2, i7);
            } else {
                onComplete();
                parcel2.writeNoException();
            }
            return true;
        }
        parcel2.writeString("com.samsung.android.sivs.ai.sdkcommon.language.ILlmServiceObserver");
        return true;
    }

    public IBinder asBinder() {
        return this;
    }
}
