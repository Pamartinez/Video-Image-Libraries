package ea;

import com.samsung.android.gallery.module.cloud.abstraction.CloudDownloadListener;
import com.samsung.android.gallery.module.service.download.CloudDownloadTask;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements CloudDownloadListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ CloudDownloadTask f3262a;

    public /* synthetic */ a(CloudDownloadTask cloudDownloadTask) {
        this.f3262a = cloudDownloadTask;
    }

    public final void onProgress(long j2, long j3) {
        this.f3262a.progress(j2, j3);
    }
}
