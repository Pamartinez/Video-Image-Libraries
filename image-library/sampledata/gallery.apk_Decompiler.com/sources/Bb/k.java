package Bb;

import android.graphics.Bitmap;
import android.widget.ImageView;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import com.samsung.android.gallery.app.ui.list.stories.viewholder.StoriesPinchViewHolder;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailLoadedListener;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k implements ThumbnailLoadedListener, StoriesPinchViewHolder.OnItemFavoriteClickListener, ListenerSet.Event {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ k(int i2, Object obj, Object obj2) {
        this.e = obj;
        this.f = obj2;
        this.d = i2;
    }

    public void invoke(Object obj) {
        ((AnalyticsListener) obj).onMediaItemTransition((AnalyticsListener.EventTime) this.e, (MediaItem) this.f, this.d);
    }

    public void onLoaded(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        ((FilmStripViewHolder) this.e).lambda$setDefaultImage$2(this.d, (ImageView) this.f, bitmap, uniqueKey, thumbKind);
    }

    public /* synthetic */ k(FilmStripViewHolder filmStripViewHolder, int i2, ImageView imageView) {
        this.e = filmStripViewHolder;
        this.d = i2;
        this.f = imageView;
    }
}
