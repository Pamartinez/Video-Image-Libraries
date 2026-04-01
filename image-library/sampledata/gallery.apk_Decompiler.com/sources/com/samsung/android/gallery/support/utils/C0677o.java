package com.samsung.android.gallery.support.utils;

import com.samsung.android.gallery.support.library.sef.SefFileCompat;
import java.util.function.BiConsumer;

/* renamed from: com.samsung.android.gallery.support.utils.o  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0677o implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3182a;
    public final /* synthetic */ Object b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f3183c;

    public /* synthetic */ C0677o(int i2, Object obj, Object obj2) {
        this.f3182a = i2;
        this.b = obj;
        this.f3183c = obj2;
    }

    public final void accept(Object obj, Object obj2) {
        switch (this.f3182a) {
            case 0:
                GalleryPreference.lambda$loadAllPrefix$1((String) this.b, (StringBuilder) this.f3183c, (String) obj, obj2);
                return;
            default:
                SefData.lambda$write$0((SefFileCompat) this.b, (SecureFile) this.f3183c, (String) obj, (byte[]) obj2);
                return;
        }
    }
}
