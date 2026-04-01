package h2;

import android.content.Context;
import android.text.TextPaint;
import c2.C0090a;
import java.lang.ref.WeakReference;
import u2.C0290e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class m {

    /* renamed from: a  reason: collision with root package name */
    public final TextPaint f1774a = new TextPaint(1);
    public final C0090a b = new C0090a(1, this);

    /* renamed from: c  reason: collision with root package name */
    public float f1775c;
    public float d;
    public boolean e = true;
    public final WeakReference f = new WeakReference((Object) null);
    public C0290e g;

    public m(l lVar) {
        this.f = new WeakReference(lVar);
    }

    public final void a(String str) {
        float f5;
        TextPaint textPaint = this.f1774a;
        float f8 = 0.0f;
        if (str == null) {
            f5 = 0.0f;
        } else {
            f5 = textPaint.measureText(str, 0, str.length());
        }
        this.f1775c = f5;
        if (str != null) {
            f8 = Math.abs(textPaint.getFontMetrics().ascent);
        }
        this.d = f8;
        this.e = false;
    }

    public final void b(C0290e eVar, Context context) {
        if (this.g != eVar) {
            this.g = eVar;
            if (eVar != null) {
                TextPaint textPaint = this.f1774a;
                C0090a aVar = this.b;
                eVar.e(context, textPaint, aVar);
                l lVar = (l) this.f.get();
                if (lVar != null) {
                    textPaint.drawableState = lVar.getState();
                }
                eVar.d(context, textPaint, aVar);
                this.e = true;
            }
            l lVar2 = (l) this.f.get();
            if (lVar2 != null) {
                lVar2.a();
                lVar2.onStateChange(lVar2.getState());
            }
        }
    }
}
