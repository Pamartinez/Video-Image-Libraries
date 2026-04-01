package com.samsung.android.sivs.ai.sdkcommon.language;

import L1.d;
import android.os.IBinder;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.samsung.android.sdk.scs.ai.language.service.LlmServiceObserver2;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* renamed from: com.samsung.android.sivs.ai.sdkcommon.language.n  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0167n implements C0169p {

    /* renamed from: a  reason: collision with root package name */
    public IBinder f1713a;

    public final void a(Map map, String str, String str2, LlmServiceObserver2 llmServiceObserver2, Map map2) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.samsung.android.sivs.ai.sdkcommon.language.IExtractionService");
            if (map == null) {
                obtain.writeInt(-1);
            } else {
                obtain.writeInt(map.size());
                map.forEach(new C0154a(obtain, 14));
            }
            obtain.writeString(str);
            obtain.writeString(str2);
            obtain.writeStrongInterface(llmServiceObserver2);
            if (map2 == null) {
                obtain.writeInt(-1);
            } else {
                obtain.writeInt(map2.size());
                map2.forEach(new C0154a(obtain, 15));
            }
            this.f1713a.transact(3, obtain, obtain2, 0);
            obtain2.readException();
            obtain2.recycle();
            obtain.recycle();
        } catch (Throwable th) {
            obtain2.recycle();
            obtain.recycle();
            throw th;
        }
    }

    public final IBinder asBinder() {
        return this.f1713a;
    }

    public final void b(Map map, String str, String str2, String str3, ParcelFileDescriptor parcelFileDescriptor, LlmServiceObserver2 llmServiceObserver2, Map map2) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.samsung.android.sivs.ai.sdkcommon.language.IExtractionService");
            if (map == null) {
                obtain.writeInt(-1);
            } else {
                obtain.writeInt(map.size());
                map.forEach(new C0154a(obtain, 20));
            }
            obtain.writeString(str);
            obtain.writeString(str2);
            obtain.writeString(str3);
            d.y(obtain, parcelFileDescriptor);
            obtain.writeStrongInterface(llmServiceObserver2);
            if (map2 == null) {
                obtain.writeInt(-1);
            } else {
                obtain.writeInt(map2.size());
                map2.forEach(new C0154a(obtain, 21));
            }
            this.f1713a.transact(6, obtain, obtain2, 0);
            obtain2.readException();
            obtain2.recycle();
            obtain.recycle();
        } catch (Throwable th) {
            obtain2.recycle();
            obtain.recycle();
            throw th;
        }
    }

    public final void c(Map map, String str, String str2, List list, ArrayList arrayList, LlmServiceObserver2 llmServiceObserver2, Map map2) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.samsung.android.sivs.ai.sdkcommon.language.IExtractionService");
            if (map == null) {
                obtain.writeInt(-1);
            } else {
                obtain.writeInt(map.size());
                map.forEach(new C0154a(obtain, 18));
            }
            obtain.writeString(str);
            obtain.writeString(str2);
            obtain.writeStringList(list);
            int size = arrayList.size();
            obtain.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                d.y(obtain, (Parcelable) arrayList.get(i2));
            }
            obtain.writeStrongInterface(llmServiceObserver2);
            if (map2 == null) {
                obtain.writeInt(-1);
            } else {
                obtain.writeInt(map2.size());
                map2.forEach(new C0154a(obtain, 19));
            }
            this.f1713a.transact(7, obtain, obtain2, 0);
            obtain2.readException();
            obtain2.recycle();
            obtain.recycle();
        } catch (Throwable th) {
            obtain2.recycle();
            obtain.recycle();
            throw th;
        }
    }

    public final void d(Map map, String str, String str2, ParcelFileDescriptor parcelFileDescriptor, LlmServiceObserver2 llmServiceObserver2, Map map2) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.samsung.android.sivs.ai.sdkcommon.language.IExtractionService");
            if (map == null) {
                obtain.writeInt(-1);
            } else {
                obtain.writeInt(map.size());
                map.forEach(new C0154a(obtain, 16));
            }
            obtain.writeString(str);
            obtain.writeString(str2);
            d.y(obtain, parcelFileDescriptor);
            obtain.writeStrongInterface(llmServiceObserver2);
            if (map2 == null) {
                obtain.writeInt(-1);
            } else {
                obtain.writeInt(map2.size());
                map2.forEach(new C0154a(obtain, 17));
            }
            this.f1713a.transact(4, obtain, obtain2, 0);
            obtain2.readException();
            obtain2.recycle();
            obtain.recycle();
        } catch (Throwable th) {
            obtain2.recycle();
            obtain.recycle();
            throw th;
        }
    }
}
