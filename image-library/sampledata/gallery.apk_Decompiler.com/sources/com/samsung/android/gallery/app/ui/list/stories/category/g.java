package com.samsung.android.gallery.app.ui.list.stories.category;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ g(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void run() {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                ((StoriesCategory2Header) obj).lambda$invalidateLayout$1();
                return;
            case 1:
                ((StoriesCategory2Header) obj).updateDivider();
                return;
            case 2:
                ((StoriesCategory2Header) obj).lambda$onLayoutChanged$0();
                return;
            default:
                ((IStoriesCategory2View) obj).startShrinkAnimation();
                return;
        }
    }
}
