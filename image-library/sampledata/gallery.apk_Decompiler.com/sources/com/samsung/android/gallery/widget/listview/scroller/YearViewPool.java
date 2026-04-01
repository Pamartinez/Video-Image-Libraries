package com.samsung.android.gallery.widget.listview.scroller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.widget.R$layout;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.Iterator;
import java.util.LinkedList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class YearViewPool {
    private LayoutInflater mLayoutInflater;
    private ViewGroup mParent;
    private final LinkedList<View> mViews = new LinkedList<>();

    public YearViewPool(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        this.mLayoutInflater = layoutInflater;
        this.mParent = viewGroup;
    }

    private void initView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R$layout.fast_scroll_year_layout, (ViewGroup) null);
        viewGroup.addView(inflate);
        ViewGroup.LayoutParams layoutParams = inflate.getLayoutParams();
        layoutParams.height = -2;
        layoutParams.width = -2;
        inflate.setLayoutParams(layoutParams);
        inflate.setVisibility(4);
        this.mViews.add(inflate);
    }

    public void add(View view) {
        this.mViews.add(view);
        view.setVisibility(4);
    }

    public void destroy() {
        Iterator<View> it = this.mViews.iterator();
        while (it.hasNext()) {
            ViewUtils.removeSelf(it.next());
        }
        this.mViews.clear();
        this.mLayoutInflater = null;
        this.mParent = null;
    }

    public View get() {
        if (this.mViews.isEmpty()) {
            initView(this.mLayoutInflater, this.mParent);
        }
        return this.mViews.poll();
    }

    public View peek() {
        if (this.mViews.isEmpty()) {
            initView(this.mLayoutInflater, this.mParent);
        }
        return this.mViews.peek();
    }
}
