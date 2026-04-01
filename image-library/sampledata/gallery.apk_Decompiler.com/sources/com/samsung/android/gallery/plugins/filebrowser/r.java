package com.samsung.android.gallery.plugins.filebrowser;

import com.samsung.android.gallery.plugins.filebrowser.LogViewFragment;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class r implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ LogViewFragment.LineViewAdapter f3096a;

    public /* synthetic */ r(LogViewFragment.LineViewAdapter lineViewAdapter) {
        this.f3096a = lineViewAdapter;
    }

    public final void accept(Object obj, Object obj2) {
        this.f3096a.lambda$onCreateViewHolder$3((LogViewFragment.LineViewHolder) obj, (Boolean) obj2);
    }
}
