package u2;

import D1.f;
import android.graphics.Typeface;
import androidx.core.content.res.ResourcesCompat;

/* renamed from: u2.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0288c extends ResourcesCompat.FontCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ f f1940a;
    public final /* synthetic */ C0290e b;

    public C0288c(C0290e eVar, f fVar) {
        this.b = eVar;
        this.f1940a = fVar;
    }

    public final void onFontRetrievalFailed(int i2) {
        this.b.m = true;
        this.f1940a.F(i2);
    }

    public final void onFontRetrieved(Typeface typeface) {
        C0290e eVar = this.b;
        eVar.n = Typeface.create(typeface, eVar.f1945c);
        eVar.m = true;
        this.f1940a.G(eVar.n, false);
    }
}
