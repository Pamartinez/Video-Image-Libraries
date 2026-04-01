package com.samsung.android.gallery.module.abstraction;

import com.samsung.android.gallery.module.abstraction.RemasterSuggestGroup;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2915a;
    public final /* synthetic */ Object b;

    public /* synthetic */ e(int i2, Object obj) {
        this.f2915a = i2;
        this.b = obj;
    }

    public final boolean test(Object obj) {
        int i2 = this.f2915a;
        Object obj2 = this.b;
        switch (i2) {
            case 0:
                return RemasterSuggestGroup.BrightenAndReduceBlurHolder.lambda$match$0((List) obj2, (List) obj);
            case 1:
                return RemasterSuggestGroup.ClearLensHolder.lambda$match$0((List) obj2, (List) obj);
            case 2:
                return RemasterSuggestGroup.ClearUpBlurryFacesHolder.lambda$match$0((List) obj2, (List) obj);
            case 3:
                return RemasterSuggestGroup.RemoveShadowAndReflections.lambda$match$0((List) obj2, (List) obj);
            case 4:
                return RemasterSuggestGroup.UpscaleHolder.lambda$match$0((List) obj2, (List) obj);
            default:
                return ((LinkedHashMap) obj2).containsKey((String) obj);
        }
    }
}
