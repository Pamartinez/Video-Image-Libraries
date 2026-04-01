package u7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.GroupLoader;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObjectComposite;
import com.samsung.android.gallery.module.data.MediaItem;

/* renamed from: u7.g  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0526g implements Runnable {
    public final /* synthetic */ ViewerObjectComposite d;
    public final /* synthetic */ GroupLoader.SubItemsInfo e;
    public final /* synthetic */ int f;
    public final /* synthetic */ MediaItem g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ int f2713h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ long f2714i;

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ MediaItem f2715j;

    public /* synthetic */ C0526g(ViewerObjectComposite viewerObjectComposite, GroupLoader.SubItemsInfo subItemsInfo, int i2, MediaItem mediaItem, int i7, long j2, MediaItem mediaItem2) {
        this.d = viewerObjectComposite;
        this.e = subItemsInfo;
        this.f = i2;
        this.g = mediaItem;
        this.f2713h = i7;
        this.f2714i = j2;
        this.f2715j = mediaItem2;
    }

    public final void run() {
        this.d.lambda$invalidate$0(this.e, this.f, this.g, this.f2713h, this.f2714i, this.f2715j);
    }
}
