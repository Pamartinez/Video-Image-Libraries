package com.samsung.android.sivs.ai.sdkcommon.language;

import L2.a;
import android.os.IBinder;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.samsung.android.sdk.scs.ai.language.service.LlmServiceObserver2;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class m0 implements o0 {

    /* renamed from: a  reason: collision with root package name */
    public IBinder f1712a;

    public final void a(Map map, String str, String str2, ParcelFileDescriptor parcelFileDescriptor, String str3, String str4, int i2, LlmServiceObserver2 llmServiceObserver2, Map map2) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.samsung.android.sivs.ai.sdkcommon.language.IWritingComposerService");
            if (map == null) {
                obtain.writeInt(-1);
            } else {
                obtain.writeInt(map.size());
                map.forEach(new f0(obtain, 6));
            }
            obtain.writeString(str);
            obtain.writeString(str2);
            a.J(obtain, parcelFileDescriptor);
            obtain.writeString(str3);
            obtain.writeString(str4);
            obtain.writeInt(i2);
            obtain.writeStrongInterface(llmServiceObserver2);
            if (map2 == null) {
                obtain.writeInt(-1);
            } else {
                obtain.writeInt(map2.size());
                map2.forEach(new f0(obtain, 7));
            }
            this.f1712a.transact(1, obtain, obtain2, 0);
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
        return this.f1712a;
    }

    public final void b(Map map, String str, List list, ArrayList arrayList, String str2, String str3, int i2, LlmServiceObserver2 llmServiceObserver2, Map map2) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.samsung.android.sivs.ai.sdkcommon.language.IWritingComposerService");
            if (map == null) {
                obtain.writeInt(-1);
            } else {
                obtain.writeInt(map.size());
                map.forEach(new f0(obtain, 8));
            }
            obtain.writeString(str);
            obtain.writeStringList(list);
            int size = arrayList.size();
            obtain.writeInt(size);
            for (int i7 = 0; i7 < size; i7++) {
                a.J(obtain, (Parcelable) arrayList.get(i7));
            }
            obtain.writeString(str2);
            obtain.writeString(str3);
            obtain.writeInt(i2);
            obtain.writeStrongInterface(llmServiceObserver2);
            if (map2 == null) {
                obtain.writeInt(-1);
            } else {
                obtain.writeInt(map2.size());
                map2.forEach(new f0(obtain, 9));
            }
            this.f1712a.transact(4, obtain, obtain2, 0);
            obtain2.readException();
            obtain2.recycle();
            obtain.recycle();
        } catch (Throwable th) {
            obtain2.recycle();
            obtain.recycle();
            throw th;
        }
    }

    public final void c(Map map, String str, String str2, ParcelFileDescriptor parcelFileDescriptor, String str3, String str4, int i2, LlmServiceObserver2 llmServiceObserver2, Map map2) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.samsung.android.sivs.ai.sdkcommon.language.IWritingComposerService");
            if (map == null) {
                obtain.writeInt(-1);
            } else {
                obtain.writeInt(map.size());
                map.forEach(new f0(obtain, 4));
            }
            obtain.writeString(str);
            obtain.writeString(str2);
            a.J(obtain, parcelFileDescriptor);
            obtain.writeString(str3);
            obtain.writeString(str4);
            obtain.writeInt(i2);
            obtain.writeStrongInterface(llmServiceObserver2);
            if (map2 == null) {
                obtain.writeInt(-1);
            } else {
                obtain.writeInt(map2.size());
                map2.forEach(new f0(obtain, 5));
            }
            this.f1712a.transact(3, obtain, obtain2, 0);
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
