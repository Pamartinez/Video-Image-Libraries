package com.samsung.android.liveeffect.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class b extends Binder implements IHeifDecodingListener {
    static final int TRANSACTION_onCameraAnimationReceived = 3;
    static final int TRANSACTION_onCompleted = 4;
    static final int TRANSACTION_onFailed = 5;
    static final int TRANSACTION_onImageReceived = 1;
    static final int TRANSACTION_onMeshReceived = 2;

    /* JADX WARNING: type inference failed for: r0v2, types: [com.samsung.android.liveeffect.service.a, java.lang.Object, com.samsung.android.liveeffect.service.IHeifDecodingListener] */
    public static IHeifDecodingListener asInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.samsung.android.liveeffect.service.IHeifDecodingListener");
        if (queryLocalInterface != null && (queryLocalInterface instanceof IHeifDecodingListener)) {
            return (IHeifDecodingListener) queryLocalInterface;
        }
        ? obj = new Object();
        obj.f3232a = iBinder;
        return obj;
    }

    public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i7) {
        if (i2 >= 1 && i2 <= 16777215) {
            parcel.enforceInterface("com.samsung.android.liveeffect.service.IHeifDecodingListener");
        }
        if (i2 == 1598968902) {
            parcel2.writeString("com.samsung.android.liveeffect.service.IHeifDecodingListener");
            return true;
        }
        boolean z = false;
        if (i2 == 1) {
            byte[] createByteArray = parcel.createByteArray();
            if (parcel.readInt() != 0) {
                z = true;
            }
            onImageReceived(createByteArray, z);
            parcel2.writeNoException();
        } else if (i2 == 2) {
            byte[] createByteArray2 = parcel.createByteArray();
            if (parcel.readInt() != 0) {
                z = true;
            }
            onMeshReceived(createByteArray2, z);
            parcel2.writeNoException();
        } else if (i2 == 3) {
            onCameraAnimationReceived(parcel.createByteArray());
            parcel2.writeNoException();
        } else if (i2 == 4) {
            onCompleted();
            parcel2.writeNoException();
        } else if (i2 != 5) {
            return super.onTransact(i2, parcel, parcel2, i7);
        } else {
            onFailed(parcel.readString(), parcel.readString());
            parcel2.writeNoException();
        }
        return true;
    }

    public IBinder asBinder() {
        return this;
    }
}
