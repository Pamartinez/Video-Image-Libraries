package com.samsung.android.gallery.module.graphics;

import com.samsung.android.gallery.module.graphics.ImageDecoder;
import com.samsung.android.gallery.support.utils.Logger;
import java.io.PrintWriter;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3025a;
    public final /* synthetic */ PrintWriter b;

    public /* synthetic */ d(PrintWriter printWriter, int i2) {
        this.f3025a = i2;
        this.b = printWriter;
    }

    public final void accept(Object obj, Object obj2) {
        int i2 = this.f3025a;
        String str = (String) obj;
        String str2 = (String) obj2;
        PrintWriter printWriter = this.b;
        switch (i2) {
            case 0:
                ImageDecoder.DebugLogger.lambda$dump$0(printWriter, str, str2);
                return;
            default:
                Logger.dumpLog(printWriter, Logger.getEncodedString(str) + " : " + str2);
                return;
        }
    }
}
