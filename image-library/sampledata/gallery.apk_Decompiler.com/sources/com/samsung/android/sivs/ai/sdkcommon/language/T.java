package com.samsung.android.sivs.ai.sdkcommon.language;

import android.os.IBinder;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import com.samsung.android.sdk.scs.ai.language.service.LlmServiceObserver2;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class T implements V {

    /* renamed from: a  reason: collision with root package name */
    public IBinder f1690a;

    public final void a(Map map, String str, String str2, LlmServiceObserver2 llmServiceObserver2, Map map2) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.samsung.android.sivs.ai.sdkcommon.language.ISuggestionService");
            if (map == null) {
                obtain.writeInt(-1);
            } else {
                obtain.writeInt(map.size());
                map.forEach(new O(obtain, 13));
            }
            obtain.writeString(str);
            obtain.writeString(str2);
            obtain.writeStrongInterface(llmServiceObserver2);
            if (map2 == null) {
                obtain.writeInt(-1);
            } else {
                obtain.writeInt(map2.size());
                map2.forEach(new O(obtain, 14));
            }
            this.f1690a.transact(7, obtain, obtain2, 0);
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
        return this.f1690a;
    }

    public final void b(Map map, String str, String str2, String str3, ParcelFileDescriptor parcelFileDescriptor, LlmServiceObserver2 llmServiceObserver2, Map map2) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.samsung.android.sivs.ai.sdkcommon.language.ISuggestionService");
            if (map == null) {
                obtain.writeInt(-1);
            } else {
                obtain.writeInt(map.size());
                map.forEach(new O(obtain, 9));
            }
            obtain.writeString(str);
            obtain.writeString(str2);
            obtain.writeString(str3);
            if (parcelFileDescriptor != null) {
                obtain.writeInt(1);
                parcelFileDescriptor.writeToParcel(obtain, 0);
            } else {
                obtain.writeInt(0);
            }
            obtain.writeStrongInterface(llmServiceObserver2);
            if (map2 == null) {
                obtain.writeInt(-1);
            } else {
                obtain.writeInt(map2.size());
                map2.forEach(new O(obtain, 10));
            }
            this.f1690a.transact(6, obtain, obtain2, 0);
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
            obtain.writeInterfaceToken("com.samsung.android.sivs.ai.sdkcommon.language.ISuggestionService");
            if (map == null) {
                obtain.writeInt(-1);
            } else {
                obtain.writeInt(map.size());
                map.forEach(new O(obtain, 11));
            }
            obtain.writeString(str);
            obtain.writeString(str2);
            obtain.writeStrongInterface(llmServiceObserver2);
            if (map2 == null) {
                obtain.writeInt(-1);
            } else {
                obtain.writeInt(map2.size());
                map2.forEach(new O(obtain, 12));
            }
            this.f1690a.transact(5, obtain, obtain2, 0);
            obtain2.readException();
            obtain2.recycle();
            obtain.recycle();
        } catch (Throwable th) {
            obtain2.recycle();
            obtain.recycle();
            throw th;
        }
    }

    public final void d(Map map, String str, LlmServiceObserver2 llmServiceObserver2, Map map2) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.samsung.android.sivs.ai.sdkcommon.language.ISuggestionService");
            if (map == null) {
                obtain.writeInt(-1);
            } else {
                obtain.writeInt(map.size());
                map.forEach(new O(obtain, 7));
            }
            obtain.writeString(str);
            obtain.writeStrongInterface(llmServiceObserver2);
            if (map2 == null) {
                obtain.writeInt(-1);
            } else {
                obtain.writeInt(map2.size());
                map2.forEach(new O(obtain, 8));
            }
            this.f1690a.transact(2, obtain, obtain2, 0);
            obtain2.readException();
            obtain2.recycle();
            obtain.recycle();
        } catch (Throwable th) {
            obtain2.recycle();
            obtain.recycle();
            throw th;
        }
    }

    public final void e(Map map, String str, String str2, LlmServiceObserver2 llmServiceObserver2, Map map2) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.samsung.android.sivs.ai.sdkcommon.language.ISuggestionService");
            if (map == null) {
                obtain.writeInt(-1);
            } else {
                obtain.writeInt(map.size());
                map.forEach(new O(obtain, 5));
            }
            obtain.writeString(str);
            obtain.writeString(str2);
            obtain.writeStrongInterface(llmServiceObserver2);
            if (map2 == null) {
                obtain.writeInt(-1);
            } else {
                obtain.writeInt(map2.size());
                map2.forEach(new O(obtain, 6));
            }
            this.f1690a.transact(4, obtain, obtain2, 0);
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
