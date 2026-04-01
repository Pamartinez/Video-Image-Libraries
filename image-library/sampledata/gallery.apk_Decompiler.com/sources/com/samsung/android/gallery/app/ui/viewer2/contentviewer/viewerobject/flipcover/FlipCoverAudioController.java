package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.flipcover;

import Ab.a;
import B7.b;
import android.view.View;
import android.widget.ImageView;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FlipCoverAudioController extends ViewerObject {
    private boolean mHasAudio = true;
    private ImageView mPlayAudioIcon;
    private boolean mViewEnabled = false;

    private void disableAudioIcon() {
        this.mThread.runOnUiThread(new b(this, 0));
    }

    private int getPlayAudioIconResId(boolean z) {
        if (z) {
            return R.drawable.gallery_ic_detail_sound_off;
        }
        return R.drawable.gallery_ic_detail_sound_on;
    }

    private boolean isPlayIconEnabled() {
        if (!ViewUtils.isVisible(this.mPlayAudioIcon) || !this.mPlayAudioIcon.isEnabled() || !this.mViewEnabled) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        disableAudioIcon();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$disableAudioIcon$3() {
        setViewEnabled(false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setAudioButton$1(View view) {
        if (this.mModel.isUnsupportedVideo()) {
            Utils.showToast(this.mModel.getContext(), (int) R.string.cant_play_video_file_type_not_supported);
        } else {
            onToggleSound(new Object[0]);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateHasAudio$2() {
        setPlayAudioIcon();
        setViewEnabled(this.mHasAudio);
    }

    /* access modifiers changed from: private */
    public void onAudioMuteChanged(Object... objArr) {
        setPlayAudioIcon();
    }

    /* access modifiers changed from: private */
    public void onToggleSound(Object... objArr) {
        if (isPlayIconEnabled()) {
            this.mModel.getContainerModel().setAudioMute(!this.mModel.getContainerModel().isAudioMute());
            this.mActionInvoker.invoke(ViewerAction.AUDIO_MUTE_CHANGED, new Object[0]);
            return;
        }
        Log.d(this.TAG, "audioView is disabled");
    }

    /* access modifiers changed from: private */
    public void setAudioButton(Object... objArr) {
        ImageView imageView = (ImageView) objArr[0].findViewById(R.id.flip_cover_play_audio_icon);
        this.mPlayAudioIcon = imageView;
        imageView.setOnClickListener(new a(6, this));
    }

    private void setPlayAudioIcon() {
        if (this.mHasAudio) {
            this.mPlayAudioIcon.setImageResource(getPlayAudioIconResId(this.mModel.getContainerModel().isAudioMute()));
        } else {
            this.mPlayAudioIcon.setImageResource(R.drawable.ic_gallery_ic_detail_no_sound);
        }
    }

    private void setViewEnabled(boolean z) {
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

    /* access modifiers changed from: private */
    public void updateHasAudio(Object... objArr) {
        this.mHasAudio = objArr[0].booleanValue();
        this.mThread.runOnUiThread(new b(this, 1));
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.VIDEO_CONTROLLER_VIEW, new B7.a(this, 0));
        this.mActionInvoker.add(ViewerAction.TOGGLE_SOUND, new B7.a(this, 1));
        this.mActionInvoker.add(ViewerAction.VIDEO_HAS_AUDIO, new B7.a(this, 2));
        this.mActionInvoker.add(ViewerAction.DISABLE_AUDIO_ICON, new B7.a(this, 3));
        this.mActionInvoker.add(ViewerAction.AUDIO_MUTE_CHANGED, new B7.a(this, 4));
    }

    public void onViewRecycled() {
        super.onViewRecycled();
        this.mHasAudio = true;
        this.mViewEnabled = false;
    }
}
