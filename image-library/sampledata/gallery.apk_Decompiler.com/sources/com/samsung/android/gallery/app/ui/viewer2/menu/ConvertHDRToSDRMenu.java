package com.samsung.android.gallery.app.ui.viewer2.menu;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.o3dp.lib3dphotography.common.O3DPConstant;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ConvertHDRToSDRMenu extends ConvertHDR10PlusToSDRMenu {
    public ConvertHDRToSDRMenu(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, R.string.convert_from_hdr_to_sdr);
    }

    public boolean supportMode(MediaItem mediaItem) {
        if (super.supportMode(mediaItem) || mediaItem == null || !StringCompat.endsWithIgnoreCase(mediaItem.getPath(), O3DPConstant.MP4_EXTENSION)) {
            return false;
        }
        if (mediaItem.isHdr10Video() || mediaItem.isHlgVideo()) {
            return true;
        }
        return false;
    }
}
