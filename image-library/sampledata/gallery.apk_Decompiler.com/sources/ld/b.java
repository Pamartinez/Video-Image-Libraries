package ld;

import com.samsung.android.vexfwk.param.VexFwkImageTaggerV2Result;
import com.samsung.android.vexfwk.param.VexFwkObjectRemoverMode;
import com.samsung.android.vexfwk.param.VexFwkOcrAdditionalMeta;
import com.samsung.android.vexfwk.param.VexFwkOcrResultMeta;
import com.samsung.android.vexfwk.param.VexFwkOcrResultMetaV2;
import com.samsung.android.vexfwk.param.VexFwkUnifiedDetectorMode;
import com.samsung.android.vexfwk.param.VexFwkUnifiedDetectorResult;
import com.samsung.android.vexfwk.sdk.imagetranslator.VexFwkImageTranslatorV2;
import java.util.HashSet;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Supplier {
    public final /* synthetic */ int d;

    public /* synthetic */ b(int i2) {
        this.d = i2;
    }

    public final Object get() {
        switch (this.d) {
            case 0:
                return VexFwkImageTaggerV2Result.Companion._init_$lambda$0();
            case 1:
                return VexFwkObjectRemoverMode.Companion._init_$lambda$0();
            case 2:
                return VexFwkOcrAdditionalMeta.Companion._init_$lambda$0();
            case 3:
                return VexFwkOcrResultMeta.Companion._init_$lambda$0();
            case 4:
                return VexFwkOcrResultMetaV2.Companion._init_$lambda$0();
            case 5:
                return VexFwkUnifiedDetectorMode.Companion._init_$lambda$0();
            case 6:
                return VexFwkUnifiedDetectorResult.Companion._init_$lambda$0();
            case 7:
                return new HashSet();
            default:
                return VexFwkImageTranslatorV2.isAvailableSize$lambda$0();
        }
    }
}
