package Ad;

import Ae.c;
import L2.a;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataKey;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataNative;
import com.samsung.android.vexfwk.param.VexFwkUnifiedDetectorMode;
import me.x;
import qe.C1227c;
import re.C1245a;
import se.i;

/* renamed from: Ad.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0722c extends i implements c {
    public /* synthetic */ Object d;
    public final /* synthetic */ VexFwkUnifiedDetectorMode e;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0722c(VexFwkUnifiedDetectorMode vexFwkUnifiedDetectorMode, C1227c cVar) {
        super(2, cVar);
        this.e = vexFwkUnifiedDetectorMode;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        C0722c cVar2 = new C0722c(this.e, cVar);
        cVar2.d = obj;
        return cVar2;
    }

    public final Object invoke(Object obj, Object obj2) {
        x xVar = x.f4917a;
        ((C0722c) create((VexFwkMetadataNative) obj, (C1227c) obj2)).invokeSuspend(xVar);
        return xVar;
    }

    public final Object invokeSuspend(Object obj) {
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        a.A(obj);
        ((VexFwkMetadataNative) this.d).set(VexFwkMetadataKey.UNIFIED_DETECTOR_MODE.INSTANCE, this.e);
        return x.f4917a;
    }
}
