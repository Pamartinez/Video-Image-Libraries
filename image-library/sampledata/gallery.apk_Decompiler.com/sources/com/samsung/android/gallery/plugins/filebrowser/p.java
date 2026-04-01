package com.samsung.android.gallery.plugins.filebrowser;

import java.io.File;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class p implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ p(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        Object obj2 = this.e;
        switch (i2) {
            case 0:
                FolderInfo.lambda$computeIfFolder$0((FolderInfo) obj2, (File) obj);
                return;
            default:
                ((PathListAdapter) obj2).lambda$new$0((PathViewHolder) obj);
                return;
        }
    }
}
