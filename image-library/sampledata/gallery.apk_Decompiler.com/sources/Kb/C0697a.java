package kb;

import com.samsung.android.gallery.widget.appbar.CustomCover;

/* renamed from: kb.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0697a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ CustomCover e;
    public final /* synthetic */ CharSequence f;

    public /* synthetic */ C0697a(CustomCover customCover, CharSequence charSequence, int i2) {
        this.d = i2;
        this.e = customCover;
        this.f = charSequence;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$setTitle$4(this.f);
                return;
            default:
                this.e.lambda$setSubTitle$5(this.f);
                return;
        }
    }
}
