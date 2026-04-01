package V3;

import com.samsung.android.gallery.app.controller.trash.EmptyTrashCmd;
import com.samsung.android.gallery.support.threadpool.ThreadPool;

/* renamed from: V3.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0410a implements ThreadPool.Job {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ EmptyTrashCmd f2458a;
    public final /* synthetic */ boolean b;

    public /* synthetic */ C0410a(EmptyTrashCmd emptyTrashCmd, boolean z) {
        this.f2458a = emptyTrashCmd;
        this.b = z;
    }

    public final Object run(ThreadPool.JobContext jobContext) {
        return this.f2458a.lambda$onExecute$0(this.b, jobContext);
    }
}
