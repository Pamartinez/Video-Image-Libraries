package com.samsung.android.gallery.widget.toolbar;

import S1.e;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.appbar.g;
import com.google.android.material.appbar.model.AppBarModel;
import com.samsung.android.gallery.module.utils.AppbarInfo;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.R$styleable;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GalleryAppBarLayout extends AppBarLayout implements e {
    protected final String TAG = getClass().getSimpleName();
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private Integer mDensity;
    private boolean mIsMultiplePickMode;
    private boolean mIsPickMode;
    private Integer mResolution;
    private boolean mScrollEnabled = true;
    private float mSeslHeightProportion;
    private boolean mSeslUseCustomHeight;
    private int mTargetViewHash;
    private int mTopOffset = 0;
    private boolean mUseCustomHeightRatio;

    public GalleryAppBarLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setAttr(context, attributeSet);
        addOnOffsetChangedListener((e) this);
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

    private int getAppbarHeight() {
        int i2;
        Context baseContext = getBaseContext();
        int appbarHeight = AppbarInfo.getAppbarHeight(baseContext);
        if (getPaddingBottom() <= 0) {
            i2 = AppbarInfo.getAppbarDefaultBottomPadding(baseContext);
        } else {
            i2 = 0;
        }
        return appbarHeight - i2;
    }

    private boolean getBooleanFromTypedArray(TypedArray typedArray, int i2) {
        try {
            return typedArray.getBoolean(i2, false);
        } catch (ArrayIndexOutOfBoundsException unused) {
            return false;
        }
    }

    private float getFloatFromTypedArray(TypedArray typedArray, int i2) {
        try {
            return typedArray.getFloat(i2, 0.0f);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException unused) {
            return 0.0f;
        }
    }

    private int getSmartAlbumMinHeight(View view) {
        View view2;
        int measuredTextViewHeight;
        if (view != null) {
            view2 = view.findViewById(R$id.smart_album_title);
        } else {
            view2 = null;
        }
        if (view2 == null || view2.getHeight() >= (measuredTextViewHeight = getMeasuredTextViewHeight((TextView) view2, getResources().getDimensionPixelSize(R$dimen.smart_album_item_title_text_size)))) {
            return -1;
        }
        View findViewById = view.findViewById(R$id.items_layout);
        View findViewById2 = view.findViewById(R$id.smart_album_image_layout);
        if (findViewById == null || findViewById2 == null) {
            return -1;
        }
        int height = findViewById2.getHeight() + (view.getHeight() - findViewById.getHeight()) + measuredTextViewHeight;
        return getPaddingBottom() + getResources().getDimensionPixelSize(R$dimen.smart_album_item_title_marginTop) + height;
    }

    private String getStringFromTypedArray(TypedArray typedArray, int i2) {
        try {
            return typedArray.getString(i2);
        } catch (ArrayIndexOutOfBoundsException unused) {
            return null;
        }
    }

    private void onConfigurationForPickMode() {
        int i2;
        Resources resources = getResources();
        if (PickerUtil.supportSearch()) {
            i2 = R$dimen.smart_album_layout_height_in_picker;
        } else {
            i2 = R$dimen.smart_album_layout_height_in_picker_without_search;
        }
        seslSetCollapsedHeight((float) resources.getDimensionPixelOffset(i2));
        if (this.mIsMultiplePickMode) {
            this.mTopOffset = getResources().getDimensionPixelOffset(PickerUtil.getClipboardHeightDimenRes());
        }
    }

    private void setCustomHeight(int i2) {
        if (this.mUseCustomHeightRatio) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
            marginLayoutParams.height = Math.max(i2, getAppbarHeight());
            setLayoutParams(marginLayoutParams);
            String str = this.TAG;
            Log.d(str, "Appbar{" + getAppbarHeight() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + i2 + "}");
        }
    }

    public boolean canExpandAppbar() {
        if (!this.mSeslUseCustomHeight || this.mSeslHeightProportion != 0.0f) {
            return true;
        }
        return false;
    }

    public void clearSuggestAppbarModel() {
        this.mCollapsingToolbarLayout.d((AppBarModel) null);
        this.mCollapsingToolbarLayout.requestLayout();
    }

    public void disableAppbarScroll() {
        setCustomHeightProportion(false, DeviceInfo.getDisplayHeight(getBaseContext()));
        setExpanded(false, false);
        setTitleEnabled(false);
    }

    public void dispatchConfigurationChanged(boolean z, Configuration configuration) {
        if (z && compareConfiguration(configuration) != 0) {
            dispatchConfigurationChanged(configuration);
        }
    }

    public final Context getBaseContext() {
        Context context = getContext();
        if (context instanceof ContextThemeWrapper) {
            return ((ContextThemeWrapper) context).getBaseContext();
        }
        return context;
    }

    public int getMeasuredTextViewHeight(TextView textView, int i2) {
        textView.setTextSize(0, (float) i2);
        textView.measure(View.MeasureSpec.makeMeasureSpec(getWidth(), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(0, 0));
        return textView.getMeasuredHeight();
    }

    public CharSequence getSubTitle() {
        CollapsingToolbarLayout collapsingToolbarLayout = this.mCollapsingToolbarLayout;
        if (collapsingToolbarLayout != null) {
            return collapsingToolbarLayout.getSubTitle();
        }
        return null;
    }

    public CharSequence getTitle() {
        CollapsingToolbarLayout collapsingToolbarLayout = this.mCollapsingToolbarLayout;
        if (collapsingToolbarLayout != null) {
            return collapsingToolbarLayout.getTitle();
        }
        return null;
    }

    public int getVisibleHeight() {
        return getBottom() - (getHeight() - getTotalScrollRange());
    }

    public boolean isLandScape(Context context) {
        if (context == null || context.getResources().getConfiguration().orientation != 2) {
            return false;
        }
        return true;
    }

    public boolean isPartiallyVisible() {
        if ((-getTotalScrollRange()) < getTop() - this.mTopOffset) {
            return true;
        }
        return false;
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        setCustomHeight(0);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.mIsPickMode) {
            onConfigurationForPickMode();
        }
        this.mDensity = Integer.valueOf(configuration.densityDpi);
        this.mResolution = Integer.valueOf(configuration.screenWidthDp * configuration.smallestScreenWidthDp);
        setCustomHeight(0);
        if (!this.mScrollEnabled) {
            setExpanded(false, false);
        }
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R$id.toolbar_layout);
    }

    public void onMultiWindowModeChanged(boolean z) {
        if (z) {
            setExpanded(false, false);
        }
    }

    public void onOffsetChanged(AppBarLayout appBarLayout, int i2) {
        int i7;
        int smartAlbumMinHeight;
        View findViewById = findViewById(R$id.smart_album_layout);
        if (findViewById != null) {
            if (isPartiallyVisible()) {
                i7 = 1;
            } else {
                i7 = 2;
            }
            if (i7 != findViewById.getImportantForAccessibility()) {
                findViewById.setImportantForAccessibility(i7);
            }
            if (this.mUseCustomHeightRatio && (smartAlbumMinHeight = getSmartAlbumMinHeight(findViewById)) > getHeight()) {
                setCustomHeight(smartAlbumMinHeight);
            }
        }
    }

    public void setAttr(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.GalleryAppBarLayout);
            TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, R$styleable.AppBarLayout);
            this.mSeslHeightProportion = getFloatFromTypedArray(obtainStyledAttributes2, R$styleable.AppBarLayout_seslHeightProportion);
            this.mSeslUseCustomHeight = getBooleanFromTypedArray(obtainStyledAttributes2, R$styleable.AppBarLayout_seslUseCustomHeight);
            this.mUseCustomHeightRatio = getBooleanFromTypedArray(obtainStyledAttributes, R$styleable.GalleryAppBarLayout_customHeightRatio);
            if (isLandScape(context)) {
                setExpanded(false);
            } else if (getStringFromTypedArray(obtainStyledAttributes, R$styleable.GalleryAppBarLayout_customPreference) != null) {
                setExpanded(false);
            }
            obtainStyledAttributes.recycle();
            obtainStyledAttributes2.recycle();
        }
    }

    public void setBackgroundColor(int i2) {
        super.setBackgroundColor(i2);
        CollapsingToolbarLayout collapsingToolbarLayout = this.mCollapsingToolbarLayout;
        if (collapsingToolbarLayout != null) {
            collapsingToolbarLayout.setBackgroundColor(i2);
        }
    }

    public void setCollapsedHeightInPickMode(int i2) {
        this.mIsPickMode = true;
        seslSetCollapsedHeight((float) i2);
    }

    public void setCustomHeightProportion(boolean z, int i2) {
        float f;
        if (!z || i2 <= 0) {
            f = 0.0f;
        } else {
            f = ((float) getAppbarHeight()) / ((float) i2);
        }
        this.mSeslHeightProportion = f;
        this.mSeslUseCustomHeight = true;
        seslSetCustomHeightProportion(true, f);
    }

    public void setMultiplePickMode() {
        this.mIsMultiplePickMode = true;
    }

    public void setScrollEnable(final boolean z, View view) {
        if (this.mScrollEnabled != z || this.mTargetViewHash != view.hashCode()) {
            this.mScrollEnabled = z;
            this.mTargetViewHash = view.hashCode();
            view.setNestedScrollingEnabled(this.mScrollEnabled);
            setActivated(z);
            CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) getLayoutParams();
            AppBarLayout.Behavior behavior = (AppBarLayout.Behavior) layoutParams.getBehavior();
            if (behavior == null) {
                behavior = new AppBarLayout.Behavior();
                layoutParams.setBehavior(behavior);
            }
            behavior.u = new g() {
                public boolean canDrag(AppBarLayout appBarLayout) {
                    return z;
                }
            };
        }
    }

    public void setSubtitle(CharSequence charSequence) {
        CollapsingToolbarLayout collapsingToolbarLayout = this.mCollapsingToolbarLayout;
        if (collapsingToolbarLayout != null && !StringCompat.equals(charSequence, collapsingToolbarLayout.getSubTitle())) {
            this.mCollapsingToolbarLayout.c(charSequence);
        }
    }

    public void setSuggestAppbarModel(AppBarModel<?> appBarModel) {
        this.mCollapsingToolbarLayout.d(appBarModel);
        this.mCollapsingToolbarLayout.requestLayout();
    }

    public void setTitle(CharSequence charSequence) {
        CollapsingToolbarLayout collapsingToolbarLayout = this.mCollapsingToolbarLayout;
        if (collapsingToolbarLayout != null && !StringCompat.equals(charSequence, collapsingToolbarLayout.getTitle())) {
            this.mCollapsingToolbarLayout.setTitle(charSequence);
        }
    }

    public void setTitleEnabled(boolean z) {
        CollapsingToolbarLayout collapsingToolbarLayout = this.mCollapsingToolbarLayout;
        if (collapsingToolbarLayout != null) {
            collapsingToolbarLayout.setTitleEnabled(z);
        }
    }

    public void setTopOffset(int i2) {
        this.mTopOffset = i2;
    }

    public void updateAppbarScroll(View view, BooleanSupplier booleanSupplier) {
        if (this.mUseCustomHeightRatio) {
            boolean asBoolean = booleanSupplier.getAsBoolean();
            setScrollEnable(asBoolean, view);
            if (!asBoolean) {
                setExpanded(false, false);
            }
        }
    }

    public void setTitle(int i2) {
        CollapsingToolbarLayout collapsingToolbarLayout = this.mCollapsingToolbarLayout;
        if (collapsingToolbarLayout != null) {
            collapsingToolbarLayout.setTitle(getBaseContext().getText(i2));
        }
    }

    public void setAlphaOnCoverView(float f) {
    }

    public void setCoverView(View view) {
    }

    public void setOnCoverVisibilityChangeListener(Consumer<Boolean> consumer) {
    }
}
