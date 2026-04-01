package A4;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailLoadedListener;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class G implements EventContext.OnSelectionListener, ThumbnailLoadedListener {
    public final /* synthetic */ BaseListViewAdapter d;

    public /* synthetic */ G(BaseListViewAdapter baseListViewAdapter) {
        this.d = baseListViewAdapter;
    }

    public void onLoaded(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        this.d.setBitmapWithBind(bitmap, uniqueKey, thumbKind);
    }

    public boolean onSelectionCompleted(EventContext eventContext, MediaItem[] mediaItemArr) {
        return Optional.ofNullable(this.d.getSelectableChecker()).ifPresent(new C0367b(2, mediaItemArr));
    }
}
