package com.samsung.android.gallery.module.fileio.compat;

import com.samsung.android.gallery.module.fileio.compat.RestoreUserData;
import java.util.ArrayList;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ RestoreUserData.TagBulkOperation f3019a;
    public final /* synthetic */ ArrayList b;

    public /* synthetic */ i(RestoreUserData.TagBulkOperation tagBulkOperation, ArrayList arrayList) {
        this.f3019a = tagBulkOperation;
        this.b = arrayList;
    }

    public final void accept(Object obj, Object obj2) {
        this.f3019a.lambda$applyBatch$1(this.b, (String) obj, (Long) obj2);
    }
}
