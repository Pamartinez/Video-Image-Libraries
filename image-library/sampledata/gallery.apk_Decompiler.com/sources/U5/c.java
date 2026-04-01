package U5;

import B8.f;
import android.app.SearchableInfo;
import android.graphics.Bitmap;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.trash.LaunchTrashBinCmd;
import com.samsung.android.gallery.app.controller.trash.OneTrashMigrationCmd;
import com.samsung.android.gallery.app.controller.viewer.CopyToClipboardCmd;
import com.samsung.android.gallery.app.controller.viewer.DeleteUndoSingleCmd;
import com.samsung.android.gallery.app.controller.viewer.EraseObjectCmd;
import com.samsung.android.gallery.app.controller.viewer.SaveCaptureCmd;
import com.samsung.android.gallery.app.controller.viewer.SaveCaptureCompatCmd;
import com.samsung.android.gallery.app.controller.viewer.StartDirectorsViewDualEditCmd;
import com.samsung.android.gallery.app.ui.list.hover.HoverHandler;
import com.samsung.android.gallery.app.ui.list.mapclustering.ClusteringMapFragmentV2;
import com.samsung.android.gallery.app.ui.list.mapclustering.ClusteringMapPresenterV2;
import com.samsung.android.gallery.app.ui.list.mapclustering.EmbeddedMapPresenter;
import com.samsung.android.gallery.app.ui.list.picker.albums.AlbumFolderPickerAdapter;
import com.samsung.android.gallery.app.ui.list.stories.category.StoriesCategory2HeaderAdapter;
import com.samsung.android.gallery.app.ui.list.stories.category.TopColorEffectHandler;
import com.samsung.android.gallery.app.ui.list.stories.category.ondemand.BottomStoryBehavior;
import com.samsung.android.gallery.app.ui.list.stories.category.ondemand.OnDemandRecommendPresenter;
import com.samsung.android.gallery.app.ui.list.stories.category.ondemand.PdcRecommendDelegate;
import com.samsung.android.gallery.app.ui.list.stories.category.ondemand.ProcessingViewManager;
import com.samsung.android.gallery.app.ui.map.clusteringmaker.MultiMarkerMapFragment;
import com.samsung.android.gallery.app.ui.viewer2.remaster.ondemand.RemasterProcessHandler;
import com.samsung.android.gallery.app.ui.viewer2.selection.FastOptionItemHandler;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.app.ui.viewholders.StoryImageViewHolder;
import com.samsung.android.gallery.module.clip.objectcapture.ObjectCaptureEraseInfo;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.search.history.HistoryItem;
import com.samsung.android.gallery.module.search.recommendation.IRecommendationItem;
import com.samsung.android.gallery.module.search.recommendation.RecommendationClickHandler;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbar;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ c(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((StoriesCategory2HeaderAdapter) this.e).lambda$invalidateLayout$2((Runnable) this.f);
                return;
            case 1:
                ((TopColorEffectHandler) this.e).lambda$adjustAlphaByPosition$0((View) this.f);
                return;
            case 2:
                ((RemasterProcessHandler) this.e).lambda$removeRemasterImage$0((String) this.f);
                return;
            case 3:
                ((LaunchTrashBinCmd) this.e).lambda$checkFamilyAlbumTrash$1((Consumer) this.f);
                return;
            case 4:
                ((OneTrashMigrationCmd) this.e).lambda$createDialog$0((EventContext) this.f);
                return;
            case 5:
                ((f) ((HoverHandler.DataLoadCallback) this.e)).a((ArrayList) this.f);
                return;
            case 6:
                ((FastOptionItemHandler) this.e).lambda$onItemSelected$0((MediaItem[]) this.f);
                return;
            case 7:
                ((SearchToolbar) this.e).lambda$updateSearchableInfo$0((SearchableInfo) this.f);
                return;
            case 8:
                ((SearchToolbar) this.e).lambda$onMenuItemClickInternal$8((MenuItem) this.f);
                return;
            case 9:
                new CopyToClipboardCmd.ClipboardService(((MediaItem) this.e).getMimeType(), (String) this.f).start();
                return;
            case 10:
                ((DeleteUndoSingleCmd) this.e).lambda$showSnackBar$1((MediaData) this.f);
                return;
            case 11:
                ((EraseObjectCmd) this.e).lambda$erase$1((ObjectCaptureEraseInfo.EraseType) this.f);
                return;
            case 12:
                ((SaveCaptureCmd) this.e).lambda$sendCompleteEvent$5((Object[]) this.f);
                return;
            case 13:
                SaveCaptureCmd.lambda$captureScreen$1((ImageView) this.e, (Bitmap[]) this.f);
                return;
            case 14:
                ((SaveCaptureCompatCmd) this.e).lambda$OnLoadAiZoomCompleted$0((Bitmap) this.f);
                return;
            case 15:
                ((StartDirectorsViewDualEditCmd) this.e).lambda$executeDownload$3((List) this.f);
                return;
            case 16:
                ((ClusteringMapFragmentV2) this.e).lambda$moveCameraToInitialLocation$3((double[]) this.f);
                return;
            case 17:
                ((ClusteringMapPresenterV2) this.e).lambda$updateClusterItems$1((MediaData) this.f);
                return;
            case 18:
                ((EmbeddedMapPresenter) this.e).lambda$createClusterData$2((ArrayList) this.f);
                return;
            case 19:
                ((Consumer) this.e).accept((LinkedHashMap) this.f);
                return;
            case 20:
                ((MultiMarkerMapFragment) this.e).lambda$onMapReady$0(this.f);
                return;
            case 21:
                ((HistoryItem) this.e).lambda$getCreatureThumbnail$1((Consumer) this.f);
                return;
            case 22:
                ((AlbumFolderPickerAdapter) this.e).lambda$onBindViewHolder$1((ListViewHolder) this.f);
                return;
            case 23:
                ((BottomStoryBehavior) this.e).lambda$showSearchToolbarWithAnim$1((SearchToolbar) this.f);
                return;
            case 24:
                ((OnDemandRecommendPresenter) this.e).lambda$onRecommendDataChanged$0((List) this.f);
                return;
            case 25:
                ((PdcRecommendDelegate) this.e).lambda$loadAndBind$5((Map) this.f);
                return;
            case 26:
                ((ProcessingViewManager) this.e).lambda$copyBlurredBackgroundView$5((Runnable) this.f);
                return;
            case 27:
                ((PreviewViewHolder) this.e).lambda$hidePreviewView$0((View) this.f);
                return;
            case 28:
                ((StoryImageViewHolder) this.e).lambda$bindTag$0((MediaItem) this.f);
                return;
            default:
                RecommendationClickHandler.lambda$consumeSuggestion$0((IRecommendationItem) this.e, (Blackboard) this.f);
                return;
        }
    }
}
