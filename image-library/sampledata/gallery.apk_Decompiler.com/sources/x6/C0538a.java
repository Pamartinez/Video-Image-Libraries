package x6;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager.BlurInterface;
import com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager.BlurUtil;
import com.samsung.android.gallery.module.data.MediaItem;

/* renamed from: x6.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0538a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ BlurInterface e;
    public final /* synthetic */ MediaItem f;
    public final /* synthetic */ Bitmap g;

    public /* synthetic */ C0538a(BlurInterface blurInterface, MediaItem mediaItem, Bitmap bitmap, int i2) {
        this.d = i2;
        this.e = blurInterface;
        this.f = mediaItem;
        this.g = bitmap;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                BlurUtil.bindBitmap(this.e, this.f, this.g);
                return;
            default:
                BlurUtil.bindBitmap(this.e, this.f, this.g);
                return;
        }
    }
}
