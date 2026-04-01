package e5;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewAdapter;
import com.samsung.android.gallery.app.ui.list.pictures.adapter.MultiClusterAdapter;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailLoadedListener;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class m implements ThumbnailLoadedListener, MultiClusterAdapter.TimelineModeLookup {
    public final /* synthetic */ PicturesViewAdapter d;

    public /* synthetic */ m(PicturesViewAdapter picturesViewAdapter) {
        this.d = picturesViewAdapter;
    }

    public void onLoaded(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        this.d.setBitmapWithBind(bitmap, uniqueKey, thumbKind);
    }
}
