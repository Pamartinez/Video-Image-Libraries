package J6;

import com.samsung.android.gallery.app.ui.list.stories.slideshow.SimpleExportHandler;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.story.ExportType;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ SimpleExportHandler d;
    public final /* synthetic */ MediaItem e;
    public final /* synthetic */ ExportType f;
    public final /* synthetic */ boolean g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ int f2379h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ int f2380i;

    public /* synthetic */ b(SimpleExportHandler simpleExportHandler, MediaItem mediaItem, ExportType exportType, boolean z, int i2, int i7) {
        this.d = simpleExportHandler;
        this.e = mediaItem;
        this.f = exportType;
        this.g = z;
        this.f2379h = i2;
        this.f2380i = i7;
    }

    public final void run() {
        this.d.lambda$startSimpleExport$0(this.e, this.f, this.g, this.f2379h, this.f2380i);
    }
}
