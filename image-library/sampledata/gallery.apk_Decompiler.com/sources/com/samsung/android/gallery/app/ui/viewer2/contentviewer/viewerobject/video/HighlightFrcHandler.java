package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video;

import Ab.a;
import H7.f;
import android.view.View;
import android.widget.ImageView;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.app.ui.viewer2.model.ContentModel;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.abstraction.VideoBackupInfo;
import com.samsung.android.sdk.scs.base.StatusCodes;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HighlightFrcHandler extends ViewerObject implements FragmentLifeCycle {
    private ImageView mFrcButton;
    private View mFrcLayout;

    private void handleVideoBackupInfo() {
        Boolean bool;
        VideoBackupInfo videoBackupInfo = (VideoBackupInfo) this.mModel.getBlackboard().read("data://viewer_group_panel_video_current_info");
        if (videoBackupInfo != null && (bool = (Boolean) videoBackupInfo.getParameter(VideoBackupInfo.KEY.HIGHLIGHT_FRC)) != null && bool.booleanValue() != this.mModel.isHighlightFrcMode()) {
            Log.d(this.TAG, "Frc mode set by videoCurrentInfo. set to ", bool);
            this.mModel.setHighlightFrcMode(bool.booleanValue());
            updateFrcIcon();
        }
    }

    /* access modifiers changed from: private */
    public void initView(Object[] objArr) {
        View view = objArr[0];
        this.mFrcLayout = view;
        ImageView imageView = (ImageView) view.findViewById(R.id.highlight_frc_button);
        this.mFrcButton = imageView;
        imageView.setVisibility(0);
        this.mFrcButton.setOnClickListener(new a(28, this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initView$0(View view) {
        ContentModel contentModel = this.mModel;
        contentModel.setHighlightFrcMode(!contentModel.isHighlightFrcMode());
        Log.d(this.TAG, "Highlights FRC clicked");
        updateFrcIcon();
        this.mActionInvoker.invoke(ViewerAction.HIGHLIGHT_FRC_BUTTON_CLICKED, new Object[0]);
    }

    /* access modifiers changed from: private */
    public void onPlayTimeUpdated(Object... objArr) {
        if (this.mModel.isHighlightFrcMode()) {
            boolean z = true;
            int intValue = objArr[1].intValue();
            MediaItem mediaItem = objArr[2];
            if (mediaItem != null) {
                int highLightClipEndTime = (int) (MediaItemUtil.getHighLightClipEndTime(mediaItem) / 1000);
                if (((int) (mediaItem.getVideoThumbStartTime() / 1000)) + 1000 >= intValue || intValue >= highLightClipEndTime + StatusCodes.UNDEFINED) {
                    z = false;
                }
                if (z && !this.mModel.isInstantSlowMoPlayEnabled()) {
                    Log.d(this.TAG, "Highlights FRC Play true");
                    this.mActionInvoker.invoke(ViewerAction.HIGHLIGHT_FRC_PLAY, Boolean.TRUE);
                } else if (!z && this.mModel.isInstantSlowMoPlayEnabled()) {
                    Log.d(this.TAG, "Highlights FRC Play false");
                    this.mActionInvoker.invoke(ViewerAction.HIGHLIGHT_FRC_PLAY, Boolean.FALSE);
                }
            }
        }
    }

    private void updateFrcIcon() {
        int i2;
        ImageView imageView = this.mFrcButton;
        if (imageView != null) {
            if (this.mModel.isHighlightFrcMode()) {
                i2 = R.drawable.gallery_ic_1x_frc_slow;
            } else {
                i2 = R.drawable.gallery_ic_4x_frc_slow;
            }
            imageView.setImageResource(i2);
        }
    }

    public void addActionInvokeListener() {
        super.addActionInvokeListener();
        this.mActionInvoker.add(ViewerAction.HIGHLIGHT_FRC_VIEW, new f(this, 0));
        this.mActionInvoker.add(ViewerAction.PLAY_TIME_UPDATED, new f(this, 1));
    }

    public void initialize() {
        if (this.mFrcLayout == null) {
            this.mActionInvoker.invoke(ViewerAction.HIGHLIGHT_FRC_VIEW_INFLATE, new Object[0]);
        }
    }

    public void onResume() {
        handleVideoBackupInfo();
    }

    public void onViewAttached() {
        super.onViewAttached();
        handleVideoBackupInfo();
        updateFrcIcon();
    }

    public void onViewDetached() {
        this.mModel.setHighlightFrcMode(false);
    }
}
