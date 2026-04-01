package com.samsung.android.gallery.module.abstraction;

import com.samsung.android.gallery.module.abstraction.VisualSearchCategory;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2917a;

    public /* synthetic */ h(int i2) {
        this.f2917a = i2;
    }

    public final boolean test(Object obj) {
        switch (this.f2917a) {
            case 0:
                return VisualSearchCategory.CustomHolder.lambda$ensureValidity$0((String) obj);
            case 1:
                return RemasterSuggestGroup.lambda$get$0((Integer) obj);
            case 2:
                return RemasterSuggestGroup.lambda$isResolution$1((Integer) obj);
            case 3:
                return RemasterType.lambda$getRemasterTypeEventDetail$0((Integer) obj);
            default:
                return RemasterType.lambda$getDecodedTypeEventDetail$1((Integer) obj);
        }
    }
}
