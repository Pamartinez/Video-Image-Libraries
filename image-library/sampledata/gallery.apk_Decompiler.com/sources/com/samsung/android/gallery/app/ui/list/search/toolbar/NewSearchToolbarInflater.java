package com.samsung.android.gallery.app.ui.list.search.toolbar;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.samsung.android.gallery.app.ui.abstraction.IMvpBaseView;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbar;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbarOptions;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class NewSearchToolbarInflater implements SearchToolbarInflater {
    public SearchToolbar inflate(IMvpBaseView iMvpBaseView, SearchToolbarOptions searchToolbarOptions) {
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(iMvpBaseView.getContext()).inflate(searchToolbarOptions.getSearchLayout(), searchToolbarOptions.getContainer(), false);
        if (!searchToolbarOptions.isAttachOnGalleryToolbar() || iMvpBaseView.getToolbar() == null) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) viewGroup.getLayoutParams();
            if (marginLayoutParams instanceof CoordinatorLayout.LayoutParams) {
                ((CoordinatorLayout.LayoutParams) marginLayoutParams).gravity = 80;
            } else if (marginLayoutParams instanceof FrameLayout.LayoutParams) {
                ((FrameLayout.LayoutParams) marginLayoutParams).gravity = 80;
            } else if (marginLayoutParams instanceof RelativeLayout.LayoutParams) {
                ((RelativeLayout.LayoutParams) marginLayoutParams).addRule(12);
            }
            viewGroup.setLayoutParams(marginLayoutParams);
            searchToolbarOptions.getContainer().addView(viewGroup);
        } else {
            GalleryToolbar toolbar = iMvpBaseView.getToolbar();
            toolbar.addView(viewGroup);
            toolbar.setContentInsetsRelative(0, 0);
        }
        if (searchToolbarOptions.isInflateWithGoneState()) {
            viewGroup.setVisibility(8);
        }
        SearchToolbar searchToolbar = (SearchToolbar) viewGroup.findViewById(R.id.search);
        searchToolbar.onAttached(searchToolbarOptions);
        return searchToolbar;
    }
}
