package com.samsung.android.gallery.plugins.filebrowser;

import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class l implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ FileListFragment e;

    public /* synthetic */ l(FileListFragment fileListFragment, int i2) {
        this.d = i2;
        this.e = fileListFragment;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        FileListFragment fileListFragment = this.e;
        switch (i2) {
            case 0:
                fileListFragment.onClick((FileViewHolder) obj);
                return;
            case 1:
                fileListFragment.onLongClick((FileViewHolder) obj);
                return;
            default:
                fileListFragment.lambda$update$11((PathListAdapter) obj);
                return;
        }
    }
}
