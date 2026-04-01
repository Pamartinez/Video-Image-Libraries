package com.samsung.android.gallery.app.ui.list.abstraction;

import com.samsung.android.gallery.widget.dragdrop.clipdata.ClipDataManager;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class m implements Runnable {
    public final void run() {
        ClipDataManager.getInstance().runDeferredUpdate();
    }
}
