package com.samsung.android.sivs.ai.sdkcommon.language;

import android.os.IBinder;
import android.os.Parcel;
import com.samsung.android.sdk.scs.ai.language.service.LlmCloudUsageRequestRunnable$execute$observer$1;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class j0 implements l0 {

    /* renamed from: a  reason: collision with root package name */
    public IBinder f1708a;

    public final void a(Map map, String str, String str2, int i2, int i7, LlmCloudUsageRequestRunnable$execute$observer$1 llmCloudUsageRequestRunnable$execute$observer$1) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.samsung.android.sivs.ai.sdkcommon.language.IUsageService");
            if (map == null) {
                obtain.writeInt(-1);
            } else {
                obtain.writeInt(map.size());
                map.forEach(new f0(obtain, 3));
            }
            obtain.writeString(str);
            obtain.writeString(str2);
            obtain.writeInt(i2);
            obtain.writeInt(i7);
            obtain.writeStrongInterface(llmCloudUsageRequestRunnable$execute$observer$1);
            this.f1708a.transact(1, obtain, obtain2, 0);
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
        return this.f1708a;
    }
}
