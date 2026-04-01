package com.samsung.android.gallery.app.service;

import com.samsung.android.gallery.module.fileio.compat.FileOpError;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2515a;
    public final /* synthetic */ ArrayList b;

    public /* synthetic */ c(ArrayList arrayList, int i2) {
        this.f2515a = i2;
        this.b = arrayList;
    }

    public final void accept(Object obj, Object obj2) {
        switch (this.f2515a) {
            case 0:
                this.b.add(((FileOpError) obj) + "=" + ((List) obj2).size());
                return;
            default:
                FileOpData.lambda$toSummary$9(this.b, (Integer) obj, (List) obj2);
                return;
        }
    }
}
