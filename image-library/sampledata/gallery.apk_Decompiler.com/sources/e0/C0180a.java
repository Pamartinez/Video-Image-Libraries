package e0;

import android.content.Context;
import android.view.Choreographer;
import androidx.profileinstaller.ProfileInstallerInitializer;

/* renamed from: e0.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0180a implements Choreographer.FrameCallback {
    public final /* synthetic */ ProfileInstallerInitializer d;
    public final /* synthetic */ Context e;

    public /* synthetic */ C0180a(ProfileInstallerInitializer profileInstallerInitializer, Context context) {
        this.d = profileInstallerInitializer;
        this.e = context;
    }

    public final void doFrame(long j2) {
        this.d.lambda$create$0(this.e, j2);
    }
}
