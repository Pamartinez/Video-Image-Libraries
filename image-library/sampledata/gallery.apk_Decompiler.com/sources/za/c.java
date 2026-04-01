package za;

import com.samsung.android.gallery.plugins.filebrowser.FileListFragment;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ FileListFragment e;

    public /* synthetic */ c(FileListFragment fileListFragment, int i2) {
        this.d = i2;
        this.e = fileListFragment;
    }

    public final void run() {
        int i2 = this.d;
        FileListFragment fileListFragment = this.e;
        switch (i2) {
            case 0:
                fileListFragment.lambda$onClick$17();
                return;
            default:
                fileListFragment.lambda$onBindView$2();
                return;
        }
    }
}
