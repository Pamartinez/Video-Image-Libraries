package com.samsung.android.gallery.app.ui.list.picker.search;

import com.samsung.android.gallery.support.utils.ThreadUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ Runnable d;

    public /* synthetic */ a(Runnable runnable) {
        this.d = runnable;
    }

    public final void run() {
        ThreadUtil.runOnUiThread(this.d);
    }
}
