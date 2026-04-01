package com.samsung.android.liveeffect.service;

import He.F;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.Parcel;
import android.os.SharedMemory;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class c implements e {

    /* renamed from: a  reason: collision with root package name */
    public IBinder f3233a;

    public final String a(Bitmap bitmap, int i2) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.samsung.android.liveeffect.service.ILiveEffectService");
            F.b(obtain, bitmap);
            obtain.writeInt(i2);
            this.f3233a.transact(7, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readString();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public final IBinder asBinder() {
        return this.f3233a;
    }

    public final Bitmap b(Bitmap bitmap) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.samsung.android.liveeffect.service.ILiveEffectService");
            F.b(obtain, bitmap);
            this.f3233a.transact(18, obtain, obtain2, 0);
            obtain2.readException();
            return (Bitmap) F.a(obtain2, Bitmap.CREATOR);
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public final void c(SharedMemory sharedMemory, IHeifDecodingListener iHeifDecodingListener) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.samsung.android.liveeffect.service.ILiveEffectService");
            F.b(obtain, sharedMemory);
            obtain.writeStrongInterface(iHeifDecodingListener);
            this.f3233a.transact(14, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public final SharedMemory d(SharedMemory sharedMemory) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.samsung.android.liveeffect.service.ILiveEffectService");
            F.b(obtain, sharedMemory);
            this.f3233a.transact(9, obtain, obtain2, 0);
            obtain2.readException();
            return (SharedMemory) F.a(obtain2, SharedMemory.CREATOR);
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public final SharedMemory e(SharedMemory sharedMemory, int i2, int i7, SharedMemory sharedMemory2, byte[] bArr) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.samsung.android.liveeffect.service.ILiveEffectService");
            F.b(obtain, sharedMemory);
            obtain.writeInt(i2);
            obtain.writeInt(i7);
            F.b(obtain, sharedMemory2);
            obtain.writeByteArray(bArr);
            this.f3233a.transact(16, obtain, obtain2, 0);
            obtain2.readException();
            return (SharedMemory) F.a(obtain2, SharedMemory.CREATOR);
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public final SharedMemory f(SharedMemory sharedMemory, SharedMemory sharedMemory2, byte[] bArr) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.samsung.android.liveeffect.service.ILiveEffectService");
            F.b(obtain, sharedMemory);
            F.b(obtain, sharedMemory2);
            obtain.writeByteArray(bArr);
            this.f3233a.transact(13, obtain, obtain2, 0);
            obtain2.readException();
            return (SharedMemory) F.a(obtain2, SharedMemory.CREATOR);
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public final SharedMemory g(SharedMemory sharedMemory) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.samsung.android.liveeffect.service.ILiveEffectService");
            F.b(obtain, sharedMemory);
            this.f3233a.transact(8, obtain, obtain2, 0);
            obtain2.readException();
            return (SharedMemory) F.a(obtain2, SharedMemory.CREATOR);
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public final Bitmap h(Bitmap bitmap) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.samsung.android.liveeffect.service.ILiveEffectService");
            F.b(obtain, bitmap);
            this.f3233a.transact(6, obtain, obtain2, 0);
            obtain2.readException();
            return (Bitmap) F.a(obtain2, Bitmap.CREATOR);
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public final Bitmap i(Bitmap bitmap, boolean z) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.samsung.android.liveeffect.service.ILiveEffectService");
            F.b(obtain, bitmap);
            obtain.writeInt(z ? 1 : 0);
            obtain.writeInt(5);
            this.f3233a.transact(15, obtain, obtain2, 0);
            obtain2.readException();
            return (Bitmap) F.a(obtain2, Bitmap.CREATOR);
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
