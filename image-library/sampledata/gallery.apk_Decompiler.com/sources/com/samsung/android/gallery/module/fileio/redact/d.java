package com.samsung.android.gallery.module.fileio.redact;

import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Consumer {
    public final /* synthetic */ FileEditor d;
    public final /* synthetic */ FileItemInterface e;
    public final /* synthetic */ int f;

    public /* synthetic */ d(FileEditor fileEditor, FileItemInterface fileItemInterface, int i2) {
        this.d = fileEditor;
        this.e = fileItemInterface;
        this.f = i2;
    }

    public final void accept(Object obj) {
        this.d.lambda$runNextFile$1(this.e, this.f, (Boolean) obj);
    }
}
