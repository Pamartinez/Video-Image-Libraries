package com.samsung.android.gallery.app.ui.list.mapclustering.list.bottomsheet;

import com.samsung.android.gallery.app.ui.list.pictures.IPicturesView;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewAdapter;
import com.samsung.android.gallery.support.utils.Features;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BottomSheetListAdapter extends PicturesViewAdapter<IPicturesView> {
    public BottomSheetListAdapter(IPicturesView iPicturesView, String str, boolean z) {
        super(iPicturesView, str, z);
    }

    public boolean isLocationTextDimmed() {
        return true;
    }

    public boolean isLocationTextDisplay() {
        return !Features.isEnabled(Features.IS_USA_DEVICE);
    }
}
