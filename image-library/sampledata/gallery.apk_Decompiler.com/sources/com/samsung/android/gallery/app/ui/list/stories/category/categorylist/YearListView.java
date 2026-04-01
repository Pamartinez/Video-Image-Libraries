package com.samsung.android.gallery.app.ui.list.stories.category.categorylist;

import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class YearListView {
    private final YearListAdapter mAdapter;
    private final View mHeaderView;
    private final RecyclerView mRecyclerView;

    public YearListView(View view, ArrayList<Integer> arrayList, Consumer<Integer> consumer) {
        this.mHeaderView = view;
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.year_list);
        this.mRecyclerView = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), 0, false));
        YearListAdapter yearListAdapter = new YearListAdapter(arrayList);
        this.mAdapter = yearListAdapter;
        yearListAdapter.setOnClickListener(consumer);
        recyclerView.setAdapter(yearListAdapter);
    }

    public void handleDensityChange() {
        this.mRecyclerView.setItemViewCacheSize(0);
        Optional.ofNullable(this.mRecyclerView.getLayoutManager()).ifPresent(new b(3));
        this.mRecyclerView.removeAllViews();
        this.mRecyclerView.getRecycledViewPool().clear();
        this.mAdapter.notifyDataSetChanged();
    }

    public void handleResolutionChange() {
        this.mAdapter.scrollToFocusedPosition(this.mRecyclerView);
    }

    public boolean isShowAll() {
        return this.mAdapter.isShowAll();
    }

    public void onSelectModeChanged(boolean z) {
        ViewUtils.setViewEnabled(this.mHeaderView, !z);
        this.mAdapter.setClickable(!z);
    }

    public void resetFilter() {
        this.mAdapter.resetFilter(this.mRecyclerView);
    }

    public void setFocus(int i2) {
        this.mAdapter.setFocus(this.mRecyclerView, i2);
    }

    public void update(ArrayList<Integer> arrayList) {
        this.mAdapter.update(arrayList);
        this.mAdapter.notifyDataSetChanged();
    }
}
