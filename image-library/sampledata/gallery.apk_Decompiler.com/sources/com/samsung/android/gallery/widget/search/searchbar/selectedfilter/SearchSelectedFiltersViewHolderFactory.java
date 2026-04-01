package com.samsung.android.gallery.widget.search.searchbar.selectedfilter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.R$layout;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SearchSelectedFiltersViewHolderFactory {
    private static final boolean NEW_SEARCH_BAR = PreferenceFeatures.isEnabled(PreferenceFeatures.NewSearchBar);

    public static RecyclerView.ViewHolder create(Context context, int i2) {
        if (i2 == 1) {
            return new SearchSelectedFiltersEditableViewHolder(LayoutInflater.from(context).inflate(R$layout.recycler_item_search_selected_filters_editable, (ViewGroup) null, false));
        }
        return new SearchSelectedFiltersViewHolder(LayoutInflater.from(context).inflate(getItemLayoutResId(), (ViewGroup) null, false));
    }

    private static int getItemLayoutResId() {
        if (NEW_SEARCH_BAR) {
            return R$layout.recycler_item_search_selected_filters_no_theme;
        }
        return R$layout.recycler_item_search_selected_filters;
    }
}
