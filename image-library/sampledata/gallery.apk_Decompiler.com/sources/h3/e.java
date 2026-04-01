package H3;

import com.samsung.android.gallery.app.controller.album.CreateFolderCmd;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ CreateFolderCmd e;

    public /* synthetic */ e(CreateFolderCmd createFolderCmd, int i2) {
        this.d = i2;
        this.e = createFolderCmd;
    }

    public final void run() {
        int i2 = this.d;
        CreateFolderCmd createFolderCmd = this.e;
        switch (i2) {
            case 0:
                createFolderCmd.lambda$createEmptyFolder$3();
                return;
            default:
                createFolderCmd.lambda$createFolder$5();
                return;
        }
    }
}
