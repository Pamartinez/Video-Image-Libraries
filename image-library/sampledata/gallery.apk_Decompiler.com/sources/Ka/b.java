package Ka;

import com.samsung.android.gallery.support.threadpool.ThreadPool;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements ThreadPool.Job {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2836a;
    public final /* synthetic */ Runnable b;

    public /* synthetic */ b(Runnable runnable, int i2) {
        this.f2836a = i2;
        this.b = runnable;
    }

    public final Object run(ThreadPool.JobContext jobContext) {
        int i2 = this.f2836a;
        Runnable runnable = this.b;
        switch (i2) {
            case 0:
                return runnable.run();
            default:
                return runnable.run();
        }
    }
}
