package T3;

import com.samsung.android.gallery.app.controller.sharing.request.RequestDeleteContent;
import com.samsung.android.gallery.app.controller.sharing.request.RequestDownloadContent;
import com.samsung.android.gallery.app.ui.list.picker.albums.AlbumsPickerPresenter;
import com.samsung.android.gallery.app.ui.list.search.category.people.CreatureHidePresenter;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Delegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.ControlDelegate;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.tables.AbstractSorter;
import com.samsung.android.gallery.module.dataset.tables.SearchCreatureSorter;
import com.samsung.android.gallery.module.map.abstraction.IMapMarker;
import com.samsung.android.gallery.module.map.transition.BaseMarkerManager;
import com.samsung.android.gallery.module.map.transition.abstraction.MarkerWithPosition;
import com.samsung.android.sdk.mobileservice.social.share.result.ContentDownloadResult;
import com.samsung.android.sdk.mobileservice.social.share.result.ItemListResult;
import com.samsung.android.sum.core.filter.ContentFilter;
import com.samsung.android.sum.core.format.MediaFormat;
import java.util.Map;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2431a;
    public final /* synthetic */ Object b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f2432c;

    public /* synthetic */ a(int i2, Object obj, Object obj2) {
        this.f2431a = i2;
        this.b = obj;
        this.f2432c = obj2;
    }

    public final boolean test(Object obj) {
        switch (this.f2431a) {
            case 0:
                return ((RequestDeleteContent) this.b).lambda$getUploadingItems$1((ItemListResult.SharedItemListFailureResult) this.f2432c, (FileItemInterface) obj);
            case 1:
                return ((RequestDownloadContent) this.b).lambda$showDownloadWarningAsPartialUploading$3((ContentDownloadResult.DownloadedContent) this.f2432c, (FileItemInterface) obj);
            case 2:
                return ((AlbumsPickerPresenter) this.b).lambda$contains$0((String) this.f2432c, (MediaItem) obj);
            case 3:
                return ((SearchCreatureSorter) this.b).lambda$sort$1((String) this.f2432c, (AbstractSorter.SortData) obj);
            case 4:
                return ((BaseMarkerManager) this.b).lambda$onMarkerClicked$2((IMapMarker) this.f2432c, (MarkerWithPosition) obj);
            case 5:
                return ((ContentFilter) this.b).lambda$filterOut$0((MediaFormat) this.f2432c, (Map.Entry) obj);
            case 6:
                return ((CreatureHidePresenter) this.b).lambda$changeItemProperty$9((MediaItem) this.f2432c, (MediaItem) obj);
            case 7:
                return ((CreatureHidePresenter) this.b).lambda$restoreChangedCreatureMapIfNeeded$3((Long) this.f2432c, (MediaItem) obj);
            default:
                return ControlDelegate.lambda$handlePostEvent$7((Event) this.b, (Object[]) this.f2432c, (Delegate) obj);
        }
    }
}
