package S5;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.ui.list.stories.StoriesViewAdapter;
import com.samsung.android.gallery.app.ui.list.stories.viewholder.StoriesViewHolder;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailLoadedListener;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements StoriesViewHolder.OnItemMenuClickListener, ThumbnailLoadedListener {
    public final /* synthetic */ StoriesViewAdapter d;

    public /* synthetic */ a(StoriesViewAdapter storiesViewAdapter) {
        this.d = storiesViewAdapter;
    }

    public void onLoaded(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        this.d.setBitmapWithBind(bitmap, uniqueKey, thumbKind);
    }
}
