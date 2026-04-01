package O3;

import com.samsung.android.gallery.app.controller.internals.ChangeLocationCmd2;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ChangeLocationCmd2 f2410a;
    public final /* synthetic */ double b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ double f2411c;

    public /* synthetic */ i(ChangeLocationCmd2 changeLocationCmd2, double d, double d2) {
        this.f2410a = changeLocationCmd2;
        this.b = d;
        this.f2411c = d2;
    }

    public final Object apply(Object obj) {
        return this.f2410a.lambda$changeLocation$3(this.b, this.f2411c, (FileItemInterface) obj);
    }
}
