package com.samsung.android.gallery.widget.search.searchbar.autocomplete;

import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.widget.R$layout;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BottomFilterAutoCompleteAdapter extends FilterAutoCompleteAdapter {
    public BottomFilterAutoCompleteAdapter(Blackboard blackboard) {
        super(blackboard);
    }

    public int getItemLayoutId() {
        return R$layout.recycler_item_bottom_search_auto_complete_layout;
    }
}
