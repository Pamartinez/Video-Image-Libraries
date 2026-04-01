package com.samsung.android.gallery.support.utils;

import com.samsung.android.gallery.support.utils.JpegParser;
import com.samsung.android.gallery.support.utils.ServiceManager;
import java.io.File;
import java.util.ArrayList;
import java.util.function.Consumer;

/* renamed from: com.samsung.android.gallery.support.utils.v  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0683v implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ ArrayList e;

    public /* synthetic */ C0683v(ArrayList arrayList, int i2) {
        this.d = i2;
        this.e = arrayList;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        ArrayList arrayList = this.e;
        switch (i2) {
            case 0:
                JpegParser.lambda$loadXmp$0(arrayList, (JpegParser.Header) obj);
                return;
            case 1:
                JpegParser.lambda$loadXmp$2(arrayList, (JpegParser.MpfInfo) obj);
                return;
            case 2:
                JpegParser.lambda$loadXmp$1(arrayList, (JpegParser.Header) obj);
                return;
            case 3:
                arrayList.add(" *" + ((ServiceManager.ServiceInfoCompat) obj));
                return;
            case 4:
                arrayList.add(((StorageInfo) obj).camera);
                return;
            case 5:
                arrayList.add(obj);
                return;
            case 6:
                FileUtils.lambda$list$9(arrayList, (File[]) obj);
                return;
            default:
                arrayList.add("  " + ((String) obj));
                return;
        }
    }
}
