package com.samsung.android.gallery.widget.details;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.core.widget.NestedScrollView;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.trace.Trace;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.R$layout;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.Optional;
import java.util.function.BooleanSupplier;
import l4.b;
import r6.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DetailsView extends FrameLayout {
    private final float LARGE_SCREEN_WIDTH_RATIO;
    private final String TAG;
    private View mBasicInfoView;
    private View mCamInfoView;
    private Button mEditButton;
    private LinearLayout mInfoItemsContainer;
    private LinearLayout mItemsContainer;
    private boolean mLocationDim;
    private NestedScrollView mScrollView;
    private final BooleanSupplier mSupportLargeScreenHorizontalGui;

    public DetailsView(Context context, BooleanSupplier booleanSupplier) {
        this(context, (AttributeSet) null, booleanSupplier);
    }

    private void bind(Context context) {
        Trace.beginSection(this.TAG + " bind");
        View inflate = LayoutInflater.from(context).inflate(getLayoutId(), this);
        this.mScrollView = (NestedScrollView) inflate.findViewById(R$id.details_scrollview);
        this.mItemsContainer = (LinearLayout) inflate.findViewById(R$id.details_items_container);
        this.mInfoItemsContainer = (LinearLayout) inflate.findViewById(R$id.details_info_items_container);
        this.mCamInfoView = inflate.findViewById(R$id.moreinfo_camerainfo_container);
        this.mBasicInfoView = inflate.findViewById(R$id.moreinfo_basic_info_simple);
        Button button = (Button) inflate.findViewById(R$id.moreinfo_item_edit_btn);
        this.mEditButton = button;
        SeApiCompat.setButtonShapeEnabled(button);
        Optional.ofNullable(this.mEditButton).ifPresent(new e(4));
        setZ(-1.0f);
        Trace.endSection();
    }

    private int getLayoutId() {
        return R$layout.details_view_layout;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$setImportantForAccessibility$1(boolean z, LinearLayout linearLayout) {
        int i2;
        if (z) {
            i2 = 4;
        } else {
            i2 = 0;
        }
        linearLayout.setImportantForAccessibility(i2);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$setImportantForAccessibility$2(boolean z, Button button) {
        int i2;
        if (z) {
            i2 = 4;
        } else {
            i2 = 0;
        }
        button.setImportantForAccessibility(i2);
    }

    public boolean getLocationDim() {
        return this.mLocationDim;
    }

    public NestedScrollView getScrollView() {
        return this.mScrollView;
    }

    public boolean onStartNestedScroll(View view, View view2, int i2) {
        if ((i2 & 1) != 0) {
            return true;
        }
        return false;
    }

    public void onViewDestroy() {
        ViewUtils.removeSelf(this);
        setScrollViewScrollChangeListener((View.OnScrollChangeListener) null);
    }

    public void onViewRecycled() {
        resetScrollPos();
    }

    public void resetScrollPos() {
        this.mScrollView.setScrollY(0);
    }

    public void setEditButtonVisibility(boolean z) {
        ViewUtils.setVisibleOrGone(this.mEditButton, z);
    }

    public void setEditClickListener(View.OnClickListener onClickListener) {
        ViewUtils.setOnClickListener(this.mEditButton, onClickListener);
    }

    public void setImportantForAccessibility(boolean z) {
        Optional.ofNullable(this.mItemsContainer).ifPresent(new b(z, 9));
        Optional.ofNullable(this.mEditButton).ifPresent(new b(z, 10));
    }

    public void setLocationViewDim(boolean z) {
        this.mLocationDim = z;
    }

    public void setScrollViewScrollChangeListener(View.OnScrollChangeListener onScrollChangeListener) {
        NestedScrollView nestedScrollView = this.mScrollView;
        if (nestedScrollView != null) {
            nestedScrollView.setOnScrollChangeListener(onScrollChangeListener);
        }
    }

    public boolean supportLargeScreenHorizontalGui() {
        return this.mSupportLargeScreenHorizontalGui.getAsBoolean();
    }

    public void updateLayout() {
        int i2;
        boolean supportLargeScreenHorizontalGui = supportLargeScreenHorizontalGui();
        this.mInfoItemsContainer.setOrientation(supportLargeScreenHorizontalGui ^ true ? 1 : 0);
        ViewMarginUtils.setTopPadding(this.mItemsContainer, ResourceCompat.getDimensionPixelOffset(getContext(), R$dimen.details_list_top_margin, 0));
        if (supportLargeScreenHorizontalGui) {
            i2 = (int) ((((float) DeviceInfo.getDisplayWidth(getContext())) * 0.13999999f) / 2.0f);
        } else {
            i2 = ResourceCompat.getDimensionPixelOffset(getContext(), R$dimen.details_list_horizontal_margin, 0);
        }
        ViewMarginUtils.setStartPadding(this.mItemsContainer, i2);
        ViewMarginUtils.setEndPadding(this.mItemsContainer, i2);
        if (supportLargeScreenHorizontalGui) {
            int dimensionPixelOffset = ResourceCompat.getDimensionPixelOffset(getContext(), R$dimen.details_info_items_gap, 0);
            ViewMarginUtils.setEndMargin(this.mBasicInfoView, dimensionPixelOffset);
            ViewMarginUtils.setStartMargin(this.mCamInfoView, dimensionPixelOffset);
        } else {
            ViewMarginUtils.setEndMargin(this.mBasicInfoView, 0);
            ViewMarginUtils.setStartMargin(this.mCamInfoView, 0);
        }
        ViewMarginUtils.setTopMargin(this.mEditButton, ResourceCompat.getDimensionPixelOffset(getContext(), R$dimen.details_edit_button_top_margin, 0));
        ViewMarginUtils.setEndMargin(this.mEditButton, ResourceCompat.getDimensionPixelOffset(getContext(), R$dimen.details_edit_button_end_margin, 0));
    }

    public DetailsView(Context context, AttributeSet attributeSet, BooleanSupplier booleanSupplier) {
        super(context, attributeSet);
        this.TAG = getClass().getSimpleName();
        this.LARGE_SCREEN_WIDTH_RATIO = 0.86f;
        bind(context);
        this.mSupportLargeScreenHorizontalGui = booleanSupplier;
    }
}
