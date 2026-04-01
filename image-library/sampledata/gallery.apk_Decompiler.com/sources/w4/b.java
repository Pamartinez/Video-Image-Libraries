package W4;

import com.samsung.android.gallery.app.ui.list.mapclustering.ClusteringMapFragment;
import com.samsung.android.gallery.app.ui.map.search.SearchMarkerManagerDelegate;
import com.samsung.android.gallery.module.abstraction.IdleWorkerJob;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaDataSearchStories;
import com.samsung.android.gallery.module.dataset.MediaDataStoriesCategory;
import com.samsung.android.gallery.module.dataset.MediaDataStoriesV7;
import com.samsung.android.gallery.module.idleworker.IdleWorker;
import com.samsung.android.gallery.module.map.clustering.ICluster;
import com.samsung.android.gallery.module.map.transition.BaseMarkerManager;
import com.samsung.android.gallery.module.map.transition.abstraction.MapItem;
import com.samsung.android.sdk.sgpl.graphics.VideoCodec;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2463a;
    public final /* synthetic */ long b;

    public /* synthetic */ b(long j2, int i2) {
        this.f2463a = i2;
        this.b = j2;
    }

    public final boolean test(Object obj) {
        switch (this.f2463a) {
            case 0:
                return ClusteringMapFragment.lambda$updateInitEntryMarker$1(this.b, (MapItem) obj);
            case 1:
                return SearchMarkerManagerDelegate.lambda$updateVisibleMarkers$3(this.b, (MapItem) obj);
            case 2:
                return MediaDataSearchStories.lambda$readById$2(this.b, (MediaItem) obj);
            case 3:
                return MediaDataStoriesCategory.lambda$readById$1(this.b, (MediaItem) obj);
            case 4:
                return MediaDataStoriesV7.lambda$readById$0(this.b, (MediaItem) obj);
            case 5:
                return BaseMarkerManager.lambda$getMarkerIncludeFocusedId$10(this.b, (MapItem) obj);
            case 6:
                return ((ICluster) obj).getItems().stream().anyMatch(new b(this.b, 5));
            case 7:
                return VideoCodec.lambda$calCoverFrameTime$1(this.b, (long[]) obj);
            default:
                return IdleWorker.lambda$onStartJob$1(this.b, (IdleWorkerJob) obj);
        }
    }
}
