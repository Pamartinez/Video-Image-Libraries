package com.samsung.android.gallery.app.ui.viewer2.selection;

import com.samsung.android.gallery.widget.filmstrip3.selection.SelectionFilmStripAdapter;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class n implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ boolean e;

    public /* synthetic */ n(boolean z, int i2) {
        this.d = i2;
        this.e = z;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        boolean z = this.e;
        switch (i2) {
            case 0:
                ((SelectionViewAdapter) obj).selectAll(z);
                return;
            default:
                ((SelectionFilmStripAdapter) obj).selectAll(z);
                return;
        }
    }
}
