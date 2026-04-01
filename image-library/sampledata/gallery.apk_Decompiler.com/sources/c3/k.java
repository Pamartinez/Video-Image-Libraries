package C3;

import android.content.Context;
import com.samsung.android.gallery.app.activity.GalleryApplication;
import com.samsung.android.gallery.support.threadpool.ThreadPool;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k implements ThreadPool.Job {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2273a;
    public final /* synthetic */ GalleryApplication b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Context f2274c;

    public /* synthetic */ k(GalleryApplication galleryApplication, Context context, int i2) {
        this.f2273a = i2;
        this.b = galleryApplication;
        this.f2274c = context;
    }

    public final Object run(ThreadPool.JobContext jobContext) {
        switch (this.f2273a) {
            case 0:
                return this.b.lambda$onCreate$0(this.f2274c, jobContext);
            default:
                return this.b.lambda$onCreate$1(this.f2274c, jobContext);
        }
    }
}
