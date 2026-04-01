package eb;

import com.samsung.android.gallery.widget.animations.SimpleAutoScroller;

/* renamed from: eb.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0688b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ SimpleAutoScroller e;

    public /* synthetic */ C0688b(SimpleAutoScroller simpleAutoScroller, int i2) {
        this.d = i2;
        this.e = simpleAutoScroller;
    }

    public final void run() {
        int i2 = this.d;
        SimpleAutoScroller simpleAutoScroller = this.e;
        switch (i2) {
            case 0:
                simpleAutoScroller.lambda$start$1();
                return;
            case 1:
                simpleAutoScroller.lambda$findAndShrink$9();
                return;
            case 2:
                simpleAutoScroller.lambda$start$0();
                return;
            case 3:
                simpleAutoScroller.lambda$start$2();
                return;
            case 4:
                simpleAutoScroller.lambda$start$3();
                return;
            case 5:
                simpleAutoScroller.notifyStartListener();
                return;
            case 6:
                simpleAutoScroller.lambda$tryStartShrink$8();
                return;
            case 7:
                simpleAutoScroller.lambda$onLayoutChange$4();
                return;
            default:
                simpleAutoScroller.lambda$onLayoutChange$5();
                return;
        }
    }
}
