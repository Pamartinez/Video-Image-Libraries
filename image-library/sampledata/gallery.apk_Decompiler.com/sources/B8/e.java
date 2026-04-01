package B8;

import android.graphics.Bitmap;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailLoadedListener;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.widget.simpleslideshow.SimpleSlideShowAdapter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements UniqueKey, ThumbnailLoadedListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ MediaItem e;

    public /* synthetic */ e(MediaItem mediaItem, int i2) {
        this.d = i2;
        this.e = mediaItem;
    }

    public int getKey() {
        int i2 = this.d;
        MediaItem mediaItem = this.e;
        switch (i2) {
            case 0:
                return mediaItem.getSimpleHashCode();
            case 1:
                return mediaItem.hashCode();
            case 2:
                return mediaItem.getSubCategory().hashCode();
            case 4:
                return mediaItem.getSubCategory().hashCode();
            case 5:
                return mediaItem.getSubCategory().hashCode();
            default:
                return mediaItem.getSubCategory().hashCode();
        }
    }

    public void onLoaded(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        SimpleSlideShowAdapter.lambda$preloadBitmap$0(this.e, bitmap, uniqueKey, thumbKind);
    }
}
