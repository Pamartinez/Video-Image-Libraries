package com.samsung.android.visual.ai.sdkcommon;

import android.os.IBinder;
import android.os.Parcel;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a implements c {

    /* renamed from: a  reason: collision with root package name */
    public IBinder f4183a;

    public final IBinder asBinder() {
        return this.f4183a;
    }

    public final void onError(String str) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.samsung.android.visual.ai.sdkcommon.IC2paEmbedCallback");
            obtain.writeString(str);
            this.f4183a.transact(2, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public final void onSuccess() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.samsung.android.visual.ai.sdkcommon.IC2paEmbedCallback");
            this.f4183a.transact(1, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
