package com.samsung.android.gallery.module.lottie.recap.binder;

import com.samsung.android.gallery.module.lottie.recap.data.RecapImageCandidate;
import com.samsung.android.gallery.module.lottie.recap.template.element.RecapImage;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3035a;
    public final /* synthetic */ RecapImage b;

    public /* synthetic */ h(int i2, RecapImage recapImage) {
        this.f3035a = i2;
        this.b = recapImage;
    }

    public final boolean test(Object obj) {
        int i2 = this.f3035a;
        RecapImage recapImage = this.b;
        RecapImageCandidate recapImageCandidate = (RecapImageCandidate) obj;
        switch (i2) {
            case 0:
                return RecapImageSelector.lambda$pickCandidate$4(recapImage, recapImageCandidate);
            case 1:
                return RecapImageSelector.lambda$pickCandidate$5(recapImage, recapImageCandidate);
            case 2:
                return RecapImageSelector.lambda$pickCandidate$6(recapImage, recapImageCandidate);
            default:
                return RecapImageSelector.lambda$isPeoplesHeadSafeForAll$7(recapImage, recapImageCandidate);
        }
    }
}
