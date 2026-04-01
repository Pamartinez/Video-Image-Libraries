package com.samsung.android.gallery.app.ui.container.tablet.drawertab;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.samsung.android.gallery.support.helper.DrawerUtil;
import com.samsung.android.gallery.widget.behavior.SheetBehaviorCompat;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DrawerTabLayoutManager extends LinearLayoutManager {
    private final int mCollapsedDividerPadding;
    private final int mCollapsedTabPaddingEnd;
    private int mDrawerState;
    private final int mExpandedDividerPaddingEnd;
    private final int mExpandedDividerPaddingStart;
    private final int mExpandedTabPaddingEnd;
    private boolean mIsDrawerOpened;

    public DrawerTabLayoutManager(Context context) {
        super(context, 1, false);
        int i2;
        Resources resources = context.getResources();
        if (DrawerUtil.isDrawerDefaultOpen(context)) {
            i2 = 3;
        } else {
            i2 = 4;
        }
        this.mDrawerState = i2;
        this.mExpandedDividerPaddingStart = resources.getDimensionPixelSize(R.dimen.drawer_tab_item_divider_padding_start);
        this.mExpandedDividerPaddingEnd = resources.getDimensionPixelSize(R.dimen.drawer_tab_item_divider_padding_end);
        this.mCollapsedDividerPadding = resources.getDimensionPixelSize(R.dimen.drawer_tab_item_divider_collapsed_padding_horizontal);
        this.mExpandedTabPaddingEnd = resources.getDimensionPixelSize(R.dimen.drawer_tab_item_expanded_padding_end);
        this.mCollapsedTabPaddingEnd = resources.getDimensionPixelSize(R.dimen.drawer_tab_item_container_padding_horizontal);
    }

    private void updateAlpha(View view) {
        if (SheetBehaviorCompat.isDragging(this.mDrawerState) && !this.mIsDrawerOpened) {
            view.setAlpha(0.0f);
        } else if (!SheetBehaviorCompat.isInTransition(this.mDrawerState)) {
            view.setAlpha(1.0f);
        }
    }

    private void updateForeground(View view) {
        if (SheetBehaviorCompat.isInTransition(this.mDrawerState)) {
            ViewUtils.setForeground(view.findViewById(R.id.drawer_tab_item), (Drawable) null);
        }
    }

    private void updatePadding(View view) {
        int i2;
        int i7;
        int i8;
        boolean isCollapsed = SheetBehaviorCompat.isCollapsed(this.mDrawerState);
        View findViewById = view.findViewById(R.id.drawer_tab_divider);
        if (findViewById != null) {
            if (isCollapsed) {
                i7 = this.mCollapsedDividerPadding;
            } else {
                i7 = this.mExpandedDividerPaddingStart;
            }
            if (isCollapsed) {
                i8 = this.mCollapsedDividerPadding;
            } else {
                i8 = this.mExpandedDividerPaddingEnd;
            }
            findViewById.setPaddingRelative(i7, findViewById.getPaddingTop(), i8, findViewById.getPaddingBottom());
        }
        View findViewById2 = view.findViewById(R.id.drawer_tab_item_container);
        if (findViewById2 != null) {
            if (isCollapsed) {
                i2 = this.mCollapsedTabPaddingEnd;
            } else {
                i2 = this.mExpandedTabPaddingEnd;
            }
            findViewById2.setPaddingRelative(findViewById2.getPaddingStart(), findViewById2.getPaddingTop(), i2, findViewById2.getPaddingBottom());
        }
    }

    private void updateVisibility(View view) {
        boolean isCollapsed = SheetBehaviorCompat.isCollapsed(this.mDrawerState);
        ViewUtils.setVisibleOrGone(view.findViewById(R.id.drawer_tab_title_container), !isCollapsed);
        ViewUtils.setVisibleOrGone(view.findViewById(R.id.drawer_tab_end_layout), !isCollapsed);
        ViewUtils.setVisibleOrGone(view.findViewById(R.id.dynamic_focus_view), SheetBehaviorCompat.isInTransition(this.mDrawerState));
    }

    public void addView(View view) {
        super.addView(view);
        updateAlpha(view);
        updatePadding(view);
        updateVisibility(view);
        updateForeground(view);
    }

    public void setDrawerOpened(boolean z) {
        this.mIsDrawerOpened = z;
    }

    public void setState(int i2) {
        this.mDrawerState = i2;
    }
}
