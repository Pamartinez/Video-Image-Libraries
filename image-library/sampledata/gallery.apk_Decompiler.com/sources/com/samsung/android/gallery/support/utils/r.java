package com.samsung.android.gallery.support.utils;

import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class r implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3185a;
    public final /* synthetic */ Object b;

    public /* synthetic */ r(int i2, Object obj) {
        this.f3185a = i2;
        this.b = obj;
    }

    public final Object apply(Object obj) {
        int i2 = this.f3185a;
        Object obj2 = this.b;
        switch (i2) {
            case 0:
                return ((GalleryPreference) obj2).lambda$toDebugString$2((String) obj);
            case 1:
                return ((GalleryPreference) obj2).lambda$toDebugString$3((String) obj);
            case 2:
                return ((GalleryPreference) obj2).lambda$toDebugString$4((String) obj);
            case 3:
                return PocFeatures.lambda$toDebugString$8((GalleryPreference) obj2, (String) obj);
            case 4:
                return PreferenceFeatures.lambda$toDebugString$68((GalleryPreference) obj2, (String) obj);
            default:
                return Boolean.valueOf(obj.equals(obj2));
        }
    }
}
