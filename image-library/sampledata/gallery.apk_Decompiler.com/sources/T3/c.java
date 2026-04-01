package T3;

import com.samsung.android.gallery.app.controller.sharing.request.AbsRequest;
import com.samsung.android.gallery.app.controller.sharing.request.RequestDeleteGroup;
import com.samsung.android.gallery.app.controller.sharing.request.RequestDeleteSpace;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import java.util.List;
import java.util.function.IntConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements IntConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2435a;
    public final /* synthetic */ AbsRequest b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f2436c;
    public final /* synthetic */ Object d;

    public /* synthetic */ c(AbsRequest absRequest, Object obj, Object obj2, int i2) {
        this.f2435a = i2;
        this.b = absRequest;
        this.f2436c = obj;
        this.d = obj2;
    }

    public final void accept(int i2) {
        switch (this.f2435a) {
            case 0:
                ((RequestDeleteGroup) this.b).lambda$requestDeleteGroup$0((String) this.f2436c, (List) this.d, i2);
                return;
            default:
                ((RequestDeleteSpace) this.b).lambda$requestDeleteSpace$1((Integer) this.f2436c, (FileItemInterface) this.d, i2);
                return;
        }
    }
}
