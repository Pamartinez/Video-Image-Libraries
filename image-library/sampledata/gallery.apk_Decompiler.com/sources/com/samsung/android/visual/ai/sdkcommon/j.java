package com.samsung.android.visual.ai.sdkcommon;

import android.os.IBinder;
import android.os.Parcel;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class j implements l {

    /* renamed from: a  reason: collision with root package name */
    public IBinder f4186a;

    public final boolean a(String str) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.samsung.android.visual.ai.sdkcommon.IDownloadService");
            obtain.writeString(str);
            boolean z = false;
            this.f4186a.transact(2, obtain, obtain2, 0);
            obtain2.readException();
            if (obtain2.readInt() != 0) {
                z = true;
            }
            return z;
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public final IBinder asBinder() {
        return this.f4186a;
    }

    public final int b(String str, boolean z, boolean z3) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.samsung.android.visual.ai.sdkcommon.IDownloadService");
            obtain.writeString(str);
            obtain.writeInt(z ? 1 : 0);
            obtain.writeInt(z3 ? 1 : 0);
            this.f4186a.transact(3, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readInt();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public final void c(String str, String str2, i iVar) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.samsung.android.visual.ai.sdkcommon.IDownloadService");
            obtain.writeString(str);
            obtain.writeString(str2);
            obtain.writeStrongInterface(iVar);
            this.f4186a.transact(1, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
