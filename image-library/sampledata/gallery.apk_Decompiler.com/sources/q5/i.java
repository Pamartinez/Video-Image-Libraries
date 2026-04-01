package q5;

import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements UniqueKey {
    public final /* synthetic */ int d;
    public final /* synthetic */ ThumbnailInterface e;

    public /* synthetic */ i(ThumbnailInterface thumbnailInterface, int i2) {
        this.d = i2;
        this.e = thumbnailInterface;
    }

    public final int getKey() {
        int i2 = this.d;
        ThumbnailInterface thumbnailInterface = this.e;
        switch (i2) {
            case 0:
                return thumbnailInterface.hashCode();
            case 1:
                int i7 = ThumbnailLoader.f3091a;
                return thumbnailInterface.hashCode();
            default:
                return thumbnailInterface.getSimpleHashCode();
        }
    }
}
