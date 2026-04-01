package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video;

import Ab.a;
import H7.C0398a;
import H7.C0399b;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.module.abstraction.VideoPropData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AudioController extends ViewerObject implements FragmentLifeCycle {
    protected boolean mHasAudio = true;
    protected ImageView mPlayAudioIcon;
    ViewStub mPlayIconViewStub;
    private boolean mViewEnabled = false;

    private void disableAudioIcon() {
        this.mThread.runOnUiThread(new C0398a(this, 3));
        updateTouchArea();
    }

    private int getPlayAudioIconResId(boolean z) {
        if (z) {
            return R.drawable.gallery_ic_detail_sound_off;
        }
        return R.drawable.gallery_ic_detail_sound_on;
    }

    private void hideAudioIcon() {
        this.mThread.runOnUiThread(new C0398a(this, 2));
        updateTouchArea();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        this.mPlayAudioIcon = objArr[0];
        this.mPlayIconViewStub = objArr[1];
        setAudioButton();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$1(Object[] objArr) {
        disableAudioIcon();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$2(Object[] objArr) {
        hideAudioIcon();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$disableAudioIcon$6() {
        setViewEnabled(false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setAudioButton$5(View view) {
        if (this.mModel.isUnsupportedVideo()) {
            Utils.showToast(this.mModel.getContext(), (int) R.string.cant_play_video_file_type_not_supported);
        } else if (!this.mViewEnabled) {
            Log.i(this.TAG, "audio icon is disabled");
        } else {
            onToggleSound(new Object[0]);
            EventContext eventContext = this.mModel.getContainerModel().getEventContext();
            if (eventContext != null) {
                AnalyticsLogger.getInstance().postLog(eventContext.getScreenId(), AnalyticsEventId.EVENT_DETAIL_VIEW_VIDEO_SOUND_TOGGLE.toString(), AnalyticsDetail.getOnOff(!this.mModel.getContainerModel().isAudioMute()));
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setIconForSharedVideo$3() {
        setPlayAudioIcon();
        updateVisibility(this.mModel.getMediaItem());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateHasAudio$4() {
        setPlayAudioIcon();
        setViewEnabled(this.mHasAudio);
    }

    /* access modifiers changed from: private */
    public void onAudioMuteChanged(Object... objArr) {
        setPlayAudioIcon();
    }

    /* access modifiers changed from: private */
    public void onToggleSound(Object... objArr) {
        if (isToggleSoundEnabled()) {
            if (this.mModel.getContainerModel().isAudioMute()) {
                showVolumePanel();
            }
            this.mModel.getContainerModel().setAudioMute(!this.mModel.getContainerModel().isAudioMute());
            this.mActionInvoker.invoke(ViewerAction.AUDIO_MUTE_CHANGED, new Object[0]);
            return;
        }
        Log.d(this.TAG, "audioView is disabled");
    }

    private void setAudioButton() {
        this.mPlayAudioIcon.setOnClickListener(new a(25, this));
    }

    /* access modifiers changed from: private */
    public void setIconForSharedVideo(Object... objArr) {
        this.mThread.runOnUiThread(new C0398a(this, 1));
    }

    private void setPlayAudioIcon() {
        int i2;
        if (this.mHasAudio) {
            boolean isAudioMute = this.mModel.getContainerModel().isAudioMute();
            this.mPlayAudioIcon.setImageResource(getPlayAudioIconResId(isAudioMute));
            ImageView imageView = this.mPlayAudioIcon;
            StringBuilder sb2 = new StringBuilder();
            if (isAudioMute) {
                i2 = R.string.off;
            } else {
                i2 = R.string.on;
            }
            sb2.append(AppResources.getString(i2));
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(AppResources.getString(R.string.toggle_audio));
            imageView.setContentDescription(sb2.toString());
            return;
        }
        this.mPlayAudioIcon.setImageResource(R.drawable.ic_gallery_ic_detail_no_sound);
    }

    /* access modifiers changed from: private */
    public void setViewHide() {
        this.mViewEnabled = false;
        ViewUtils.setAlpha(this.mPlayAudioIcon, 0.0f);
    }

    private void showVolumePanel() {
        ((AudioManager) this.mModel.getContext().getSystemService("audio")).adjustStreamVolume(3, 0, 1);
    }

    /* access modifiers changed from: private */
    public void updateHasAudio(Object... objArr) {
        this.mHasAudio = objArr[0].booleanValue();
        if (objArr[1].booleanValue()) {
            this.mThread.runOnUiThread(new C0398a(this, 0));
            updateTouchArea();
        }
    }

    /* access modifiers changed from: private */
    public void updateTouchArea() {
        ViewUtils.setTouchAreaComposite(this.mPlayAudioIcon, R.dimen.decor_button_touch_area);
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.AUDIO_ICON_VIEW, new C0399b(this, 0));
        this.mActionInvoker.add(ViewerAction.DISABLE_AUDIO_ICON, new C0399b(this, 1));
        this.mActionInvoker.add(ViewerAction.HIDE_AUDIO_ICON, new C0399b(this, 2));
        this.mActionInvoker.add(ViewerAction.VIDEO_HAS_AUDIO, new C0399b(this, 3));
        this.mActionInvoker.add(ViewerAction.AUDIO_MUTE_CHANGED, new C0399b(this, 4));
        this.mActionInvoker.add(ViewerAction.TOGGLE_SOUND, new C0399b(this, 5));
        if (Features.isEnabled(Features.USE_SHARED_DOWNLOAD_FILE_VERIFY)) {
            this.mActionInvoker.add(ViewerAction.INITIALIZE_SHARED_VIDEO, new C0399b(this, 6));
            this.mActionInvoker.add(ViewerAction.DOWNLOADED_SHARE_VIDEO_VERIFY_UPDATED, new C0399b(this, 6));
        }
    }

    public void initialize() {
        this.mActionInvoker.invoke(ViewerAction.AUDIO_ICON_VIEW_INFLATE, new Object[0]);
    }

    public void invalidate(MediaItem mediaItem, int i2, MediaItem mediaItem2, int i7) {
        super.invalidate(mediaItem, i2, mediaItem2, i7);
        if (!MediaItemUtil.equals(this.mModel.getMediaItem(), mediaItem)) {
            setPlayAudioIcon();
            updateVisibility(mediaItem);
        }
    }

    public boolean isToggleSoundEnabled() {
        if (!ViewUtils.isVisible(this.mPlayAudioIcon) || !this.mPlayAudioIcon.isEnabled() || !this.mViewEnabled) {
            return false;
        }
        return true;
    }

    public void onConfigurationChanged(Configuration configuration) {
        ViewUtils.postOnGlobalLayout(this.mPlayAudioIcon, new C0398a(this, 4));
    }

    public void onResolutionChanged() {
        ImageView imageView = this.mPlayAudioIcon;
        if (imageView != null) {
            this.mPlayAudioIcon = (ImageView) ViewUtils.reinflateViewStub(this.mPlayIconViewStub, imageView);
            setAudioButton();
            updateHasAudio(Boolean.valueOf(this.mHasAudio), Boolean.TRUE);
        }
    }

    public void onStop() {
        disableAudioIcon();
    }

    public void onViewAttached() {
        super.onViewAttached();
        setPlayAudioIcon();
        setViewEnabled(false);
        updateVisibility(this.mModel.getMediaItem());
    }

    public void onViewRecycled() {
        super.onViewRecycled();
        this.mHasAudio = true;
        setViewEnabled(false);
    }

    public void setViewEnabled(boolean z) {
        float f;
        this.mViewEnabled = z;
        ImageView imageView = this.mPlayAudioIcon;
        if (z) {
            f = 1.0f;
        } else {
            f = 0.45f;
        }
        ViewUtils.setAlpha(imageView, f);
    }

    public boolean supportAudioController(MediaItem mediaItem) {
        if (!MediaItemUtil.supportPreviewPlay(mediaItem) || VideoPropData.of(mediaItem).longExposure) {
            return false;
        }
        return true;
    }

    public void updateVisibility(MediaItem mediaItem) {
        int i2;
        ImageView imageView = this.mPlayAudioIcon;
        if (supportAudioController(mediaItem)) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        ViewUtils.setVisibility(imageView, i2);
    }
}
