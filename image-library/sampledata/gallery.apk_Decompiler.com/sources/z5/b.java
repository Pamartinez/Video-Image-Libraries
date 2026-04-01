package z5;

import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.FaceClusterMergeDelegate;
import com.samsung.android.gallery.support.threadpool.ThreadPool;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements ThreadPool.Job {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2764a;
    public final /* synthetic */ FaceClusterMergeDelegate b;

    public /* synthetic */ b(FaceClusterMergeDelegate faceClusterMergeDelegate, int i2) {
        this.f2764a = i2;
        this.b = faceClusterMergeDelegate;
    }

    public final Object run(ThreadPool.JobContext jobContext) {
        int i2 = this.f2764a;
        FaceClusterMergeDelegate faceClusterMergeDelegate = this.b;
        switch (i2) {
            case 0:
                return faceClusterMergeDelegate.lambda$handleMergeCancel$4(jobContext);
            default:
                return faceClusterMergeDelegate.lambda$loadClusterItem$3(jobContext);
        }
    }
}
