package com.samsung.android.gallery.app.ui.list.albums.mx.header;

import android.view.View;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IAlbumsHeaderView {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnDataChangeListener {
    }

    void destroy();

    int getHeight();

    View getView();

    void onEnableHeaderView(boolean z);

    void refreshHeader(boolean z, boolean z3);

    void setOnDataChangedListener(OnDataChangeListener onDataChangeListener);
}
