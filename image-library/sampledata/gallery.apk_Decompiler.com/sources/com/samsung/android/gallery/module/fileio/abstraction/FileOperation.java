package com.samsung.android.gallery.module.fileio.abstraction;

import X3.c;
import a6.g;
import android.content.Context;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.fileio.database.abstraction.DbOperationInterface;
import com.samsung.android.gallery.module.fileio.type.FileInfo;
import com.samsung.android.gallery.support.utils.chain.Chain;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class FileOperation implements Chain<FileOperation> {
    protected final String TAG = tag();
    private boolean mIsCancelled = false;
    private FileOperation mNext;
    private OnProgressListener mProgressListener = null;

    @FunctionalInterface
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnProgressListener {
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$isActiveAll$0(int i2, int i7) {
        if ((i2 & i7) != 0) {
            return true;
        }
        return false;
    }

    public void cancel() {
        do {
            this.mIsCancelled = true;
            this = this.mNext;
        } while (this != null);
    }

    public final FileOpResult copy(Context context, FileInfo fileInfo) {
        if (support(fileInfo.getMediaItem())) {
            return copyInternal(context, fileInfo);
        }
        FileOperation fileOperation = this.mNext;
        if (fileOperation != null) {
            return fileOperation.copy(context, fileInfo);
        }
        return FileOpResult.OP_NOT_FOUND_OPERATION;
    }

    public abstract FileOpResult copyInternal(Context context, FileInfo fileInfo);

    public final DbOperationInterface getDbOperation() {
        return DbOperationFactory.getFactory(getType());
    }

    public abstract int getType();

    public boolean isActiveAll(int i2, int... iArr) {
        return Arrays.stream(iArr).allMatch(new c(i2, 3));
    }

    public boolean isActiveMode(int i2, int i7) {
        if ((i2 & i7) != 0) {
            return true;
        }
        return false;
    }

    public boolean isCancelled() {
        return this.mIsCancelled;
    }

    public final FileOpResult move(Context context, FileInfo fileInfo) {
        if (support(fileInfo.getMediaItem())) {
            return moveInternal(context, fileInfo);
        }
        FileOperation fileOperation = this.mNext;
        if (fileOperation != null) {
            return fileOperation.move(context, fileInfo);
        }
        return FileOpResult.OP_NOT_FOUND_OPERATION;
    }

    public abstract FileOpResult moveInternal(Context context, FileInfo fileInfo);

    public void setProgressListener(OnProgressListener onProgressListener) {
        do {
            this.mProgressListener = onProgressListener;
            this = this.mNext;
        } while (this != null);
    }

    public abstract boolean support(FileItemInterface fileItemInterface);

    public abstract String tag();

    public void updateProgress(long j2) {
        OnProgressListener onProgressListener = this.mProgressListener;
        if (onProgressListener != null) {
            ((g) onProgressListener).b(j2);
        }
    }

    public final void setNext(FileOperation fileOperation) {
        this.mNext = fileOperation;
    }
}
