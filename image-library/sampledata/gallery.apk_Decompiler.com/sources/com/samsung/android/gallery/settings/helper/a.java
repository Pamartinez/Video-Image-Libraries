package com.samsung.android.gallery.settings.helper;

import android.widget.Toast;
import com.samsung.android.gallery.settings.R$string;
import com.samsung.android.gallery.support.utils.AppResources;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;

    public /* synthetic */ a(int i2) {
        this.d = i2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                Toast.makeText(AppResources.getAppContext(), R$string.ts_guide_null_cloud, 0).show();
                return;
            default:
                Toast.makeText(AppResources.getAppContext(), R$string.ts_guide_hide_album, 1).show();
                return;
        }
    }
}
