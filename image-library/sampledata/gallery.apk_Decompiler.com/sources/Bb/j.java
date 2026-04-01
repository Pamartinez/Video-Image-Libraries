package Bb;

import B6.c;
import android.graphics.Bitmap;
import android.widget.ImageView;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailLoadedListener;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.utils.ThreadUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements UniqueKey, ThumbnailLoadedListener {
    public final /* synthetic */ ImageView d;

    public /* synthetic */ j(ImageView imageView) {
        this.d = imageView;
    }

    public int getKey() {
        return this.d.hashCode();
    }

    public void onLoaded(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        ThreadUtil.postOnUiThread(new c(this.d, bitmap, 1));
    }
}
