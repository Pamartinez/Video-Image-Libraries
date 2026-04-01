package c2;

import D1.f;
import android.graphics.Typeface;
import com.google.android.material.chip.Chip;
import h2.l;
import h2.m;

/* renamed from: c2.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0090a extends f {
    public final /* synthetic */ int g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Object f1052h;

    public /* synthetic */ C0090a(int i2, Object obj) {
        this.g = i2;
        this.f1052h = obj;
    }

    public final void F(int i2) {
        switch (this.g) {
            case 0:
                return;
            default:
                m mVar = (m) this.f1052h;
                mVar.e = true;
                l lVar = (l) mVar.f.get();
                if (lVar != null) {
                    lVar.a();
                    return;
                }
                return;
        }
    }

    public final void G(Typeface typeface, boolean z) {
        CharSequence charSequence;
        switch (this.g) {
            case 0:
                Chip chip = (Chip) this.f1052h;
                e eVar = chip.d;
                if (eVar.f1063E0) {
                    charSequence = eVar.f1066G;
                } else {
                    charSequence = chip.getText();
                }
                chip.setText(charSequence);
                chip.requestLayout();
                chip.invalidate();
                return;
            default:
                if (!z) {
                    m mVar = (m) this.f1052h;
                    mVar.e = true;
                    l lVar = (l) mVar.f.get();
                    if (lVar != null) {
                        lVar.a();
                        return;
                    }
                    return;
                }
                return;
        }
    }

    private final void Q(int i2) {
    }
}
