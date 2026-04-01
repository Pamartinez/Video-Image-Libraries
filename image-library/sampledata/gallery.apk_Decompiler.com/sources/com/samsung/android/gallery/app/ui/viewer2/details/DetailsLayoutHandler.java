package com.samsung.android.gallery.app.ui.viewer2.details;

import L7.b;
import android.animation.Animator;
import android.content.res.Configuration;
import android.view.View;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.common.state.BottomSheetState;
import com.samsung.android.gallery.app.ui.viewer2.common.state.OverlayViewState;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.utils.ViewerUtils;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimatorListener;
import com.samsung.android.gallery.widget.animator.AlphaAnimator;
import com.samsung.android.gallery.widget.details.DetailsView;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.scs.base.StatusCodes;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DetailsLayoutHandler extends ViewerObject implements FragmentLifeCycle {
    private AlphaAnimator mAlphaAnimator;
    /* access modifiers changed from: private */
    public DetailsView mDetailsView;

    /* access modifiers changed from: private */
    public void initDetailView(Object... objArr) {
        this.mDetailsView = objArr[0];
        updateLayout();
    }

    /* access modifiers changed from: private */
    public void onBottomSheetStateChanged(Object... objArr) {
        if (objArr[1].booleanValue()) {
            updateZOrder(objArr[0].intValue());
        }
    }

    /* access modifiers changed from: private */
    public void onOverlayStateChanged(Object... objArr) {
        this.mDetailsView.setImportantForAccessibility(OverlayViewState.isShow(objArr[0]));
    }

    private void startAlphaAnimation() {
        if (this.mAlphaAnimator == null) {
            AlphaAnimator alphaAnimator = new AlphaAnimator((View) this.mDetailsView, 0.0f, 1.0f);
            this.mAlphaAnimator = alphaAnimator;
            alphaAnimator.setDuration(StatusCodes.INPUT_MISSING);
            this.mAlphaAnimator.addListener(new SimpleAnimatorListener() {
                public void onAnimationStart(Animator animator) {
                    ViewUtils.setAlpha(DetailsLayoutHandler.this.mDetailsView, 0.0f);
                }
            });
        }
        this.mAlphaAnimator.start();
    }

    private void updateEditButtonVisibility(MediaItem mediaItem) {
        boolean z;
        boolean z3;
        boolean z7;
        boolean z9 = false;
        if (mediaItem == null || mediaItem.isDrm() || mediaItem.isPostProcessing() || mediaItem.isBroken() || mediaItem.isCloudOnly() || mediaItem.isUriItem() || mediaItem.isMtp() || mediaItem.isSharing() || mediaItem.isPrivateItem() || FileUtils.isEmptyDummyImage(mediaItem.getPath())) {
            z = false;
        } else {
            z = true;
        }
        if (Features.isEnabled(Features.IS_GED) || Features.isEnabled(Features.IS_UPSM)) {
            z3 = false;
        } else {
            z3 = true;
        }
        if (LocationKey.isSelectedItems(this.mModel.getContainerModel().getLocationKey()) || ArgumentsUtil.getArgValue(this.mModel.getContainerModel().getLocationKey(), "cluster_map_date", (String) null) != null) {
            z7 = false;
        } else {
            z7 = true;
        }
        boolean isMultipleViewerStacked = ViewerUtils.isMultipleViewerStacked(this.mModel.getBlackboard());
        if (z && z3 && z7 && !isMultipleViewerStacked) {
            z9 = true;
        }
        if (!z9) {
            Log.d(this.TAG, "not support edit button", Boolean.valueOf(z), Boolean.valueOf(z3), Boolean.valueOf(z7), Boolean.valueOf(isMultipleViewerStacked));
        }
        this.mDetailsView.setEditButtonVisibility(z9);
    }

    private void updateLayout() {
        this.mDetailsView.updateLayout();
        this.mDetailsView.resetScrollPos();
    }

    private void updateZOrder(int i2) {
        float f;
        DetailsView detailsView = this.mDetailsView;
        if (3 == i2) {
            f = 0.0f;
        } else {
            f = -1.0f;
        }
        detailsView.setZ(f);
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.BOTTOM_SHEET_STATE_CHANGED, new b(this, 0));
        this.mActionInvoker.add(ViewerAction.DETAILS_VIEW, new b(this, 1));
        this.mActionInvoker.add(ViewerAction.OVERLAY_VIEW_STATE_CHANGED, new b(this, 2));
    }

    public void invalidate(MediaItem mediaItem, int i2, MediaItem mediaItem2, int i7) {
        super.invalidate(mediaItem, i2, mediaItem2, i7);
        if (!MediaItemUtil.equals(mediaItem2, mediaItem)) {
            updateEditButtonVisibility(mediaItem);
        }
    }

    public void onBind(MediaItem mediaItem, int i2) {
        super.onBind(mediaItem, i2);
        updateEditButtonVisibility(mediaItem);
    }

    public void onConfigurationChanged(Configuration configuration) {
        updateLayout();
    }

    public void onResume() {
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (BottomSheetState.Details.isClosed(this.mModel) && mediaItem != null && mediaItem.isSingleTakenShot()) {
            updateEditButtonVisibility(mediaItem);
        }
    }

    public void onTableModeChanged(boolean z, int i2) {
        startAlphaAnimation();
        this.mDetailsView.updateLayout();
    }

    public void onViewAttached() {
        updateLayout();
    }

    public void onViewDetached() {
        super.onViewDetached();
        this.mDetailsView.resetScrollPos();
    }
}
