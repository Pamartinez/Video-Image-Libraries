package com.samsung.android.gallery.app.ui.list.search.pictures.abstraction;

import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.list.pictures.IPicturesView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.search.pictures.SearchHeaderView;
import java.util.HashMap;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ISearchPicturesView extends IPicturesView {
    boolean allowEmptyView();

    void bindHeader(MediaItem mediaItem);

    ICreatureContactDelegate getContactDelegate();

    ViewGroup getHeaderTargetView();

    HashMap<String, Supplier<Object>> getHeaderUpdateSupplierMap() {
        return new HashMap<>();
    }

    SearchHeaderView getHeaderView();

    boolean handleRelationshipRecommend();

    boolean isPicturesOnlyMode() {
        return false;
    }

    void onHeaderItemClicked(View view, int i2, MediaItem mediaItem, Bitmap bitmap);

    void onReopenData();

    void onScopedSearchVisibilityChanged(boolean z);

    void refreshLocationKey(String str);

    void reopenData(String str);

    void setEnabledHeader(boolean z);

    void setProgressBarVisibility(boolean z);

    void setSuggesterForSettingAction(Object obj);

    boolean skipEmptyView(boolean z) {
        return false;
    }

    boolean supportExpand();

    void updateEmptyViews();
}
