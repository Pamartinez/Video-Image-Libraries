package com.samsung.android.gallery.widget.listview;

import D7.g;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.google.android.material.appbar.AppBarLayout;
import com.samsung.android.gallery.support.utils.Log;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ListScrollBuilder {
    private AppBarLayout appbar;
    private long delay = 500;
    private final ArrayList<ScrollParam> list = new ArrayList<>();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ScrollParam {
        int offset;
        int position;
        RecyclerView view;

        public ScrollParam(RecyclerView recyclerView, int i2, int i7) {
            this.view = recyclerView;
            this.position = i2;
            this.offset = i7;
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$apply$0(ScrollParam scrollParam) {
        int i2 = scrollParam.offset;
        if (i2 != 0) {
            scrollToPositionWithOffset(scrollParam.view, scrollParam.position, i2);
        } else {
            scrollParam.view.scrollToPosition(scrollParam.position);
        }
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [java.util.function.Consumer, java.lang.Object] */
    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$apply$1() {
        AppBarLayout appBarLayout = this.appbar;
        if (appBarLayout != null) {
            appBarLayout.setExpanded(false, false);
        }
        this.list.forEach(new Object());
    }

    public static void scrollToPositionWithOffset(RecyclerView recyclerView, int i2, int i7) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            ((GridLayoutManager) layoutManager).scrollToPositionWithOffset(i2, i7);
        } else if (layoutManager instanceof LinearLayoutManager) {
            ((LinearLayoutManager) layoutManager).scrollToPositionWithOffset(i2, i7);
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            ((StaggeredGridLayoutManager) layoutManager).scrollToPositionWithOffset(i2, i7);
        } else if (layoutManager != null) {
            Log.w((CharSequence) "ListScrollBuilder", "scrollToPositionWithOffset unknown lm", layoutManager, Integer.valueOf(i2), Integer.valueOf(i7));
            layoutManager.scrollToPosition(i2);
        } else {
            Log.e((CharSequence) "ListScrollBuilder", "scrollToPositionWithOffset failed. null lm", Integer.valueOf(i2), Integer.valueOf(i7));
        }
    }

    public ListScrollBuilder append(RecyclerView recyclerView, int i2, int i7) {
        if (recyclerView != null) {
            this.list.add(new ScrollParam(recyclerView, i2, i7));
        }
        return this;
    }

    public void apply() {
        View view = this.appbar;
        if (view == null) {
            if (!this.list.isEmpty()) {
                view = this.list.get(0).view;
            } else {
                view = null;
            }
        }
        if (view != null) {
            view.postDelayed(new g(19, this), this.delay);
        }
    }

    public ListScrollBuilder withAppbarCollapsing(AppBarLayout appBarLayout) {
        this.appbar = appBarLayout;
        return this;
    }

    public ListScrollBuilder withDelay(long j2) {
        this.delay = j2;
        return this;
    }
}
