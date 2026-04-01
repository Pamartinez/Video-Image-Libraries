package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video;

import Ab.a;
import H7.C0400c;
import H7.C0401d;
import H7.C0402e;
import android.content.res.Configuration;
import android.view.View;
import android.view.ViewStub;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.module.abstraction.VideoPropData;
import com.samsung.android.gallery.module.account.IntelligenceServiceManager;
import com.samsung.android.gallery.module.account.SamsungAccountManager;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.animations.viewer.AudioEraserAnimation;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AudioEraserController extends ViewerObject implements FragmentLifeCycle {
    private AudioEraserAnimation mAnimation;
    private boolean mAudioEraserDimed = false;
    ViewStub mAudioEraserIconViewStub;
    private boolean mAudioEraserInit = false;
    private boolean mAudioEraserViewEnabled = false;
    protected boolean mHasAudio = true;
    private Predicate<Object> mIconDimCondition = new C0402e(this, 1);
    private boolean mIsAudioEraserViewable = false;
    private Predicate<Object> mPlayingCondition = new C0402e(this, 0);
    private long mVideoDuration = 0;
    View mView;

    private AudioEraserAnimation getAnimation() {
        if (this.mAnimation == null) {
            this.mAnimation = new AudioEraserAnimation(this.mView);
        }
        return this.mAnimation;
    }

    /* access modifiers changed from: private */
    public void handleInstantSlowMoPlay(Object... objArr) {
        Boolean bool = objArr[0];
        boolean booleanValue = bool.booleanValue();
        Log.e((CharSequence) "AudioEraserController", "handleInstantSlowMoPlay ", bool);
        if (booleanValue) {
            this.mAudioEraserDimed = true;
            updateIconAlpha(true);
            return;
        }
        this.mAudioEraserDimed = false;
        updateIconAlpha(false);
    }

    private void hideAudioIcon() {
        this.mThread.runOnUiThread(new C0400c(this, 2));
        updateTouchArea();
    }

    private void hideEraserButton() {
        this.mThread.runOnUiThread(new C0400c(this, 4));
    }

    private void initAudioEraserButton() {
        View view;
        View view2 = this.mView;
        if (view2 != null) {
            view = view2.findViewById(R.id.effect_view);
        } else {
            view = null;
        }
        if (view == null) {
            Log.e("AudioEraserController", "initAudioEraserButton failed: view is null");
        } else {
            view.setOnClickListener(new a(26, this));
        }
    }

    private void initValues() {
        this.mHasAudio = true;
        this.mVideoDuration = 0;
        this.mIsAudioEraserViewable = false;
        this.mAudioEraserInit = false;
        this.mAudioEraserDimed = false;
        this.mModel.getContainerModel().setAudioEraserOff(true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$2(Object[] objArr) {
        this.mView = objArr[0];
        this.mAudioEraserIconViewStub = objArr[1];
        initAudioEraserButton();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$3(Object[] objArr) {
        hideAudioIcon();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleBlackboardEvent$4() {
        onToggleAudioEraser(new Object[0]);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$hideAudioIcon$8() {
        setAudioEraserViewEnabled(false);
        setAudioEraserIcon(false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$hideEraserButton$7() {
        AudioEraserAnimation audioEraserAnimation = this.mAnimation;
        if (audioEraserAnimation == null || !audioEraserAnimation.isAnimating()) {
            ViewUtils.setVisibility(this.mView, 8);
        } else {
            this.mAnimation.hideButton(true);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initAudioEraserButton$12() {
        getAnimation().startErasedAnim(this.mPlayingCondition);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initAudioEraserButton$13() {
        getAnimation().startAnalyzingAnim();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initAudioEraserButton$14(View view) {
        if (this.mModel.isUnsupportedVideo()) {
            Utils.showToast(this.mModel.getContext(), (int) R.string.cant_play_video_file_type_not_supported);
            return;
        }
        boolean z = this.mAudioEraserViewEnabled;
        if (!z || this.mAudioEraserDimed) {
            Log.i("AudioEraserController", "audio eraser icon is disabled ", Boolean.valueOf(z), Boolean.valueOf(this.mAudioEraserDimed));
            return;
        }
        Log.d("AudioEraserController", "Click Eraser button [" + this.mModel.getContainerModel().isAudioEraserOff() + "]");
        this.mModel.getBlackboard().erase("data://viewer_executed_intelligence_service");
        boolean z3 = true;
        if (this.mModel.getContainerModel().isAudioEraserOff()) {
            Features features = Features.IS_RDU_MODE;
            if (!Features.isEnabled(features) && IntelligenceServiceManager.getInstance().needToShownSignIn(this.mModel.getContext())) {
                this.mModel.getBlackboard().publish("data://viewer_executed_intelligence_service", "audio_eraser_ai");
                IntelligenceServiceManager.getInstance().startIntelligenceService(this.mModel.getActivity());
            } else if (Features.isEnabled(features) || SamsungAccountManager.isSamsungAiSupportAccount(this.mModel.getContext())) {
                if (this.mAudioEraserInit) {
                    onToggleAudioEraser(new Object[0]);
                    this.mThread.runOnUiThread(new C0400c(this, 8));
                } else {
                    this.mThread.runOnUiThread(new C0400c(this, 9));
                    requestAudioEraserInit();
                }
                EventContext eventContext = this.mModel.getContainerModel().getEventContext();
                if (eventContext != null) {
                    AnalyticsLogger instance = AnalyticsLogger.getInstance();
                    String screenId = eventContext.getScreenId();
                    String analyticsEventId = AnalyticsEventId.EVENT_DETAIL_VIEW_VIDEO_SOUND_ERASER_TOGGLE.toString();
                    if (this.mAudioEraserInit && this.mModel.getContainerModel().isAudioEraserOff()) {
                        z3 = false;
                    }
                    instance.postLog(screenId, analyticsEventId, AnalyticsDetail.getOnOff(z3));
                }
            } else {
                IntelligenceServiceManager.getInstance().showRestrictedAccountToast(this.mModel.getContext());
            }
        } else {
            if (getAnimation().isErasingAnimating()) {
                getAnimation().startEraseOffAnim();
            }
            onToggleAudioEraser(new Object[0]);
            EventContext eventContext2 = this.mModel.getContainerModel().getEventContext();
            if (eventContext2 != null) {
                AnalyticsLogger.getInstance().postLog(eventContext2.getScreenId(), AnalyticsEventId.EVENT_DETAIL_VIEW_VIDEO_SOUND_ERASER_TOGGLE.toString(), AnalyticsDetail.getOnOff(!this.mModel.getContainerModel().isAudioEraserOff()));
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$new$0(Object obj) {
        return this.mModel.isPlaying();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$new$1(Object obj) {
        return this.mAudioEraserDimed;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onAudioEraserInitCompleted$11() {
        getAnimation().startErasedAnim(this.mPlayingCondition);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onResolutionChanged$5() {
        this.mView = ViewUtils.reinflateViewStub(this.mAudioEraserIconViewStub, this.mView);
        AudioEraserAnimation audioEraserAnimation = this.mAnimation;
        if (audioEraserAnimation != null) {
            audioEraserAnimation.hideButton(false);
            this.mAnimation = null;
        }
        initAudioEraserButton();
        setAudioEraserViewable();
        setAudioEraserIcon(false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onVideoStarted$9() {
        if (ViewUtils.isVisible(this.mView)) {
            getAnimation().resumeErasingAnimation();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onVideoStopped$10() {
        if (ViewUtils.isVisible(this.mView)) {
            getAnimation().pauseErasingAnimation();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateHasAudio$6() {
        setAudioEraserIcon(false);
    }

    private boolean notSupportedType(MediaItem mediaItem) {
        if (SdkConfig.atLeast(SdkConfig.SEM.B_MR5)) {
            return mediaItem.is360Video();
        }
        if (mediaItem.isLogVideo() || mediaItem.is360Video()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void onAudioEraserInitCompleted(Object... objArr) {
        Log.i("AudioEraserController", "onAudioEraserInitCompleted");
        if (this.mAudioEraserInit) {
            Log.w("AudioEraserController", "onAudioEraserInitCompleted: already init completed");
            return;
        }
        this.mAudioEraserInit = true;
        this.mModel.getContainerModel().setAudioEraserOff(false);
        this.mThread.runOnUiThread(new C0400c(this, 5));
    }

    /* access modifiers changed from: private */
    public void onAudioIconDisabled(Object... objArr) {
        setAudioEraserViewEnabled(false);
        this.mModel.getContainerModel().setAudioEraserVisible(false);
        hideEraserButton();
    }

    /* access modifiers changed from: private */
    public void onAudioMuteChanged(Object... objArr) {
        setAudioEraserIcon(true);
    }

    /* access modifiers changed from: private */
    public void onToggleAudioEraser(Object... objArr) {
        if (isToggleAudioEraserEnabled()) {
            if (this.mModel.getContainerModel().isAudioMute()) {
                this.mModel.getContainerModel().setAudioEraserOff(true);
            } else {
                this.mModel.getContainerModel().setAudioEraserOff(true ^ this.mModel.getContainerModel().isAudioEraserOff());
            }
            this.mActionInvoker.invoke(ViewerAction.AUDIO_ERASER_CHANGED, new Object[0]);
            return;
        }
        Log.w("AudioEraserController", "onToggleAudioEraser: audioEraserView is disabled");
    }

    /* access modifiers changed from: private */
    public void onVideoPrepared(Object... objArr) {
        this.mVideoDuration = (long) VideoPropData.getVideoDuration(this.mModel.getMediaItem());
    }

    /* access modifiers changed from: private */
    public void onVideoStarted(Object... objArr) {
        if (!this.mModel.getContainerModel().isAudioEraserOff()) {
            this.mThread.runOnUiThread(new C0400c(this, 7));
        }
    }

    /* access modifiers changed from: private */
    public void onVideoStopped(Object... objArr) {
        if (!this.mModel.getContainerModel().isAudioEraserOff()) {
            this.mThread.runOnUiThread(new C0400c(this, 3));
        }
    }

    private void requestAudioEraserInit() {
        Log.d("AudioEraserController", "requestAudioEraserInit");
        this.mActionInvoker.invoke(ViewerAction.AUDIO_ERASER_ON_REQUEST, new Object[0]);
    }

    private void setAudioEraserIcon(boolean z) {
        boolean z3;
        View view = this.mView;
        if (view != null) {
            int visibility = view.getVisibility();
            if (this.mHasAudio && this.mIsAudioEraserViewable && this.mAudioEraserViewEnabled && supportAudioController(this.mModel.getMediaItem())) {
                if (!this.mModel.getContainerModel().isAudioMute()) {
                    AudioEraserAnimation animation = getAnimation();
                    if (!this.mAudioEraserInit || visibility != 0 || this.mModel.getContainerModel().isAudioEraserOff()) {
                        z3 = false;
                    } else {
                        z3 = true;
                    }
                    animation.showButton(z3, this.mIconDimCondition);
                    this.mModel.getContainerModel().setAudioEraserVisible(true);
                    setAudioEraserViewEnabled(true);
                    if (visibility != this.mView.getVisibility()) {
                        this.mActionInvoker.invoke(ViewerAction.AUDIO_ERASER_VIEW_VISIBILITY_CHANGED, new Object[0]);
                    }
                    if (!this.mModel.getContainerModel().isAudioEraserOff()) {
                        getAnimation().startErasedAnim(this.mPlayingCondition);
                        return;
                    }
                    return;
                } else if (z) {
                    onToggleAudioEraser(new Object[0]);
                }
            }
            this.mModel.getContainerModel().setAudioEraserVisible(false);
            hideEraserButton();
            if (visibility != this.mView.getVisibility()) {
                this.mActionInvoker.invoke(ViewerAction.AUDIO_ERASER_VIEW_VISIBILITY_CHANGED, new Object[0]);
            }
        }
    }

    private void setAudioEraserViewEnabled(boolean z) {
        this.mAudioEraserViewEnabled = z;
    }

    private void setAudioEraserViewable() {
        long j2;
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (mediaItem != null) {
            boolean z = false;
            if (!notSupportedType(mediaItem)) {
                if (mediaItem.getFileDuration() == 0) {
                    j2 = this.mVideoDuration;
                } else {
                    j2 = (long) mediaItem.getFileDuration();
                }
                if (j2 >= 2000 && j2 <= 5400000) {
                    z = true;
                }
                this.mIsAudioEraserViewable = z;
                return;
            }
            this.mIsAudioEraserViewable = false;
        }
    }

    private boolean supportAudioController(MediaItem mediaItem) {
        boolean z;
        if (mediaItem == null) {
            return false;
        }
        if (!MediaItemUtil.supportPreviewPlay(mediaItem) || VideoPropData.of(mediaItem).longExposure) {
            z = false;
        } else {
            z = true;
        }
        if (!mediaItem.isMotionPhoto()) {
            return z;
        }
        if (!z || !this.mModel.getMotionPlayViewer().isOriginVideo) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void updateHasAudio(Object... objArr) {
        this.mHasAudio = objArr[0].booleanValue();
        setAudioEraserViewable();
        setAudioEraserViewEnabled(true);
        if (objArr[1].booleanValue()) {
            this.mThread.runOnUiThread(new C0400c(this, 6));
            updateTouchArea();
        }
    }

    private void updateIconAlpha(boolean z) {
        float f;
        if (ViewUtils.isVisible(this.mView)) {
            View view = this.mView;
            if (z) {
                f = 0.4f;
            } else {
                f = 1.0f;
            }
            view.setAlpha(f);
        }
    }

    /* access modifiers changed from: private */
    public void updateTouchArea() {
        ViewUtils.setTouchAreaComposite(this.mView, R.dimen.decor_button_touch_area);
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.AUDIO_ERASER_ICON_VIEW, new C0401d(this, 5));
        this.mActionInvoker.add(ViewerAction.VIDEO_PREPARED, new C0401d(this, 8));
        this.mActionInvoker.add(ViewerAction.VIDEO_STARTED, new C0401d(this, 9));
        this.mActionInvoker.add(ViewerAction.VIDEO_STOPPED, new C0401d(this, 10));
        this.mActionInvoker.add(ViewerAction.HIDE_AUDIO_ICON, new C0401d(this, 11));
        this.mActionInvoker.add(ViewerAction.VIDEO_HAS_AUDIO, new C0401d(this, 0));
        this.mActionInvoker.add(ViewerAction.AUDIO_MUTE_CHANGED, new C0401d(this, 1));
        this.mActionInvoker.add(ViewerAction.AUDIO_ERASER_CHANGED, new C0401d(this, 2));
        this.mActionInvoker.add(ViewerAction.TOGGLE_AUDIO_ERASER, new C0401d(this, 3));
        this.mActionInvoker.add(ViewerAction.AUDIO_ERASER_INIT_COMPLETED, new C0401d(this, 4));
        this.mActionInvoker.add(ViewerAction.DISABLE_AUDIO_ICON, new C0401d(this, 6));
        this.mActionInvoker.add(ViewerAction.INSTANT_SLOW_MO_PLAY, new C0401d(this, 7));
    }

    public boolean handleBlackboardEvent(EventMessage eventMessage) {
        if (eventMessage.what != 3059) {
            return false;
        }
        Log.i("AudioEraserController", "EVENT_INTELLIGENCE_ACCOUNT_SIGN_IN_SUCCEED : " + eventMessage.obj);
        Object obj = eventMessage.obj;
        if (obj == null || !obj.equals("audio_eraser_ai")) {
            return false;
        }
        ThreadUtil.postOnUiThreadDelayed(new C0400c(this, 10), 200);
        return false;
    }

    public void initialize() {
        if (Features.isEnabled(Features.SUPPORT_AUDIO_ERASER_IN_GALLERY)) {
            this.mActionInvoker.invoke(ViewerAction.AUDIO_ERASER_ICON_VIEW_INFLATE, new Object[0]);
        }
    }

    public void invalidate(MediaItem mediaItem, int i2, MediaItem mediaItem2, int i7) {
        super.invalidate(mediaItem, i2, mediaItem2, i7);
        if (!MediaItemUtil.equals(this.mModel.getMediaItem(), mediaItem)) {
            setAudioEraserIcon(false);
        }
    }

    public boolean isToggleAudioEraserEnabled() {
        if (!ViewUtils.isVisible(this.mView) || !this.mView.isEnabled()) {
            return false;
        }
        return true;
    }

    public void onConfigurationChanged(Configuration configuration) {
        ViewUtils.postOnGlobalLayout(this.mView, new C0400c(this, 0));
    }

    public void onResolutionChanged() {
        View view = this.mView;
        if (view != null) {
            ViewUtils.postOnGlobalLayout(view, new C0400c(this, 1));
        }
    }

    public void onViewAttached() {
        super.onViewAttached();
        this.mModel.getContainerModel().setAudioEraserVisible(true);
        setAudioEraserIcon(false);
    }

    public void onViewDetached() {
        super.onViewDetached();
        initValues();
    }

    /* access modifiers changed from: private */
    public void onAudioEraserChanged(Object... objArr) {
    }
}
