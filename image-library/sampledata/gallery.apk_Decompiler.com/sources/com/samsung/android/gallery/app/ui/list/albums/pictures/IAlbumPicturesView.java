package com.samsung.android.gallery.app.ui.list.albums.pictures;

import com.samsung.android.gallery.app.ui.list.pictures.IPicturesView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IAlbumPicturesView extends IPicturesView {
    void changeAlbum(String str);

    int getAlbumListWidth();

    boolean isAlbumChanged();

    boolean isAlbumPaneAvailable();

    boolean isEnterTransition();

    boolean isSplitBlocked();

    boolean isSplitMode();

    boolean loadSplitModeFromPreference();

    void onAlbumPaneDataPrepared();

    void onChangeAlbum(String str);

    void setLocationKey(String str);

    boolean skipEmptyView();

    void toggleSplitMode();

    void updateHeaderView(boolean z, boolean z3);
}
