package com.samsung.android.gallery.app.ui.list.search;

import com.samsung.android.gallery.app.ui.list.search.CategoryActivityCardHolder;
import com.samsung.android.gallery.module.abstraction.VisualSearchCategory;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2538a;

    public /* synthetic */ a(int i2) {
        this.f2538a = i2;
    }

    public final boolean test(Object obj) {
        switch (this.f2538a) {
            case 0:
                return CategoryActivityCardHolder.ActivityItemAdapter.UnsupportedInPicker.contains(((MediaItem) obj).getSubCategory());
            case 1:
                return "PrivateAlbum".equals(((MediaItem) obj).getSubCategory());
            default:
                return VisualSearchCategory.isEnabled((String) obj);
        }
    }
}
