package j9;

import com.samsung.android.gallery.module.fileio.redact.FileRedactor;

/* renamed from: j9.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0695a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ FileRedactor e;

    public /* synthetic */ C0695a(FileRedactor fileRedactor, int i2) {
        this.d = i2;
        this.e = fileRedactor;
    }

    public final void run() {
        int i2 = this.d;
        FileRedactor fileRedactor = this.e;
        switch (i2) {
            case 0:
                fileRedactor.handleFile();
                return;
            default:
                fileRedactor.lambda$close$0();
                return;
        }
    }
}
