package com.samsung.android.gallery.plugins.portrait;

import android.content.DialogInterface;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class p implements DialogInterface.OnClickListener {
    public final /* synthetic */ Runnable d;

    public /* synthetic */ p(Runnable runnable) {
        this.d = runnable;
    }

    public final void onClick(DialogInterface dialogInterface, int i2) {
        SimpleThreadPool.getInstance().execute(this.d);
    }
}
