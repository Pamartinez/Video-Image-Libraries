package com.samsung.android.gallery.app.ui.container.tablet.drawertab;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.helper.DrawerUtil;
import com.samsung.android.gallery.support.trace.Trace;
import com.samsung.android.gallery.widget.behavior.SheetBehaviorCompat;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import l4.b;
import n5.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DrawerFakeViewManager {
    private int mDrawerState;
    private final LinearLayout mFakeView;
    private final AtomicBoolean mIsAnimating = new AtomicBoolean(false);
    private boolean mIsSelectionMode;
    private final List<DrawerTabItem> mItemData;
    private final DrawerTabView mRefView;

    public DrawerFakeViewManager(DrawerTabView drawerTabView, Context context) {
        int i2;
        this.mRefView = drawerTabView;
        LinearLayout linearLayout = new LinearLayout(context);
        this.mFakeView = linearLayout;
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-2, -1));
        this.mItemData = (List) Optional.ofNullable(drawerTabView.getAdapter()).map(new e(12)).orElse((Object) null);
        if (DrawerUtil.isDrawerDefaultOpen(context)) {
            i2 = 3;
        } else {
            i2 = 4;
        }
        this.mDrawerState = i2;
    }

    private void enableFakeView(boolean z) {
        ViewUtils.setVisibleOrGone(this.mFakeView, z);
        Optional.ofNullable(this.mRefView.getListView()).ifPresent(new b(z, 7));
    }

    private boolean isAlbumsTab(DrawerTabItem drawerTabItem) {
        if (drawerTabItem == null) {
            return false;
        }
        if (drawerTabItem.isAlbums() || drawerTabItem.isAllAlbums()) {
            return true;
        }
        return false;
    }

    private boolean isAlbumsTabExpanded() {
        List<DrawerTabItem> list = this.mItemData;
        if (list == null) {
            return false;
        }
        for (DrawerTabItem next : list) {
            if (isAlbumsTab(next)) {
                return next.isExpanded();
            }
        }
        return false;
    }

    private boolean isFocused(DrawerTabItem drawerTabItem) {
        if (drawerTabItem.isSelectFocused() || needAlbumsTabFocus(drawerTabItem)) {
            return true;
        }
        return false;
    }

    private boolean isListViewScrolled() {
        if (this.mRefView.getListView() == null || this.mRefView.getListView().computeVerticalScrollOffset() == 0) {
            return false;
        }
        return true;
    }

    private boolean needAlbumsTabFocus(DrawerTabItem drawerTabItem) {
        if (!isAlbumsTab(drawerTabItem) || !LocationKey.isChildOfAlbums((String) Optional.ofNullable(this.mRefView.getAdapter()).map(new e(13)).orElse((Object) null))) {
            return false;
        }
        return true;
    }

    private void updateDivider(View view, DrawerTabItem drawerTabItem) {
        boolean z;
        View findViewById = view.findViewById(R.id.drawer_tab_divider);
        if (drawerTabItem.isCollection() || drawerTabItem.isEnableDivider()) {
            z = true;
        } else {
            z = false;
        }
        ViewUtils.setVisibleOrGone(findViewById, z);
    }

    private void updateDividerAlpha(View view, float f) {
        ViewUtils.setAlpha(view.findViewById(R.id.drawer_tab_divider), f);
    }

    private void updateFocus(View view, boolean z) {
        Drawable drawable = view.getContext().getDrawable(R.drawable.drawer_focused_item_foreground);
        if (drawable != null) {
            drawable.setAlpha(12);
        }
        View findViewById = view.findViewById(R.id.drawer_tab_item);
        if (!z) {
            drawable = null;
        }
        ViewUtils.setForeground(findViewById, drawable);
    }

    private void updateFocusAlpha(View view, float f) {
        View findViewById = view.findViewById(R.id.drawer_tab_item);
        if (findViewById != null && findViewById.getForeground() != null) {
            findViewById.getForeground().setAlpha((int) (f * 12.0f));
        }
    }

    private void updateIcon(View view, DrawerTabItem drawerTabItem) {
        ImageView imageView = (ImageView) view.findViewById(R.id.drawer_tab_icon);
        if (imageView != null) {
            if (imageView.getDrawable() == null) {
                imageView.setImageDrawable(view.getContext().getDrawable(drawerTabItem.getIconResId()));
                if (drawerTabItem.isVideoStudio()) {
                    imageView.setImageTintList((ColorStateList) null);
                }
            }
            imageView.setSelected(isFocused(drawerTabItem));
        }
    }

    private void updateIconAlpha(View view, float f) {
        ViewUtils.setAlpha(view.findViewById(R.id.drawer_tab_icon), f);
    }

    private void updateNewBadge(View view, boolean z) {
        ViewUtils.setVisibleOrGone(view.findViewById(R.id.collapsed_drawer_tab_new_badge), z);
    }

    private void updateNewBadgeAlpha(View view, float f) {
        ViewUtils.setAlpha(view.findViewById(R.id.collapsed_drawer_tab_new_badge), f);
    }

    public void createFakeView() {
        if (this.mItemData != null && this.mRefView.getListView() != null) {
            this.mFakeView.removeAllViews();
            Trace.beginSection("createFakeView");
            LayoutInflater from = LayoutInflater.from(this.mFakeView.getContext());
            for (DrawerTabItem next : this.mItemData) {
                if (next != null) {
                    View inflate = from.inflate(R.layout.recycler_item_collapsed_drawer_tab_layout, this.mFakeView, false);
                    updateIcon(inflate, next);
                    updateDivider(inflate, next);
                    this.mFakeView.addView(inflate);
                }
            }
            Trace.endSection();
            ViewUtils.removeSelf(this.mFakeView);
            ((ViewGroup) this.mRefView.getListView().getParent()).addView(this.mFakeView);
            enableFakeView(false);
        }
    }

    public void onCompleteAnimation(Runnable runnable) {
        if (!SheetBehaviorCompat.isInTransition(this.mDrawerState)) {
            enableFakeView(false);
            this.mIsAnimating.set(false);
            runnable.run();
        }
    }

    public void onPrepareAnimation(boolean z, boolean z3) {
        float f;
        float f5;
        float f8;
        float f10;
        boolean z7 = true;
        if (!this.mIsAnimating.getAndSet(true)) {
            this.mIsSelectionMode = z3;
            enableFakeView(true);
            if (!isListViewScrolled() && !isAlbumsTabExpanded() && !z3) {
                z7 = false;
            }
            List<DrawerTabItem> list = this.mItemData;
            if (list != null && list.size() == this.mFakeView.getChildCount()) {
                for (int i2 = 0; i2 < this.mFakeView.getChildCount(); i2++) {
                    View childAt = this.mFakeView.getChildAt(i2);
                    DrawerTabItem drawerTabItem = this.mItemData.get(i2);
                    float f11 = 0.0f;
                    if (!z7 || !z) {
                        f = 1.0f;
                    } else {
                        f = 0.0f;
                    }
                    ViewUtils.setAlpha(childAt, f);
                    if (!(childAt == null || drawerTabItem == null)) {
                        updateIcon(childAt, drawerTabItem);
                        if (drawerTabItem.isEnabled()) {
                            f5 = 1.0f;
                        } else {
                            f5 = 0.4f;
                        }
                        updateIconAlpha(childAt, f5);
                        if (z) {
                            f8 = 0.0f;
                        } else {
                            f8 = 1.0f;
                        }
                        updateDividerAlpha(childAt, f8);
                        if (z) {
                            f10 = 0.0f;
                        } else {
                            f10 = 1.0f;
                        }
                        updateNewBadgeAlpha(childAt, f10);
                        if (!z) {
                            f11 = 1.0f;
                        }
                        updateFocusAlpha(childAt, f11);
                    }
                }
            }
            this.mRefView.updateIconVisibility(z7);
        }
    }

    public void onSlide(float f) {
        float f5;
        float f8;
        float f10;
        boolean isListViewScrolled = isListViewScrolled();
        boolean isAlbumsTabExpanded = isAlbumsTabExpanded();
        float f11 = 1.0f - f;
        for (int i2 = 0; i2 < this.mFakeView.getChildCount(); i2++) {
            View childAt = this.mFakeView.getChildAt(i2);
            if (isListViewScrolled || isAlbumsTabExpanded || this.mIsSelectionMode) {
                ViewUtils.setAlpha(childAt, f11);
            }
            if (isListViewScrolled) {
                f5 = 1.0f;
            } else {
                f5 = f11;
            }
            updateDividerAlpha(childAt, f5);
            if (isListViewScrolled) {
                f8 = 1.0f;
            } else {
                f8 = f11;
            }
            updateNewBadgeAlpha(childAt, f8);
            if (isListViewScrolled) {
                f10 = 1.0f;
            } else {
                f10 = f11;
            }
            updateFocusAlpha(childAt, f10);
        }
    }

    public void setState(int i2) {
        this.mDrawerState = i2;
    }

    public void updateDeco() {
        boolean z;
        List<DrawerTabItem> list = this.mItemData;
        if (list != null) {
            if (list.size() == this.mFakeView.getChildCount()) {
                for (int i2 = 0; i2 < this.mItemData.size(); i2++) {
                    View childAt = this.mFakeView.getChildAt(i2);
                    DrawerTabItem drawerTabItem = this.mItemData.get(i2);
                    if (!(childAt == null || drawerTabItem == null)) {
                        if (!drawerTabItem.showNewBadge() || drawerTabItem.isSelectFocused()) {
                            z = false;
                        } else {
                            z = true;
                        }
                        updateNewBadge(childAt, z);
                        updateFocus(childAt, isFocused(drawerTabItem));
                    }
                }
                return;
            }
            createFakeView();
        }
    }
}
