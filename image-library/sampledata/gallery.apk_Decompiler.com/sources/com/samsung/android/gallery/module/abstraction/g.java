package com.samsung.android.gallery.module.abstraction;

import i.C0212a;
import java.util.Set;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2916a;

    public /* synthetic */ g(int i2) {
        this.f2916a = i2;
    }

    public final Object apply(Object obj) {
        switch (this.f2916a) {
            case 0:
                return C0212a.l("location://search/fileList/Category/", (String) obj);
            case 1:
                return ((String) obj).substring(((String) obj).lastIndexOf("/") + 1);
            case 2:
                return Boolean.valueOf(((Set) obj).contains("android.intent.category.LAUNCHER"));
            case 3:
                return String.valueOf((Integer) obj);
            default:
                return VideoPropData.lambda$static$0((String) obj);
        }
    }
}
