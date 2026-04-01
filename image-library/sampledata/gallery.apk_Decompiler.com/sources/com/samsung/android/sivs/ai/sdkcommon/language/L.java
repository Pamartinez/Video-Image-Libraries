package com.samsung.android.sivs.ai.sdkcommon.language;

import android.os.IBinder;
import android.os.Parcel;
import com.samsung.android.sdk.scs.ai.language.service.LlmServiceObserver2;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class L implements N {

    /* renamed from: a  reason: collision with root package name */
    public IBinder f1685a;

    public final void a(Map map, String str, LlmServiceObserver2 llmServiceObserver2, Map map2) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.samsung.android.sivs.ai.sdkcommon.language.ISmartCoverService");
            if (map == null) {
                obtain.writeInt(-1);
            } else {
                obtain.writeInt(map.size());
                map.forEach(new C0154a(obtain, 28));
            }
            obtain.writeString(str);
            obtain.writeStrongInterface(llmServiceObserver2);
            if (map2 == null) {
                obtain.writeInt(-1);
            } else {
                obtain.writeInt(map2.size());
                map2.forEach(new C0154a(obtain, 29));
            }
            this.f1685a.transact(4, obtain, obtain2, 0);
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
        return this.f1685a;
    }
}
