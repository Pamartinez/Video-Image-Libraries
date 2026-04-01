package com.samsung.android.gallery.module.data;

import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2929a;

    public /* synthetic */ e(int i2) {
        this.f2929a = i2;
    }

    public final boolean test(Object obj) {
        switch (this.f2929a) {
            case 0:
                return MdeData.lambda$parseSpace$10((String) obj);
            case 1:
                return MdeData.lambda$parse$0((String) obj);
            case 2:
                return MdeData.lambda$parse$1((String) obj);
            default:
                return UriItemLoader.lambda$loadFromContentUris$17((String[]) obj);
        }
    }
}
