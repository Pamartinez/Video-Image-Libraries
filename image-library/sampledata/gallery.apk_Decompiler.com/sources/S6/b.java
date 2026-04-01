package S6;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.ui.viewholders.ImageTitleViewHolder;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailLoadedListener;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.utils.ThreadUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements ThumbnailLoadedListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ ImageTitleViewHolder e;

    public /* synthetic */ b(ImageTitleViewHolder imageTitleViewHolder, int i2) {
        this.d = i2;
        this.e = imageTitleViewHolder;
    }

    public final void onLoaded(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        int i2 = this.d;
        ImageTitleViewHolder imageTitleViewHolder = this.e;
        switch (i2) {
            case 0:
                ThreadUtil.postOnUiThread(new a(imageTitleViewHolder, bitmap, 0));
                return;
            default:
                ThreadUtil.postOnUiThread(new a(imageTitleViewHolder, bitmap, 1));
                return;
        }
    }
}
