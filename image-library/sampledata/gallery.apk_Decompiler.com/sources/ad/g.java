package Ad;

import Ae.b;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest;
import me.x;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements b {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ g(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final Object invoke(Object obj) {
        VexFwkSessionRequest.BufferSetter bufferSetter = (VexFwkSessionRequest.BufferSetter) obj;
        switch (this.d) {
            case 0:
                bufferSetter.add(0, this.e);
                break;
            default:
                bufferSetter.add(0, this.e);
                break;
        }
        return x.f4917a;
    }
}
