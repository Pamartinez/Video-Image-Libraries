package gc;

import android.app.Activity;
import android.graphics.Bitmap;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.utils.DebugSmartCropRectInfo;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ DebugSmartCropRectInfo e;
    public final /* synthetic */ Activity f;
    public final /* synthetic */ MediaItem g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Bitmap f3267h;

    public /* synthetic */ d(DebugSmartCropRectInfo debugSmartCropRectInfo, Activity activity, MediaItem mediaItem, Bitmap bitmap, int i2) {
        this.d = i2;
        this.e = debugSmartCropRectInfo;
        this.f = activity;
        this.g = mediaItem;
        this.f3267h = bitmap;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$showCropRectForThumbnail$1(this.f, this.g, this.f3267h);
                return;
            default:
                this.e.lambda$showCropRectForThumbnail$0(this.f, this.g, this.f3267h);
                return;
        }
    }
}
