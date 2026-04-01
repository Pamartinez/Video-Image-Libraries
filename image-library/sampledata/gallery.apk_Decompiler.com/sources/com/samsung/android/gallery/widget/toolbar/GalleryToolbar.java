package com.samsung.android.gallery.widget.toolbar;

import W8.C0579a;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.DisplayCutout;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageButton;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.R$color;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.Optional;
import java.util.function.BooleanSupplier;
import p7.d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GalleryToolbar extends Toolbar {
    private Integer mDensity;
    private boolean mIsSelectionMode = false;
    private boolean mIsSelfSelectCountMode = false;
    private BooleanSupplier mIsTouchBlocked;
    private Drawable mNavigationIcon;
    private View.OnClickListener mOnClickListener;
    private OnOverflowMenuShownListener mOverflowMenuShowingListener;
    private Integer mResolution;
    private SelectInfoView mSelectInfoView;
    private CharSequence mSubTitle;
    private boolean mSupportNaviBackInSelectionMode = false;
    private CharSequence mTitle;
    private boolean mWindowFocused;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnConfigChangedListener {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnOverflowMenuShownListener {
    }

    public GalleryToolbar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mSelectInfoView = new SelectInfoView(context, attributeSet);
    }

    private void backupData() {
        this.mTitle = super.getTitle();
        this.mSubTitle = super.getSubtitle();
        super.setTitle((CharSequence) null);
        super.setSubtitle((CharSequence) null);
        if (!this.mSupportNaviBackInSelectionMode) {
            this.mNavigationIcon = super.getNavigationIcon();
            super.setNavigationIcon((Drawable) null);
        }
    }

    private int compareConfiguration(Configuration configuration) {
        Integer num = this.mDensity;
        if (num == null || num.intValue() != configuration.densityDpi) {
            return 1;
        }
        Integer num2 = this.mResolution;
        if (num2 == null || num2.intValue() != configuration.screenWidthDp * configuration.smallestScreenWidthDp) {
            return -1;
        }
        return 0;
    }

    public static GalleryToolbar findActivityToolbar(View view) {
        GalleryToolbar galleryToolbar = (GalleryToolbar) ((Activity) view.getContext()).findViewById(R$id.activity_toolbar);
        if (galleryToolbar != null) {
            return galleryToolbar;
        }
        Optional.ofNullable((ViewStub) view.findViewById(R$id.appbar_container)).ifPresent(new C0579a(4));
        return (GalleryToolbar) view.findViewById(R$id.toolbar);
    }

    private void restoreData() {
        super.setTitle(this.mTitle);
        super.setSubtitle(this.mSubTitle);
        this.mTitle = null;
        this.mSubTitle = null;
        if (!this.mSupportNaviBackInSelectionMode) {
            super.setNavigationIcon(this.mNavigationIcon);
            this.mNavigationIcon = null;
        }
    }

    private void setContentInset(boolean z) {
        Resources resources;
        int i2;
        if (this.mIsSelectionMode || z) {
            resources = getResources();
            i2 = R$dimen.action_bar_contents_start_inset_for_selection;
        } else {
            resources = getResources();
            i2 = R$dimen.action_bar_contents_start_inset;
        }
        setContentInsetsRelative(resources.getDimensionPixelSize(i2), 0);
    }

    private void setOnSelectAllListener(OnSelectAllListener onSelectAllListener) {
        this.mSelectInfoView.setOnSelectAllListener(onSelectAllListener);
    }

    public void applyDisplayCutOutPadding(DisplayCutout displayCutout, boolean z) {
        boolean z3;
        if (displayCutout == null || displayCutout.getBoundingRects().isEmpty()) {
            setPadding(0, getPaddingTop(), 0, getPaddingBottom());
            return;
        }
        Rect rect = displayCutout.getBoundingRects().get(0);
        boolean z7 = true;
        if (rect.left != 0 || rect.width() <= 0) {
            z3 = false;
        } else {
            z3 = true;
        }
        if (!z && (rect.top >= 10 || rect.height() <= 0)) {
            z7 = false;
        }
        if (z3 && z7) {
            setPadding(displayCutout.getSafeInsetLeft(), getPaddingTop(), 0, getPaddingBottom());
        } else if (z3 || !z7) {
            setPadding(0, getPaddingTop(), 0, getPaddingBottom());
        } else {
            setPadding(0, getPaddingTop(), displayCutout.getSafeInsetRight(), getPaddingBottom());
        }
    }

    public void disableLayoutTransition() {
        this.mSelectInfoView.disableLayoutTransition();
    }

    public void dispatchConfigurationChanged(boolean z, Configuration configuration) {
        if (z && compareConfiguration(configuration) != 0) {
            Log.d("GalleryToolbar", "dispatchConfigurationChanged (self)");
            dispatchConfigurationChanged(configuration);
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        BooleanSupplier booleanSupplier = this.mIsTouchBlocked;
        if (booleanSupplier == null || !booleanSupplier.getAsBoolean()) {
            return super.dispatchTouchEvent(motionEvent);
        }
        Log.d("GalleryToolbar", "dispatchTouchEvent blocked");
        return true;
    }

    public void enterPickerMode(Blackboard blackboard, String str) {
        if (!this.mIsSelectionMode && PickerUtil.isMultiplePickLaunchMode(blackboard)) {
            this.mSelectInfoView.initView();
            this.mSelectInfoView.setPickerMode(str);
            backupData();
            addView(this.mSelectInfoView, 0);
            this.mIsSelectionMode = true;
        }
    }

    public void enterSelectionMode(OnSelectAllListener onSelectAllListener, int i2, boolean z, boolean z3) {
        if (!this.mIsSelectionMode) {
            this.mSelectInfoView.initView();
            this.mSelectInfoView.setSelectedInfoTitle(i2);
            this.mSupportNaviBackInSelectionMode = z3;
            backupData();
            addView(this.mSelectInfoView, 0);
            this.mIsSelectionMode = true;
            setContentInset(z);
            setOnSelectAllListener(onSelectAllListener);
        }
    }

    public void enterSelfSelectCountMode(View view, int i2) {
        if (!this.mIsSelfSelectCountMode) {
            Log.d("GalleryToolbar", "enterSelfSelectCountMode");
            this.mIsSelfSelectCountMode = true;
            addView(view, 0, new Toolbar.LayoutParams(-2, i2));
            setContentInset();
        }
    }

    public void exitSelectionMode(boolean z) {
        if (this.mIsSelectionMode) {
            this.mSupportNaviBackInSelectionMode = z;
            restoreData();
            this.mSelectInfoView.exitSelectionMode();
            ViewUtils.removeView(this, this.mSelectInfoView);
            this.mIsSelectionMode = false;
            setContentInset();
            setOnSelectAllListener((OnSelectAllListener) null);
        }
    }

    public void exitSelfSelectCountMode(View view) {
        if (this.mIsSelfSelectCountMode) {
            Log.d("GalleryToolbar", "exitSelfSelectCountMode");
            removeView(view);
            this.mIsSelfSelectCountMode = false;
            setContentInset();
        }
    }

    public View getNaviUpButton() {
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if (childAt instanceof ImageButton) {
                return childAt;
            }
        }
        return null;
    }

    public CharSequence getSelectedCountString(int i2, int i7) {
        return this.mSelectInfoView.getSelectCountString(i2, i7);
    }

    public void handleDensityChanged() {
        this.mSelectInfoView.resetView();
    }

    public void onAppbarVisibleRatio(float f) {
        this.mSelectInfoView.onAppbarVisibleRatio(f);
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mWindowFocused = hasWindowFocus();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mDensity = Integer.valueOf(configuration.densityDpi);
        this.mResolution = Integer.valueOf(configuration.screenWidthDp * configuration.smallestScreenWidthDp);
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (this.mWindowFocused != z) {
            this.mWindowFocused = z;
            if (this.mOverflowMenuShowingListener != null && isOverflowMenuShowing()) {
                ((d) this.mOverflowMenuShowingListener).d.lambda$setToolbarOverflowShowingListener$9();
            }
        }
    }

    public void removeOnClickListener(View.OnClickListener onClickListener) {
        if (this.mOnClickListener == onClickListener) {
            setNavigationOnClickListener((View.OnClickListener) null);
        }
    }

    public void setAlpha(float f) {
        super.setAlpha(f);
        SelectInfoView selectInfoView = this.mSelectInfoView;
        if (selectInfoView != null) {
            selectInfoView.setAlpha(f);
        }
    }

    public void setBackgroundColor(Context context, boolean z) {
        int i2;
        if (z) {
            i2 = R$color.actionbar_fw_bg_color;
        } else {
            i2 = R$color.actionbar_bg_color;
        }
        setBackgroundColor(context.getColor(i2));
    }

    public void setCheckBoxEnabled(boolean z) {
        SelectInfoView selectInfoView = this.mSelectInfoView;
        if (selectInfoView != null) {
            selectInfoView.setCheckBoxEnabled(z);
        }
    }

    public void setCheckBoxVisible(boolean z) {
        SelectInfoView selectInfoView = this.mSelectInfoView;
        if (selectInfoView != null) {
            selectInfoView.setCheckBoxVisible(z);
        }
    }

    public void setCheckSelectAll(boolean z) {
        this.mSelectInfoView.setCheckSelectAll(z);
    }

    public void setFitsSystemWindows(boolean z) {
        ((View) getParent()).setFitsSystemWindows(z);
        if (!z) {
            ((View) getParent()).setPadding(0, 0, 0, 0);
        }
    }

    public void setFloatingMode(boolean z) {
        this.mSelectInfoView.setFloatingMode(z);
    }

    public void setNavigationContentDescription(int i2) {
        if (!Features.isEnabled(Features.IS_GED)) {
            super.setNavigationContentDescription(i2);
        }
    }

    public void setNavigationOnClickListener(View.OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
        super.setNavigationOnClickListener(onClickListener);
    }

    public void setOverflowMenuShowingListener(OnOverflowMenuShownListener onOverflowMenuShownListener) {
        this.mOverflowMenuShowingListener = onOverflowMenuShownListener;
    }

    public void setSelectedCountInfo(int i2, int i7, int i8) {
        this.mSelectInfoView.setSelectedCountInfo(i2, i7, i8);
    }

    public void setSelectedSizeInfo(long j2) {
        this.mSelectInfoView.setSelectedSizeInfo(j2);
    }

    public void setSubtitle(CharSequence charSequence) {
        if (this.mIsSelectionMode) {
            this.mSubTitle = charSequence;
        } else {
            super.setSubtitle(charSequence);
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (!this.mIsSelectionMode) {
            super.setTitle(charSequence);
        } else {
            this.mTitle = charSequence;
        }
    }

    public void setTitleTextColor(int i2) {
        SelectInfoView selectInfoView;
        if (this.mIsSelectionMode && (selectInfoView = this.mSelectInfoView) != null) {
            selectInfoView.updateTextColor();
            if (getNavigationIcon() != null) {
                getNavigationIcon().setTint(i2);
            }
        }
        super.setTitleTextColor(i2);
    }

    public void setTouchBlocker(BooleanSupplier booleanSupplier) {
        this.mIsTouchBlocked = booleanSupplier;
    }

    public void showSelectAll(boolean z) {
        if (z) {
            this.mSelectInfoView.showSelectAll();
        } else {
            this.mSelectInfoView.hideSelectAll();
        }
    }

    public void setSelectedCountInfo(int i2, int i7, int i8, int i10) {
        this.mSelectInfoView.setSelectedCountInfo(i2, i7, i8, i10);
    }

    public void setContentInset() {
        Resources resources;
        int i2;
        if (this.mIsSelectionMode || this.mIsSelfSelectCountMode) {
            resources = getResources();
            i2 = R$dimen.action_bar_contents_start_inset_for_selection;
        } else {
            resources = getResources();
            i2 = R$dimen.action_bar_contents_start_inset;
        }
        setContentInsetsRelative(resources.getDimensionPixelSize(i2), 0);
    }

    public void setSelectedCountInfo(int i2) {
        this.mSelectInfoView.setSelectedCountInfo(i2);
    }

    public void setSubtitle(int i2) {
        if (this.mIsSelectionMode) {
            this.mSubTitle = getResources().getString(i2);
        } else {
            super.setSubtitle(i2);
        }
    }

    public void enterPickerMode(Blackboard blackboard, String str, boolean z) {
        this.mSupportNaviBackInSelectionMode = z;
        enterPickerMode(blackboard, str);
    }

    public void setOnConfigChangedListener(OnConfigChangedListener onConfigChangedListener) {
    }
}
