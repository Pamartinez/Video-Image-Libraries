package V3;

import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.ProcessLifecycleOwner;
import com.google.android.material.carousel.CarouselLayoutManager;
import com.samsung.android.app.sdk.deepsky.textextraction.Recognizer;
import com.samsung.android.gallery.app.controller.trash.OneTrashMigrationCmd;
import com.samsung.android.gallery.app.controller.viewer.CopyToClipboardCmd;
import com.samsung.android.gallery.app.controller.viewer.DeleteSingleCmd;
import com.samsung.android.gallery.app.controller.viewer.StartCapturedFileViewCmd;
import com.samsung.android.gallery.app.provider.LocalProvider;
import com.samsung.android.gallery.app.remote.SlideshowServiceOnRemoteV2;
import com.samsung.android.gallery.app.ui.list.mapclustering.ClusteringMapFragment;
import com.samsung.android.gallery.app.ui.list.mapclustering.ClusteringMapPresenterV2;
import com.samsung.android.gallery.app.ui.list.mtp.pictures.MtpPicturesPresenter;
import com.samsung.android.gallery.app.ui.list.stories.category.category.StoriesCatBaseViewHolder;
import com.samsung.android.gallery.app.ui.list.stories.category.category.categoryitem.StoriesCatTransitoryItemOnDemandViewHolder;
import com.samsung.android.gallery.app.ui.list.stories.category.helper.StoryLauncher;
import com.samsung.android.gallery.app.ui.list.stories.category.ondemand.OnDemandFloatingFragment;
import com.samsung.android.gallery.app.ui.list.stories.category.ondemand.OnDemandRecommendPresenter;
import com.samsung.android.gallery.app.ui.list.stories.header.PinAnimHandler;
import com.samsung.android.gallery.app.ui.map.MapSnapshotPublisher;
import com.samsung.android.gallery.app.ui.viewer2.selection.SelectionPageChangeDelegate;
import com.samsung.android.gallery.module.debugger.LocalProviderDebugHelper;
import com.samsung.android.gallery.module.remote.dlna.DlnaHelper;
import com.samsung.android.gallery.module.search.engine.PersonalDataCore;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.support.library.v9.media.Sec100MediaPlayerCompat;
import com.samsung.android.gallery.widget.fastoption2.FastOptionView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.search.searchbar.selectedfilter.SearchSelectedFiltersEditableViewHolder;
import com.samsung.android.gallery.widget.simpleslideshow.SimpleSlideShowViewPager;
import com.samsung.android.gallery.widget.smartalbum.SmartAlbumHolder;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ b(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void run() {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                ((OneTrashMigrationCmd) obj).lambda$hideDialog$1();
                return;
            case 1:
                ((MapSnapshotPublisher) obj).lambda$removeMapFragment$2();
                return;
            case 2:
                ((FastOptionView) obj).updatePadding();
                return;
            case 3:
                ((SelectionPageChangeDelegate) obj).onPageIdle();
                return;
            case 4:
                ((CopyToClipboardCmd) obj).copyToClipboard();
                return;
            case 5:
                ((DeleteSingleCmd) obj).operateDeleteInternal();
                return;
            case 6:
                ((StartCapturedFileViewCmd) obj).loadMediaItem();
                return;
            case 7:
                ((ClusteringMapFragment) obj).onMarkersTransitionEnd();
                return;
            case 8:
                ((ClusteringMapPresenterV2) obj).lambda$updateClusterItems$0();
                return;
            case 9:
                ((StoriesCatBaseViewHolder) obj).onDataChangedOnUi();
                return;
            case 10:
                ((PersonalDataCore) obj).lambda$prepareSearchEngine$15();
                return;
            case 11:
                ((LocalProvider) obj).initOnBg();
                return;
            case 12:
                ((ListViewHolder) obj).bindImage(ThumbnailLoader.getInstance().getMtpAlbumImage());
                return;
            case 13:
                ((StoriesCatTransitoryItemOnDemandViewHolder) obj).onSuggestionChanged();
                return;
            case 14:
                ((Sec100MediaPlayerCompat) obj).onTimeTickUpdated();
                return;
            case 15:
                ((SearchSelectedFiltersEditableViewHolder) obj).itemView.requestFocus();
                return;
            case 16:
                ((MtpPicturesPresenter) obj).restartGallery();
                return;
            case 17:
                ((SimpleSlideShowViewPager) obj).start();
                return;
            case 18:
                ((DlnaHelper) obj).stopDlnaService();
                return;
            case 19:
                ((SlideshowServiceOnRemoteV2) obj).onDataChangedOnUi();
                return;
            case 20:
                ((StoryLauncher) obj).lambda$launchStoryHighlight$0();
                return;
            case 21:
                ((SmartAlbumHolder) obj).lambda$inflateSmartAlbum$0();
                return;
            case 22:
                ((CarouselLayoutManager) obj).requestLayout();
                return;
            case 23:
                ((OnDemandFloatingFragment) obj).lambda$hideResultView$2();
                return;
            case 24:
                ((OnDemandRecommendPresenter) obj).onSuggestionChanged();
                return;
            case 25:
                LocalProviderDebugHelper.writeViewHistory((ArrayList) obj);
                return;
            case 26:
                ((NestedScrollView) obj).invalidate();
                return;
            case 27:
                ProcessLifecycleOwner.delayedPauseRunnable$lambda$0((ProcessLifecycleOwner) obj);
                return;
            case 28:
                ((PinAnimHandler) obj).triggerPinShowAnimation();
                return;
            default:
                ((Recognizer) obj).release();
                return;
        }
    }
}
