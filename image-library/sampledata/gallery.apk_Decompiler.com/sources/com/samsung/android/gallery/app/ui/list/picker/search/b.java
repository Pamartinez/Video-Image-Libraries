package com.samsung.android.gallery.app.ui.list.picker.search;

import com.samsung.android.gallery.support.utils.TimeTickLog;
import java.util.LinkedHashSet;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ SearchPicturesPickerAdapter d;
    public final /* synthetic */ Runnable e;
    public final /* synthetic */ TimeTickLog f;
    public final /* synthetic */ LinkedHashSet g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ LinkedHashSet f2537h;

    public /* synthetic */ b(SearchPicturesPickerAdapter searchPicturesPickerAdapter, a aVar, TimeTickLog timeTickLog, LinkedHashSet linkedHashSet, LinkedHashSet linkedHashSet2) {
        this.d = searchPicturesPickerAdapter;
        this.e = aVar;
        this.f = timeTickLog;
        this.g = linkedHashSet;
        this.f2537h = linkedHashSet2;
    }

    public final void run() {
        this.d.lambda$restoreClipboard$1(this.e, this.f, this.g, this.f2537h);
    }
}
