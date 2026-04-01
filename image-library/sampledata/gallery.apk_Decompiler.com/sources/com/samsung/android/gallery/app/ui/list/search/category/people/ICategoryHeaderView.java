package com.samsung.android.gallery.app.ui.list.search.category.people;

import android.view.View;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ICategoryHeaderView {
    void dataChanged();

    View get();

    void initView();

    boolean isTop5EditMode();

    boolean onBackPressed();

    void onSelectMeDone();

    void onSelectionModeChanged(boolean z);

    void setTop5ViewEnable(boolean z);
}
