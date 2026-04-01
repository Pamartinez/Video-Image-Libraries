package ce;

import com.samsung.android.sdk.scs.ai.visual.c2pa.C2paClient;
import com.samsung.android.sdk.scs.base.tasks.OnCompleteListener;
import com.samsung.android.sdk.scs.base.tasks.Task;
import com.sec.longexposure.LongExposureC2paUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements OnCompleteListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ C2paClient e;

    public /* synthetic */ a(C2paClient c2paClient, int i2) {
        this.d = i2;
        this.e = c2paClient;
    }

    public final void onComplete(Task task) {
        int i2 = this.d;
        C2paClient c2paClient = this.e;
        switch (i2) {
            case 0:
                LongExposureC2paUtil.lambda$hasC2paManifest$0(c2paClient, task);
                return;
            default:
                LongExposureC2paUtil.lambda$manifestToFile$1(c2paClient, task);
                return;
        }
    }
}
