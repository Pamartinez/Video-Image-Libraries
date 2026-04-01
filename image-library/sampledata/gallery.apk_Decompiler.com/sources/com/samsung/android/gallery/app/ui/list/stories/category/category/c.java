package com.samsung.android.gallery.app.ui.list.stories.category.category;

import androidx.recyclerview.widget.LinearLayoutManager;
import com.samsung.android.gallery.app.ui.list.stories.category.category.categoryitem.StoriesCatItemAdapter;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ int e;

    public /* synthetic */ c(int i2, int i7) {
        this.d = i7;
        this.e = i2;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        int i7 = this.e;
        switch (i2) {
            case 0:
                ((LinearLayoutManager) obj).setOrientation(i7);
                return;
            default:
                ((StoriesCatItemAdapter) obj).notifyItemRangeChanged(i7, 1, "select_mode");
                return;
        }
    }
}
