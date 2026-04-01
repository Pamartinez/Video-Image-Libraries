package com.samsung.android.sum.core.format;

import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4098a;

    public /* synthetic */ b(int i2) {
        this.f4098a = i2;
    }

    public final boolean test(Object obj) {
        switch (this.f4098a) {
            case 0:
                return StapleMutableMediaFormat.lambda$config$0(obj);
            default:
                return StapleMutableMediaFormat.lambda$adjustChannels$9((Integer) obj);
        }
    }
}
