package com.samsung.android.gallery.module.fileio.compat;

import android.database.Cursor;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ f(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        Object obj2 = this.e;
        switch (i2) {
            case 0:
                ((List) obj2).add(0, (FileOpJob) obj);
                return;
            default:
                ((HashMap) obj2).put(((Cursor) obj).getString(1), Long.valueOf(((Cursor) obj).getLong(0)));
                return;
        }
    }
}
