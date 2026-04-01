package g9;

import com.samsung.android.gallery.module.fileio.compat.FileOpJob;
import com.samsung.android.gallery.module.fileio.compat.RestoreUserData;
import java.util.function.Consumer;

/* renamed from: g9.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0692a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ RestoreUserData e;
    public final /* synthetic */ FileOpJob f;

    public /* synthetic */ C0692a(RestoreUserData restoreUserData, FileOpJob fileOpJob, int i2) {
        this.d = i2;
        this.e = restoreUserData;
        this.f = fileOpJob;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                this.e.lambda$prepare$6(this.f, obj);
                return;
            case 1:
                this.e.lambda$prepare$4(this.f, obj);
                return;
            default:
                this.e.lambda$prepare$5(this.f, obj);
                return;
        }
    }
}
