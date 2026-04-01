package ob;

import android.app.Activity;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.widget.cache.LayoutCache;

/* renamed from: ob.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0704a implements ThreadPool.Job {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3281a;
    public final /* synthetic */ LayoutCache b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Activity f3282c;

    public /* synthetic */ C0704a(LayoutCache layoutCache, Activity activity, int i2) {
        this.f3281a = i2;
        this.b = layoutCache;
        this.f3282c = activity;
    }

    public final Object run(ThreadPool.JobContext jobContext) {
        switch (this.f3281a) {
            case 0:
                return this.b.lambda$inflate$0(this.f3282c, jobContext);
            default:
                return this.b.lambda$inflate$1(this.f3282c, jobContext);
        }
    }
}
