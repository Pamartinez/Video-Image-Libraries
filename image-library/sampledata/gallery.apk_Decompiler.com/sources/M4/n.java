package M4;

import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class n implements BooleanSupplier {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2397a;
    public final /* synthetic */ MediaItem b;

    public /* synthetic */ n(MediaItem mediaItem, int i2) {
        this.f2397a = i2;
        this.b = mediaItem;
    }

    public final boolean getAsBoolean() {
        int i2 = this.f2397a;
        MediaItem mediaItem = this.b;
        switch (i2) {
            case 0:
                return MediaItemUtil.containsLockedAlbum(mediaItem);
            default:
                return mediaItem.isSupportRegionDecoding();
        }
    }
}
