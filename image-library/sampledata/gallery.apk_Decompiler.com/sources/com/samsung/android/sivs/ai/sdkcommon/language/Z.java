package com.samsung.android.sivs.ai.sdkcommon.language;

import android.os.IBinder;
import android.os.Parcel;
import com.samsung.android.sdk.scs.ai.language.service.LlmServiceObserver2;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Z implements b0 {

    /* renamed from: a  reason: collision with root package name */
    public IBinder f1694a;

    public final void a(Map map, String str, String str2, String str3, LlmServiceObserver2 llmServiceObserver2, Map map2) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.samsung.android.sivs.ai.sdkcommon.language.ISummarizationService");
            if (map == null) {
                obtain.writeInt(-1);
            } else {
                obtain.writeInt(map.size());
                map.forEach(new O(obtain, 20));
            }
            obtain.writeString(str);
            obtain.writeString(str2);
            obtain.writeString(str3);
            obtain.writeStrongInterface(llmServiceObserver2);
            if (map2 == null) {
                obtain.writeInt(-1);
            } else {
                obtain.writeInt(map2.size());
                map2.forEach(new O(obtain, 21));
            }
            this.f1694a.transact(7, obtain, obtain2, 0);
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
        return this.f1694a;
    }

    public final void b(Map map, String str, String str2, LlmServiceObserver2 llmServiceObserver2) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.samsung.android.sivs.ai.sdkcommon.language.ISummarizationService");
            if (map == null) {
                obtain.writeInt(-1);
            } else {
                obtain.writeInt(map.size());
                map.forEach(new O(obtain, 17));
            }
            obtain.writeString(str);
            obtain.writeString(str2);
            obtain.writeStrongInterface(llmServiceObserver2);
            this.f1694a.transact(6, obtain, obtain2, 0);
            obtain2.readException();
            obtain2.recycle();
            obtain.recycle();
        } catch (Throwable th) {
            obtain2.recycle();
            obtain.recycle();
            throw th;
        }
    }

    public final void c(Map map, String str, String str2, LlmServiceObserver2 llmServiceObserver2, Map map2) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.samsung.android.sivs.ai.sdkcommon.language.ISummarizationService");
            if (map == null) {
                obtain.writeInt(-1);
            } else {
                obtain.writeInt(map.size());
                map.forEach(new O(obtain, 22));
            }
            obtain.writeString(str);
            obtain.writeString(str2);
            obtain.writeStrongInterface(llmServiceObserver2);
            if (map2 == null) {
                obtain.writeInt(-1);
            } else {
                obtain.writeInt(map2.size());
                map2.forEach(new O(obtain, 23));
            }
            this.f1694a.transact(8, obtain, obtain2, 0);
            obtain2.readException();
            obtain2.recycle();
            obtain.recycle();
        } catch (Throwable th) {
            obtain2.recycle();
            obtain.recycle();
            throw th;
        }
    }

    public final void d(Map map, String str, String str2, String str3, LlmServiceObserver2 llmServiceObserver2, Map map2) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.samsung.android.sivs.ai.sdkcommon.language.ISummarizationService");
            if (map == null) {
                obtain.writeInt(-1);
            } else {
                obtain.writeInt(map.size());
                map.forEach(new O(obtain, 18));
            }
            obtain.writeString(str);
            obtain.writeString("");
            obtain.writeString("");
            obtain.writeString(str2);
            obtain.writeString(str3);
            obtain.writeStrongInterface(llmServiceObserver2);
            if (map2 == null) {
                obtain.writeInt(-1);
            } else {
                obtain.writeInt(map2.size());
                map2.forEach(new O(obtain, 19));
            }
            this.f1694a.transact(4, obtain, obtain2, 0);
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
