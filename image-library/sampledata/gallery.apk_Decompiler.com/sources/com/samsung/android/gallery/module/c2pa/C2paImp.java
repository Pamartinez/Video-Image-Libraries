package com.samsung.android.gallery.module.c2pa;

import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.visual.ai.sdkcommon.c;
import java.util.function.Consumer;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface C2paImp {
    void close();

    void create(String str, FileItemInterface fileItemInterface, FileItemInterface fileItemInterface2, boolean z, c cVar);

    void extract(String str, Consumer<C2paInfo> consumer);

    void open();

    void update(String str, FileItemInterface fileItemInterface, Function<FileItemInterface, Boolean> function, c cVar);
}
