package com.samsung.android.gallery.support.utils;

import java.util.Map;
import java.util.function.Predicate;

/* renamed from: com.samsung.android.gallery.support.utils.p  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0678p implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3184a;
    public final /* synthetic */ Object b;

    public /* synthetic */ C0678p(int i2, Object obj) {
        this.f3184a = i2;
        this.b = obj;
    }

    public final boolean test(Object obj) {
        int i2 = this.f3184a;
        Object obj2 = this.b;
        switch (i2) {
            case 0:
                return GalleryPreference.lambda$backup$5((Predicate) obj2, (Map.Entry) obj);
            default:
                return ((GalleryPreference) obj2).hasPreference((String) obj);
        }
    }
}
