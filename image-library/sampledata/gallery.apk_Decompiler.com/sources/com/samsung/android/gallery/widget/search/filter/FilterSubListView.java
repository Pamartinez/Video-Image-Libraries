package com.samsung.android.gallery.widget.search.filter;

import android.view.View;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.samsung.android.gallery.module.search.root.FilterPriority;
import com.samsung.android.gallery.module.search.root.FilterResultsEntity;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.R$layout;
import com.samsung.android.gallery.widget.flexboxlist.FlexBoxListView;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class FilterSubListView {
    private static final int[] LIST_VIEW_RES_ID = {R$id.all_filters_list_priority0, R$id.all_filters_list_priority1, R$id.all_filters_list_priority2, R$id.all_filters_list_priority3};
    private final FilterSubListViewAdapter adapter;
    private final FlexBoxListView listView;
    private final Blackboard mBlackboard;
    private int mLimitCount = 5;

    public FilterSubListView(Blackboard blackboard, View view, int i2, Runnable runnable) {
        this.mBlackboard = blackboard;
        FlexBoxListView flexBoxListView = (FlexBoxListView) view.findViewById(LIST_VIEW_RES_ID[i2]);
        this.listView = flexBoxListView;
        flexBoxListView.setVisibility(0);
        flexBoxListView.setLayoutManager(new FlexboxLayoutManager(view.getContext()) {
            public boolean canScrollVertically() {
                return false;
            }
        });
        FilterSubListViewAdapter createListAdapter = createListAdapter(i2, runnable);
        this.adapter = createListAdapter;
        flexBoxListView.setAdapter(createListAdapter);
    }

    private FilterSubListViewAdapter createListAdapter(final int i2, Runnable runnable) {
        return new FilterSubListViewAdapter(this.mBlackboard, runnable) {
            public int getItemLayoutResId() {
                if (FilterPriority.isCreaturePriority(i2)) {
                    return R$layout.recycler_item_all_filters_creature_layout;
                }
                return super.getItemLayoutResId();
            }
        };
    }

    public void addEntity(FilterResultsEntity filterResultsEntity) {
        if (this.adapter.getItemCount() <= this.mLimitCount) {
            this.adapter.addEntity(filterResultsEntity);
        }
    }

    public void setOnlyThemClickedListener(Runnable runnable) {
        this.adapter.setOnlyThemClickedListener(new b(runnable));
    }

    public void updateListView() {
        boolean z;
        FlexBoxListView flexBoxListView = this.listView;
        if (this.adapter.getItemCount() > 0) {
            z = true;
        } else {
            z = false;
        }
        ViewUtils.setVisibleOrGone(flexBoxListView, z);
    }
}
