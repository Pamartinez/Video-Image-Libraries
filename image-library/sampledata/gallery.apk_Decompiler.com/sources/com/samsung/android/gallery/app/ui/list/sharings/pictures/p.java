package com.samsung.android.gallery.app.ui.list.sharings.pictures;

import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class p implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ String f2554a;

    public /* synthetic */ p(String str) {
        this.f2554a = str;
    }

    public final boolean test(Object obj) {
        return MediaItemMde.getGroupId((MediaItem) obj).equals(this.f2554a);
    }
}
