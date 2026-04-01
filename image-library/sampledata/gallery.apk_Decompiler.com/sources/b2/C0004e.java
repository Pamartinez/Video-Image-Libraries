package B2;

import L2.a;
import android.view.View;
import com.google.android.material.internal.CheckableImageButton;

/* renamed from: B2.e  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0004e extends q {
    public final /* synthetic */ int e;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0004e(p pVar, int i2) {
        super(pVar);
        this.e = i2;
    }

    public void q() {
        switch (this.e) {
            case 0:
                p pVar = this.b;
                pVar.r = null;
                CheckableImageButton checkableImageButton = pVar.f58j;
                checkableImageButton.setOnLongClickListener((View.OnLongClickListener) null);
                a.x(checkableImageButton, (View.OnLongClickListener) null);
                return;
            default:
                return;
        }
    }
}
