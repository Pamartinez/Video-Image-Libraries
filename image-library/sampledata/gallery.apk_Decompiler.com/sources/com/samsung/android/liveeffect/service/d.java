package com.samsung.android.liveeffect.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class d extends Binder implements e {
    /* JADX WARNING: type inference failed for: r0v2, types: [java.lang.Object, com.samsung.android.liveeffect.service.e, com.samsung.android.liveeffect.service.c] */
    public static e a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.samsung.android.liveeffect.service.ILiveEffectService");
        if (queryLocalInterface != null && (queryLocalInterface instanceof e)) {
            return (e) queryLocalInterface;
        }
        ? obj = new Object();
        obj.f3233a = iBinder;
        return obj;
    }
}
