package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.flipcover;

import Ab.a;
import B7.d;
import B7.e;
import B7.f;
import android.view.View;
import android.view.ViewStub;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.common.shotmode.AbsShotModeHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.PlayButtonTextView;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FlipCoverVideoShotModeHandler extends ViewerObject implements FragmentLifeCycle {
    private View mShotModeButton;
    private AbsShotModeHandler mShotModeHandler;

    private void inflateView() {
        View view = this.mShotModeButton;
        if (view instanceof ViewStub) {
            View inflate = ((ViewStub) view).inflate();
            this.mShotModeButton = inflate;
            inflate.setOnClickListener(new a(7, this));
        }
    }

    /* access modifiers changed from: private */
    public void initView(Object... objArr) {
        this.mShotModeButton = objArr[0].findViewById(R.id.flip_cover_shot_mode_button);
    }

    private boolean isUnsupportedItem(MediaItem mediaItem) {
        if (mediaItem == null || !mediaItem.isVideo() || !mediaItem.isCloudOnly()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewAttached$0() {
        lambda$invalidate$1(this.mModel.getMediaItem());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateShotModeHandler$2() {
        this.mActionInvoker.invoke(ViewerAction.VIDEO_CONTROLLER_VISIBILITY_CHANGE, 0);
    }

    /* access modifiers changed from: private */
    public void onClick(View view) {
        if (view != null) {
            Utils.showToast(view.getContext(), (int) R.string.open_phone_and_try_again, 0);
        }
    }

    private void showShotModeView(MediaItem mediaItem) {
        int titleId;
        if (this.mShotModeHandler != null && !isUnsupportedItem(mediaItem) && (titleId = this.mShotModeHandler.getTitleId(mediaItem)) != 0) {
            this.mActionInvoker.invoke(ViewerAction.VIDEO_CONTROLLER_VISIBILITY_CHANGE, 8);
            updateShotModeButtonView(titleId);
        }
    }

    private void updateShotModeButtonView(int i2) {
        if (this.mShotModeButton != null) {
            StringCompat stringCompat = this.TAG;
            Log.d(stringCompat, "set ShotMode Button [" + this.mShotModeHandler + "]");
            ((PlayButtonTextView) this.mShotModeButton).setText(i2);
            ((PlayButtonTextView) this.mShotModeButton).setContentDescription(i2);
            ((PlayButtonTextView) this.mShotModeButton).setIconVisibility(0);
            this.mShotModeButton.setVisibility(0);
            ViewUtils.setViewEnabled(this.mShotModeButton, true);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: updateShotModeHandler */
    public void lambda$invalidate$1(MediaItem mediaItem) {
        if (mediaItem != null && mediaItem.isVideo()) {
            if (!mediaItem.isCloudOnly()) {
                this.mThread.runOnUiThread(new f(this, 1));
                return;
            }
            this.mShotModeHandler = AbsShotModeHandler.get(mediaItem);
            this.mThread.runOnUiThread(new e(this, mediaItem, 1));
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: updateView */
    public void lambda$updateShotModeHandler$3(MediaItem mediaItem) {
        if (this.mShotModeHandler == null) {
            ViewUtils.setVisibility(this.mShotModeButton, 8);
            return;
        }
        inflateView();
        showShotModeView(mediaItem);
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.FLIP_COVER_DECOR_LAYOUT, new d(0, this));
    }

    public void invalidate(MediaItem mediaItem, int i2, MediaItem mediaItem2, int i7) {
        super.invalidate(mediaItem, i2, mediaItem2, i7);
        this.mThread.runOnBgThread(new e(this, mediaItem, 0));
    }

    public void onBind(MediaItem mediaItem, int i2) {
        super.onBind(mediaItem, i2);
        ViewUtils.setVisibility(this.mShotModeButton, 8);
    }

    public void onViewAttached() {
        this.mThread.runOnBgThread(new f(this, 0));
    }

    public void onViewDetached() {
        this.mThread.cancel();
    }
}
