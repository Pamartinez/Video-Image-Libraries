package com.samsung.android.gallery.app.ui.list.trash;

import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements ThreadPool.Job {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2566a;
    public final /* synthetic */ TrashPresenter b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Toolbar f2567c;

    public /* synthetic */ c(TrashPresenter trashPresenter, Toolbar toolbar, int i2) {
        this.f2566a = i2;
        this.b = trashPresenter;
        this.f2567c = toolbar;
    }

    public final Object run(ThreadPool.JobContext jobContext) {
        switch (this.f2566a) {
            case 0:
                return this.b.lambda$updateToolbar$4(this.f2567c, jobContext);
            default:
                return this.b.lambda$onDataPrepared$0((GalleryToolbar) this.f2567c, jobContext);
        }
    }
}
