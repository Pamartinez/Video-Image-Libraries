package k8;

import com.samsung.android.gallery.database.dal.DbDump;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Process e;

    public /* synthetic */ a(Process process, int i2) {
        this.d = i2;
        this.e = process;
    }

    public final void run() {
        int i2 = this.d;
        Process process = this.e;
        switch (i2) {
            case 0:
                DbDump.lambda$execCommand$0(process);
                return;
            case 1:
                DbDump.lambda$execCommand$1(process);
                return;
            default:
                process.destroy();
                return;
        }
    }
}
