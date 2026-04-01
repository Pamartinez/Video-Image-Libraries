package J;

import android.graphics.Bitmap;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.exoplayer.analytics.DefaultAnalyticsCollector;
import androidx.media3.exoplayer.source.LoadEventInfo;
import androidx.media3.exoplayer.source.MediaLoadData;
import androidx.media3.exoplayer.source.MediaSourceEventListener;
import com.samsung.android.gallery.app.ui.map.search.SearchMarkerManagerDelegate;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.map.abstraction.IMapMarker;
import com.samsung.android.gallery.module.map.clustering.ICluster;
import com.samsung.android.gallery.module.map.transition.BaseMarkerManager;
import com.samsung.android.gallery.module.search.root.FilterResultsEntity;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailLoadedListener;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.widget.search.filter.AbsFiltersAdapter;
import com.samsung.android.gallery.widget.search.filter.SearchFiltersViewHolder;
import java.io.IOException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements ListenerSet.Event, Consumer, ThumbnailLoadedListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ boolean f350h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ Object f351i;

    public /* synthetic */ g(int i2, Object obj, Object obj2, Object obj3, Object obj4, boolean z) {
        this.d = i2;
        this.f351i = obj;
        this.e = obj2;
        this.f = obj3;
        this.g = obj4;
        this.f350h = z;
    }

    public void accept(Object obj) {
        ((MediaSourceEventListener.EventDispatcher) this.f351i).lambda$loadError$3((LoadEventInfo) this.e, (MediaLoadData) this.f, (IOException) this.g, this.f350h, (MediaSourceEventListener) obj);
    }

    public void invoke(Object obj) {
        DefaultAnalyticsCollector.lambda$onLoadError$29((AnalyticsListener.EventTime) this.f351i, (LoadEventInfo) this.e, (MediaLoadData) this.f, (IOException) this.g, this.f350h, (AnalyticsListener) obj);
    }

    public void onLoaded(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        switch (this.d) {
            case 2:
                ((AbsFiltersAdapter) this.f351i).lambda$loadThumb$3((FilterResultsEntity) this.e, (MediaItem) this.f, (SearchFiltersViewHolder) this.g, this.f350h, bitmap, uniqueKey, thumbKind);
                return;
            case 3:
                ((SearchMarkerManagerDelegate) this.f351i).lambda$updateHighlightMarker$1((IMapMarker) this.e, (ICluster) this.f, this.f350h, (ThumbnailInterface) this.g, bitmap, uniqueKey, thumbKind);
                return;
            default:
                ((BaseMarkerManager) this.f351i).lambda$highlightMarker$5((IMapMarker) this.e, (ICluster) this.f, this.f350h, (ThumbnailInterface) this.g, bitmap, uniqueKey, thumbKind);
                return;
        }
    }

    public /* synthetic */ g(Object obj, IMapMarker iMapMarker, ICluster iCluster, boolean z, ThumbnailInterface thumbnailInterface, int i2) {
        this.d = i2;
        this.f351i = obj;
        this.e = iMapMarker;
        this.f = iCluster;
        this.f350h = z;
        this.g = thumbnailInterface;
    }
}
