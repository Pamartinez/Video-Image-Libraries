package com.samsung.android.gallery.plugins.filebrowser;

import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ FileListAdapter e;

    public /* synthetic */ g(FileListAdapter fileListAdapter, int i2) {
        this.d = i2;
        this.e = fileListAdapter;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        FileListAdapter fileListAdapter = this.e;
        FileViewHolder fileViewHolder = (FileViewHolder) obj;
        switch (i2) {
            case 0:
                fileListAdapter.lambda$new$0(fileViewHolder);
                return;
            default:
                fileListAdapter.lambda$new$1(fileViewHolder);
                return;
        }
    }
}
