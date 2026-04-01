package com.samsung.android.gallery.module.fileio.redact;

import android.net.Uri;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.c2pa.C2paWrapper;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class FileRedactorBuilder {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CreateBuilder {
        private Consumer<Boolean> mCallback;
        private String mManifest;
        private FileItemInterface mOrg;
        private BiConsumer<String, Uri> mScanCallback;
        private FileItemInterface mTarget;
        private boolean mUsePFD;

        public CreateBuilder(FileItemInterface fileItemInterface, FileItemInterface fileItemInterface2) {
            this.mOrg = fileItemInterface2;
            this.mTarget = fileItemInterface;
        }

        public FileRedactor build() {
            if (this.mCallback != null) {
                if (TextUtils.isEmpty(this.mManifest)) {
                    this.mManifest = C2paWrapper.Manifest.actionEdit;
                }
                FileCreator fileCreator = new FileCreator(this.mManifest, this.mTarget, this.mOrg, this.mUsePFD, this.mCallback);
                BiConsumer<String, Uri> biConsumer = this.mScanCallback;
                if (biConsumer != null) {
                    fileCreator.withMediaScan(biConsumer);
                }
                return fileCreator;
            }
            throw new RuntimeException("Callback is null");
        }

        public CreateBuilder setCallback(Consumer<Boolean> consumer) {
            this.mCallback = consumer;
            return this;
        }

        public CreateBuilder setManifest(String str) {
            this.mManifest = str;
            return this;
        }

        public CreateBuilder setUseFileDescriptor(boolean z) {
            this.mUsePFD = z;
            return this;
        }

        public CreateBuilder withMediaScan(BiConsumer<String, Uri> biConsumer) {
            this.mScanCallback = biConsumer;
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class RedactBuilder {
        private OnProgress mCallback;
        private FileItemInterface[] mFiles;
        private boolean mIgnoreGroup = false;
        private Function<FileItemInterface, Boolean> mIsValidItem;
        private Function<FileItemInterface, Boolean> mOperation;
        private BiConsumer<Integer, Integer> mScanCallback;

        public RedactBuilder(FileItemInterface[] fileItemInterfaceArr) {
            this.mFiles = fileItemInterfaceArr;
        }

        public FileRedactor build() {
            int i2;
            Function<FileItemInterface, Boolean> function;
            OnProgress onProgress;
            FileItemInterface[] fileItemInterfaceArr = this.mFiles;
            if (fileItemInterfaceArr == null || (function = this.mOperation) == null || (onProgress = this.mCallback) == null) {
                StringBuilder sb2 = new StringBuilder("Mandatory argument is null file=");
                FileItemInterface[] fileItemInterfaceArr2 = this.mFiles;
                if (fileItemInterfaceArr2 != null) {
                    i2 = fileItemInterfaceArr2.length;
                } else {
                    i2 = -1;
                }
                sb2.append(i2);
                sb2.append(",op=");
                sb2.append(this.mOperation);
                sb2.append(",callback=");
                sb2.append(this.mCallback);
                throw new IllegalArgumentException(sb2.toString());
            }
            FileEditor fileEditor = new FileEditor(fileItemInterfaceArr, function, onProgress);
            if (this.mIgnoreGroup) {
                fileEditor.ignoreGroup();
            }
            Function<FileItemInterface, Boolean> function2 = this.mIsValidItem;
            if (function2 != null) {
                fileEditor.setCheckValidFunction(function2);
            }
            BiConsumer<Integer, Integer> biConsumer = this.mScanCallback;
            if (biConsumer != null) {
                fileEditor.withMediaScan(biConsumer);
            }
            return fileEditor;
        }

        public RedactBuilder ignoreGroup() {
            this.mIgnoreGroup = true;
            return this;
        }

        public RedactBuilder setCallback(OnProgress onProgress) {
            this.mCallback = onProgress;
            return this;
        }

        public RedactBuilder setCheckValidFunction(Function<FileItemInterface, Boolean> function) {
            this.mIsValidItem = function;
            return this;
        }

        public RedactBuilder setOperation(Function<FileItemInterface, Boolean> function) {
            this.mOperation = function;
            return this;
        }

        public RedactBuilder withMediaScan(BiConsumer<Integer, Integer> biConsumer) {
            this.mScanCallback = biConsumer;
            return this;
        }
    }

    public static CreateBuilder create(FileItemInterface fileItemInterface, FileItemInterface fileItemInterface2) {
        return new CreateBuilder(fileItemInterface, fileItemInterface2);
    }

    public static RedactBuilder edit(FileItemInterface[] fileItemInterfaceArr) {
        return new RedactBuilder(fileItemInterfaceArr);
    }

    public static CreateBuilder create(String str, String str2) {
        MediaItem mediaItem = new MediaItem();
        mediaItem.setPath(str);
        MediaItem mediaItem2 = new MediaItem();
        mediaItem2.setPath(str2);
        return new CreateBuilder(mediaItem, mediaItem2);
    }
}
