package nb;

import com.samsung.android.gallery.widget.bottom.BottomTabLayout;

/* renamed from: nb.d  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0703d implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ BottomTabLayout e;

    public /* synthetic */ C0703d(BottomTabLayout bottomTabLayout, int i2) {
        this.d = i2;
        this.e = bottomTabLayout;
    }

    public final void run() {
        int i2 = this.d;
        BottomTabLayout bottomTabLayout = this.e;
        switch (i2) {
            case 0:
                bottomTabLayout.lambda$onLayout$0();
                return;
            default:
                bottomTabLayout.lambda$invalidateTabLayout$1();
                return;
        }
    }
}
