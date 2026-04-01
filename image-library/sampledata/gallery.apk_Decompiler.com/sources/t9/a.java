package t9;

import Dd.C0733d;
import com.samsung.android.gallery.support.utils.PreferenceName;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ C0733d e;
    public final /* synthetic */ String f;

    public /* synthetic */ a(C0733d dVar, String str, int i2) {
        this.d = i2;
        this.e = dVar;
        this.f = str;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                this.e.a(this.f, ((PreferenceName) obj).key());
                return;
            case 1:
                this.e.a(this.f, (String) obj);
                return;
            case 2:
                this.e.a(this.f, (String) obj);
                return;
            case 3:
                this.e.a(this.f, (String) obj);
                return;
            default:
                this.e.a(this.f, (String) obj);
                return;
        }
    }
}
