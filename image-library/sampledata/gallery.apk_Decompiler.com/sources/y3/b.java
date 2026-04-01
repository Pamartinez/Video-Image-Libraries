package y3;

import com.samsung.android.app.sdk.deepsky.textextraction.translate.ImageTranslateListener;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.engine.LttV5ImageTranslationEngine;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.engine.LttV5ImageTranslationEngine$initLttEngineClient$1;
import com.samsung.android.imagetranslation.LttEngineClient;
import java.util.concurrent.CountDownLatch;
import me.x;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Ae.b {
    public final /* synthetic */ int d;
    public final /* synthetic */ LttEngineClient e;
    public final /* synthetic */ ImageTranslateListener f;
    public final /* synthetic */ CountDownLatch g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ CountDownLatch f2142h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ LttV5ImageTranslationEngine f2143i;

    public /* synthetic */ b(int i2, LttEngineClient lttEngineClient, ImageTranslateListener imageTranslateListener, CountDownLatch countDownLatch, CountDownLatch countDownLatch2, LttV5ImageTranslationEngine lttV5ImageTranslationEngine) {
        this.d = i2;
        this.e = lttEngineClient;
        this.f = imageTranslateListener;
        this.g = countDownLatch;
        this.f2142h = countDownLatch2;
        this.f2143i = lttV5ImageTranslationEngine;
    }

    public final Object invoke(Object obj) {
        return LttV5ImageTranslationEngine$initLttEngineClient$1.onFailure$lambda$1(this.d, this.e, this.f, this.g, this.f2142h, this.f2143i, (x) obj);
    }
}
