package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.module.remote.v2.RemoteDisplayState;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimationListener;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.Optional;
import v7.v;
import v7.w;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VideoMirroringUi extends ViewerObject implements FragmentLifeCycle {
    /* access modifiers changed from: private */
    public View mSmartViewIcon;
    private boolean mSmartViewIconState;

    private AlphaAnimation getAlphaAnimation(final boolean z) {
        float f;
        if (z) {
            f = 1.0f;
        } else {
            f = 0.0f;
        }
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, f);
        alphaAnimation.setDuration(180);
        alphaAnimation.setAnimationListener(new SimpleAnimationListener() {
            public void onAnimationEnd(Animation animation) {
                if (!z) {
                    ViewUtils.setVisibility(VideoMirroringUi.this.mSmartViewIcon, 8);
                }
            }

            public void onAnimationStart(Animation animation) {
                ViewUtils.setVisibility(VideoMirroringUi.this.mSmartViewIcon, 0);
            }
        });
        return alphaAnimation;
    }

    /* access modifiers changed from: private */
    public void onVideoMirroringView(Object... objArr) {
        View view = objArr[0];
        if (PreferenceFeatures.VideoPlayerFeature.isOpenInGalleryVideoPlayer()) {
            this.mSmartViewIcon = view.findViewById(R.id.video_mirroring_ui);
        }
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.VIDEO_MIRRORING_VIEW, new v(this, 0));
        this.mActionInvoker.add(ViewerAction.UPDATE_VIDEO_MIRRORING_UI, new v(this, 1));
    }

    public void onViewAttached() {
        super.onViewAttached();
        if (!this.mSmartViewIconState) {
            ViewUtils.setVisibility(this.mSmartViewIcon, 8);
        }
    }

    public void onViewConfirm() {
        super.onViewConfirm();
        if (this.mSmartViewIcon == null) {
            this.mActionInvoker.invoke(ViewerAction.VIDEO_MIRRORING_UI_INFLATE, new Object[0]);
        }
    }

    public void onViewDetached() {
        super.onViewDetached();
        this.mSmartViewIconState = false;
    }

    public void onViewRecycled() {
        super.onViewRecycled();
        this.mSmartViewIconState = false;
    }

    public void updateVideoMirroringUi(Object[] objArr) {
        boolean z = false;
        if (objArr[0].booleanValue() && RemoteDisplayState.getInstance().isConnected() && !RemoteDisplayState.getInstance().isBackgroundPlaying()) {
            z = true;
        }
        updateVideoMirroringUi(z);
    }

    private void updateVideoMirroringUi(boolean z) {
        if (this.mSmartViewIcon != null && this.mSmartViewIconState != z) {
            StringCompat stringCompat = this.TAG;
            Log.rm(stringCompat, "updateVideoMirroringUi {" + z + "}");
            this.mSmartViewIconState = z;
            Optional.ofNullable(this.mSmartViewIcon.getAnimation()).ifPresent(new w(0));
            this.mSmartViewIcon.startAnimation(getAlphaAnimation(z));
            this.mActionInvoker.invoke(ViewerAction.SET_ZOOM_ENABLED, Boolean.valueOf(!z));
        }
    }
}
