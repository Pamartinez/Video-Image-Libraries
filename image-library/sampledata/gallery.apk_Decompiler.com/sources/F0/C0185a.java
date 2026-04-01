package f0;

import androidx.recyclerview.widget.RecyclerView;

/* renamed from: f0.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0185a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ RecyclerView e;

    public /* synthetic */ C0185a(RecyclerView recyclerView, int i2) {
        this.d = i2;
        this.e = recyclerView;
    }

    public final void run() {
        int i2 = this.d;
        RecyclerView recyclerView = this.e;
        switch (i2) {
            case 0:
                recyclerView.invalidate();
                return;
            default:
                recyclerView.invalidateItemDecorations();
                return;
        }
    }
}
