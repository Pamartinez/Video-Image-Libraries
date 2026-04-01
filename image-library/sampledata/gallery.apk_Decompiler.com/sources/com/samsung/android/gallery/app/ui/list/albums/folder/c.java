package com.samsung.android.gallery.app.ui.list.albums.folder;

import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.support.threadpool.ThreadPool;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements ThreadPool.Job {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ FolderViewPresenter f2530a;
    public final /* synthetic */ Toolbar b;

    public /* synthetic */ c(FolderViewPresenter folderViewPresenter, Toolbar toolbar) {
        this.f2530a = folderViewPresenter;
        this.b = toolbar;
    }

    public final Object run(ThreadPool.JobContext jobContext) {
        return this.f2530a.lambda$updateToolbar$0(this.b, jobContext);
    }
}
