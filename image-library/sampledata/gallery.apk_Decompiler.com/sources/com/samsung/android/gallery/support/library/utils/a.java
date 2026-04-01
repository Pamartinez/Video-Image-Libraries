package com.samsung.android.gallery.support.library.utils;

import com.samsung.android.gallery.support.library.utils.Reflector;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Class f3149a;
    public final /* synthetic */ String b;

    public /* synthetic */ a(Class cls, String str) {
        this.f3149a = cls;
        this.b = str;
    }

    public final Object apply(Object obj) {
        return Reflector.UniqueMethodHolder.lambda$computeMethod$0(this.f3149a, this.b, (String) obj);
    }
}
