package M4;

import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.xmp.XmpUtils;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class m implements Supplier {
    public final /* synthetic */ int d;
    public final /* synthetic */ MediaItem e;

    public /* synthetic */ m(MediaItem mediaItem, int i2) {
        this.d = i2;
        this.e = mediaItem;
    }

    public final Object get() {
        int i2 = this.d;
        MediaItem mediaItem = this.e;
        switch (i2) {
            case 0:
                return DbCompat.autoAlbumApi().getAlbumCount(mediaItem.getAlbumID());
            case 1:
                return MediaItemStory.getStoryTimeDurationArray(mediaItem);
            default:
                return XmpUtils.getXmpTags(mediaItem);
        }
    }
}
