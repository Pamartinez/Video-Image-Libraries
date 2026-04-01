package x6;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager.BlurInterface;
import com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager.BlurUtil;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ BlurInterface e;
    public final /* synthetic */ MediaItem f;
    public final /* synthetic */ Bitmap g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ long f2743h;

    public /* synthetic */ b(BlurInterface blurInterface, MediaItem mediaItem, Bitmap bitmap, long j2, int i2) {
        this.d = i2;
        this.e = blurInterface;
        this.f = mediaItem;
        this.g = bitmap;
        this.f2743h = j2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                BlurUtil.blurAndBindBitmap(this.e, this.f, this.g, this.f2743h);
                return;
            default:
                BlurUtil.blurAndBindFilteredBitmap(this.e, this.f, this.g, this.f2743h);
                return;
        }
    }
}
