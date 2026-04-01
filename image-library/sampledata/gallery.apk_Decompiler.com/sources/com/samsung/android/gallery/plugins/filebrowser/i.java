package com.samsung.android.gallery.plugins.filebrowser;

import android.content.Context;
import android.content.DialogInterface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements DialogInterface.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ FileListFragment e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ i(FileListFragment fileListFragment, Object obj, Object obj2, int i2) {
        this.d = i2;
        this.e = fileListFragment;
        this.f = obj;
        this.g = obj2;
    }

    public final void onClick(DialogInterface dialogInterface, int i2) {
        switch (this.d) {
            case 0:
                this.e.lambda$onLongClick$12((Context) this.f, (FileViewHolder) this.g, dialogInterface, i2);
                return;
            case 1:
                this.e.lambda$onLongClick$13((Context) this.f, (FileViewHolder) this.g, dialogInterface, i2);
                return;
            case 2:
                this.e.lambda$onLongClick$14((Context) this.f, (FileViewHolder) this.g, dialogInterface, i2);
                return;
            case 3:
                this.e.lambda$onLongClick$15((Context) this.f, (FileViewHolder) this.g, dialogInterface, i2);
                return;
            default:
                ((FilePreviewFragment) this.e).lambda$saveZipFile$3((FileInfo) this.f, (String) this.g, dialogInterface, i2);
                return;
        }
    }
}
