package B8;

import android.content.Context;
import android.graphics.Bitmap;
import com.samsung.android.gallery.module.album.ShortcutHelper;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ ShortcutHelper e;
    public final /* synthetic */ Context f;
    public final /* synthetic */ MediaItem g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Bitmap f2773h;

    public /* synthetic */ i(ShortcutHelper shortcutHelper, Context context, MediaItem mediaItem, Bitmap bitmap, int i2) {
        this.d = i2;
        this.e = shortcutHelper;
        this.f = context;
        this.g = mediaItem;
        this.f2773h = bitmap;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$setHomeScreenShortcut$0(this.f, this.g, this.f2773h);
                return;
            default:
                this.e.lambda$updateHomeScreenShortcut$5(this.f, this.g, this.f2773h);
                return;
        }
    }
}
