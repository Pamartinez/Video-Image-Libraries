package E3;

import com.samsung.android.gallery.app.activity.external.launcher.HttpItemViewLauncher;
import com.samsung.android.gallery.app.controller.viewer.DownloadHttpForViewerCmd;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements DownloadHttpForViewerCmd.DownloadCompleteListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ HttpItemViewLauncher f2313a;

    public /* synthetic */ g(HttpItemViewLauncher httpItemViewLauncher) {
        this.f2313a = httpItemViewLauncher;
    }

    public final void a(Object[] objArr) {
        this.f2313a.onHttpDownloadCompleted(objArr);
    }
}
