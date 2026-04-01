package E7;

import android.os.Handler;
import android.view.DisplayCutout;
import android.view.MenuItem;
import android.view.View;
import androidx.fragment.app.Fragment;
import com.samsung.android.gallery.app.service.HighlightEncodeService;
import com.samsung.android.gallery.app.service.MediaCaptureService;
import com.samsung.android.gallery.app.service.VideoConversionService;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.app.ui.abstraction.delegate.AbsDelegate;
import com.samsung.android.gallery.app.ui.abstraction.delegate.DelegateComposite;
import com.samsung.android.gallery.app.ui.container.tablet.ListContainerFragment;
import com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerTabViewAdapter;
import com.samsung.android.gallery.app.ui.list.albums.mx.manage.MxManageAlbumsPresenter;
import com.samsung.android.gallery.app.ui.list.picker.search.suggestion.SuggestionContainerPickerFragment;
import com.samsung.android.gallery.app.ui.list.search.creaturecoverchoice.CreatureCoverChoiceFragment;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.SearchClusterResultPresenter;
import com.samsung.android.gallery.app.ui.list.sharings.pictures.setting.SharingPicturesSettingFragment;
import com.samsung.android.gallery.app.ui.list.timeline.quicksearch.QuickSearchView;
import com.samsung.android.gallery.app.ui.viewer2.container.VuContainerPresenter;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.liveeffectvideo.LiveEffectVideoPlayerHandler;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.search.root.FilterResultsEntry;
import com.samsung.android.gallery.module.service.support.StoryServiceHelper;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.gallery.widget.videoview.mediaplayer.IMediaPlayerView;
import com.samsung.android.sdk.mobileservice.social.share.result.SpaceResult;
import com.samsung.android.sesl.visualeffect.lighteffects.processinglight.ProcessingLightControl;
import java.util.ArrayList;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ boolean e;
    public final /* synthetic */ Object f;

    public /* synthetic */ c(Object obj, boolean z, int i2) {
        this.d = i2;
        this.f = obj;
        this.e = z;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((LiveEffectVideoPlayerHandler) this.f).lambda$updateLoopEnabled$1(this.e, (IMediaPlayerView) obj);
                return;
            case 1:
                ((MxManageAlbumsPresenter) this.f).lambda$onAllItemsSelected$0(this.e, (MediaItem) obj);
                return;
            case 2:
                ((SharingPicturesSettingFragment) this.f).lambda$onSharingWebLinkPrefChanged$15(this.e, (SpaceResult) obj);
                return;
            case 3:
                ((BiConsumer) obj).accept(((MediaItem) this.f).getSubCategory(), Boolean.valueOf(this.e));
                return;
            case 4:
                ((QuickSearchView) this.f).lambda$rotateExpandIcon$6(this.e, (View) obj);
                return;
            case 5:
                ((GalleryToolbar) obj).applyDisplayCutOutPadding((DisplayCutout) this.f, this.e);
                return;
            case 6:
                ((FilterResultsEntry.Builder) this.f).lambda$checkCreatureIdValidity$4(this.e, (String) obj);
                return;
            case 7:
                ((HighlightEncodeService) this.f).lambda$onCompleted$6(this.e, (Blackboard) obj);
                return;
            case 8:
                ((MediaCaptureService) this.f).lambda$onCompleted$5(this.e, (Handler) obj);
                return;
            case 9:
                ((VideoConversionService) this.f).lambda$onCompleted$0(this.e, (Handler) obj);
                return;
            case 10:
                ((SuggestionContainerPickerFragment) this.f).lambda$setBackgroundAndSystemUiBarColor$1(this.e, (GalleryToolbar) obj);
                return;
            case 11:
                ProcessingLightControl.buildAnimation$lambda$7((ProcessingLightControl) this.f, this.e, (View) obj);
                return;
            case 12:
                ((MvpBaseFragment) this.f).lambda$showOrHideChildFragments$3(this.e, (Fragment) obj);
                return;
            case 13:
                DelegateComposite.lambda$onMultiWindowModeChangedInternal$6(this.e, (ArrayList) this.f, (AbsDelegate) obj);
                return;
            case 14:
                ((StoryServiceHelper) this.f).lambda$complete$2(this.e, (Consumer) obj);
                return;
            case 15:
                ((VuContainerPresenter) this.f).lambda$onToggleOsd$7(this.e, (View) obj);
                return;
            case 16:
                ((ListContainerFragment) this.f).lambda$updateToolbarNavigationRipple$6(this.e, (GalleryToolbar) obj);
                return;
            case 17:
                ((DrawerTabViewAdapter) this.f).updateBadgeOnTab((String) obj, this.e);
                return;
            case 18:
                ((CreatureCoverChoiceFragment) this.f).lambda$setDoneButtonEnabled$0(this.e, (MenuItem) obj);
                return;
            default:
                ((SearchClusterResultPresenter) this.f).lambda$changeViewBy$0(this.e, (String) obj);
                return;
        }
    }

    public /* synthetic */ c(ArrayList arrayList, boolean z) {
        this.d = 13;
        this.e = z;
        this.f = arrayList;
    }
}
