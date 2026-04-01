package com.samsung.android.gallery.module.graphics;

import com.samsung.android.gallery.module.graphics.BitmapHelper;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ StringBuilder d;

    public /* synthetic */ a(StringBuilder sb2) {
        this.d = sb2;
    }

    public final void accept(Object obj) {
        BitmapHelper.DebugLog.lambda$dump$0(this.d, (String) obj);
    }
}
