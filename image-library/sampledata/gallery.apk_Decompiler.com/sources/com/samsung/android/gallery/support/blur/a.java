package com.samsung.android.gallery.support.blur;

import com.samsung.android.gallery.support.blur.BlurEffectHolder;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3132a;
    public final /* synthetic */ BlurParams b;

    public /* synthetic */ a(BlurParams blurParams, int i2) {
        this.f3132a = i2;
        this.b = blurParams;
    }

    public final Object apply(Object obj) {
        int i2 = this.f3132a;
        BlurParams blurParams = this.b;
        BlurEffectHolder.Key key = (BlurEffectHolder.Key) obj;
        switch (i2) {
            case 0:
                return BlurEffectHolder.getRenderEffect(blurParams);
            case 1:
                return BlurEffectHolder.getRenderEffect(blurParams);
            default:
                return BlurEffectHolder.getRenderEffect(blurParams);
        }
    }
}
