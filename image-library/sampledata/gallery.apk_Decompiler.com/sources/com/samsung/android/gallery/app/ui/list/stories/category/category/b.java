package com.samsung.android.gallery.app.ui.list.stories.category.category;

import com.samsung.android.gallery.app.ui.list.stories.category.category.categoryitem.StoriesCatItemAdapter;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ StoriesCatViewHolder e;

    public /* synthetic */ b(StoriesCatViewHolder storiesCatViewHolder, int i2) {
        this.d = i2;
        this.e = storiesCatViewHolder;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        StoriesCatViewHolder storiesCatViewHolder = this.e;
        switch (i2) {
            case 0:
                ((StoriesCatOnDemandViewHolder) storiesCatViewHolder).onRecommendItemClicked((String) obj);
                return;
            default:
                storiesCatViewHolder.lambda$onDataChangedOnUi$0((StoriesCatItemAdapter) obj);
                return;
        }
    }
}
