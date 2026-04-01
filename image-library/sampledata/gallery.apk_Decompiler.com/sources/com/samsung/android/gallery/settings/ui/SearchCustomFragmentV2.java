package com.samsung.android.gallery.settings.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DividerItemDecoration;
import com.samsung.android.gallery.module.abstraction.VisualSearchCategory;
import com.samsung.android.gallery.settings.R$color;
import com.samsung.android.gallery.settings.R$id;
import com.samsung.android.gallery.settings.R$layout;
import com.samsung.android.gallery.settings.R$string;
import com.samsung.android.gallery.settings.ui.abstaction.IBaseActivity;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchCustomFragmentV2 extends SearchCustomFragment {
    public SearchCustomFragmentV2(IBaseActivity iBaseActivity) {
        super(iBaseActivity);
    }

    public void addDividerToRecyclerView() {
        this.mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), 1));
    }

    public void bindViews(ViewGroup viewGroup) {
        ViewUtils.setVisibleOrGone(viewGroup.findViewById(R$id.search_customize_description), false);
        super.bindViews(viewGroup);
    }

    public SearchCustomViewAdapter createListAdapter() {
        return new SearchCustomViewAdapter(VisualSearchCategory.listOf()) {
            public boolean isReorderMode() {
                return true;
            }

            public SearchCustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
                SearchCustomViewHolderV2 searchCustomViewHolderV2 = new SearchCustomViewHolderV2(LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.recycler_item_search_customize_v2, viewGroup, false), i2);
                searchCustomViewHolderV2.setTouchCallback(new y(1, this));
                return searchCustomViewHolderV2;
            }
        };
    }

    public int getLayoutId() {
        return R$layout.fragment_search_customize_layout_v2;
    }

    public boolean onBackPressed() {
        return false;
    }

    public void onViewCreated(View view, Bundle bundle) {
        View rootView;
        super.onViewCreated(view, bundle);
        if (getTitleId() == R$string.edit_collections && (rootView = view.getRootView()) != null) {
            rootView.setBackgroundColor(view.getContext().getColor(R$color.default_fw_background));
        }
    }

    public void recyclerViewInit() {
        super.recyclerViewInit();
    }

    public boolean supportMenu() {
        return false;
    }
}
