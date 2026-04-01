package U9;

import android.graphics.Bitmap;
import android.location.Location;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.fragment.app.FragmentActivity;
import com.samsung.android.gallery.app.controller.trash.LaunchTrashBinCmd;
import com.samsung.android.gallery.app.controller.viewer.DownloadNdeOriginalCmd;
import com.samsung.android.gallery.app.controller.viewer.SaveCaptureCmd;
import com.samsung.android.gallery.app.controller.viewer.SaveCaptureCompatCmd;
import com.samsung.android.gallery.app.controller.viewer.StartDirectorsViewDualEditCmd;
import com.samsung.android.gallery.app.provider.ShareProvider;
import com.samsung.android.gallery.app.remote.v2.PresentationViewPager;
import com.samsung.android.gallery.app.ui.list.mapclustering.ClusteringMapFragment;
import com.samsung.android.gallery.app.ui.list.mapclustering.EmbeddedMapPresenter;
import com.samsung.android.gallery.app.ui.list.picker.albums.AlbumFolderPickerFragment;
import com.samsung.android.gallery.app.ui.list.picker.albums.AlbumsPickerFragment;
import com.samsung.android.gallery.app.ui.list.picker.pictures.PicturesPickerFragment;
import com.samsung.android.gallery.app.ui.list.picker.search.SearchPicturesPickerFragment;
import com.samsung.android.gallery.app.ui.list.stories.category.category.categoryitem.OnDemandSuggestionItemAdapter;
import com.samsung.android.gallery.app.ui.list.stories.category.category.categoryitem.StoriesCatTransitoryItemOnDemandViewHolder;
import com.samsung.android.gallery.app.ui.list.stories.category.categorylist.StoriesTripListFragment;
import com.samsung.android.gallery.app.ui.list.stories.category.ondemand.OnDemandFloatingItemAdapter;
import com.samsung.android.gallery.app.ui.list.stories.category.ondemand.PdcRecommendDelegate;
import com.samsung.android.gallery.app.ui.list.stories.header.BasePinView;
import com.samsung.android.gallery.app.ui.map.picker.google.GoogleMapPickerContainer;
import com.samsung.android.gallery.app.ui.viewer2.selection.SelectionViewFragment;
import com.samsung.android.gallery.app.ui.viewer2.singletaken.SingleTakenViewerHolder;
import com.samsung.android.gallery.database.dbtype.ExtrasID;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.chapter.LayoutInfo;
import com.samsung.android.gallery.module.dataset.tables.AbstractSorter;
import com.samsung.android.gallery.module.dataset.tables.ChapterIndexer;
import com.samsung.android.gallery.module.dataset.tables.SearchCreatureSorter;
import com.samsung.android.gallery.module.debugger.ExceptionHandler;
import com.samsung.android.gallery.module.foldable.FoldStateManager;
import com.samsung.android.gallery.module.search.root.AllScreenshotFilterResultsEntity;
import com.samsung.android.gallery.module.search.root.FilterResultsEntity;
import com.samsung.android.gallery.widget.filmstrip3.selection.SelectionFilmStripView;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbar;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.sesl.visualeffect.lighteffects.common.control.VibeEffectBase;
import java.util.Map;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ b(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        Object obj2 = this.e;
        switch (i2) {
            case 0:
                ((MediaItem) obj).setExtra(ExtrasID.SCREENSHOT_FILTER_ON, ((Pair) ((Map) obj2).getOrDefault(((MediaItem) obj).getSubCategory(), new Pair(Boolean.TRUE, Integer.MAX_VALUE))).first);
                return;
            case 1:
                ((LaunchTrashBinCmd) obj2).launchTrashBin(((Boolean) obj).booleanValue());
                return;
            case 2:
                ((SelectionViewFragment) obj2).lambda$refreshFilmStripView$9((SelectionFilmStripView) obj);
                return;
            case 3:
                ((SearchToolbar) obj2).addViewBeforeInputField((ViewGroup) obj);
                return;
            case 4:
                ((DownloadNdeOriginalCmd) obj2).lambda$onExecute$0((Boolean) obj);
                return;
            case 5:
                ((SaveCaptureCmd) obj2).lambda$copyAndScan$9((Boolean) obj);
                return;
            case 6:
                ((SaveCaptureCompatCmd) obj2).OnLoadAiZoomCompleted((Bitmap) obj);
                return;
            case 7:
                ((StartDirectorsViewDualEditCmd) obj2).lambda$executeDownload$2(obj);
                return;
            case 8:
                ((ClusteringMapFragment) obj2).lambda$setToolbar$0((GalleryToolbar) obj);
                return;
            case 9:
                ((EmbeddedMapPresenter) obj2).lambda$createClusterData$1((MediaItem) obj);
                return;
            case 10:
                ((SingleTakenViewerHolder) obj2).lambda$replaceComposite$4((MediaItem) obj);
                return;
            case 11:
                ((ShareProvider) obj2).lambda$loadMediaItem$3((MediaItem) obj);
                return;
            case 12:
                ((OnDemandSuggestionItemAdapter) obj2).lambda$onCreateViewHolder$0((String) obj);
                return;
            case 13:
                ((StoriesCatTransitoryItemOnDemandViewHolder) obj2).onRecommendItemClicked((String) obj);
                return;
            case 14:
                ((FilterResultsEntity) obj2).setMediaItem((MediaItem) obj);
                return;
            case 15:
                ((StoriesTripListFragment) obj2).onSelectYear((Integer) obj);
                return;
            case 16:
                ((AlbumFolderPickerFragment) obj2).lambda$onCreate$0((FoldStateManager) obj);
                return;
            case 17:
                ((AlbumsPickerFragment) obj2).lambda$onBindView$0((FragmentActivity) obj);
                return;
            case 18:
                ((ChapterIndexer) obj2).lambda$updateExtraDisplayable$0((LayoutInfo) obj);
                return;
            case 19:
                ((SearchCreatureSorter) obj2).lambda$sort$4((AbstractSorter.SortData) obj);
                return;
            case 20:
                VibeEffectBase.destroy$lambda$15((VibeEffectBase) obj2, (View) obj);
                return;
            case 21:
                ((PicturesPickerFragment) obj2).lambda$onGridChanged$1((String) obj);
                return;
            case 22:
                ((OnDemandFloatingItemAdapter) obj2).lambda$onCreateViewHolder$1((Integer) obj);
                return;
            case 23:
                ((PdcRecommendDelegate) obj2).onItemSelected(((Boolean) obj).booleanValue());
                return;
            case 24:
                ((GoogleMapPickerContainer) obj2).lambda$new$1((Location) obj);
                return;
            case 25:
                ((ExceptionHandler) obj2).Log((String) obj);
                return;
            case 26:
                ((PresentationViewPager) obj2).lambda$updateViews$6((ImageView) obj);
                return;
            case 27:
                ((SearchPicturesPickerFragment) obj2).lambda$updateEmptyViews$0((View) obj);
                return;
            case 28:
                ((BasePinView) obj2).onDataChangedOnUi(((Boolean) obj).booleanValue());
                return;
            default:
                ((AllScreenshotFilterResultsEntity) obj2).addRawLabel((String) obj);
                return;
        }
    }
}
