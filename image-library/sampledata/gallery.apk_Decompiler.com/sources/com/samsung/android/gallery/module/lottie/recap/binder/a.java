package com.samsung.android.gallery.module.lottie.recap.binder;

import com.samsung.android.gallery.module.lottie.recap.data.RecapImageCandidate;
import com.samsung.android.gallery.module.lottie.recap.template.element.RecapBgLayer;
import java.util.Objects;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3030a;

    public /* synthetic */ a(int i2) {
        this.f3030a = i2;
    }

    public final boolean test(Object obj) {
        switch (this.f3030a) {
            case 0:
                return Objects.isNull((RecapBgLayer) obj);
            case 1:
                return RecapColorPicker.lambda$findTargetBgColor$0((RecapBgLayer) obj);
            case 2:
                return RecapColorPicker.lambda$updateTargetColor$7((RecapBgLayer) obj);
            default:
                return ((RecapImageCandidate) obj).canUse();
        }
    }
}
