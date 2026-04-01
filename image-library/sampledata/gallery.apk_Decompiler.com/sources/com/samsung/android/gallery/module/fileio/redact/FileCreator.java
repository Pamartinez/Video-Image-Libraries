package com.samsung.android.gallery.module.fileio.redact;

import android.net.Uri;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.utils.MediaFileScanner;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class FileCreator extends FileRedactor {
    private final Consumer<Boolean> mCallback;
    private final String mManifest;
    private final FileItemInterface mOriginFile;
    private BiConsumer<String, Uri> mScanCallback;
    private final FileItemInterface mTargetFile;
    private final boolean mUsePFD;

    public FileCreator(String str, FileItemInterface fileItemInterface, FileItemInterface fileItemInterface2, boolean z, Consumer<Boolean> consumer) {
        this.mManifest = str;
        this.mTargetFile = fileItemInterface;
        this.mOriginFile = fileItemInterface2;
        this.mUsePFD = z;
        this.mCallback = consumer;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleFile$0(String str, Uri uri) {
        BiConsumer<String, Uri> biConsumer = this.mScanCallback;
        if (biConsumer != null) {
            biConsumer.accept(str, uri);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleFile$1(Boolean bool) {
        if (this.mScanCallback != null) {
            MediaFileScanner.scanFile(this.mTargetFile, new a(this));
        }
        this.mCallback.accept(bool);
        close();
    }

    public void handleFile() {
        this.mC2paWrapper.create(this.mManifest, this.mTargetFile, this.mOriginFile, this.mUsePFD, new b(this));
    }

    public void withMediaScan(BiConsumer<String, Uri> biConsumer) {
        this.mScanCallback = biConsumer;
    }
}
