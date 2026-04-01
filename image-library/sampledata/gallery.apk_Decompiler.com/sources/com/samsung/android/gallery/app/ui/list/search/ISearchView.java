package com.samsung.android.gallery.app.ui.list.search;

import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.app.ui.list.search.category.ISearchHeaderView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ISearchView extends IBaseListView {
    ISearchHeaderView getSearchHeaderView() {
        return null;
    }

    boolean isEnterTransitionRunning();

    void onCategoryExpansionClick(String str);

    boolean supportFooter() {
        return false;
    }
}
