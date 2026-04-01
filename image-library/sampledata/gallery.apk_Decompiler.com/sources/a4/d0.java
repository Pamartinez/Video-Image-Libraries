package A4;

import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import java.util.List;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d0 implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2245a;
    public final /* synthetic */ List b;

    public /* synthetic */ d0(int i2, List list) {
        this.f2245a = i2;
        this.b = list;
    }

    public final boolean test(Object obj) {
        Object obj2;
        int i2 = this.f2245a;
        List list = this.b;
        switch (i2) {
            case 0:
                obj2 = (PreviewViewHolder) obj;
                break;
            default:
                obj2 = (Integer) obj;
                break;
        }
        return list.contains(obj2);
    }
}
