package Sd;

import S8.C0577a;
import android.os.Bundle;
import java.util.function.Consumer;

/* renamed from: Sd.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0838c implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ C0577a e;

    public /* synthetic */ C0838c(C0577a aVar, int i2) {
        this.d = i2;
        this.e = aVar;
    }

    public final void accept(Object obj) {
        Bundle bundle = (Bundle) obj;
        switch (this.d) {
            case 0:
                this.e.accept(new w(bundle));
                return;
            case 1:
                this.e.accept(new w(bundle));
                return;
            default:
                this.e.accept(new w(bundle));
                return;
        }
    }
}
