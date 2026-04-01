package com.samsung.android.gallery.app.controller.internals;

import com.samsung.android.gallery.app.controller.internals.EditDateAndTimeCmd2;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ EditDateAndTimeCmd2.DateUpdater f2506a;

    public /* synthetic */ d(EditDateAndTimeCmd2.DateUpdater dateUpdater) {
        this.f2506a = dateUpdater;
    }

    public final Object apply(Object obj) {
        return Boolean.valueOf(this.f2506a.updateFunction((FileItemInterface) obj));
    }
}
