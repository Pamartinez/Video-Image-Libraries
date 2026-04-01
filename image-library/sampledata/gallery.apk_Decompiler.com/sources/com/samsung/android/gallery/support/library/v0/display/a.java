package com.samsung.android.gallery.support.library.v0.display;

import com.samsung.android.gallery.support.library.abstraction.DisplayStatusListener;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ boolean d;

    public /* synthetic */ a(boolean z) {
        this.d = z;
    }

    public final void accept(Object obj) {
        ((DisplayStatusListener) obj).onDlnaConnectionStatusChanged(this.d);
    }
}
