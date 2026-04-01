package x5;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.clusteritem.ClusterResultViewAdapter;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailLoadedListener;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements ThumbnailLoadedListener {
    public final /* synthetic */ int d;

    public /* synthetic */ b(int i2) {
        this.d = i2;
    }

    public final void onLoaded(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        ClusterResultViewAdapter.lambda$replaceIfHighQualityRequired$5(this.d, bitmap, uniqueKey, thumbKind);
    }
}
