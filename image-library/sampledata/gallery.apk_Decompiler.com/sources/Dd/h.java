package dd;

import Ae.b;
import android.view.textclassifier.TextClassification;
import com.samsung.android.app.sdk.deepsky.textextraction.textclassification.TextClassificationHelper;
import com.samsung.android.sesl.visualeffect.lighteffects.radialgrad.WiggleAnimationConfig;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements b {
    public final /* synthetic */ int d;
    public final /* synthetic */ b e;

    public /* synthetic */ h(int i2, b bVar) {
        this.d = i2;
        this.e = bVar;
    }

    public final Object invoke(Object obj) {
        int i2 = this.d;
        b bVar = this.e;
        switch (i2) {
            case 0:
                return WiggleAnimationConfig.buildListener$lambda$4$lambda$0(bVar, obj);
            case 1:
                return WiggleAnimationConfig.buildListener$lambda$4$lambda$1(bVar, obj);
            case 2:
                return WiggleAnimationConfig.buildListener$lambda$4$lambda$3$lambda$2(bVar, obj);
            default:
                return TextClassificationHelper.classifyTextWithTimeout$lambda$1(bVar, (TextClassification) obj);
        }
    }
}
