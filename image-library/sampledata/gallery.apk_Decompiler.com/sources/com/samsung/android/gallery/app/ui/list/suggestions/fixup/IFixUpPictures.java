package com.samsung.android.gallery.app.ui.list.suggestions.fixup;

import com.samsung.android.gallery.app.ui.list.pictures.IPicturesView;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewHolderFactory;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IFixUpPictures extends IPicturesView {
    PicturesViewHolderFactory createViewHolderFactory();

    boolean isHighLightPictures();
}
