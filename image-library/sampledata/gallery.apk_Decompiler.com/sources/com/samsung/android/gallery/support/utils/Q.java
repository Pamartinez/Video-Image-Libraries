package com.samsung.android.gallery.support.utils;

import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class Q implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ StringBuilder e;

    public /* synthetic */ Q(StringBuilder sb2, int i2) {
        this.d = i2;
        this.e = sb2;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        StringBuilder sb2 = this.e;
        String str = (String) obj;
        switch (i2) {
            case 0:
                StringCompat.lambda$wrapText$0(sb2, str);
                return;
            default:
                VerboseLogger.lambda$dump$0(sb2, str);
                return;
        }
    }
}
