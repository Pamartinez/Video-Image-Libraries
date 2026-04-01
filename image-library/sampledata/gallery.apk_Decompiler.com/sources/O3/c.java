package O3;

import android.util.Pair;
import com.samsung.android.gallery.app.controller.internals.ChangeBestItemCmd2;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.groupshot.GroupShotFormat;
import com.samsung.android.sum.core.buffer.BufferExtension;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2403a;
    public final /* synthetic */ Object b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f2404c;
    public final /* synthetic */ Object d;
    public final /* synthetic */ Object e;

    public /* synthetic */ c(Object obj, Object obj2, Object obj3, Object obj4, int i2) {
        this.f2403a = i2;
        this.b = obj;
        this.f2404c = obj2;
        this.d = obj3;
        this.e = obj4;
    }

    public final Object apply(Object obj) {
        switch (this.f2403a) {
            case 0:
                return ((ChangeBestItemCmd2) this.b).lambda$onExecute$1((MediaItem[]) this.f2404c, (GroupShotFormat) this.d, (double[]) this.e, (FileItemInterface) obj);
            case 1:
                return ((BufferExtension) this.b).lambda$findAvailableBinaryKey$48((List) this.f2404c, (String) this.d, (Map) this.e, (Pair) obj);
            default:
                return ((BufferExtension) this.b).lambda$findAvailableBinaryKey$47((Pair) this.f2404c, (String) this.d, (Map) this.e, (Pair) obj);
        }
    }
}
