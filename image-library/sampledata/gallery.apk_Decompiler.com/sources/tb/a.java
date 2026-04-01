package Tb;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.ui.map.search.SearchMarkerManagerDelegate;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.map.abstraction.IMapMarker;
import com.samsung.android.gallery.module.map.clustering.ICluster;
import com.samsung.android.gallery.module.map.transition.BaseMarkerManager;
import com.samsung.android.gallery.module.search.root.FilterResultsEntity;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.widget.search.filter.AbsFiltersAdapter;
import com.samsung.android.gallery.widget.search.filter.SearchFiltersViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Bitmap e;
    public final /* synthetic */ boolean f;
    public final /* synthetic */ Object g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Object f2880h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ Object f2881i;

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ Object f2882j;

    public /* synthetic */ a(AbsFiltersAdapter absFiltersAdapter, FilterResultsEntity filterResultsEntity, MediaItem mediaItem, Bitmap bitmap, SearchFiltersViewHolder searchFiltersViewHolder, boolean z) {
        this.d = 0;
        this.g = absFiltersAdapter;
        this.f2880h = filterResultsEntity;
        this.f2881i = mediaItem;
        this.e = bitmap;
        this.f2882j = searchFiltersViewHolder;
        this.f = z;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                boolean z = this.f;
                ((AbsFiltersAdapter) this.g).lambda$loadThumb$2((FilterResultsEntity) this.f2880h, (MediaItem) this.f2881i, this.e, (SearchFiltersViewHolder) this.f2882j, z);
                return;
            case 1:
                ((SearchMarkerManagerDelegate) this.g).lambda$updateHighlightMarker$0((IMapMarker) this.f2880h, this.e, (ICluster) this.f2881i, this.f, (ThumbnailInterface) this.f2882j);
                return;
            default:
                ((BaseMarkerManager) this.g).lambda$highlightMarker$4((IMapMarker) this.f2880h, this.e, (ICluster) this.f2881i, this.f, (ThumbnailInterface) this.f2882j);
                return;
        }
    }

    public /* synthetic */ a(Object obj, IMapMarker iMapMarker, Bitmap bitmap, ICluster iCluster, boolean z, ThumbnailInterface thumbnailInterface, int i2) {
        this.d = i2;
        this.g = obj;
        this.f2880h = iMapMarker;
        this.e = bitmap;
        this.f2881i = iCluster;
        this.f = z;
        this.f2882j = thumbnailInterface;
    }
}
