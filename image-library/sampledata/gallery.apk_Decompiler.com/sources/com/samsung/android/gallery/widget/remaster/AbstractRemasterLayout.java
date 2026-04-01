package com.samsung.android.gallery.widget.remaster;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.RectF;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.widget.R$bool;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.R$layout;
import com.samsung.android.gallery.widget.R$string;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimationListener;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AbstractRemasterLayout {
    private static final boolean IS_TABLET = Features.isEnabled(Features.IS_TABLET_BY_SYSTEM_PROPERTIES);
    protected int mActionbarHeight;
    protected final Activity mActivity;
    private TextView mAfterTextView;
    private TextView mBeforeTextView;
    protected FrameLayout mCircleHandler;
    protected View mEffectHandlerView;
    protected View mFocusView;
    private boolean mHasFocusRoi;
    protected LinearLayout mHeaderView;
    /* access modifiers changed from: private */
    public boolean mIsAnimCanceled = false;
    protected final boolean mIsTabletDpi;
    protected final Animation mMarginAnim;
    private int mNavigationBarHeight;
    protected RelativeLayout mPhotoViewContainer;
    private List<Integer> mRemasterTypeList;
    protected final Resources mResources;
    protected final View mRootView;
    protected int mSourceMargin;
    protected View mTableModeBG;
    protected int mTargetMargin;
    protected View mVerticalLine;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum LayoutType {
        NORMAL,
        FOLD,
        TABLE_MODE
    }

    public AbstractRemasterLayout(Activity activity, ViewGroup viewGroup) {
        AnonymousClass2 r0 = new Animation() {
            public void applyTransformation(float f, Transformation transformation) {
                if (!AbstractRemasterLayout.this.mIsAnimCanceled) {
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) AbstractRemasterLayout.this.mPhotoViewContainer.getLayoutParams();
                    AbstractRemasterLayout abstractRemasterLayout = AbstractRemasterLayout.this;
                    int i2 = abstractRemasterLayout.mSourceMargin;
                    layoutParams.bottomMargin = i2 + ((int) (((float) (abstractRemasterLayout.mTargetMargin - i2)) * f));
                    abstractRemasterLayout.mPhotoViewContainer.setLayoutParams(layoutParams);
                }
            }
        };
        this.mMarginAnim = r0;
        this.mRootView = viewGroup;
        this.mPhotoViewContainer = (RelativeLayout) viewGroup.findViewById(R$id.content_container);
        this.mVerticalLine = viewGroup.findViewById(R$id.vertical_line);
        this.mCircleHandler = (FrameLayout) viewGroup.findViewById(R$id.circle_handler);
        this.mTableModeBG = viewGroup.findViewById(R$id.table_mode_bg);
        this.mHeaderView = (LinearLayout) viewGroup.findViewById(R$id.remaster_header_view);
        this.mEffectHandlerView = viewGroup.findViewById(R$id.effect_handler_view);
        this.mFocusView = viewGroup.findViewById(R$id.remaster_focus_view);
        this.mBeforeTextView = (TextView) viewGroup.findViewById(R$id.before_text);
        this.mAfterTextView = (TextView) viewGroup.findViewById(R$id.after_text);
        r0.setDuration(200);
        r0.setAnimationListener(new SimpleAnimationListener() {
            public void onAnimationStart(Animation animation) {
                AbstractRemasterLayout.this.mIsAnimCanceled = false;
            }
        });
        this.mActivity = activity;
        Resources resources = activity.getResources();
        this.mResources = resources;
        this.mIsTabletDpi = resources.getBoolean(R$bool.isTabletDpi);
    }

    private int getContentTopMargin() {
        return this.mActionbarHeight;
    }

    private int getPhotoViewMaxHeight(int i2) {
        int dimensionPixelSize;
        int i7;
        if (isInMultiWindowMode(this.mActivity)) {
            return Math.abs((i2 - getContentTopMargin()) - Math.max(getMinBottomMargin(false), getFooterHeight()));
        }
        if (DeviceInfo.isDexMode(this.mActivity)) {
            i7 = i2 - (DeviceInfo.getStatusBarHeight() + getContentTopMargin());
            dimensionPixelSize = getStableInsetBottom();
        } else if (ResourceCompat.isLandscape((Context) this.mActivity)) {
            return getLandPhotoViewHeight(i2);
        } else {
            int statusBarHeight = DeviceInfo.getStatusBarHeight() + getContentTopMargin();
            int navigationBarHeight = getNavigationBarHeight() + getFooterHeight();
            dimensionPixelSize = this.mResources.getDimensionPixelSize(R$dimen.remaster_viewer_min_bottom_margin);
            i7 = (i2 - statusBarHeight) - navigationBarHeight;
        }
        return i7 - dimensionPixelSize;
    }

    private int getPortPhotoViewHeight(int i2, int i7) {
        int i8;
        float f;
        int dimensionPixelSize = this.mResources.getDimensionPixelSize(R$dimen.remaster_viewer_min_size);
        if (supportFocusView()) {
            int statusBarHeight = DeviceInfo.getStatusBarHeight() + getContentTopMargin();
            int i10 = i7 - statusBarHeight;
            i8 = i10 - (getMinBottomMargin(false) + getNavigationBarHeight());
        } else {
            if (IS_TABLET) {
                f = 1.255f;
            } else {
                f = ResourceCompat.getFloatFromDimension(this.mResources, R$dimen.remaster_viewer_content_height_ratio);
            }
            i8 = (int) (((float) i2) * f);
        }
        return Math.max(i8, dimensionPixelSize);
    }

    public static boolean isInMultiWindowMode(Activity activity) {
        return SystemUi.isInMultiWindowMode(activity);
    }

    private void updateHeaderText() {
        if (this.mRemasterTypeList.contains(19) || this.mRemasterTypeList.contains(9) || this.mRemasterTypeList.contains(17) || this.mRemasterTypeList.contains(15)) {
            this.mBeforeTextView.setText(R$string.remaster_before);
            this.mAfterTextView.setText(R$string.remaster_after);
        }
    }

    private void updateHeaderTextView(ViewGroup viewGroup) {
        View childAt = viewGroup.getChildAt(0);
        View childAt2 = viewGroup.getChildAt(1);
        if (childAt != null && childAt2 != null) {
            int dimensionPixelSize = this.mResources.getDimensionPixelSize(R$dimen.remaster_viewer_header_min_width) / 2;
            int max = Math.max(childAt.getWidth(), childAt2.getWidth());
            if (max <= dimensionPixelSize) {
                ViewMarginUtils.setHorizontalPadding(childAt, 0);
                ViewMarginUtils.setHorizontalPadding(childAt2, 0);
            } else {
                dimensionPixelSize = max;
            }
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
            ViewGroup.LayoutParams layoutParams2 = childAt2.getLayoutParams();
            layoutParams.width = dimensionPixelSize;
            layoutParams2.width = dimensionPixelSize;
            childAt.setLayoutParams(layoutParams);
            childAt2.setLayoutParams(layoutParams2);
        }
    }

    public void cancelAnim() {
        Animation animation;
        RelativeLayout relativeLayout = this.mPhotoViewContainer;
        if (relativeLayout != null && (animation = relativeLayout.getAnimation()) != null && animation.hasStarted()) {
            animation.cancel();
            this.mIsAnimCanceled = true;
        }
    }

    public int getBottomMargin(int i2, int i7, boolean z) {
        if (!z || isInMultiWindowMode(this.mActivity)) {
            return getPortBottomMargin(i2, i7);
        }
        return getLandBottomMargin();
    }

    public int getCircleHandlerTopMargin(boolean z, int i2) {
        if (z) {
            return (-(this.mVerticalLine.getHeight() + i2)) / 2;
        }
        return -(getHandlerBottomMargin(false) + i2);
    }

    public int getContentRightMargin(boolean z) {
        if (!hasFocusRoi() || !z || Features.isEnabled(Features.IS_RTL)) {
            return 0;
        }
        return this.mResources.getDimensionPixelSize(R$dimen.remaster_viewer_content_end_margin);
    }

    public final int getDeviceHeight() {
        return DeviceInfo.getDisplayHeight(this.mActivity);
    }

    public final int getFastOptionHeight(boolean z) {
        if (!z || isInMultiWindowMode(this.mActivity)) {
            return this.mResources.getDimensionPixelSize(R$dimen.fast_menu_imageview_height);
        }
        return 0;
    }

    public int getFooterHeight() {
        int i2;
        if (supportFocusView()) {
            i2 = 0;
        } else {
            i2 = this.mResources.getDimensionPixelSize(R$dimen.remaster_viewer_circle_horizontal_padding);
        }
        return this.mResources.getDimensionPixelSize(R$dimen.fast_menu_imageview_height) + i2;
    }

    public int getHandlerBottomMargin(boolean z) {
        if (isInMultiWindowMode(this.mActivity)) {
            return this.mResources.getDimensionPixelSize(R$dimen.fast_menu_imageview_height);
        }
        return 0;
    }

    public int getLandBottomMargin() {
        int i2;
        int i7 = 0;
        if (isInMultiWindowMode(this.mActivity)) {
            i2 = this.mResources.getDimensionPixelSize(R$dimen.fast_menu_imageview_height);
        } else if (DeviceInfo.isDexMode(this.mActivity) || this.mIsTabletDpi) {
            i2 = getStableInsetBottom();
        } else {
            i2 = 0;
        }
        if (hasFocusRoi()) {
            i7 = getMinBottomMargin(true);
        }
        return Math.max(i2, i7);
    }

    public int getLandPhotoViewHeight(int i2) {
        if (!this.mIsTabletDpi || isInMultiWindowMode(this.mActivity)) {
            return i2 - this.mActionbarHeight;
        }
        return i2 - ((DeviceInfo.getStatusBarHeight() + getStableInsetBottom()) + this.mActionbarHeight);
    }

    public abstract LayoutType getLayoutType();

    public int getMinBottomMargin(boolean z) {
        if (!supportFocusView() || z) {
            return 0;
        }
        return getFastOptionHeight(z) + this.mResources.getDimensionPixelSize(R$dimen.remaster_viewer_bottom_margin);
    }

    public final int getNavigationBarHeight() {
        return this.mNavigationBarHeight;
    }

    public int getPhotoViewHeight(int i2, int i7, boolean z) {
        int i8;
        int portPhotoViewHeight;
        int photoViewMaxHeight = getPhotoViewMaxHeight(i7);
        if (isInMultiWindowMode(this.mActivity)) {
            i8 = (i7 - this.mActionbarHeight) - this.mResources.getDimensionPixelSize(R$dimen.fast_menu_imageview_height);
        } else {
            if (z) {
                portPhotoViewHeight = getLandPhotoViewHeight(i7);
            } else {
                portPhotoViewHeight = getPortPhotoViewHeight(i2, i7);
            }
            i8 = portPhotoViewHeight;
        }
        if (photoViewMaxHeight > 0) {
            return Math.min(photoViewMaxHeight, i8);
        }
        return i8;
    }

    public int getPortBottomMargin(int i2, int i7) {
        int i8;
        int i10 = 0;
        if (hasFocusRoi()) {
            i8 = getMinBottomMargin(false);
        } else {
            i8 = 0;
        }
        int photoViewHeight = i7 - getPhotoViewHeight(i2, i7, false);
        int i11 = photoViewHeight / 2;
        if (!isInMultiWindowMode(this.mActivity)) {
            i10 = getNavigationBarHeight();
        }
        if (!isInMultiWindowMode(this.mActivity) || getContentTopMargin() <= i11) {
            return Math.max(i8 + i10, i11);
        }
        return Math.max(i8, photoViewHeight - getContentTopMargin());
    }

    public int getStableInsetBottom() {
        WindowInsets rootWindowInsets = this.mRootView.getRootWindowInsets();
        if (rootWindowInsets != null) {
            return rootWindowInsets.getStableInsetBottom();
        }
        return 0;
    }

    public boolean hasFocusRoi() {
        return this.mHasFocusRoi;
    }

    public boolean isLandscape() {
        if (!ResourceCompat.isLandscape((Context) this.mActivity) || SystemUi.isInMultiWindowMode(this.mActivity)) {
            return false;
        }
        return true;
    }

    public void onPhotoViewTopChanged(RectF rectF, boolean z) {
        updateHeader(rectF);
        updateHandlerLayout(rectF, z);
    }

    public void replaceLayout() {
        View inflate = LayoutInflater.from(this.mActivity).inflate(R$layout.remaster_viewer_handler_layout, (ViewGroup) null);
        inflate.setId(R$id.effect_handler_view);
        View view = this.mEffectHandlerView;
        if (view != null) {
            inflate.setLayoutParams((RelativeLayout.LayoutParams) view.getLayoutParams());
            inflate.setVisibility(this.mEffectHandlerView.getVisibility());
        }
        ViewUtils.replaceView(this.mEffectHandlerView, inflate);
        this.mEffectHandlerView = inflate;
        this.mVerticalLine = inflate.findViewById(R$id.vertical_line);
        this.mCircleHandler = (FrameLayout) this.mEffectHandlerView.findViewById(R$id.circle_handler);
        this.mHeaderView = (LinearLayout) this.mEffectHandlerView.findViewById(R$id.remaster_header_view);
        this.mBeforeTextView = (TextView) this.mEffectHandlerView.findViewById(R$id.before_text);
        this.mAfterTextView = (TextView) this.mEffectHandlerView.findViewById(R$id.after_text);
    }

    public void setHasFocusRoi(boolean z) {
        this.mHasFocusRoi = z;
    }

    public void setRemasterTypeList(List<Integer> list) {
        this.mRemasterTypeList = list;
    }

    public boolean supportFocusView() {
        return Features.isEnabled(Features.SUPPORT_REMASTER_FOCUS_ROI);
    }

    public void update(View view, boolean z, boolean z3, RectF rectF) {
        updateAttrs();
        updateHeaderText();
        updateViewContainerLayout(view, z, z3);
        updateHandlerLayout(rectF, z);
        updateTableModeBG(!z);
        updateHeader(rectF);
        if (supportFocusView()) {
            updateFocusView(z);
        }
    }

    public void updateAttrs() {
        this.mActionbarHeight = ViewUtils.getMeasuredHeight(this.mActivity.findViewById(R$id.toolbar));
        this.mNavigationBarHeight = SystemUi.getNavigationBarHeight(this.mRootView.getRootWindowInsets());
    }

    public abstract void updateCircleHandler(boolean z);

    public void updateFocusView(boolean z) {
        int i2;
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mFocusView.getLayoutParams();
        if (!z || IS_TABLET) {
            int dimensionPixelSize = this.mResources.getDimensionPixelSize(R$dimen.remaster_viewer_focus_bottom_margin);
            layoutParams.topMargin = 0;
            int footerHeight = getFooterHeight() + dimensionPixelSize;
            if (isInMultiWindowMode(this.mActivity)) {
                i2 = 0;
            } else {
                i2 = getNavigationBarHeight();
            }
            layoutParams.bottomMargin = footerHeight + i2;
            layoutParams.setMarginEnd(0);
            layoutParams.removeRule(21);
            layoutParams.addRule(12);
            layoutParams.addRule(13);
        } else {
            layoutParams.topMargin = this.mActionbarHeight + this.mResources.getDimensionPixelSize(R$dimen.remaster_viewer_focus_view_top_margin);
            layoutParams.bottomMargin = 0;
            layoutParams.setMarginEnd(WindowUtils.getDisplayCutoutEnd(this.mRootView.getRootWindowInsets()));
            layoutParams.removeRule(12);
            layoutParams.removeRule(13);
            layoutParams.addRule(21);
        }
        this.mFocusView.setLayoutParams(layoutParams);
    }

    public void updateHandlerLayout(RectF rectF, boolean z) {
        updateVerticalLine(rectF, z);
        updateCircleHandler(z);
    }

    public void updateHeader(RectF rectF) {
        int i2;
        if (this.mHeaderView != null) {
            if (rectF != null) {
                i2 = (int) rectF.top;
            } else {
                i2 = -1;
            }
            int max = Math.max(0, getHeaderTextBaseTop(i2));
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mHeaderView.getLayoutParams();
            marginLayoutParams.topMargin = this.mHeaderView.getResources().getDimensionPixelOffset(R$dimen.remaster_viewer_header_top_margin) + max;
            this.mHeaderView.setLayoutParams(marginLayoutParams);
            updateHeaderTextView(this.mHeaderView);
        }
    }

    public abstract void updateTableModeBG(boolean z);

    public abstract void updateVerticalLine(RectF rectF, boolean z);

    public abstract void updateViewContainerLayout(View view, boolean z, boolean z3);

    public int getHeaderTextBaseTop(int i2) {
        return i2;
    }

    public void prepareFocusRoiAnim(RectF rectF) {
    }
}
