package u2;

import D1.f;
import android.graphics.Typeface;

/* renamed from: u2.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0287b extends f {
    public final Typeface g;

    /* renamed from: h  reason: collision with root package name */
    public final C0286a f1938h;

    /* renamed from: i  reason: collision with root package name */
    public boolean f1939i;

    public C0287b(C0286a aVar, Typeface typeface) {
        this.g = typeface;
        this.f1938h = aVar;
    }

    public final void F(int i2) {
        if (!this.f1939i) {
            this.f1938h.b(this.g);
        }
    }

    public final void G(Typeface typeface, boolean z) {
        if (!this.f1939i) {
            this.f1938h.b(typeface);
        }
    }
}
