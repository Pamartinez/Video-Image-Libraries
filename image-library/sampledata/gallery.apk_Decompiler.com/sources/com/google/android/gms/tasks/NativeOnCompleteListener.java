package com.google.android.gms.tasks;

import P1.b;
import P1.h;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class NativeOnCompleteListener implements b {
    public native void nativeOnComplete(long j2, Object obj, boolean z, boolean z3, String str);

    public final void o(h hVar) {
        String str;
        Object obj;
        if (hVar.g()) {
            obj = hVar.f();
            str = null;
        } else {
            Exception e = hVar.e();
            if (e != null) {
                str = e.getMessage();
                obj = null;
            } else {
                obj = null;
                str = null;
            }
        }
        nativeOnComplete(0, obj, hVar.g(), false, str);
    }
}
