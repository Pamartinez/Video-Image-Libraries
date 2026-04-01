package com.samsung.android.app.sdk.deepsky.objectcapture.ui.popup;

import android.view.animation.Interpolator;
import kotlin.Metadata;
import kotlin.jvm.internal.e;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\b\u0000\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0005H\u0002J\u0010\u0010\n\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005XD¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/popup/LogAccelerateInterpolator;", "Landroid/view/animation/Interpolator;", "<init>", "()V", "base", "", "logScale", "", "computeLog", "t", "getInterpolation", "Companion", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class LogAccelerateInterpolator implements Interpolator {
    public static final Companion Companion = new Companion((e) null);
    private static final String TAG = "LogAccelerateInterpolator";
    private final int base = 100;
    private final float logScale = (1.0f / computeLog(1.0f, 100));

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/popup/LogAccelerateInterpolator$Companion;", "", "<init>", "()V", "TAG", "", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    private final float computeLog(float f, int i2) {
        return (float) (((double) 1) - Math.pow((double) i2, -((double) f)));
    }

    public float getInterpolation(float f) {
        float f5 = (float) 1;
        return f5 - (computeLog(f5 - f, this.base) * this.logScale);
    }
}
