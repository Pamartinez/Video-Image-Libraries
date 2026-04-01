package Ib;

import com.samsung.android.gallery.app.ui.dialog.creature.relationship.RelationshipChoiceAdapter;
import com.samsung.android.gallery.widget.listview.pinch.PinchItem;
import java.io.Serializable;
import java.util.function.IntPredicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements IntPredicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2826a;
    public final /* synthetic */ Object b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Serializable f2827c;

    public /* synthetic */ b(Object obj, Serializable serializable, int i2) {
        this.f2826a = i2;
        this.b = obj;
        this.f2827c = serializable;
    }

    public final boolean test(int i2) {
        switch (this.f2826a) {
            case 0:
                return ((PinchItem) this.b).lambda$isReverse$0((int[]) this.f2827c, i2);
            case 1:
                return ((PinchItem) this.b).lambda$isReverse$1((int[]) this.f2827c, i2);
            default:
                return ((RelationshipChoiceAdapter) this.b).lambda$loadPredefinedRelationship$0((String) this.f2827c, i2);
        }
    }
}
