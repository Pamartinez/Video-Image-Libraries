package y3;

import Ae.b;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.ImageTranslateListener;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.engine.LttImageTranslationEngine;
import me.x;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements b {
    public final /* synthetic */ int d;
    public final /* synthetic */ ImageTranslateListener e;

    public /* synthetic */ a(ImageTranslateListener imageTranslateListener, int i2) {
        this.d = i2;
        this.e = imageTranslateListener;
    }

    public final Object invoke(Object obj) {
        int i2 = this.d;
        ImageTranslateListener imageTranslateListener = this.e;
        switch (i2) {
            case 0:
                return LttImageTranslationEngine.doImageTranslation$lambda$3$lambda$0(imageTranslateListener, (x) obj);
            case 1:
                return LttImageTranslationEngine.doImageTranslation$lambda$3$lambda$1(imageTranslateListener, (x) obj);
            default:
                return LttImageTranslationEngine.doImageTranslation$lambda$5(imageTranslateListener, (Throwable) obj);
        }
    }
}
