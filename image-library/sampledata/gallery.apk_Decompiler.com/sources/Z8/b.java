package Z8;

import com.samsung.android.gallery.module.dataset.tables.DefaultTable;
import com.samsung.android.gallery.support.threadpool.ThreadPool;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements ThreadPool.Job {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ DefaultTable f2909a;
    public final /* synthetic */ boolean b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ long f2910c;

    public /* synthetic */ b(DefaultTable defaultTable, boolean z, long j2) {
        this.f2909a = defaultTable;
        this.b = z;
        this.f2910c = j2;
    }

    public final Object run(ThreadPool.JobContext jobContext) {
        return this.f2909a.lambda$readDataCursor$0(this.b, this.f2910c, jobContext);
    }
}
