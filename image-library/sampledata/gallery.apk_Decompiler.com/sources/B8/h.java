package B8;

import android.content.Context;
import android.graphics.Bitmap;
import com.samsung.android.gallery.module.album.ShortcutHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailLoadedListener;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements ThumbnailLoadedListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ ShortcutHelper e;
    public final /* synthetic */ Context f;
    public final /* synthetic */ MediaItem g;

    public /* synthetic */ h(ShortcutHelper shortcutHelper, Context context, MediaItem mediaItem, int i2) {
        this.d = i2;
        this.e = shortcutHelper;
        this.f = context;
        this.g = mediaItem;
    }

    public final void onLoaded(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        switch (this.d) {
            case 0:
                Bitmap bitmap2 = bitmap;
                MediaItem mediaItem = this.g;
                Bitmap bitmap3 = bitmap2;
                UniqueKey uniqueKey2 = uniqueKey;
                this.e.lambda$setHomeScreenShortcut$1(this.f, mediaItem, bitmap3, uniqueKey2, thumbKind);
                return;
            default:
                this.e.lambda$updateHomeScreenShortcut$6(this.f, this.g, bitmap, uniqueKey, thumbKind);
                return;
        }
    }
}
