package O9;

import com.samsung.android.gallery.app.ui.viewer2.aiedit.items.AbsRemasterAiEdit;
import java.util.ArrayList;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2859a;
    public final /* synthetic */ ArrayList b;

    public /* synthetic */ a(ArrayList arrayList, int i2) {
        this.f2859a = i2;
        this.b = arrayList;
    }

    public final boolean test(Object obj) {
        int i2 = this.f2859a;
        ArrayList arrayList = this.b;
        switch (i2) {
            case 0:
                return arrayList.contains((Long) obj);
            case 1:
                return arrayList.contains(((AbsRemasterAiEdit) obj).getEstimatorText());
            default:
                return arrayList.contains((Integer) obj);
        }
    }
}
