package com.samsung.android.gallery.plugins.filebrowser;

import com.samsung.android.gallery.plugins.filebrowser.LogViewFragment;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class q implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ LogViewFragment.LineViewAdapter f3095a;

    public /* synthetic */ q(LogViewFragment.LineViewAdapter lineViewAdapter) {
        this.f3095a = lineViewAdapter;
    }

    public final Object apply(Object obj) {
        return this.f3095a.lambda$getSelectedText$1((Integer) obj);
    }
}
