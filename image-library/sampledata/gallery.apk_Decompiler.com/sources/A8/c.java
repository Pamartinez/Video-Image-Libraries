package a8;

import com.samsung.android.gallery.app.ui.viewholders.ImageViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.threadpool.ThreadPool;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements ThreadPool.Job {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ImageViewHolder f2481a;
    public final /* synthetic */ MediaItem b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ long f2482c;
    public final /* synthetic */ int d;

    public /* synthetic */ c(ImageViewHolder imageViewHolder, MediaItem mediaItem, long j2, int i2) {
        this.f2481a = imageViewHolder;
        this.b = mediaItem;
        this.f2482c = j2;
        this.d = i2;
    }

    public final Object run(ThreadPool.JobContext jobContext) {
        return this.f2481a.lambda$updateSimilarItem$8(this.b, this.f2482c, this.d, jobContext);
    }
}
