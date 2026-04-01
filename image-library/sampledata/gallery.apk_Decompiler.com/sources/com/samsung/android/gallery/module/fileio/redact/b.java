package com.samsung.android.gallery.module.fileio.redact;

import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ FileCreator d;

    public /* synthetic */ b(FileCreator fileCreator) {
        this.d = fileCreator;
    }

    public final void accept(Object obj) {
        this.d.lambda$handleFile$1((Boolean) obj);
    }
}
