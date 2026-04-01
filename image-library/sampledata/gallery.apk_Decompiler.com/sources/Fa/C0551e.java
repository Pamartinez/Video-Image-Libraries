package Fa;

import android.view.View;
import java.util.function.Consumer;

/* renamed from: Fa.e  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0551e implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ int e;
    public final /* synthetic */ int f;

    public /* synthetic */ C0551e(int i2, int i7, int i8) {
        this.d = i2;
        this.e = i7;
        this.f = i8;
    }

    public final void accept(Object obj) {
        ((View) obj).setPadding(this.d + this.e, ((View) obj).getPaddingTop(), this.f + this.e, ((View) obj).getPaddingBottom());
    }
}
