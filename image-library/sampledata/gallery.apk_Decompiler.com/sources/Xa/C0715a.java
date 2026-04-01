package xa;

import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.xmp.XmpUtils;
import java.util.function.Function;

/* renamed from: xa.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0715a implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3299a;
    public final /* synthetic */ FileItemInterface b;

    public /* synthetic */ C0715a(FileItemInterface fileItemInterface, int i2) {
        this.f3299a = i2;
        this.b = fileItemInterface;
    }

    public final Object apply(Object obj) {
        int i2 = this.f3299a;
        FileItemInterface fileItemInterface = this.b;
        Integer num = (Integer) obj;
        switch (i2) {
            case 0:
                return XmpUtils.lambda$getXmpTags$0(fileItemInterface, num);
            default:
                return XmpUtils.lambda$loadMotionPhotoPresentationTime$1(fileItemInterface, num);
        }
    }
}
