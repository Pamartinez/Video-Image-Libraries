package com.samsung.android.gallery.settings.helper;

import android.widget.Toast;
import com.samsung.android.gallery.support.utils.AppResources;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ String d;

    public /* synthetic */ c(String str) {
        this.d = str;
    }

    public final void run() {
        Toast.makeText(AppResources.getAppContext(), this.d, 1).show();
    }
}
