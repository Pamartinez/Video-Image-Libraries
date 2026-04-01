package com.samsung.android.gallery.app.ui.list.albums.pictures.filter;

import A4.C0369d;
import K5.a;
import N4.c;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ScreenShotFilterView {
    private View mContainer;
    private RecyclerView mListView;

    public void bindView(View view) {
        this.mContainer = view.findViewById(R.id.screen_shot_filter_container);
        this.mListView = (RecyclerView) view.findViewById(R.id.screen_shot_filter_list);
    }

    public boolean getVisibility() {
        return ViewUtils.isVisible(this.mContainer);
    }

    public boolean isListViewScrolling() {
        RecyclerView recyclerView = this.mListView;
        if (recyclerView == null || recyclerView.getScrollState() == 0) {
            return false;
        }
        return true;
    }

    public boolean isViewReady() {
        if (this.mContainer == null || this.mListView == null) {
            return false;
        }
        return true;
    }

    public void scrollToPosition(int i2) {
        Optional.ofNullable(this.mListView).ifPresent(new C0369d(i2, 10));
    }

    public void setAdapter(ScreenShotFilterListViewAdapter screenShotFilterListViewAdapter) {
        Optional.ofNullable(this.mListView).ifPresent(new a(14, screenShotFilterListViewAdapter));
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        Optional.ofNullable(this.mListView).ifPresent(new c(0, layoutManager));
    }

    public void setVisibility(boolean z) {
        ViewUtils.setVisibleOrGone(this.mContainer, z);
    }

    public void unbindView() {
        this.mContainer = null;
        this.mListView = null;
    }
}
