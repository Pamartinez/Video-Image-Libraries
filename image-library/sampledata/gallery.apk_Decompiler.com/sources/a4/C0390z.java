package A4;

import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import java.util.function.BooleanSupplier;

/* renamed from: A4.z  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0390z implements BooleanSupplier {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2262a;
    public final /* synthetic */ IBaseListView b;

    public /* synthetic */ C0390z(IBaseListView iBaseListView, int i2) {
        this.f2262a = i2;
        this.b = iBaseListView;
    }

    public final boolean getAsBoolean() {
        int i2 = this.f2262a;
        IBaseListView iBaseListView = this.b;
        switch (i2) {
            case 0:
                return iBaseListView.useAdvancedMouseDragAndDrop();
            default:
                return iBaseListView.isAllowAdvancedMouseEvent();
        }
    }
}
