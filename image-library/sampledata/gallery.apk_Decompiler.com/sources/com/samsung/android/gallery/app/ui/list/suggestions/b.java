package com.samsung.android.gallery.app.ui.list.suggestions;

import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.threadpool.ThreadPool;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements ThreadPool.Job {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ MediaItem f2564a;

    public /* synthetic */ b(MediaItem mediaItem) {
        this.f2564a = mediaItem;
    }

    public final Object run(ThreadPool.JobContext jobContext) {
        return SuggestionsPresenter.lambda$updateAutoItemStatus$1(this.f2564a, jobContext);
    }
}
