package com.samsung.android.gallery.module.lottie.recap.binder;

import com.samsung.android.gallery.module.lottie.recap.data.parser.AnalyzedData;
import com.samsung.android.gallery.module.lottie.recap.template.element.RecapTextLayer;
import com.samsung.android.gallery.support.utils.TimeUtil;
import java.util.function.BiFunction;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements BiFunction {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3033a;

    public /* synthetic */ e(int i2) {
        this.f3033a = i2;
    }

    public final Object apply(Object obj, Object obj2) {
        AnalyzedData analyzedData = (AnalyzedData) obj;
        RecapTextLayer recapTextLayer = (RecapTextLayer) obj2;
        switch (this.f3033a) {
            case 0:
                return TimeUtil.toLocalDate(RecapDataVars.getImageTime(recapTextLayer), "MMMM_UTC");
            case 1:
                return TimeUtil.getBasicIsoUtcDate(RecapDataVars.getImageTime(recapTextLayer)).substring(0, 4);
            case 2:
                return TimeUtil.getBasicIsoUtcDate(RecapDataVars.getImageTime(recapTextLayer)).substring(4, 8);
            case 3:
                return TimeUtil.getBasicIsoUtcDate(RecapDataVars.getImageTime(recapTextLayer), ".");
            case 4:
                return TimeUtil.toLocalDate(RecapDataVars.getImageTime(recapTextLayer), "E_UTC");
            case 5:
                return RecapDataVars.lambda$static$29(analyzedData, recapTextLayer);
            case 6:
                return RecapDataVars.updatePeopleName(recapTextLayer, 0);
            case 7:
                return RecapDataVars.updatePeopleName(recapTextLayer, 1);
            case 8:
                return RecapDataVars.updatePeopleName(recapTextLayer, 2);
            case 9:
                return RecapDataVars.lambda$static$34(analyzedData, recapTextLayer);
            case 10:
                return RecapDataVars.lambda$static$35(analyzedData, recapTextLayer);
            case 11:
                return RecapDataVars.lambda$static$36(analyzedData, recapTextLayer);
            default:
                return RecapDataVars.lambda$static$42(analyzedData, recapTextLayer);
        }
    }
}
