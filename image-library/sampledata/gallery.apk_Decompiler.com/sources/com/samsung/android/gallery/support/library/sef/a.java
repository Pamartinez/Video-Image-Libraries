package com.samsung.android.gallery.support.library.sef;

import com.samsung.android.gallery.support.library.sef.CameraCaptureMode;
import com.samsung.android.gallery.support.library.sef.SefInfo;
import com.samsung.android.gallery.support.library.sef.SingleTakeType;
import java.util.HashMap;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ HashMap e;

    public /* synthetic */ a(int i2, HashMap hashMap) {
        this.d = i2;
        this.e = hashMap;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        HashMap hashMap = this.e;
        switch (i2) {
            case 0:
                ((CameraCaptureMode.DataHolder.AnonymousClass1) hashMap).lambda$new$0((CameraCaptureMode) obj);
                return;
            case 1:
                ((SefInfo.DataHolder.AnonymousClass1) hashMap).lambda$new$1((SefInfo) obj);
                return;
            default:
                ((SingleTakeType.DataHolder.AnonymousClass1) hashMap).lambda$new$0((SingleTakeType) obj);
                return;
        }
    }
}
