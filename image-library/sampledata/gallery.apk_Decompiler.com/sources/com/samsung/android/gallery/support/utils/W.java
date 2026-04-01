package com.samsung.android.gallery.support.utils;

import com.samsung.android.gallery.support.utils.UrlLookup;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class W implements Supplier {
    public final /* synthetic */ UrlLookup.BackupFileHolder d;

    public /* synthetic */ W(UrlLookup.BackupFileHolder backupFileHolder) {
        this.d = backupFileHolder;
    }

    public final Object get() {
        return this.d.lambda$load$0();
    }
}
