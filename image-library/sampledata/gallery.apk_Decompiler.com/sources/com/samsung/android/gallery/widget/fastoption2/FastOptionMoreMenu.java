package com.samsung.android.gallery.widget.fastoption2;

import A4.C0367b;
import A4.C0372g;
import Ab.c;
import Ab.d;
import Ab.f;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import androidx.appcompat.R$attr;
import androidx.appcompat.widget.ListPopupWindow;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.R$layout;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FastOptionMoreMenu {
    private final FastOptionMoreMenuAdapter mAdapter;
    private WeakReference<Context> mContext;
    private final boolean mIsRtl = Features.isEnabled(Features.IS_RTL);
    private MoreMenuItemSelectedListener mItemSelectedListener;
    private View mMoreBtn;
    private final int mOffset;
    private ListPopupWindow mPopupWindow;
    private ListPopupWindow mPopupWindowInClickedPosition;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface MoreMenuItemSelectedListener {
    }

    public FastOptionMoreMenu(Context context, MoreMenuItemSelectedListener moreMenuItemSelectedListener) {
        this.mAdapter = new FastOptionMoreMenuAdapter(context, R$layout.fast_option_more_menu_item, -1);
        this.mOffset = context.getResources().getDimensionPixelSize(R$dimen.fast_more_menu_horizontal_offset);
        this.mItemSelectedListener = moreMenuItemSelectedListener;
        this.mContext = new WeakReference<>(context);
    }

    private void addDividerIfGroupChanged(FastOptionMenuItem fastOptionMenuItem) {
        int count = this.mAdapter.getCount();
        if (count > 0 && ((FastOptionMenuItem) this.mAdapter.getItem(count - 1)).getGroupId() != fastOptionMenuItem.getGroupId()) {
            this.mAdapter.add(new FastOptionMenuItem(FastOptionItemParams.builder().setTitleRes(-1).setMenuId(-1).build()));
        }
    }

    private int getHorizontalOffset() {
        if (this.mMoreBtn == null) {
            return 0;
        }
        Rect rect = new Rect();
        this.mMoreBtn.getGlobalVisibleRect(rect);
        if (this.mIsRtl) {
            return Math.max(this.mPopupWindow.getWidth() - (rect.right - this.mOffset), 0);
        }
        return Math.min((this.mMoreBtn.getRootView().getWidth() - this.mOffset) - (this.mPopupWindow.getWidth() + rect.left), 0);
    }

    private void getListPopupWindowInClickedPosition(ViewGroup viewGroup, MotionEvent motionEvent) {
        if (this.mPopupWindowInClickedPosition == null) {
            ListPopupWindow listPopupWindow = new ListPopupWindow(viewGroup.getContext(), (AttributeSet) null, R$attr.actionOverflowMenuStyle);
            this.mPopupWindowInClickedPosition = listPopupWindow;
            initListPopupWindow(listPopupWindow);
        }
        ListPopupWindow listPopupWindow2 = this.mPopupWindowInClickedPosition;
        listPopupWindow2.setWidth(measureContentWidth(listPopupWindow2));
        View createAnchorViewInEventPosition = ViewUtils.createAnchorViewInEventPosition(viewGroup, motionEvent);
        this.mPopupWindowInClickedPosition.setAnchorView(createAnchorViewInEventPosition);
        this.mPopupWindowInClickedPosition.setOnDismissListener(new c(viewGroup, createAnchorViewInEventPosition));
    }

    private int getVerticalOffset() {
        View view = this.mMoreBtn;
        if (view != null) {
            return -view.getHeight();
        }
        return 0;
    }

    private void initListPopupWindow() {
        if (this.mPopupWindow == null) {
            ListPopupWindow listPopupWindow = new ListPopupWindow(this.mContext.get(), (AttributeSet) null, R$attr.actionOverflowBottomMenuStyle);
            this.mPopupWindow = listPopupWindow;
            initListPopupWindow(listPopupWindow);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initListPopupWindow$0(AdapterView adapterView, View view, int i2, long j2) {
        onListItemClicked(i2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateAnchor$1() {
        this.mPopupWindow.show();
    }

    private int measureContentWidth(ListPopupWindow listPopupWindow) {
        View view = null;
        int i2 = 0;
        for (int i7 = 0; i7 < this.mAdapter.getCount(); i7++) {
            view = this.mAdapter.getView(i7, view, listPopupWindow.getListView());
            view.measure(0, 0);
            int measuredWidth = view.getMeasuredWidth();
            if (measuredWidth > i2) {
                i2 = measuredWidth;
            }
        }
        return i2;
    }

    private void onListItemClicked(int i2) {
        FastOptionMenuItem fastOptionMenuItem = (FastOptionMenuItem) this.mAdapter.getItem(i2);
        if (fastOptionMenuItem != null) {
            MoreMenuItemSelectedListener moreMenuItemSelectedListener = this.mItemSelectedListener;
            ((f) moreMenuItemSelectedListener).e.lambda$init$1(fastOptionMenuItem.getMenuId());
        }
        dismiss();
    }

    public void addItem(FastOptionMenuItem fastOptionMenuItem) {
        addDividerIfGroupChanged(fastOptionMenuItem);
        this.mAdapter.add(fastOptionMenuItem);
    }

    public void clear() {
        this.mAdapter.clear();
    }

    public void dismiss() {
        ListPopupWindow listPopupWindow = this.mPopupWindow;
        if (listPopupWindow != null) {
            listPopupWindow.dismiss();
        }
        ListPopupWindow listPopupWindow2 = this.mPopupWindowInClickedPosition;
        if (listPopupWindow2 != null) {
            listPopupWindow2.dismiss();
        }
    }

    public int getCount() {
        return this.mAdapter.getCount();
    }

    public boolean hasNewBadgeMenu() {
        return this.mAdapter.hasNewBadgeMenu();
    }

    public void setAnchorView(View view) {
        this.mMoreBtn = view;
        initListPopupWindow();
        this.mPopupWindow.setAnchorView(view);
    }

    public void showMenu() {
        initListPopupWindow();
        showMenu(this.mPopupWindow);
    }

    public void showMoreMenuInClickedPosition(ViewGroup viewGroup, MotionEvent motionEvent) {
        getListPopupWindowInClickedPosition(viewGroup, motionEvent);
        showMenu(this.mPopupWindowInClickedPosition);
    }

    public void updateAdapter(ArrayList<FastOptionMenuItem> arrayList) {
        this.mAdapter.clear();
        arrayList.forEach(new C0367b(6, this));
        this.mAdapter.notifyDataSetChanged();
    }

    public void updateAnchor(View view) {
        ListPopupWindow listPopupWindow = this.mPopupWindow;
        if (listPopupWindow != null && listPopupWindow.isShowing() && view != this.mPopupWindow.getAnchorView()) {
            this.mPopupWindow.setAnchorView(view);
            ViewUtils.post(view, new C0372g(2, this));
        }
    }

    private void showMenu(ListPopupWindow listPopupWindow) {
        listPopupWindow.setWidth(measureContentWidth(listPopupWindow));
        listPopupWindow.setHorizontalOffset(getHorizontalOffset());
        listPopupWindow.setVerticalOffset(getVerticalOffset());
        listPopupWindow.show();
        try {
            Blackboard.getInstance(listPopupWindow.getListView().getContext().toString()).postEvent(EventMessage.obtain(1100));
        } catch (NullPointerException unused) {
        }
    }

    private void initListPopupWindow(ListPopupWindow listPopupWindow) {
        listPopupWindow.setAdapter(this.mAdapter);
        listPopupWindow.setDropDownGravity(8388611);
        listPopupWindow.setModal(true);
        listPopupWindow.setOnItemClickListener(new d(0, this));
    }
}
