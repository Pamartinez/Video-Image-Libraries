package com.samsung.android.gallery.support.library.v0.media;

import com.samsung.android.gallery.support.library.v0.media.SemMediaResourceHelperCompat;
import java.util.ArrayList;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3150a;
    public final /* synthetic */ int b;

    public /* synthetic */ a(int i2, int i7) {
        this.f3150a = i2;
        this.b = i7;
    }

    public final void accept(Object obj, Object obj2) {
        SemMediaResourceHelperCompat.AnonymousClass1.lambda$notifyListeners$0(this.f3150a, this.b, (String) obj, (ArrayList) obj2);
    }
}
