package com.samsung.android.gallery.app.ui.list.search;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ CategoryLocation2CardHolder e;

    public /* synthetic */ g(CategoryLocation2CardHolder categoryLocation2CardHolder, int i2) {
        this.d = i2;
        this.e = categoryLocation2CardHolder;
    }

    public final void run() {
        int i2 = this.d;
        CategoryLocation2CardHolder categoryLocation2CardHolder = this.e;
        switch (i2) {
            case 0:
                categoryLocation2CardHolder.lambda$new$8();
                return;
            default:
                categoryLocation2CardHolder.lambda$setMapCacheImage$3();
                return;
        }
    }
}
