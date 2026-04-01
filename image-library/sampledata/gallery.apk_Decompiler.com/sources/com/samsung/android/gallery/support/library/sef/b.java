package com.samsung.android.gallery.support.library.sef;

import com.samsung.android.gallery.support.library.sef.SefInfo;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3142a;

    public /* synthetic */ b(int i2) {
        this.f3142a = i2;
    }

    public final boolean test(Object obj) {
        switch (this.f3142a) {
            case 0:
                return SefInfo.DataHolder.AnonymousClass1.lambda$new$0((SefInfo) obj);
            case 1:
                return SefParser.lambda$apply$5((SefData) obj);
            default:
                return SefParser.lambda$apply$3((SefData) obj);
        }
    }
}
