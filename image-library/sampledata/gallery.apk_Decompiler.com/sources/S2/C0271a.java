package s2;

import androidx.core.widget.NestedScrollView;
import java.util.Iterator;
import kotlin.jvm.internal.j;
import q2.r;

/* renamed from: s2.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0271a implements NestedScrollView.OnScrollChangeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C0272b f1914a;

    public /* synthetic */ C0271a(C0272b bVar) {
        this.f1914a = bVar;
    }

    public final void a(NestedScrollView nestedScrollView, int i2, int i7, int i8, int i10) {
        j.e(nestedScrollView, "v");
        int i11 = i7 - i10;
        Iterator it = this.f1914a.e.iterator();
        while (it.hasNext()) {
            ((r) it.next()).a(i11);
        }
    }
}
