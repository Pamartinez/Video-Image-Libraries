package u2;

import D1.f;
import android.content.Context;
import android.graphics.Typeface;
import android.text.TextPaint;

/* renamed from: u2.d  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0289d extends f {
    public final /* synthetic */ Context g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ TextPaint f1941h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ f f1942i;

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ C0290e f1943j;

    public C0289d(C0290e eVar, Context context, TextPaint textPaint, f fVar) {
        this.f1943j = eVar;
        this.g = context;
        this.f1941h = textPaint;
        this.f1942i = fVar;
    }

    public final void F(int i2) {
        this.f1942i.F(i2);
    }

    public final void G(Typeface typeface, boolean z) {
        this.f1943j.f(this.g, this.f1941h, typeface);
        this.f1942i.G(typeface, z);
    }
}
