package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.accessibility.AccessibilityEvent;
import android.widget.ImageView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.behavior.SheetBehaviorCompat;
import com.samsung.android.gallery.widget.photoview.PhotoView;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.videoview.mediaplayer.IMediaPlayerView;
import com.sec.android.gallery3d.R;
import t8.e;
import v7.h;
import v7.i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DexZoomButtonUi extends ViewerObject implements FragmentLifeCycle {
    private final View.AccessibilityDelegate mAccessibilityDelegate = new View.AccessibilityDelegate() {
        public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            super.onPopulateAccessibilityEvent(view, accessibilityEvent);
            view.setHovered(true);
        }
    };
    private IMediaPlayerView mMediaView;
    private PhotoView mPhotoView;
    private View mView;
    private CoordinatorLayout mViewerLayout;
    private ImageView mZoomInButton;
    private ImageView mZoomOutButton;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum DexZoom {
        Min,
        Max,
        Normal
    }

    private void init() {
        if (this.mView == null) {
            this.mView = this.mViewerLayout.findViewById(R.id.zoom_in_out_layout);
        }
        View view = this.mView;
        if (view instanceof ViewStub) {
            this.mView = ((ViewStub) view).inflate();
        }
        if (this.mView.getTag() == null) {
            this.mView.setTag(DexZoom.Min);
        }
        this.mZoomInButton = (ImageView) this.mView.findViewById(R.id.zoom_in_button);
        this.mZoomOutButton = (ImageView) this.mView.findViewById(R.id.zoom_out_button);
        this.mZoomInButton.setAccessibilityDelegate(this.mAccessibilityDelegate);
        this.mZoomOutButton.setAccessibilityDelegate(this.mAccessibilityDelegate);
        this.mZoomOutButton.setEnabled(false);
        updateVisibility();
        setListener();
    }

    private boolean isDexZoomButtonVisible() {
        boolean isProperWindowSize = isProperWindowSize();
        if (!isProperWindowSize || !this.mModel.isSingleTakenShot()) {
            return isProperWindowSize;
        }
        return isVisibleInSingleTaken();
    }

    private boolean isProperWindowSize() {
        Resources resources;
        Context context = this.mModel.getContext();
        if (context != null) {
            resources = context.getResources();
        } else {
            resources = null;
        }
        if (resources == null) {
            Log.d(this.TAG, "update dexZoom visibility failed : resources is null");
            return false;
        }
        float dimension = resources.getDimension(R.dimen.zoom_in_out_button_visible_height_min_dp) / resources.getDisplayMetrics().density;
        float dimension2 = resources.getDimension(R.dimen.zoom_in_out_button_visible_width_min_dp) / resources.getDisplayMetrics().density;
        if (((float) resources.getConfiguration().screenHeightDp) < dimension || ((float) resources.getConfiguration().screenWidthDp) < dimension2) {
            return false;
        }
        return true;
    }

    private boolean isVisibleInSingleTaken() {
        boolean isCollapsed = SheetBehaviorCompat.isCollapsed(this.mModel.getDetailsState());
        boolean isTableMode = this.mModel.getContainerModel().isTableMode();
        if (isCollapsed || isTableMode) {
            return true;
        }
        return false;
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
    public /* synthetic */ void lambda$addActionInvokeListener$2(Object[] objArr) {
        this.mMediaView = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$3(Object[] objArr) {
        if (!objArr[0].booleanValue()) {
            updateZoomButton();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$4(Object[] objArr) {
        updateZoomButton();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setListener$5(View view) {
        setScaleRelative(1.25f);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setListener$6(View view) {
        setScaleRelative(0.75f);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateVisibility$7() {
        int i2;
        if (isDexZoomButtonVisible()) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        ViewUtils.setVisibility(this.mView, i2);
        updateMargin();
        updateTouchArea();
    }

    private void setListener() {
        ImageView imageView = this.mZoomInButton;
        if (imageView != null) {
            imageView.setOnClickListener(new i(this, 0));
        }
        ImageView imageView2 = this.mZoomOutButton;
        if (imageView2 != null) {
            imageView2.setOnClickListener(new i(this, 1));
        }
    }

    private void setScaleRelative(float f) {
        IMediaPlayerView iMediaPlayerView = this.mMediaView;
        if (iMediaPlayerView != null) {
            iMediaPlayerView.setScaleRelative(f);
        }
        PhotoView photoView = this.mPhotoView;
        if (photoView != null) {
            photoView.setScaleRelative(f);
        }
    }

    private void updateMargin() {
        ImageView imageView = this.mZoomInButton;
        if (imageView != null) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) imageView.getLayoutParams();
            marginLayoutParams.setMarginStart(0);
            this.mZoomInButton.setLayoutParams(marginLayoutParams);
        }
        ImageView imageView2 = this.mZoomOutButton;
        if (imageView2 != null) {
            ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) imageView2.getLayoutParams();
            marginLayoutParams2.setMarginStart(0);
            marginLayoutParams2.bottomMargin = 0;
            this.mZoomOutButton.setLayoutParams(marginLayoutParams2);
        }
    }

    private void updateTouchArea() {
        View view = this.mView;
        if (view != null) {
            int dimensionPixelSize = view.getResources().getDimensionPixelSize(R.dimen.select_pictures_icon_container_side_margin);
            int dimensionPixelOffset = this.mView.getResources().getDimensionPixelOffset(R.dimen.horizontal_navigation_icon_extra_margin);
            ImageView imageView = this.mZoomInButton;
            if (imageView != null) {
                ViewUtils.setTouchAreaComposite(imageView, dimensionPixelSize, dimensionPixelOffset, dimensionPixelOffset, 0);
            }
            ImageView imageView2 = this.mZoomOutButton;
            if (imageView2 != null) {
                ViewUtils.setTouchAreaComposite(imageView2, dimensionPixelSize, 0, dimensionPixelOffset, dimensionPixelOffset);
            }
        }
    }

    private void updateVisibility() {
        ViewUtils.post(this.mView, new e(12, this));
    }

    private void updateZoomButton() {
        boolean z;
        boolean z3;
        DexZoom dexZoom;
        if (this.mView != null) {
            IMediaPlayerView iMediaPlayerView = this.mMediaView;
            if (iMediaPlayerView != null) {
                z3 = iMediaPlayerView.isMinScale();
                z = this.mMediaView.isMaxScale();
            } else {
                PhotoView photoView = this.mPhotoView;
                if (photoView != null) {
                    z3 = photoView.isMinScale();
                    z = this.mPhotoView.isMaxScale();
                } else {
                    z3 = false;
                    z = false;
                }
            }
            if (z3) {
                dexZoom = DexZoom.Min;
            } else if (z) {
                dexZoom = DexZoom.Max;
            } else {
                dexZoom = DexZoom.Normal;
            }
            if (!dexZoom.equals(this.mView.getTag())) {
                this.mView.setTag(dexZoom);
                if (DexZoom.Min.equals(dexZoom)) {
                    ViewUtils.setViewEnabledWithoutAlphaChange(this.mZoomInButton, true);
                    ViewUtils.setViewEnabledWithoutAlphaChange(this.mZoomOutButton, false);
                } else if (DexZoom.Max.equals(dexZoom)) {
                    ViewUtils.setViewEnabledWithoutAlphaChange(this.mZoomInButton, false);
                    ViewUtils.setViewEnabledWithoutAlphaChange(this.mZoomOutButton, true);
                } else {
                    ViewUtils.setViewEnabledWithoutAlphaChange(this.mZoomInButton, true);
                    ViewUtils.setViewEnabledWithoutAlphaChange(this.mZoomOutButton, true);
                }
            }
        }
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.VIEWER_LAYOUT, new h(this, 0));
        this.mActionInvoker.add(ViewerAction.IMAGE_PHOTO_VIEW, new h(this, 1));
        this.mActionInvoker.add(ViewerAction.MEDIA_VIEW, new h(this, 2));
        this.mActionInvoker.add(ViewerAction.PHOTOVIEW_MATRIX_CHANGED, new h(this, 3));
        this.mActionInvoker.add(ViewerAction.VIDEO_SCALE_END, new h(this, 4));
    }

    public void onConfigurationChanged(Configuration configuration) {
        updateVisibility();
    }

    public void onViewConfirm() {
        super.onViewConfirm();
        init();
    }

    public void onViewRecycled() {
        super.onViewRecycled();
        this.mView = null;
        this.mZoomInButton = null;
        this.mZoomOutButton = null;
    }
}
