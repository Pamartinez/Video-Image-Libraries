package com.samsung.android.gallery.plugins.filebrowser;

import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class m implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ m(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((FileListFragment) this.e).lambda$update$9((FileInfo) this.f, (FileListAdapter) obj);
                return;
            default:
                ((FolderInfo) this.e).lambda$toString$3((StringBuilder) this.f, (FolderInfo) obj);
                return;
        }
    }
}
