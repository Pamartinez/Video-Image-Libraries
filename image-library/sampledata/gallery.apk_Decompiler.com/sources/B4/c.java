package B4;

import android.content.Context;
import android.media.session.MediaSession;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.creature.MergeCreatureChoiceCmd;
import com.samsung.android.gallery.app.controller.sharing.request.RequestDeleteFamilySpace;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.app.ui.list.albums.AlbumsFooterView;
import com.samsung.android.gallery.app.ui.list.albums.AlbumsPresenter;
import com.samsung.android.gallery.app.ui.list.search.analysis.SearchAnalysisTipView;
import com.samsung.android.gallery.app.ui.list.search.toolbar.SearchFiltersDelegate;
import com.samsung.android.gallery.app.ui.list.sharings.family.FamilySharedAlbumWelcomeFragment;
import com.samsung.android.gallery.app.ui.list.sharings.pictures.setting.SharingPicturesSettingFragment;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.BottomDecoViewDelegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.StoryLiveEffectDelegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.theme.StoryThemeView;
import com.samsung.android.gallery.app.ui.list.stories.recap.SharedTransitionHandler;
import com.samsung.android.gallery.app.ui.viewer2.aiedit.AwesomeIntelligenceHandler;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.menu.ViewerMenuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.DlnaUi2;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.capture.CaptureHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.motionphoto.MotionPhotoMediaPlayer;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.VideoController;
import com.samsung.android.gallery.app.ui.viewer2.slideshow.delegate.SlideDelegate;
import com.samsung.android.gallery.module.bgm.BgmUriService;
import com.samsung.android.gallery.module.media.GalleryMediaSession;
import com.samsung.android.gallery.plugins.motionphoto.MotionPhotoViewPlayer;
import com.samsung.android.gallery.plugins.motionphoto.VideoCtrlView;
import com.samsung.android.gallery.settings.ui.LabsDevManageFragment;
import com.samsung.android.gallery.widget.story.transitory.AccessibilityState;
import com.samsung.android.gallery.widget.videoview.mediaplayer.InstantSlowMoPlayDelegate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ boolean e;
    public final /* synthetic */ Object f;

    public /* synthetic */ c(Object obj, boolean z, int i2) {
        this.d = i2;
        this.f = obj;
        this.e = z;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((AlbumsFooterView) this.f).lambda$new$0(this.e);
                return;
            case 1:
                ((AlbumsPresenter) this.f).lambda$handleEvent$5(this.e);
                return;
            case 2:
                ((MotionPhotoViewPlayer) this.f).lambda$setAudioMute$3(this.e);
                return;
            case 3:
                ((VideoCtrlView) this.f).lambda$setPlayButton$3(this.e);
                return;
            case 4:
                ((BaseCommand) this.f).lambda$showProgress$1(this.e);
                return;
            case 5:
                ((MotionPhotoMediaPlayer) this.f).lambda$setPhotoViewVisibility$6(this.e);
                return;
            case 6:
                ((BgmUriService) this.f).lambda$onDownloadedAll$5(this.e);
                return;
            case 7:
                GalleryMediaSession.lambda$createMediaSession$0(this.e, (MediaSession.Callback) this.f);
                return;
            case 8:
                ((LabsDevManageFragment) this.f).lambda$loadYearCacheInfo$67(this.e);
                return;
            case 9:
                ((SharedTransitionHandler) this.f).lambda$bindView$0(this.e);
                return;
            case 10:
                ((VideoController) this.f).lambda$onVideoStarted$1(this.e);
                return;
            case 11:
                ((MergeCreatureChoiceCmd) this.f).lambda$unmerge$4(this.e);
                return;
            case 12:
                ((SearchFiltersDelegate) this.f).lambda$updateView$1(this.e);
                return;
            case 13:
                ((FamilySharedAlbumWelcomeFragment) this.f).lambda$onGetStartedButtonClicked$2(this.e);
                return;
            case 14:
                ((SharingPicturesSettingFragment) this.f).lambda$onSharingWebLinkPrefChanged$16(this.e);
                return;
            case 15:
                ((RequestDeleteFamilySpace) this.f).lambda$requestDeleteFamilySpace$0(this.e);
                return;
            case 16:
                SlideDelegate.lambda$setAnnounceVoiceAssistant$3(this.e, (Context) this.f);
                return;
            case 17:
                ((NestedScrollView) this.f).lambda$seslSetFadingEdgeEnabled$1(this.e);
                return;
            case 18:
                ((AccessibilityState) this.f).lambda$onAccessibilityStateChanged$2(this.e);
                return;
            case 19:
                ((MvpBaseFragment) this.f).lambda$keepScreenOn$4(this.e);
                return;
            case 20:
                ((RecyclerView) this.f).lambda$seslSetFadingEdgeEnabled$1(this.e);
                return;
            case 21:
                ((AwesomeIntelligenceHandler) this.f).lambda$execute$2(this.e);
                return;
            case 22:
                ((SearchAnalysisTipView) this.f).lambda$checkAndUpdateAnalysisTip$0(this.e);
                return;
            case 23:
                ((InstantSlowMoPlayDelegate) this.f).lambda$setExecutionSection$0(this.e);
                return;
            case 24:
                ((BottomDecoViewDelegate) this.f).lambda$setVisible$9(this.e);
                return;
            case 25:
                ((StoryLiveEffectDelegate) this.f).lambda$setVisible$1(this.e);
                return;
            case 26:
                ((ViewerMenuDelegate) this.f).lambda$showPppLoadingToolbar$15(this.e);
                return;
            case 27:
                ((StoryThemeView) this.f).lambda$setVisible$1(this.e);
                return;
            case 28:
                ((DlnaUi2) this.f).lambda$updateDlnaButton$0(this.e);
                return;
            default:
                ((CaptureHandler) this.f).lambda$setVideoCaptureEnabled$12(this.e);
                return;
        }
    }

    public /* synthetic */ c(boolean z, Object obj, int i2) {
        this.d = i2;
        this.e = z;
        this.f = obj;
    }
}
