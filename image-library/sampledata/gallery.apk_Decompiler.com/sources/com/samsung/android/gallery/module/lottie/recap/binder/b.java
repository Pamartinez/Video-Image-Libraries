package com.samsung.android.gallery.module.lottie.recap.binder;

import com.samsung.android.gallery.module.lottie.recap.template.element.RecapBgLayer;
import com.samsung.android.gallery.module.lottie.recap.template.element.RecapImage;
import com.samsung.android.gallery.module.lottie.recap.template.element.RecapTextLayer;
import java.util.function.ToIntFunction;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements ToIntFunction {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3031a;

    public /* synthetic */ b(int i2) {
        this.f3031a = i2;
    }

    public final int applyAsInt(Object obj) {
        switch (this.f3031a) {
            case 0:
                return ((RecapImage) obj).fileIndex;
            case 1:
                return ((RecapBgLayer) obj).fileIndex;
            case 2:
                return ((RecapTextLayer) obj).fileIndex;
            case 3:
                return ((RecapImage) obj).fileIndex;
            default:
                return ((RecapImage) obj).getIndex();
        }
    }
}
