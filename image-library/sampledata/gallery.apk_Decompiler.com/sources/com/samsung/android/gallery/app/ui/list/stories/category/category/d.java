package com.samsung.android.gallery.app.ui.list.stories.category.category;

import androidx.recyclerview.widget.LinearLayoutManager;
import com.samsung.android.gallery.app.ui.list.stories.category.category.categoryitem.StoriesCatItemAdapter;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Consumer {
    public final /* synthetic */ int d;

    public /* synthetic */ d(int i2) {
        this.d = i2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((LinearLayoutManager) obj).setOrientation(1);
                return;
            case 1:
                ((StoriesCatItemAdapter) obj).notifyItemRangeChanged(0, ((StoriesCatItemAdapter) obj).getItemCount(), "PAYLOAD_UPDATE_BADGE");
                return;
            case 2:
                ((StoriesCatItemAdapter) obj).notifyItemRangeChanged(0, ((StoriesCatItemAdapter) obj).getItemCount(), "select_mode");
                return;
            default:
                ((StoriesCatItemAdapter) obj).notifyItemRangeChanged(0, ((StoriesCatItemAdapter) obj).getItemCount(), "select_mode");
                return;
        }
    }
}
