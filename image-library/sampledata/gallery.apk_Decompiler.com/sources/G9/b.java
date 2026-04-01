package g9;

import com.samsung.android.gallery.module.fileio.compat.RestoreUserData;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ RestoreUserData e;

    public /* synthetic */ b(RestoreUserData restoreUserData, int i2) {
        this.d = i2;
        this.e = restoreUserData;
    }

    public final void run() {
        int i2 = this.d;
        RestoreUserData restoreUserData = this.e;
        switch (i2) {
            case 0:
                restoreUserData.lambda$tryRestore$9();
                return;
            default:
                restoreUserData.executeRestore();
                return;
        }
    }
}
