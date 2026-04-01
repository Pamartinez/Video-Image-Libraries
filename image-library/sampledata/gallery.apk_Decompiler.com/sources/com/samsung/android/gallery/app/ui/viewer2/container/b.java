package com.samsung.android.gallery.app.ui.viewer2.container;

import android.view.View;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ View d;

    public /* synthetic */ b(View view) {
        this.d = view;
    }

    public final void run() {
        ViewUtils.setTranslationZ(this.d, 0.0f);
    }
}
