package E3;

import android.graphics.Bitmap;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ String e;
    public final /* synthetic */ MediaItem f;

    public /* synthetic */ h(String str, MediaItem mediaItem, int i2) {
        this.d = i2;
        this.e = str;
        this.f = mediaItem;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((QueryParams) obj).setDbKey(this.e).setGroupMediaFilter(this.f.getAlbumID(), this.f.getGroupMediaId(), true);
                return;
            default:
                BitmapUtils.saveAs((Bitmap) obj, this.e, 100, this.f.isPng());
                return;
        }
    }
}
