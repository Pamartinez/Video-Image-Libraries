package com.samsung.android.sivs.ai.sdkcommon.translation;

import a.C0068a;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class f implements h {

    /* renamed from: a  reason: collision with root package name */
    public IBinder f1723a;

    public final ArrayList a(int i2, String str, String str2) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.samsung.android.sivs.ai.sdkcommon.translation.INeuralTranslationService");
            obtain.writeString(str);
            obtain.writeString(str2);
            obtain.writeInt(i2);
            this.f1723a.transact(14, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.createTypedArrayList(a.CREATOR);
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public final IBinder asBinder() {
        return this.f1723a;
    }

    public final HashMap b() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.samsung.android.sivs.ai.sdkcommon.translation.INeuralTranslationService");
            this.f1723a.transact(9, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readHashMap(f.class.getClassLoader());
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public final String c(String str, String str2) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.samsung.android.sivs.ai.sdkcommon.translation.INeuralTranslationService");
            obtain.writeString(str);
            obtain.writeString(str2);
            this.f1723a.transact(10, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readString();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public final ArrayList d(String str) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.samsung.android.sivs.ai.sdkcommon.translation.INeuralTranslationService");
            obtain.writeString(str);
            this.f1723a.transact(18, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.createStringArrayList();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public final String e(Bundle bundle) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.samsung.android.sivs.ai.sdkcommon.translation.INeuralTranslationService");
            C0068a.a(obtain, bundle);
            this.f1723a.transact(8, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readString();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public final ArrayList f(Bundle bundle) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.samsung.android.sivs.ai.sdkcommon.translation.INeuralTranslationService");
            C0068a.a(obtain, bundle);
            this.f1723a.transact(12, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.createTypedArrayList(b.CREATOR);
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public final String g(Bundle bundle) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.samsung.android.sivs.ai.sdkcommon.translation.INeuralTranslationService");
            C0068a.a(obtain, bundle);
            this.f1723a.transact(13, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readString();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public final ArrayList h(Bundle bundle) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.samsung.android.sivs.ai.sdkcommon.translation.INeuralTranslationService");
            C0068a.a(obtain, bundle);
            this.f1723a.transact(11, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.createStringArrayList();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public final boolean i(String str, String str2) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.samsung.android.sivs.ai.sdkcommon.translation.INeuralTranslationService");
            obtain.writeString(str);
            obtain.writeString(str2);
            boolean z = false;
            this.f1723a.transact(16, obtain, obtain2, 0);
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

    public final boolean j(Bundle bundle) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.samsung.android.sivs.ai.sdkcommon.translation.INeuralTranslationService");
            C0068a.a(obtain, bundle);
            boolean z = false;
            this.f1723a.transact(19, obtain, obtain2, 0);
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

    public final void k() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.samsung.android.sivs.ai.sdkcommon.translation.INeuralTranslationService");
            this.f1723a.transact(1, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public final void l(Bundle bundle, d dVar) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.samsung.android.sivs.ai.sdkcommon.translation.INeuralTranslationService");
            C0068a.a(obtain, bundle);
            obtain.writeStrongInterface(dVar);
            this.f1723a.transact(2, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
