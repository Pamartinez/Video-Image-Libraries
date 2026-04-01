package com.samsung.android.gallery.app.ui.viewer2.details.items;

import com.samsung.android.gallery.app.ui.viewer2.details.items.DetailsItemDebugExif;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2583a;

    public /* synthetic */ k(int i2) {
        this.f2583a = i2;
    }

    public final boolean test(Object obj) {
        switch (this.f2583a) {
            case 0:
                return DetailsItemDebugExif.DebugExifAdapter.lambda$new$2((String) obj);
            default:
                return DetailsItemDebugExif.DebugExifAdapter.lambda$new$4((String[]) obj);
        }
    }
}
