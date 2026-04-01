package K5;

import android.util.Pair;
import android.view.MenuItem;
import android.widget.ImageView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.oneui.floatingactioncontainer.FloatingToolbarLayout;
import com.samsung.android.gallery.app.controller.externals.CreateMeituPostCmd;
import com.samsung.android.gallery.app.controller.externals.PlayVideoCmd;
import com.samsung.android.gallery.app.controller.internals.AddSingleTagCmd;
import com.samsung.android.gallery.app.controller.internals.DownloadCmd;
import com.samsung.android.gallery.app.controller.internals.LongExposureCmd;
import com.samsung.android.gallery.app.controller.internals.QuickSearchRangeDatePickerCmd;
import com.samsung.android.gallery.app.controller.mxalbum.SharedAlbumCreatePopupDialogCmd;
import com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumPicturesPresenter;
import com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumsPaneFragment;
import com.samsung.android.gallery.app.ui.list.albums.pictures.filter.ScreenShotFilterListViewAdapter;
import com.samsung.android.gallery.app.ui.list.albums.pictures.setting.ScreenshotFilterCustomFragment;
import com.samsung.android.gallery.app.ui.list.albums.pictures.widget.WidgetAlbumPicturesFragment;
import com.samsung.android.gallery.app.ui.list.search.util.ActionSuggester;
import com.samsung.android.gallery.app.ui.list.sharings.SharingsFragment;
import com.samsung.android.gallery.app.ui.list.sharings.choice.SharingAlbumChoiceAdapter;
import com.samsung.android.gallery.app.ui.list.sharings.family.FamilySharedAlbumWelcomeFragment;
import com.samsung.android.gallery.app.ui.list.sharings.pictures.SharingHeaderViewDelegateV2;
import com.samsung.android.gallery.app.ui.list.stories.spotify.SpotifySlideshowFragment;
import com.samsung.android.gallery.app.ui.list.stories.videopreview.VideoPreviewHandler;
import com.samsung.android.gallery.app.ui.list.suggestions.cleanout.CleanOutPicturesFragment;
import com.samsung.android.gallery.app.ui.viewer2.details.DetailsListHandler;
import com.samsung.android.gallery.app.ui.viewer2.details.items.DetailsItem;
import com.samsung.android.gallery.app.ui.viewer2.grouppanel.list.GroupPanelVideoPreview;
import com.samsung.android.gallery.app.ui.viewer2.menu.AddPortraitEffectMenuItem;
import com.samsung.android.gallery.app.ui.viewer2.menu.CopyEffectMenu;
import com.samsung.android.gallery.app.ui.viewer2.menu.RevertToOriginalMenuItem;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.module.clip.objectcapture.ObjectCaptureGPPMSession;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.foldable.FoldStateManager;
import com.samsung.android.gallery.module.media.CreateMediaHelper;
import com.samsung.android.gallery.module.publisher.RemoteAlbumPublisher;
import com.samsung.android.gallery.module.quicksearch.QuickSearchManager;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.InstantSubscriberListener;
import com.samsung.android.gallery.support.utils.CollectionCursor;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ a(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        Object obj2 = this.e;
        switch (i2) {
            case 0:
                ((ActionSuggester) obj2).lambda$launchActionSuggesterLogging$1((Blackboard) obj);
                return;
            case 1:
                ((SpotifySlideshowFragment) obj2).lambda$initViewPager$8((Integer) obj);
                return;
            case 2:
                ((Blackboard) obj2).unsubscribe((String) ((Pair) obj).first, (InstantSubscriberListener) ((Pair) obj).second);
                return;
            case 3:
                ((SharingsFragment) obj2).lambda$updateExtendedAppBar$1((GalleryAppBarLayout) obj);
                return;
            case 4:
                ((VideoPreviewHandler.VideoPreviewProvider) obj).onComplete((PreviewViewHolder) obj2);
                return;
            case 5:
                ((DetailsListHandler) obj2).lambda$setTag$3((DetailsItem) obj);
                return;
            case 6:
                ((AlbumPicturesPresenter) obj2).lambda$turnOffMergedAlbum$22((MediaItem) obj);
                return;
            case 7:
                ((AlbumPicturesPresenter.AlbumPicturesMenuUpdater) obj2).lambda$updateOptionsMenuAction$1((MenuItem) obj);
                return;
            case 8:
                ((AlbumsPaneFragment) obj2).lambda$setNestedScroll$0((GalleryListView) obj);
                return;
            case 9:
                ((SharingAlbumChoiceAdapter) obj2).lambda$bindHeader$1((ImageView) obj);
                return;
            case 10:
                ((RemoteAlbumPublisher) obj2).lambda$exec$0((MediaItem) obj);
                return;
            case 11:
                ((CollectionCursor) obj2).add(Long.valueOf(((MediaItem) obj).getFileId()));
                return;
            case 12:
                ((CreateMeituPostCmd) obj2).lambda$showCombinationErrorToast$4((CreateMediaHelper.SupportType) obj);
                return;
            case 13:
                ((PlayVideoCmd) obj2).requestStreamingVideoUrl(((Boolean) obj).booleanValue());
                return;
            case 14:
                ((RecyclerView) obj).setAdapter((ScreenShotFilterListViewAdapter) obj2);
                return;
            case 15:
                ((FamilySharedAlbumWelcomeFragment) obj2).loadArgument((String) obj);
                return;
            case 16:
                ((ObjectCaptureGPPMSession) obj2).lambda$onTaskCompleted$1((MediaItem) obj);
                return;
            case 17:
                ((AddSingleTagCmd) obj2).lambda$getTagList$0((QueryParams) obj);
                return;
            case 18:
                ((DownloadCmd) obj2).lambda$startDownloadIfNetworkAvailable$3((Boolean) obj);
                return;
            case 19:
                ((LongExposureCmd) obj2).onFinishLongExposure((String) obj);
                return;
            case 20:
                QuickSearchRangeDatePickerCmd.lambda$onConfirmed$0((Object[]) obj2, (QuickSearchManager) obj);
                return;
            case 21:
                ((SharingHeaderViewDelegateV2) obj2).lambda$startSlideShow$0((MediaItem) obj);
                return;
            case 22:
                ((CleanOutPicturesFragment) obj2).lambda$adjustEmptyViewMargin$2((FloatingToolbarLayout) obj);
                return;
            case 23:
                ((ScreenshotFilterCustomFragment) obj2).lambda$updateMainLayoutPaddingHorizontal$1((FragmentActivity) obj);
                return;
            case 24:
                ((GroupPanelVideoPreview) obj2).lambda$makePreviewCandidates$1((PreviewViewHolder) obj);
                return;
            case 25:
                ((SharedAlbumCreatePopupDialogCmd) obj2).launchProperDialog(((Boolean) obj).booleanValue());
                return;
            case 26:
                ((WidgetAlbumPicturesFragment) obj2).lambda$onCreate$0((FoldStateManager) obj);
                return;
            case 27:
                ((AddPortraitEffectMenuItem) obj2).lambda$onMenuSelectInternal$1(obj);
                return;
            case 28:
                ((CopyEffectMenu) obj2).updateMenu(obj);
                return;
            default:
                ((RevertToOriginalMenuItem) obj2).updateMenuDimEnabled(obj);
                return;
        }
    }
}
