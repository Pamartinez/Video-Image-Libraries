package Ad;

import Ae.c;
import L2.a;
import c0.C0086a;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataKey;
import com.samsung.android.vexfwk.param.VexFwkNodeResultCode;
import com.samsung.android.vexfwk.param.VexFwkUnifiedDetectorResult;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionTotalResult;
import com.samsung.android.vexfwk.sdk.unifiedDetector.VexFwkUnifiedDetector;
import me.x;
import qe.C1227c;
import re.C1245a;
import se.i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class k extends i implements c {
    public /* synthetic */ Object d;

    /* JADX WARNING: type inference failed for: r1v1, types: [se.i, qe.c, Ad.k] */
    public final C1227c create(Object obj, C1227c cVar) {
        ? iVar = new i(2, cVar);
        iVar.d = obj;
        return iVar;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((k) create(new me.k(((me.k) obj).d), (C1227c) obj2)).invokeSuspend(x.f4917a);
    }

    public final Object invokeSuspend(Object obj) {
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        a.A(obj);
        Object obj2 = ((me.k) this.d).d;
        Throwable a7 = me.k.a(obj2);
        if (a7 == null) {
            VexFwkSessionTotalResult vexFwkSessionTotalResult = (VexFwkSessionTotalResult) obj2;
            if (((VexFwkNodeResultCode) vexFwkSessionTotalResult.getResultMetadata().get(VexFwkMetadataKey.RESULT_CODE.INSTANCE)) == VexFwkNodeResultCode.OK) {
                return (VexFwkUnifiedDetectorResult) vexFwkSessionTotalResult.getResultMetadata().getOrElse(VexFwkMetadataKey.UNIFIED_DETECTOR_RESULT.INSTANCE, new j(0));
            }
            throw new IllegalStateException("Failed to get unified detector result");
        }
        C0086a.x("Failed to process request : ", VexFwkUnifiedDetector.TAG, a7);
        throw a7;
    }
}
