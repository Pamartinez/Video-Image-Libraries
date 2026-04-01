package com.samsung.android.gallery.app.ui.list.search.toolbar;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.abstraction.IMvpBaseView;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbar;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbarOptions;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class LegacySearchToolbarInflater implements SearchToolbarInflater {
    public SearchToolbar inflate(IMvpBaseView iMvpBaseView, SearchToolbarOptions searchToolbarOptions) {
        ViewGroup viewGroup;
        LayoutInflater from = LayoutInflater.from(iMvpBaseView.getContext());
        int searchLayout = searchToolbarOptions.getSearchLayout();
        if (searchToolbarOptions.isAttachOnGalleryToolbar()) {
            viewGroup = iMvpBaseView.getToolbar();
        } else {
            viewGroup = searchToolbarOptions.getContainer();
        }
        SearchToolbar searchToolbar = (SearchToolbar) from.inflate(searchLayout, viewGroup, false);
        if (searchToolbarOptions.isAttachOnGalleryToolbar()) {
            iMvpBaseView.getToolbar().addView(searchToolbar);
            iMvpBaseView.getToolbar().setContentInsetsRelative(0, 0);
        } else {
            searchToolbarOptions.getContainer().addView(searchToolbar);
        }
        searchToolbar.onAttached(searchToolbarOptions);
        return searchToolbar;
    }
}
