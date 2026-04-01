package com.samsung.android.gallery.app.ui.list.albums.pictures.setting;

import android.content.Context;
import android.os.Bundle;
import com.samsung.android.gallery.settings.ui.abstaction.IBasePreferenceView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IAlbumSettingView extends IBasePreferenceView {
    Context getApplicationContext();

    void onDataChanged(Object obj, Bundle bundle);
}
