package com.samsung.android.gallery.module.fileio.redact;

import com.samsung.android.gallery.module.c2pa.C2paWrapper;
import com.samsung.android.gallery.support.utils.NamedThreadHandler;
import j9.C0695a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class FileRedactor {
    protected final String TAG = getClass().getSimpleName();
    protected C2paWrapper mC2paWrapper = C2paWrapper.getInstance();
    protected NamedThreadHandler mThreadHandler = new NamedThreadHandler("FileHandler");

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$close$0() {
        this.mC2paWrapper.close();
        this.mC2paWrapper = null;
    }

    public void close() {
        this.mThreadHandler.run(new C0695a(this, 1));
    }

    public abstract void handleFile();

    public FileRedactor run() {
        this.mC2paWrapper.open();
        this.mThreadHandler.run(new C0695a(this, 0));
        return this;
    }

    public void cancel() {
    }
}
