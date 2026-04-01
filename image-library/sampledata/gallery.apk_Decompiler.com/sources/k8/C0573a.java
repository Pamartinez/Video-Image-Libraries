package K8;

import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.c2pa.C2paScsImpl;
import com.samsung.android.visual.ai.sdkcommon.c;
import com.samsung.android.visual.ai.sdkcommon.o;
import java.util.function.Consumer;

/* renamed from: K8.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0573a implements Consumer {
    public final /* synthetic */ C2paScsImpl d;
    public final /* synthetic */ c e;
    public final /* synthetic */ String f;
    public final /* synthetic */ FileItemInterface g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ FileItemInterface f2834h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ boolean f2835i;

    public /* synthetic */ C0573a(C2paScsImpl c2paScsImpl, c cVar, String str, FileItemInterface fileItemInterface, FileItemInterface fileItemInterface2, boolean z) {
        this.d = c2paScsImpl;
        this.e = cVar;
        this.f = str;
        this.g = fileItemInterface;
        this.f2834h = fileItemInterface2;
        this.f2835i = z;
    }

    public final void accept(Object obj) {
        this.d.lambda$create$1(this.e, this.f, this.g, this.f2834h, this.f2835i, (o) obj);
    }
}
