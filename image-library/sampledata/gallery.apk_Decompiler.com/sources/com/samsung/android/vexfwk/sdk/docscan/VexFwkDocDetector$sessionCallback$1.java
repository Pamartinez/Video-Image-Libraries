package com.samsung.android.vexfwk.sdk.docscan;

import L2.a;
import android.media.Image;
import android.util.Log;
import com.samsung.android.vexfwk.sdk.common.runtime.IVexFwkSessionCallback;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionPartialResult;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionTotalResult;
import com.samsung.android.vexfwk.sdk.docscan.VexFwkDocDetector;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import me.i;
import me.k;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"com/samsung/android/vexfwk/sdk/docscan/VexFwkDocDetector$sessionCallback$1", "Lcom/samsung/android/vexfwk/sdk/common/runtime/IVexFwkSessionCallback;", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionTotalResult;", "totalResult", "Lme/x;", "onRequestCompleted", "(Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionTotalResult;)V", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkDocDetector$sessionCallback$1 implements IVexFwkSessionCallback {
    final /* synthetic */ VexFwkDocDetector this$0;

    public VexFwkDocDetector$sessionCallback$1(VexFwkDocDetector vexFwkDocDetector) {
        this.this$0 = vexFwkDocDetector;
    }

    public void onRequestCompleted(VexFwkSessionTotalResult vexFwkSessionTotalResult) {
        Object obj;
        j.e(vexFwkSessionTotalResult, "totalResult");
        IVexFwkSessionCallback.DefaultImpls.onRequestCompleted(this, vexFwkSessionTotalResult);
        i iVar = (i) this.this$0.requestMap.remove(Integer.valueOf(vexFwkSessionTotalResult.getRequestNumber()));
        if (iVar != null) {
            try {
                obj = VexFwkDocDetector.Companion.createDetectResult(vexFwkSessionTotalResult.getResultMetadata());
            } catch (Throwable th) {
                obj = a.l(th);
            }
            if (!(obj instanceof me.j)) {
                ((VexFwkDocDetector.Callback) iVar.d).onDetected((Image) iVar.e, (VexFwkDocDetector.DetectResult) obj);
            }
            Throwable a7 = k.a(obj);
            if (a7 != null) {
                String access$getTAG$cp = VexFwkDocDetector.TAG;
                Log.w(access$getTAG$cp, "onRequestCompleted : createDetectResult failed, " + a7);
            }
        }
    }

    public void onRequestProgressed(VexFwkSessionPartialResult vexFwkSessionPartialResult) {
        IVexFwkSessionCallback.DefaultImpls.onRequestProgressed(this, vexFwkSessionPartialResult);
    }
}
