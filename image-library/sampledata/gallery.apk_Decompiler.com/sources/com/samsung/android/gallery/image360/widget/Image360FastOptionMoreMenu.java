package com.samsung.android.gallery.image360.widget;

import A.a;
import Ab.d;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListPopupWindow;
import androidx.appcompat.R$attr;
import com.samsung.android.gallery.image360.R$dimen;
import com.samsung.android.gallery.image360.R$layout;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.ArrayList;
import x6.f;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Image360FastOptionMoreMenu {
    private final Image360FastOptionMoreMenuAdapter mAdapter;
    private final ListPopupWindow mListPopupWindow;
    private Image360FastOptionViewListener mListener;
    private final Image360FastOptionItem mMoreBtn;

    public Image360FastOptionMoreMenu(Context context, Image360FastOptionItem image360FastOptionItem) {
        this.mMoreBtn = image360FastOptionItem;
        this.mAdapter = new Image360FastOptionMoreMenuAdapter(context, R$layout.fast_option_more_menu_item);
        this.mListPopupWindow = new ListPopupWindow(context, (AttributeSet) null, R$attr.actionOverflowBottomMenuStyle);
        initListPopupWindow(context);
    }

    private void initListPopupWindow(Context context) {
        this.mListPopupWindow.setDropDownGravity(8388611);
        this.mListPopupWindow.setAnchorView(this.mMoreBtn);
        this.mListPopupWindow.setAdapter(this.mAdapter);
        this.mListPopupWindow.setModal(true);
        this.mListPopupWindow.setVerticalOffset(context.getResources().getDimensionPixelOffset(R$dimen.fast_more_menu_popup_vertical_offset));
        this.mListPopupWindow.setOnItemClickListener(new d(2, this));
    }

    private int measureContentWidth(Context context, ArrayAdapter<MenuItem> arrayAdapter) {
        FrameLayout frameLayout = new FrameLayout(context);
        View view = null;
        int i2 = 0;
        for (int i7 = 0; i7 < arrayAdapter.getCount(); i7++) {
            view = arrayAdapter.getView(i7, view, frameLayout);
            view.measure(0, 0);
            int measuredWidth = view.getMeasuredWidth();
            if (measuredWidth > i2) {
                i2 = measuredWidth;
            }
        }
        return i2;
    }

    /* access modifiers changed from: private */
    public void onListItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
        CharSequence charSequence;
        try {
            MenuItem menuItem = (MenuItem) this.mAdapter.getItem(i2);
            StringBuilder sb2 = new StringBuilder("more menu item clicked i=");
            if (menuItem != null) {
                charSequence = menuItem.getTitle();
            } else {
                charSequence = null;
            }
            sb2.append(Logger.getEncodedString((Object) charSequence));
            sb2.append(", l=");
            sb2.append(this.mListener);
            Log.d("Image360FastOptionMoreMenu", sb2.toString());
            Image360FastOptionViewListener image360FastOptionViewListener = this.mListener;
            if (!(image360FastOptionViewListener == null || menuItem == null)) {
                image360FastOptionViewListener.onMoreMenuItemClicked(menuItem);
            }
        } catch (Exception e) {
            a.s(e, new StringBuilder("more menu click failed, e="), "Image360FastOptionMoreMenu");
        }
        this.mListPopupWindow.dismiss();
    }

    private void updateAdapter(ArrayList<MenuItem> arrayList) {
        ThreadUtil.runOnUiThread(new f(6, this, arrayList));
    }

    /* access modifiers changed from: private */
    /* renamed from: updateAdapterInternal */
    public void lambda$updateAdapter$0(ArrayList<MenuItem> arrayList) {
        this.mAdapter.clear();
        this.mAdapter.addAll(arrayList);
        this.mAdapter.notifyDataSetChanged();
    }

    private void updateList(Context context) {
        this.mListPopupWindow.setWidth(measureContentWidth(context, this.mAdapter));
    }

    public void dismiss() {
        this.mListPopupWindow.dismiss();
    }

    public boolean isEnabled() {
        return !this.mAdapter.isEmpty();
    }

    public void setListener(Image360FastOptionViewListener image360FastOptionViewListener) {
        this.mListener = image360FastOptionViewListener;
    }

    public void toggle() {
        if (this.mListPopupWindow.isShowing()) {
            this.mListPopupWindow.dismiss();
        } else {
            this.mListPopupWindow.show();
        }
    }

    public void update(Context context, ArrayList<MenuItem> arrayList) {
        updateAdapter(arrayList);
        updateList(context);
    }
}
