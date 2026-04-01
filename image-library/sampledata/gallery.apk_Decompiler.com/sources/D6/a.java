package D6;

import F2.E;
import Sd.o;
import androidx.media3.common.Format;
import androidx.media3.exoplayer.trackselection.BaseTrackSelection;
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector;
import androidx.media3.extractor.metadata.mp4.SlowMotionData;
import com.samsung.android.gallery.app.activity.external.launcher.UriItemViewLauncher;
import com.samsung.android.gallery.app.controller.story.SaveHideRuleCmd;
import com.samsung.android.gallery.app.ui.dialog.creature.merge.MergeCreatureAdapter;
import com.samsung.android.gallery.app.ui.list.pictures.DeleteAnimationHelper;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesPinchAnimationManager;
import com.samsung.android.gallery.app.ui.list.search.category.document.SearchDocumentItemAdapter;
import com.samsung.android.gallery.app.ui.list.stories.highlight.collage.CollageItemPicker;
import com.samsung.android.gallery.app.ui.list.stories.pictures.related.RelatedInfo;
import com.samsung.android.gallery.app.ui.list.suggestions.revitalized.RevitalizedPicturesPinchAnimationManager;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.tables.AbstractSorter;
import com.samsung.android.gallery.module.dataset.tables.SearchSorter;
import com.samsung.android.gallery.module.details.DetailsDbQueryDataLoader;
import com.samsung.android.gallery.module.search.engine.MyQueryCreator;
import com.samsung.android.gallery.module.search.root.ClusterResultsEntity;
import com.samsung.android.gallery.module.search.root.ClusterResultsEntry;
import com.samsung.android.gallery.module.search.root.FilterResultsEntity;
import com.samsung.android.gallery.module.search.root.FilterResultsEntry;
import com.samsung.android.gallery.widget.listview.pinch.PinchItem;
import com.samsung.android.gallery.widget.search.filter.AllFiltersView;
import java.io.File;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Comparator {
    public final /* synthetic */ int d;

    public /* synthetic */ a(int i2) {
        this.d = i2;
    }

    public final int compare(Object obj, Object obj2) {
        switch (this.d) {
            case 0:
                return ((RelatedInfo) obj).compare((RelatedInfo) obj2);
            case 1:
                return UriItemViewLauncher.lambda$sortQuickViewUriItemList$2((MediaItem) obj, (MediaItem) obj2);
            case 2:
                Map.Entry entry = (Map.Entry) obj;
                Map.Entry entry2 = (Map.Entry) obj2;
                Objects.requireNonNull(entry);
                Objects.requireNonNull(entry2);
                Comparable comparable = (Comparable) entry.getKey();
                Comparable comparable2 = (Comparable) entry2.getKey();
                comparable.getClass();
                comparable2.getClass();
                return comparable.compareTo(comparable2);
            case 3:
                return BaseTrackSelection.lambda$new$0((Format) obj, (Format) obj2);
            case 4:
                return DefaultTrackSelector.lambda$static$0((Integer) obj, (Integer) obj2);
            case 5:
                return RevitalizedPicturesPinchAnimationManager.lambda$static$3((PinchItem) obj, (PinchItem) obj2);
            case 6:
                String m = o.m(((File) obj).getName());
                String m4 = o.m(((File) obj2).getName());
                if (m == null || m4 == null) {
                    return 0;
                }
                return m.compareTo(m4);
            case 7:
                String m8 = o.m(((File) obj).getName());
                String m9 = o.m(((File) obj2).getName());
                if (m8 == null || m9 == null) {
                    return 0;
                }
                return m8.compareTo(m9);
            case 8:
                return AllFiltersView.lambda$updateSelectedFilters$3((FilterResultsEntity) obj, (FilterResultsEntity) obj2);
            case 9:
                return E.f239a.b(((SlowMotionData.Segment) obj).startTimeMs, ((SlowMotionData.Segment) obj2).startTimeMs).b(((SlowMotionData.Segment) obj).endTimeMs, ((SlowMotionData.Segment) obj2).endTimeMs).a(((SlowMotionData.Segment) obj).speedDivisor, ((SlowMotionData.Segment) obj2).speedDivisor).f();
            case 10:
                return SaveHideRuleCmd.lambda$getHideItemComparator$9((MediaItem) obj, (MediaItem) obj2);
            case 11:
                return MyQueryCreator.lambda$sortCreatureEntity$1((FilterResultsEntity) obj, (FilterResultsEntity) obj2);
            case 12:
                return Integer.compare(((Integer) ((Map.Entry) obj).getKey()).intValue(), ((Integer) ((Map.Entry) obj2).getKey()).intValue());
            case 13:
                return SearchSorter.lambda$new$0((AbstractSorter.SortData) obj, (AbstractSorter.SortData) obj2);
            case 14:
                return SearchSorter.lambda$new$1((AbstractSorter.SortData) obj, (AbstractSorter.SortData) obj2);
            case 15:
                return SearchSorter.lambda$new$2((AbstractSorter.SortData) obj, (AbstractSorter.SortData) obj2);
            case 16:
                return SearchSorter.lambda$new$3((AbstractSorter.SortData) obj, (AbstractSorter.SortData) obj2);
            case 17:
                return ClusterResultsEntry.Extractor.lambda$new$2((ClusterResultsEntity) obj, (ClusterResultsEntity) obj2);
            case 18:
                return Integer.compare(((FilterResultsEntity) obj2).getCount(), ((FilterResultsEntity) obj).getCount());
            case 19:
                return ((FilterResultsEntity) obj).getName().compareToIgnoreCase(((FilterResultsEntity) obj2).getName());
            case 20:
                return FilterResultsEntry.Builder.lambda$static$1((FilterResultsEntity) obj, (FilterResultsEntity) obj2);
            case 21:
                return ((String) obj).compareTo((String) obj2);
            case 22:
                return CollageItemPicker.lambda$sortContentInfo$5((CollageItemPicker.Content) obj, (CollageItemPicker.Content) obj2);
            case 23:
                return DetailsDbQueryDataLoader.lambda$static$4((MediaItem) obj, (MediaItem) obj2);
            case 24:
                return DeleteAnimationHelper.lambda$static$0((Integer) obj, (Integer) obj2);
            case 25:
                return PicturesPinchAnimationManager.lambda$static$12((PinchItem) obj, (PinchItem) obj2);
            case 26:
                return ((Integer) obj).compareTo((Integer) obj2);
            case 27:
                return SearchDocumentItemAdapter.lambda$sortSubCategory$0((Map.Entry) obj, (Map.Entry) obj2);
            case 28:
                return ((Long) ((Map.Entry) obj).getValue()).compareTo((Long) ((Map.Entry) obj2).getValue());
            default:
                return MergeCreatureAdapter.lambda$new$3((MediaItem) obj, (MediaItem) obj2);
        }
    }
}
