package com.samsung.android.visual.ai.sdkcommon;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class s implements u {

    /* renamed from: a  reason: collision with root package name */
    public IBinder f4192a;

    public final Bundle a(Bundle bundle) {
        Object obj;
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.samsung.android.visual.ai.sdkcommon.IWallpaperService");
            obtain.writeInt(1);
            bundle.writeToParcel(obtain, 0);
            this.f4192a.transact(3, obtain, obtain2, 0);
            obtain2.readException();
            Parcelable.Creator creator = Bundle.CREATOR;
            if (obtain2.readInt() != 0) {
                obj = creator.createFromParcel(obtain2);
            } else {
                obj = null;
            }
            return (Bundle) obj;
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public final IBinder asBinder() {
        return this.f4192a;
    }

    public final Bundle b(Bundle bundle) {
        Object obj;
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.samsung.android.visual.ai.sdkcommon.IWallpaperService");
            obtain.writeInt(1);
            bundle.writeToParcel(obtain, 0);
            this.f4192a.transact(2, obtain, obtain2, 0);
            obtain2.readException();
            Parcelable.Creator creator = Bundle.CREATOR;
            if (obtain2.readInt() != 0) {
                obj = creator.createFromParcel(obtain2);
            } else {
                obj = null;
            }
            return (Bundle) obj;
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
