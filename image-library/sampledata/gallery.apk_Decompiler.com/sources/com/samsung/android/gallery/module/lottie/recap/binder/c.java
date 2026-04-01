package com.samsung.android.gallery.module.lottie.recap.binder;

import com.samsung.android.gallery.module.lottie.recap.template.element.RecapImage;
import java.util.Comparator;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Comparator {
    public final /* synthetic */ int d;

    public /* synthetic */ c(int i2) {
        this.d = i2;
    }

    public final int compare(Object obj, Object obj2) {
        switch (this.d) {
            case 0:
                return RecapColorPicker.lambda$getImagesByHueDiff$6((RecapImage) obj, (RecapImage) obj2);
            default:
                return RecapImageSelector.lambda$imageSelectionPriority$1((Map.Entry) obj, (Map.Entry) obj2);
        }
    }
}
