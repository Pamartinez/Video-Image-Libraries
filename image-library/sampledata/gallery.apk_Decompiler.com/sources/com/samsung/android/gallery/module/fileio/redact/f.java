package com.samsung.android.gallery.module.fileio.redact;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ FileEditor e;
    public final /* synthetic */ int f;

    public /* synthetic */ f(FileEditor fileEditor, int i2, int i7) {
        this.d = i7;
        this.e = fileEditor;
        this.f = i2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$onFileOperation$2(this.f);
                return;
            default:
                this.e.lambda$loadGroup$3(this.f);
                return;
        }
    }
}
