package com.samsung.android.gallery.app.ui.list.stories.category.categorylist;

import com.samsung.android.gallery.module.data.MediaItem;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ StoriesTripListAdapter f2555a;

    public /* synthetic */ a(StoriesTripListAdapter storiesTripListAdapter) {
        this.f2555a = storiesTripListAdapter;
    }

    public final Object apply(Object obj) {
        return Integer.valueOf(this.f2555a.getStartYear((MediaItem) obj));
    }
}
