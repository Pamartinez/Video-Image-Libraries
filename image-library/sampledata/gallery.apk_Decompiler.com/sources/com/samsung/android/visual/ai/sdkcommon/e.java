package com.samsung.android.visual.ai.sdkcommon;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class e extends Binder implements f {
    static final int TRANSACTION_onError = 2;
    static final int TRANSACTION_onPfdCreation = 3;
    static final int TRANSACTION_onResult = 1;

    /* JADX WARNING: type inference failed for: r0v2, types: [java.lang.Object, com.samsung.android.visual.ai.sdkcommon.f, com.samsung.android.visual.ai.sdkcommon.d] */
    public static f asInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.samsung.android.visual.ai.sdkcommon.IC2paManifestsCallback");
        if (queryLocalInterface != null && (queryLocalInterface instanceof f)) {
            return (f) queryLocalInterface;
        }
        ? obj = new Object();
        obj.f4184a = iBinder;
        return obj;
    }

    public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i7) {
        boolean z;
        Object obj;
        if (i2 >= 1 && i2 <= 16777215) {
            parcel.enforceInterface("com.samsung.android.visual.ai.sdkcommon.IC2paManifestsCallback");
        }
        if (i2 != 1598968902) {
            boolean z3 = false;
            if (i2 == 1) {
                String readString = parcel.readString();
                if (parcel.readInt() != 0) {
                    z = true;
                } else {
                    z = false;
                }
                if (parcel.readInt() != 0) {
                    z3 = true;
                }
                onResult(readString, z, z3);
                parcel2.writeNoException();
            } else if (i2 == 2) {
                onError(parcel.readString());
                parcel2.writeNoException();
            } else if (i2 != 3) {
                return super.onTransact(i2, parcel, parcel2, i7);
            } else {
                Parcelable.Creator creator = ParcelFileDescriptor.CREATOR;
                if (parcel.readInt() != 0) {
                    obj = creator.createFromParcel(parcel);
                } else {
                    obj = null;
                }
                ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor) obj;
                if (parcel.readInt() != 0) {
                    z3 = true;
                }
                onPfdCreation(parcelFileDescriptor, z3);
                parcel2.writeNoException();
            }
            return true;
        }
        parcel2.writeString("com.samsung.android.visual.ai.sdkcommon.IC2paManifestsCallback");
        return true;
    }

    public IBinder asBinder() {
        return this;
    }
}
