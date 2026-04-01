package com.samsung.android.gallery.plugins.filebrowser;

import android.view.View;
import android.view.WindowInsets;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements View.OnApplyWindowInsetsListener {
    public final /* synthetic */ FileBaseFragment d;

    public /* synthetic */ c(FileBaseFragment fileBaseFragment) {
        this.d = fileBaseFragment;
    }

    public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        return this.d.onApplyWindowInsets(view, windowInsets);
    }
}
