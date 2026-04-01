package com.samsung.android.gallery.app.ui.list.abstraction;

import A2.d;
import A4.C0367b;
import A4.C0368c;
import A4.C0369d;
import androidx.fragment.app.Fragment;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BaseListBottomHandler {
    private final CharSequence TAG;
    private int mBottomBarHeight = 0;
    private final IBaseListView mView;

    public BaseListBottomHandler(IBaseListView iBaseListView) {
        this.TAG = iBaseListView.tag();
        this.mView = iBaseListView;
    }

    private Blackboard getBlackboard() {
        return this.mView.getBlackboard();
    }

    private int getLastSelectedItemViewPosition() {
        ArrayList<Integer> arrayList;
        GalleryListView listView = this.mView.getListView();
        if (listView == null) {
            arrayList = null;
        } else {
            arrayList = listView.getSelectedItemList();
        }
        if (arrayList == null || arrayList.size() != 1) {
            return -1;
        }
        return arrayList.get(0).intValue();
    }

    private Fragment getParentFragment() {
        return ((Fragment) this.mView).getParentFragment();
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x008c A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int getScrollDyToFitBottomLine(int r7, boolean r8) {
        /*
            r6 = this;
            r0 = -1
            if (r7 >= 0) goto L_0x0004
            return r0
        L_0x0004:
            com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView r1 = r6.mView
            com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter r1 = r1.getAdapter()
            com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView r2 = r6.mView
            android.view.View r2 = r2.getView()
            if (r1 == 0) goto L_0x008c
            if (r2 != 0) goto L_0x0016
            goto L_0x008c
        L_0x0016:
            com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView r3 = r6.mView
            com.samsung.android.gallery.widget.listview.GalleryListView r3 = r3.getListView()
            if (r3 != 0) goto L_0x0020
            r3 = 0
            goto L_0x0024
        L_0x0020:
            androidx.recyclerview.widget.RecyclerView$ViewHolder r3 = r3.findViewHolderForAdapterPosition(r7)
        L_0x0024:
            if (r3 != 0) goto L_0x003a
            java.lang.CharSequence r6 = r6.TAG
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r1 = "getScrollDyToFitBottomLine: findViewHolderForAdapterPosition() returns null for "
            r8.<init>(r1)
            r8.append(r7)
            java.lang.String r7 = r8.toString()
            com.samsung.android.gallery.support.utils.Log.w(r6, r7)
            return r0
        L_0x003a:
            android.view.View r3 = r3.itemView
            r4 = 2
            int[] r5 = new int[r4]
            r3.getLocationInWindow(r5)
            int[] r3 = new int[r4]
            r2.getLocationInWindow(r3)
            r4 = 1
            r3 = r3[r4]
            int r2 = r2.getBottom()
            int r2 = r2 + r3
            int r7 = r1.getItemHeight(r7)
            r1 = r5[r4]
            int r1 = r1 + r7
            boolean r3 = r6.hasBottomTab()
            int r4 = r1 - r2
            r5 = 1036831949(0x3dcccccd, float:0.1)
            if (r3 == 0) goto L_0x006b
            if (r4 < 0) goto L_0x008c
            float r6 = (float) r4
            float r7 = (float) r7
            float r7 = r7 * r5
            int r6 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
            if (r6 < 0) goto L_0x008c
            return r4
        L_0x006b:
            if (r8 == 0) goto L_0x006f
            r6 = 0
            goto L_0x0075
        L_0x006f:
            com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView r6 = r6.mView
            int r6 = r6.getBottomBarHeight()
        L_0x0075:
            int r2 = r2 - r6
            int r1 = r1 - r2
            if (r4 < 0) goto L_0x0082
            float r8 = (float) r1
            float r7 = (float) r7
            float r7 = r7 * r5
            int r7 = (r8 > r7 ? 1 : (r8 == r7 ? 0 : -1))
            if (r7 < 0) goto L_0x008c
            int r4 = r4 + r6
            return r4
        L_0x0082:
            if (r1 < 0) goto L_0x008c
            float r6 = (float) r1
            float r7 = (float) r7
            float r7 = r7 * r5
            int r6 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
            if (r6 < 0) goto L_0x008c
            return r1
        L_0x008c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.abstraction.BaseListBottomHandler.getScrollDyToFitBottomLine(int, boolean):int");
    }

    private boolean isBottomBarVisibleChanged(int i2) {
        if (i2 > 0 && this.mView.getListView() != null && this.mView.getListView().getPaddingBottom() != i2) {
            return true;
        }
        Object pop = getBlackboard().pop("data://bottom_bar_visible_changed");
        if (!(pop instanceof Boolean) || !((Boolean) pop).booleanValue()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$moveToScrollOnBottomLine$1(int i2) {
        if (this.mView.isAppBarPartiallyVisible()) {
            GalleryAppBarLayout appbarLayout = this.mView.getAppbarLayout();
            if (appbarLayout != null) {
                appbarLayout.setExpanded(false, true);
                return;
            }
            return;
        }
        Optional.ofNullable(this.mView.getListView()).ifPresent(new C0369d(i2, 0));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateListViewBottomPadding$2(GalleryListView galleryListView) {
        boolean z;
        if (this.mView.getPresenter() != null && !this.mView.isEmptyViewShowing()) {
            int i2 = this.mBottomBarHeight;
            if (i2 <= 0) {
                i2 = this.mView.getBottomTabHeight();
            }
            int dimensionPixelOffset = galleryListView.getResources().getDimensionPixelOffset(R.dimen.list_view_default_bottom_padding);
            int dividerButtonHeight = this.mView.getPresenter().getDividerButtonHeight() + this.mView.getPresenter().getSearchToolbarHeight() + WindowUtils.getSystemInsetsBottom(galleryListView.getRootWindowInsets()) + i2 + dimensionPixelOffset;
            ViewMarginUtils.setBottomPadding(galleryListView, dividerButtonHeight);
            if (this.mView.getAppbarLayout() != null && this.mView.getToolbar() != null) {
                int systemInsetsTop = WindowUtils.getSystemInsetsTop(galleryListView.getRootWindowInsets()) + this.mView.getToolbar().getHeight();
                GalleryAppBarLayout appbarLayout = this.mView.getAppbarLayout();
                if (galleryListView.computeVerticalScrollRange() <= (galleryListView.getHeight() - systemInsetsTop) - dividerButtonHeight || galleryListView.computeVerticalScrollRange() >= galleryListView.getHeight()) {
                    z = false;
                } else {
                    z = true;
                }
                appbarLayout.seslSetAllowStateToHide(true, z);
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateListViewBottomPadding$3(GalleryListView galleryListView) {
        galleryListView.postDelayed(new d(1, this, galleryListView), 100);
    }

    public boolean hasBottomTab() {
        if (getParentFragment() != null) {
            return true;
        }
        return false;
    }

    public void moveToScrollOnBottomLine(int i2) {
        long j2;
        int scrollDyToFitBottomLine = getScrollDyToFitBottomLine(i2, !PickerUtil.isNormalLaunchMode(getBlackboard()));
        if (scrollDyToFitBottomLine > 0) {
            C0368c cVar = new C0368c(this, scrollDyToFitBottomLine, 0);
            if (hasBottomTab()) {
                j2 = 0;
            } else {
                j2 = 250;
            }
            ThreadUtil.postOnUiThreadDelayed(cVar, j2);
        }
    }

    public void setBottomBarHeight(int i2) {
        this.mBottomBarHeight = i2;
        updateListViewBottomPadding();
        int lastSelectedItemViewPosition = getLastSelectedItemViewPosition();
        if (lastSelectedItemViewPosition >= 0 && isBottomBarVisibleChanged(i2)) {
            moveToScrollOnBottomLine(lastSelectedItemViewPosition);
        }
    }

    public void updateBottomBar() {
        if (this.mView.isSelectionMode() && this.mView.getSelectedItemCount() > 0) {
            getBlackboard().post("command://RestoreBottomBar", (Object) null);
        } else if (this.mView.isMoveMode()) {
            getBlackboard().post("command://RestoreBottomMoveBar", (Object) null);
        }
    }

    public void updateListViewBottomPadding() {
        Optional.ofNullable(this.mView.getListView()).ifPresent(new C0367b(0, this));
    }
}
