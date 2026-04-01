package com.samsung.android.gallery.plugins.filebrowser;

import com.samsung.android.gallery.plugins.filebrowser.LogViewFragment;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ f(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void run() {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                ((FileBaseFragment) obj).finishFragment();
                return;
            case 1:
                ((FilePreviewFragment) obj).finishFragment();
                return;
            default:
                ((LogViewFragment.LineViewAdapter) obj).lambda$setSelectMode$0();
                return;
        }
    }
}
