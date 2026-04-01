package G3;

import com.samsung.android.gallery.app.controller.abstraction.ViewerDownloadTask;
import com.samsung.android.gallery.module.cloud.abstraction.CloudDownloadListener;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements CloudDownloadListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ViewerDownloadTask f2327a;
    public final /* synthetic */ AtomicInteger b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ long f2328c;

    public /* synthetic */ b(ViewerDownloadTask viewerDownloadTask, AtomicInteger atomicInteger, long j2) {
        this.f2327a = viewerDownloadTask;
        this.b = atomicInteger;
        this.f2328c = j2;
    }

    public final void onProgress(long j2, long j3) {
        this.f2327a.lambda$processDownloadItem$1(this.b, this.f2328c, j2, j3);
    }
}
