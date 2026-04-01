package com.samsung.android.vexfwk.sdk.docscan;

import android.util.Log;
import com.samsung.android.vexfwk.sdk.common.runtime.IVexFwkSessionCallback;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionPartialResult;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionTotalResult;
import com.samsung.android.vexfwk.sdk.docscan.VexFwkDocRefiner;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import me.i;

@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\t\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"com/samsung/android/vexfwk/sdk/docscan/VexFwkDocRefiner$sessionCallback$1", "Lcom/samsung/android/vexfwk/sdk/common/runtime/IVexFwkSessionCallback;", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionPartialResult;", "partialResult", "Lme/x;", "onRequestProgressed", "(Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionPartialResult;)V", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionTotalResult;", "totalResult", "onRequestCompleted", "(Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionTotalResult;)V", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkDocRefiner$sessionCallback$1 implements IVexFwkSessionCallback {
    final /* synthetic */ VexFwkDocRefiner this$0;

    public VexFwkDocRefiner$sessionCallback$1(VexFwkDocRefiner vexFwkDocRefiner) {
        this.this$0 = vexFwkDocRefiner;
    }

    /* access modifiers changed from: private */
    public static final void onRequestCompleted$lambda$3(VexFwkDocRefiner vexFwkDocRefiner, i iVar) {
        VexFwkDocRefiner.VexFwkBatchRefineCallback mCallback = vexFwkDocRefiner.getMCallback();
        if (mCallback != null) {
            mCallback.onAllTasksCompleted(iVar);
        }
    }

    /* access modifiers changed from: private */
    public static final void onRequestProgressed$lambda$1(VexFwkDocRefiner vexFwkDocRefiner, i iVar) {
        VexFwkDocRefiner.VexFwkBatchRefineCallback mCallback = vexFwkDocRefiner.getMCallback();
        if (mCallback != null) {
            mCallback.onTaskCompleted(iVar);
        }
    }

    public void onRequestCompleted(VexFwkSessionTotalResult vexFwkSessionTotalResult) {
        j.e(vexFwkSessionTotalResult, "totalResult");
        IVexFwkSessionCallback.DefaultImpls.onRequestCompleted(this, vexFwkSessionTotalResult);
        Log.i(VexFwkDocRefiner.TAG, "onRequestCompleted E");
        i access$handleProcessResult = this.this$0.handleProcessResult(vexFwkSessionTotalResult);
        Executor mExecutor = this.this$0.getMExecutor();
        if (mExecutor != null) {
            mExecutor.execute(new d(this.this$0, access$handleProcessResult, 0));
        }
    }

    public void onRequestProgressed(VexFwkSessionPartialResult vexFwkSessionPartialResult) {
        j.e(vexFwkSessionPartialResult, "partialResult");
        IVexFwkSessionCallback.DefaultImpls.onRequestProgressed(this, vexFwkSessionPartialResult);
        Log.i(VexFwkDocRefiner.TAG, "onRequestProgressed E");
        i access$handleProcessResult = this.this$0.handleProcessResult(vexFwkSessionPartialResult);
        Executor mExecutor = this.this$0.getMExecutor();
        if (mExecutor != null) {
            mExecutor.execute(new d(this.this$0, access$handleProcessResult, 1));
        }
    }
}
