package com.samsung.android.gallery.app.ui.viewer2.remaster;

import Qb.c;
import Qb.e;
import S7.d;
import S7.f;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemSuggest;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.library.abstraction.VslMesDetectorCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.widget.animator.PathInterpolator;
import com.samsung.android.gallery.widget.photoview.PhotoViewCompat;
import com.samsung.android.gallery.widget.remaster.AbstractRemasterLayout;
import com.samsung.android.gallery.widget.remaster.RemasterLayoutFactory;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemasterLayoutHandler extends ViewerObject implements FragmentLifeCycle {
    private ValueAnimator mFocusROIAnim;
    private boolean mFocusRoiLoaded = false;
    private boolean mIsRemastering = false;
    private PhotoViewCompat mPhotoView;
    private AbstractRemasterLayout mRemasterLayout;
    private RelativeLayout mViewerContainer;
    private CoordinatorLayout mViewerLayout;

    private void addPhotoViewPositionChangedListener() {
        PhotoViewCompat photoViewCompat = this.mPhotoView;
        if (photoViewCompat != null) {
            photoViewCompat.setPositionChangeListener(new c(7, this));
        }
    }

    private RectF getPhotoViewDisplayRectF() {
        PhotoViewCompat photoViewCompat = this.mPhotoView;
        if (photoViewCompat == null || photoViewCompat.getDisplayRect() == null) {
            return new RectF(-1.0f, -1.0f, -1.0f, -1.0f);
        }
        return this.mPhotoView.getDisplayRect();
    }

    private AbstractRemasterLayout getRemasterLayout(Activity activity) {
        RemasterLayoutFactory remasterLayoutFactory = new RemasterLayoutFactory();
        AbstractRemasterLayout.LayoutType layoutType = remasterLayoutFactory.getLayoutType(activity);
        AbstractRemasterLayout abstractRemasterLayout = this.mRemasterLayout;
        if (abstractRemasterLayout != null && abstractRemasterLayout.getLayoutType() == layoutType) {
            return this.mRemasterLayout;
        }
        AbstractRemasterLayout create = remasterLayoutFactory.create(activity, this.mViewerLayout);
        MediaItem mediaItem = this.mModel.getMediaItem();
        create.setHasFocusRoi(this.mFocusRoiLoaded);
        create.setRemasterTypeList(VslMesDetectorCompat.decodeEnhances(MediaItemSuggest.getRevitalizedType(mediaItem)));
        return create;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        this.mViewerLayout = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$1(Object[] objArr) {
        this.mPhotoView = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addPhotoViewPositionChangedListener$2() {
        updateHandlerLayout();
        this.mActionInvoker.invoke(ViewerAction.ON_REMASTER_PHOTO_POSITION_CHANGED, new Object[0]);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addPhotoViewPositionChangedListener$3(PointF pointF) {
        ViewUtils.post(this.mPhotoView, new e(12, this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startFocusRoiAnim$8(boolean z, ViewGroup.MarginLayoutParams marginLayoutParams, View view, ValueAnimator valueAnimator) {
        if (z) {
            marginLayoutParams.setMarginEnd(((Integer) valueAnimator.getAnimatedValue()).intValue());
        } else {
            marginLayoutParams.bottomMargin = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        }
        view.setLayoutParams(marginLayoutParams);
        this.mActionInvoker.invoke(ViewerAction.RESET_REMASTER_HANDLER_POSITION, new Object[0]);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateLayout$4(boolean z, boolean z3) {
        Activity activity = this.mModel.getActivity();
        updateViewerContainer(activity);
        updateLayoutComponent(activity, z, z3);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateLayoutComponent$5(boolean z, boolean z3) {
        if (z || this.mIsRemastering || z3) {
            this.mActionInvoker.invoke(ViewerAction.RESET_SCALE_AND_REGION_DECODING, new Object[0]);
        }
        if (z3) {
            this.mActionInvoker.invoke(ViewerAction.RESET_REMASTER_HANDLER_POSITION, new Object[0]);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateLayoutComponent$6(Activity activity, boolean z, boolean z3) {
        boolean z7;
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (activity == null || mediaItem == null) {
            Log.w(this.TAG, "failed to update layout -> (" + this.mViewerContainer + ")");
            return;
        }
        boolean z9 = true;
        if (!ResourceCompat.isLandscape((Context) activity) || SystemUi.isInMultiWindowMode(activity)) {
            z7 = false;
        } else {
            z7 = true;
        }
        if (this.mRemasterLayout != null) {
            z9 = false;
        }
        AbstractRemasterLayout remasterLayout = getRemasterLayout(activity);
        this.mRemasterLayout = remasterLayout;
        remasterLayout.update(this.mViewerContainer, z7, z, getPhotoViewDisplayRectF());
        this.mViewerContainer.setVisibility(0);
        ViewUtils.post(this.mViewerContainer, new S7.c(this, z9, z3, 0));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateLayoutComponent$7(Activity activity, boolean z, boolean z3) {
        ViewUtils.post(this.mViewerContainer, new d(this, activity, z, z3, 0));
    }

    /* access modifiers changed from: private */
    public void onLoadedFocusData(Object... objArr) {
        this.mFocusRoiLoaded = true;
        AbstractRemasterLayout abstractRemasterLayout = this.mRemasterLayout;
        if (abstractRemasterLayout != null) {
            abstractRemasterLayout.setHasFocusRoi(true);
            startFocusRoiAnim();
        }
    }

    /* access modifiers changed from: private */
    public void onRemastered(Object... objArr) {
        this.mIsRemastering = false;
    }

    private void startFocusRoiAnim() {
        boolean z;
        int i2;
        int i7;
        View findViewById = this.mViewerContainer.findViewById(R.id.content_container);
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) findViewById.getLayoutParams();
        View rootView = this.mViewerContainer.getRootView();
        if (this.mRemasterLayout.getLayoutType() != AbstractRemasterLayout.LayoutType.FOLD && ResourceCompat.isLandscape((Context) this.mModel.getActivity()) && !SystemUi.isInMultiWindowMode(this.mModel.getActivity())) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            i2 = this.mRemasterLayout.getContentRightMargin(true);
        } else {
            i2 = this.mRemasterLayout.getBottomMargin(rootView.getWidth(), rootView.getHeight(), ResourceCompat.isLandscape((Context) this.mModel.getActivity()));
        }
        if (z) {
            i7 = marginLayoutParams.getMarginEnd();
        } else {
            i7 = marginLayoutParams.bottomMargin;
        }
        this.mRemasterLayout.prepareFocusRoiAnim(getPhotoViewDisplayRectF());
        if (i7 != i2) {
            ValueAnimator duration = ValueAnimator.ofInt(new int[]{marginLayoutParams.bottomMargin, i2}).setDuration(500);
            this.mFocusROIAnim = duration;
            duration.setInterpolator(PathInterpolator.create(PathInterpolator.Type.TYPE_SINE_IN_OUT_60));
            this.mFocusROIAnim.addUpdateListener(new f(this, z, marginLayoutParams, findViewById));
            this.mFocusROIAnim.start();
        }
    }

    private void updateHandlerLayout() {
        boolean z;
        if (this.mRemasterLayout != null) {
            Activity activity = this.mModel.getActivity();
            if (!ResourceCompat.isLandscape((Context) activity) || SystemUi.isInMultiWindowMode(activity)) {
                z = false;
            } else {
                z = true;
            }
            this.mRemasterLayout.onPhotoViewTopChanged(getPhotoViewDisplayRectF(), z);
        }
    }

    private void updateLayout(boolean z, boolean z3) {
        ViewUtils.post(this.mViewerContainer, new S7.c(this, z, z3, 1));
    }

    private void updateLayoutComponent(Activity activity, boolean z, boolean z3) {
        ViewUtils.postOnGlobalLayout(this.mViewerContainer, new d(this, activity, z, z3, 1));
    }

    private void updateViewerContainer(Activity activity) {
        RelativeLayout relativeLayout;
        int i2;
        int i7;
        if (activity != null && (relativeLayout = this.mViewerContainer) != null) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) relativeLayout.getLayoutParams();
            Rect systemInsetsIgnoringVisibility = this.mModel.getContainerModel().getSystemInsetsIgnoringVisibility();
            if (systemInsetsIgnoringVisibility == null) {
                Log.w(this.TAG, "couldn't update remaster viewer container, insets is null");
                return;
            }
            if (SystemUi.isFullScreen(getBlackboard())) {
                Rect pinEdgeRect = SystemUi.getPinEdgeRect(this.mViewerContainer.getRootWindowInsets(), activity.getApplicationContext());
                i2 = systemInsetsIgnoringVisibility.left - pinEdgeRect.left;
                i7 = systemInsetsIgnoringVisibility.right - pinEdgeRect.right;
            } else {
                i7 = 0;
                i2 = 0;
            }
            marginLayoutParams.setMargins(i2, 0, i7, 0);
            this.mViewerContainer.setLayoutParams(marginLayoutParams);
        }
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.VIEWER_LAYOUT, new S7.e(this, 0));
        this.mActionInvoker.add(ViewerAction.IMAGE_PHOTO_VIEW, new S7.e(this, 1));
        this.mActionInvoker.add(ViewerAction.ON_DEMAND_REMASTERED, new S7.e(this, 2));
        this.mActionInvoker.add(ViewerAction.ON_READY_FOCUS_ROI_IMAGES, new S7.e(this, 3));
    }

    public void onApplyWindowInsets() {
        updateLayout(false, false);
    }

    public void onConfigurationChanged(Configuration configuration) {
        AbstractRemasterLayout abstractRemasterLayout = this.mRemasterLayout;
        if (abstractRemasterLayout != null) {
            abstractRemasterLayout.replaceLayout();
        }
        ValueAnimator valueAnimator = this.mFocusROIAnim;
        if (valueAnimator != null && valueAnimator.isStarted()) {
            this.mFocusROIAnim.cancel();
        }
        this.mActionInvoker.invoke(ViewerAction.REPLACED_HANDLER_LAYOUT, new Object[0]);
        updateLayout(false, true);
    }

    public void onInitialized() {
        this.mViewerContainer = (RelativeLayout) this.mViewerLayout.findViewById(R.id.viewer_container);
        this.mIsRemastering = LocationKey.isRemasterSingle(this.mModel.getContainerModel().getLocationKey());
    }

    public void onTableModeChanged(boolean z, int i2) {
        updateLayout(!this.mIsRemastering, false);
    }

    public void onViewAttached() {
        super.onViewAttached();
        addPhotoViewPositionChangedListener();
        updateLayout(false, false);
    }

    public void onViewConfirm() {
        super.onViewConfirm();
        updateLayout(false, false);
    }

    public void onViewDetached() {
        super.onViewDetached();
        PhotoViewCompat photoViewCompat = this.mPhotoView;
        if (photoViewCompat != null) {
            photoViewCompat.setPositionChangeListener((Consumer<PointF>) null);
        }
    }

    public void onViewRecycled() {
        super.onViewRecycled();
        this.mFocusRoiLoaded = false;
        this.mIsRemastering = false;
        this.mRemasterLayout = null;
    }
}
