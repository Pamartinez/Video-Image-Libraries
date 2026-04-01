package ld;

import Ae.b;
import com.samsung.android.vexfwk.param.VexFwkBokehEffectSupportedFeatures;
import com.samsung.android.vexfwk.param.VexFwkDocumentDetectionSupportedTypes;
import com.samsung.android.vexfwk.param.VexFwkDocumentRefinerSupportedModes;
import com.samsung.android.vexfwk.param.VexFwkObjectRemoverSupportedModes;
import com.samsung.android.vexfwk.param.VexFwkUnifiedDetectorSupportedModes;
import java.nio.IntBuffer;

/* renamed from: ld.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C1145a implements b {
    public final /* synthetic */ int d;
    public final /* synthetic */ IntBuffer e;

    public /* synthetic */ C1145a(IntBuffer intBuffer, int i2) {
        this.d = i2;
        this.e = intBuffer;
    }

    public final Object invoke(Object obj) {
        int i2 = this.d;
        int intValue = ((Integer) obj).intValue();
        IntBuffer intBuffer = this.e;
        switch (i2) {
            case 0:
                return VexFwkBokehEffectSupportedFeatures.Companion.fromBuffer$lambda$1(intBuffer, intValue);
            case 1:
                return VexFwkDocumentDetectionSupportedTypes.Companion.fromBuffer$lambda$1(intBuffer, intValue);
            case 2:
                return VexFwkDocumentRefinerSupportedModes.Companion.fromBuffer$lambda$1(intBuffer, intValue);
            case 3:
                return VexFwkObjectRemoverSupportedModes.Companion.fromBuffer$lambda$1(intBuffer, intValue);
            default:
                return VexFwkUnifiedDetectorSupportedModes.Companion.fromBuffer$lambda$1(intBuffer, intValue);
        }
    }
}
