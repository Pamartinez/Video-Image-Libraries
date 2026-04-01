package com.samsung.android.gallery.module.receiver;

import android.content.Context;
import android.net.Uri;
import com.samsung.android.gallery.module.mtp.UsbStorageState;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.ArrayList;
import java.util.Collections;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class UsbStorageObserver extends AbsContentObserver {
    public UsbStorageObserver() {
        super(ThreadUtil.getBgThreadHandler());
    }

    public ArrayList<Uri> getWatchUris() {
        if (Features.isEnabled(Features.SUPPORT_USB_STORAGE)) {
            return new ArrayList<>(Collections.singletonList(Uri.parse("content://com.samsung.android.providers.otg/volumes")));
        }
        return null;
    }

    public void onChange(boolean z) {
        UsbStorageState.getInstance().notifyIfChanged();
    }

    public boolean registerObserver(Context context) {
        if (!super.registerObserver(context)) {
            return false;
        }
        UsbStorageState.getInstance().notifyIfChanged();
        return true;
    }
}
