package H3;

import androidx.media3.exoplayer.analytics.DefaultAnalyticsCollector;
import com.samsung.android.gallery.app.controller.album.MoveFilesOnPrivateCmd;
import com.samsung.android.gallery.app.controller.album.MoveToGroupCmd;
import com.samsung.android.gallery.app.controller.creature.abstraction.CreatureHideCmd;
import com.samsung.android.gallery.app.ui.list.albums.mx.header.MxAlbumsHeaderPresenter;
import com.samsung.android.gallery.app.ui.list.albums.pictures.IAlbumPicturesView;
import com.samsung.android.gallery.app.ui.list.search.suggestionview.HierarchicalView;
import com.samsung.android.gallery.app.ui.list.search.suggestionview.HierarchicalViewV2;
import com.samsung.android.gallery.app.ui.list.search.suggestionview.RelationshipView;
import com.samsung.android.gallery.app.ui.list.search.suggestionview.TagMeView;
import com.samsung.android.gallery.app.ui.list.sharings.SharingsViewAdapter;
import com.samsung.android.gallery.app.ui.list.sharings.choice.SharingAlbumChoiceAdapter;
import com.samsung.android.gallery.app.ui.list.stories.recap.delegate.RecapLastPageDelegate;
import com.samsung.android.gallery.app.ui.list.stories.slideshow.SlideshowV2Presenter;
import com.samsung.android.gallery.app.ui.list.stories.spotify.SpotifySlideshowFragment;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.InstantSlowMoTipHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.LogVideoTipHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.VideoSeekController;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.VideoSpeedControlHandler;
import com.samsung.android.gallery.app.ui.viewer2.details.DetailsHandler;
import com.samsung.android.gallery.app.ui.viewer2.details.DetailsListHandler;
import com.samsung.android.gallery.app.ui.viewer2.details.DetailsLoadHandler;
import com.samsung.android.gallery.app.ui.viewer2.details.DetailsSlideHandler;
import com.samsung.android.gallery.app.ui.viewer2.details.DetailsStateHandler;
import com.samsung.android.gallery.app.ui.viewer2.details.EditDetailsHandler;
import com.samsung.android.gallery.module.bgm.updater.ProviderUpdater;
import com.samsung.android.gallery.module.c2pa.C2paDummyImp;
import com.samsung.android.gallery.widget.listview.noitem.NoItemVI;
import com.samsung.android.gallery.widget.listview.pinch.v3.DataItem;
import com.samsung.android.gallery.widget.listview.scroller.Scroller;
import com.samsung.android.visual.ai.sdkcommon.c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class l implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ l(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void run() {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                ((MoveFilesOnPrivateCmd) obj).lambda$onConfirmed$0();
                return;
            case 1:
                ((MoveToGroupCmd) obj).moveToGroup();
                return;
            case 2:
                ((RecapLastPageDelegate) obj).lambda$handleEvent$5();
                return;
            case 3:
                ((InstantSlowMoTipHandler) obj).lambda$showInstantSlowMoTip$2();
                return;
            case 4:
                ((LogVideoTipHandler) obj).lambda$showLogVideoTip$1();
                return;
            case 5:
                ((VideoSeekController) obj).lambda$onPlayTimeUpdated$2();
                return;
            case 6:
                ((VideoSpeedControlHandler) obj).lambda$new$2();
                return;
            case 7:
                ((NoItemVI) obj).showInitAnimation();
                return;
            case 8:
                ((HierarchicalView) obj).updateEmptyViewPadding();
                return;
            case 9:
                ((HierarchicalViewV2) obj).lambda$updateEmptyViewPadding$0();
                return;
            case 10:
                ((RelationshipView) obj).updateEmptyViewPadding();
                return;
            case 11:
                ((TagMeView) obj).updateEmptyViewPadding();
                return;
            case 12:
                ((DefaultAnalyticsCollector) obj).releaseInternal();
                return;
            case 13:
                ((CreatureHideCmd) obj).lambda$hide$2();
                return;
            case 14:
                ((SlideshowV2Presenter) obj).onDataChangedOnUi();
                return;
            case 15:
                ((ProviderUpdater) obj).lambda$new$0();
                return;
            case 16:
                ((DataItem) obj).lambda$updateAlphaToPreventFlickering$0();
                return;
            case 17:
                ((MxAlbumsHeaderPresenter) obj).onDataChangedOnUi();
                return;
            case 18:
                ((SpotifySlideshowFragment) obj).lambda$onEnterTransitionEndV2$1();
                return;
            case 19:
                C2paDummyImp.lambda$create$0((c) obj);
                return;
            case 20:
                ((Scroller) obj).lambda$new$0();
                return;
            case 21:
                ((SharingsViewAdapter) obj).notifyDataSetChanged();
                return;
            case 22:
                ((DetailsHandler) obj).collapseDetails();
                return;
            case 23:
                ((DetailsListHandler) obj).lambda$addActionInvokeListener$1();
                return;
            case 24:
                ((DetailsLoadHandler) obj).lambda$registerSubscriber$4();
                return;
            case 25:
                ((DetailsSlideHandler) obj).lambda$updateLayout$18();
                return;
            case 26:
                ((DetailsStateHandler) obj).updateState();
                return;
            case 27:
                ((EditDetailsHandler) obj).onSaveDone();
                return;
            case 28:
                ((IAlbumPicturesView) obj).invalidateOptionsMenu();
                return;
            default:
                ((SharingAlbumChoiceAdapter) obj).notifyDataSetChanged();
                return;
        }
    }
}
