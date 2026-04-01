package B7;

import com.samsung.android.gallery.app.ui.viewer2.aiedit.AwesomeIntelligenceHandler;
import com.samsung.android.gallery.app.ui.viewer2.aiedit.ColorCorrectHandler;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.AiEditDetectorDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.AliveZoomDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.AudioPocDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.DlnaDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.HdrContentsDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.JumpToTimelineDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.MediaDataDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.SelectModeDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.editor.PhotoEditorTransitionHandler;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.film.FilmStripSnapDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.input.ExitGestureDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.menu.FavoriteMenuAnimDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.menu.MenuTipPopupDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.pager.ForceSwipeDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.share.ShareSheetDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.transition.GroupPanelTransitionDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.transition.RemasterTransitionDelegate;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.flipcover.FlipCoverVideoShotModeHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.groupshot.SubItemLoader;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.imageloader.PreviewLoader;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.liveeffectvideo.LiveEffectVideoPlayerHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.motionphoto.MotionAudioController;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.LogVideoTipHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.MediaPlayerDoubleTapSeeker;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.SlideShowMediaPlayerHandler;
import com.samsung.android.gallery.app.ui.viewer2.remaster.RemasterImageLoader;
import com.samsung.android.gallery.app.ui.viewer2.remaster.ondemand.RemasterProcessHandler;
import com.samsung.android.gallery.app.ui.viewer2.unlock.UnlockHandler;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2269a;
    public final /* synthetic */ Object b;

    public /* synthetic */ d(int i2, Object obj) {
        this.f2269a = i2;
        this.b = obj;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2269a;
        Object obj = this.b;
        switch (i2) {
            case 0:
                ((FlipCoverVideoShotModeHandler) obj).initView(objArr);
                return;
            case 1:
                ((SubItemLoader) obj).lambda$addActionInvokeListener$0(objArr);
                return;
            case 2:
                ((PreviewLoader) obj).lambda$addActionInvokeListener$0(objArr);
                return;
            case 3:
                ((LiveEffectVideoPlayerHandler) obj).lambda$addActionInvokeListener$0(objArr);
                return;
            case 4:
                ((MotionAudioController) obj).lambda$addActionInvokeListener$0(objArr);
                return;
            case 5:
                ((LogVideoTipHandler) obj).onVideoStarted(objArr);
                return;
            case 6:
                ((MediaPlayerDoubleTapSeeker) obj).lambda$addActionInvokeListener$0(objArr);
                return;
            case 7:
                ((SlideShowMediaPlayerHandler) obj).onTransitionPrepare(objArr);
                return;
            case 8:
                ((RemasterImageLoader) obj).onRemastered(objArr);
                return;
            case 9:
                ((RemasterProcessHandler) obj).onBackKeyPressed(objArr);
                return;
            case 10:
                ((UnlockHandler) obj).onUnLockScreen(objArr);
                return;
            case 11:
                ((PhotoEditorTransitionHandler) obj).lambda$setEventHandlerListener$0(objArr);
                return;
            case 12:
                ((AwesomeIntelligenceHandler) obj).lambda$addActionInvokeListener$0(objArr);
                return;
            case 13:
                ((ColorCorrectHandler) obj).lambda$addActionInvokeListener$0(objArr);
                return;
            case 14:
                ((AiEditDetectorDelegate) obj).onBottomSheetStateChanged(objArr);
                return;
            case 15:
                ((AliveZoomDelegate) obj).onTransitionEnd(objArr);
                return;
            case 16:
                ((AudioPocDelegate) obj).onAudioMuteChanged(objArr);
                return;
            case 17:
                ((DlnaDelegate) obj).connectOriginalContents(objArr);
                return;
            case 18:
                ((HdrContentsDelegate) obj).lambda$setActionInvokeListener$2(objArr);
                return;
            case 19:
                ((JumpToTimelineDelegate) obj).lambda$setActionInvokeListener$0(objArr);
                return;
            case 20:
                ((MediaDataDelegate) obj).lambda$new$1(objArr);
                return;
            case 21:
                ((SelectModeDelegate) obj).lambda$setActionInvokeListener$0(objArr);
                return;
            case 22:
                ((FilmStripSnapDelegate) obj).lambda$setActionInvokeListener$0(objArr);
                return;
            case 23:
                ((ExitGestureDelegate) obj).onExitGesture(objArr);
                return;
            case 24:
                ((FavoriteMenuAnimDelegate) obj).startFavoriteVI(objArr);
                return;
            case 25:
                ((MenuTipPopupDelegate) obj).showMenuTipPopup(objArr);
                return;
            case 26:
                ((ForceSwipeDelegate) obj).lambda$setActionInvokeListener$0(objArr);
                return;
            case 27:
                ((ShareSheetDelegate) obj).onPrepareShareSheet(objArr);
                return;
            case 28:
                ((GroupPanelTransitionDelegate) obj).onReadyView(objArr);
                return;
            default:
                ((RemasterTransitionDelegate) obj).onReadyRemasterView(objArr);
                return;
        }
    }
}
