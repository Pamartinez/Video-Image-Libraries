package com.samsung.android.gallery.app.ui.list.stories.category.ondemand;

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
                ((Consumer) obj).accept(Boolean.TRUE);
                return;
            case 1:
                ((Consumer) obj).accept(Boolean.FALSE);
                return;
            default:
                ((PdcRecommendAdapter) obj).setItemSelectListener((Consumer<Boolean>) null);
                return;
        }
    }
}
