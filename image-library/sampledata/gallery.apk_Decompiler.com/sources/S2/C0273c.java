package s2;

import androidx.recyclerview.widget.RecyclerView;
import java.util.Iterator;
import kotlin.jvm.internal.j;
import q2.r;

/* renamed from: s2.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0273c extends RecyclerView.OnScrollListener {
    public final /* synthetic */ C0274d d;

    public C0273c(C0274d dVar) {
        this.d = dVar;
    }

    public final void onScrolled(RecyclerView recyclerView, int i2, int i7) {
        j.e(recyclerView, "recyclerView");
        Iterator it = this.d.e.iterator();
        while (it.hasNext()) {
            ((r) it.next()).a(i7);
        }
    }
}
