package Ad;

import Ae.c;
import L2.a;
import android.content.Context;
import android.util.Log;
import com.samsung.android.vexfwk.param.VexFwkOrientation;
import com.samsung.android.vexfwk.sdk.common.VexFwkHelperProcess;
import com.samsung.android.vexfwk.sdk.imagetranslator.VexFwkImageTranslatorV2;
import com.samsung.android.vexfwk.sdk.unifiedDetector.VexFwkUnifiedDetector;
import java.util.List;
import me.x;
import qe.C1227c;
import re.C1245a;
import se.i;
import vd.v;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class l extends i implements c {
    public final /* synthetic */ int d = 0;
    public /* synthetic */ Object e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public l(VexFwkOrientation vexFwkOrientation, Object obj, C1227c cVar) {
        super(2, cVar);
        this.g = vexFwkOrientation;
        this.f = obj;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        switch (this.d) {
            case 0:
                l lVar = new l((VexFwkOrientation) this.g, this.f, cVar);
                lVar.e = obj;
                return lVar;
            default:
                l lVar2 = new l((List) this.f, (Context) this.g, cVar);
                lVar2.e = obj;
                return lVar2;
        }
    }

    public final Object invoke(Object obj, Object obj2) {
        VexFwkHelperProcess vexFwkHelperProcess = (VexFwkHelperProcess) obj;
        C1227c cVar = (C1227c) obj2;
        switch (this.d) {
            case 0:
                x xVar = x.f4917a;
                ((l) create(vexFwkHelperProcess, cVar)).invokeSuspend(xVar);
                return xVar;
            default:
                x xVar2 = x.f4917a;
                ((l) create(vexFwkHelperProcess, cVar)).invokeSuspend(xVar2);
                return xVar2;
        }
    }

    /* JADX WARNING: type inference failed for: r7v3, types: [Ae.c, se.i] */
    /* JADX WARNING: type inference failed for: r7v6, types: [Ae.c, se.i] */
    public final Object invokeSuspend(Object obj) {
        int i2 = this.d;
        x xVar = x.f4917a;
        Object obj2 = this.g;
        Object obj3 = this.f;
        switch (i2) {
            case 0:
                C1245a aVar = C1245a.COROUTINE_SUSPENDED;
                a.A(obj);
                VexFwkHelperProcess vexFwkHelperProcess = (VexFwkHelperProcess) this.e;
                Log.d(VexFwkUnifiedDetector.TAG, "detectImpl E");
                vexFwkHelperProcess.setRequest(new i((VexFwkOrientation) obj2, obj3, (C1227c) null));
                vexFwkHelperProcess.setFinally(new i(2, (C1227c) null));
                Log.d(VexFwkUnifiedDetector.TAG, "detectImpl X");
                return xVar;
            default:
                C1245a aVar2 = C1245a.COROUTINE_SUSPENDED;
                a.A(obj);
                VexFwkHelperProcess vexFwkHelperProcess2 = (VexFwkHelperProcess) this.e;
                Log.i(VexFwkImageTranslatorV2.TAG, "showInstallPopupAsync");
                vexFwkHelperProcess2.setRequest(new i(2, (C1227c) null));
                vexFwkHelperProcess2.setFinally(new v((List) obj3, (Context) obj2, (C1227c) null));
                return xVar;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public l(List list, Context context, C1227c cVar) {
        super(2, cVar);
        this.f = list;
        this.g = context;
    }
}
