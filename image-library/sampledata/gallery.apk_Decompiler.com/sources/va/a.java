package va;

import com.samsung.android.gallery.module.viewer.DualPhotoManager;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements ThreadPool.Job {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ DualPhotoManager f3296a;
    public final /* synthetic */ boolean b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ BiConsumer f3297c;

    public /* synthetic */ a(DualPhotoManager dualPhotoManager, BiConsumer biConsumer, boolean z) {
        this.f3296a = dualPhotoManager;
        this.b = z;
        this.f3297c = biConsumer;
    }

    public final Object run(ThreadPool.JobContext jobContext) {
        return this.f3296a.lambda$changeDualPhoto$0(this.b, this.f3297c, jobContext);
    }
}
