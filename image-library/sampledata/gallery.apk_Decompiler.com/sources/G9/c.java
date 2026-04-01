package g9;

import com.samsung.android.gallery.module.fileio.compat.FileOpJob;
import com.samsung.android.gallery.module.fileio.compat.RestoreUserData;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Consumer {
    public final /* synthetic */ RestoreUserData d;
    public final /* synthetic */ boolean e;
    public final /* synthetic */ boolean f;
    public final /* synthetic */ boolean g;

    public /* synthetic */ c(RestoreUserData restoreUserData, boolean z, boolean z3, boolean z7) {
        this.d = restoreUserData;
        this.e = z;
        this.f = z3;
        this.g = z7;
    }

    public final void accept(Object obj) {
        this.d.lambda$prepare$7(this.e, this.f, this.g, (FileOpJob) obj);
    }
}
