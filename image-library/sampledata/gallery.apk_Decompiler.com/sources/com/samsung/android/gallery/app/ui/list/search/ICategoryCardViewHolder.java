package com.samsung.android.gallery.app.ui.list.search;

import com.samsung.android.gallery.app.ui.map.search.SearchMapFragmentContainerView;
import com.samsung.android.gallery.module.dataset.MediaData;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ICategoryCardViewHolder {
    void bind(ISearchView iSearchView, MediaData mediaData);

    void onConfigurationChanged();

    void updateContentPadding(boolean z);

    void updateDivider(boolean z);

    void setNotifySupplier(BooleanSupplier booleanSupplier) {
    }

    void bindMapView(ISearchView iSearchView, SearchMapFragmentContainerView searchMapFragmentContainerView, MediaData mediaData) {
    }
}
