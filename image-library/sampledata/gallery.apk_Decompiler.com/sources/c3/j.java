package C3;

import com.samsung.android.gallery.app.activity.GalleryActivityHandler;
import com.samsung.android.gallery.support.threadpool.ThreadPool;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements ThreadPool.Job {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2272a;
    public final /* synthetic */ GalleryActivityHandler b;

    public /* synthetic */ j(GalleryActivityHandler galleryActivityHandler, int i2) {
        this.f2272a = i2;
        this.b = galleryActivityHandler;
    }

    public final Object run(ThreadPool.JobContext jobContext) {
        int i2 = this.f2272a;
        GalleryActivityHandler galleryActivityHandler = this.b;
        switch (i2) {
            case 0:
                return Boolean.valueOf(galleryActivityHandler.preloadHeavyFeatures(jobContext));
            default:
                return galleryActivityHandler.onActivityPostResumeOnBg(jobContext);
        }
    }
}
