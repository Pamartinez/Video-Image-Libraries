package com.samsung.android.gallery.module.clip.textextraction;

import A.a;
import M4.d;
import O8.e;
import android.app.Dialog;
import android.graphics.Bitmap;
import com.samsung.android.gallery.module.clip.IClipInfo;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.deepsky.DeepSkyHelper;
import com.samsung.android.gallery.support.trace.Trace;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TextExtractionTask implements Runnable {
    private final IClipInfo mClipInfo;
    private Dialog mDialog;
    private boolean mIsDialogVisible;
    private boolean mIsProgressVisible;
    private final OnCompleteListener mListener;
    private final Mode mMode;
    private final TextExtractionHelper mTextHelper;

    /* renamed from: com.samsung.android.gallery.module.clip.textextraction.TextExtractionTask$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$gallery$module$clip$textextraction$TextExtractionTask$Mode;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.samsung.android.gallery.module.clip.textextraction.TextExtractionTask$Mode[] r0 = com.samsung.android.gallery.module.clip.textextraction.TextExtractionTask.Mode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$gallery$module$clip$textextraction$TextExtractionTask$Mode = r0
                com.samsung.android.gallery.module.clip.textextraction.TextExtractionTask$Mode r1 = com.samsung.android.gallery.module.clip.textextraction.TextExtractionTask.Mode.DETECT     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$clip$textextraction$TextExtractionTask$Mode     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.gallery.module.clip.textextraction.TextExtractionTask$Mode r1 = com.samsung.android.gallery.module.clip.textextraction.TextExtractionTask.Mode.EXTRACT_BY_BUTTON     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$clip$textextraction$TextExtractionTask$Mode     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.gallery.module.clip.textextraction.TextExtractionTask$Mode r1 = com.samsung.android.gallery.module.clip.textextraction.TextExtractionTask.Mode.EXTRACT_BY_LONG_PRESS     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.clip.textextraction.TextExtractionTask.AnonymousClass1.<clinit>():void");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum Mode {
        DETECT,
        EXTRACT_BY_BUTTON,
        EXTRACT_BY_LONG_PRESS
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnCompleteListener {
        void onComplete();
    }

    public TextExtractionTask(TextExtractionHelper textExtractionHelper, Mode mode, IClipInfo iClipInfo, OnCompleteListener onCompleteListener) {
        this.mTextHelper = textExtractionHelper;
        this.mMode = mode;
        this.mClipInfo = iClipInfo;
        this.mListener = onCompleteListener;
    }

    private void dismissDialog() {
        ThreadUtil.postOnUiThreadDelayed(new e(this, 1), 100);
    }

    private void handleDetect() {
        this.mTextHelper.detect(this.mClipInfo.isCacheAvailable());
    }

    private void handleExtract(boolean z, MediaItem mediaItem) {
        boolean z3;
        if (z || this.mTextHelper.isDocumentScanned()) {
            z3 = true;
        } else {
            z3 = false;
        }
        boolean isExtracted = this.mTextHelper.isExtracted();
        if (!z3 || !isExtracted) {
            Bitmap bitmap = this.mClipInfo.getBitmap(true);
            Bitmap lowBitmap = this.mClipInfo.getLowBitmap();
            if (bitmap == null) {
                Log.w("TextExtractionTask", "task#skip bitmap is null");
                return;
            }
            if (lowBitmap == null) {
                lowBitmap = bitmap;
            }
            if (!z3) {
                DocumentScanner.getInstance().findDocument(mediaItem, lowBitmap);
                this.mTextHelper.setDocumentScanned();
            }
            if (!isExtracted) {
                this.mTextHelper.extract(bitmap, lowBitmap, z, this.mClipInfo.getFilterText());
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$dismissDialog$0() {
        try {
            if (this.mIsDialogVisible) {
                Optional.ofNullable(this.mDialog).ifPresent(new d(28));
                this.mDialog = null;
            } else if (this.mIsProgressVisible) {
                this.mClipInfo.requestCaptureProgress(false);
            }
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showDialog$1() {
        try {
            Dialog progressDialog = this.mClipInfo.getProgressDialog();
            this.mDialog = progressDialog;
            progressDialog.show();
            this.mIsDialogVisible = true;
        } catch (Exception unused) {
        }
    }

    private boolean needToDisplayDialog() {
        TextExtractionHelper textExtractionHelper;
        if (Mode.DETECT.equals(this.mMode)) {
            return false;
        }
        if ((!Mode.EXTRACT_BY_BUTTON.equals(this.mMode) && DeepSkyHelper.isObjectCaptureSupported()) || (textExtractionHelper = this.mTextHelper) == null) {
            return false;
        }
        if (!textExtractionHelper.isExtracted() || !this.mTextHelper.isDocumentScanned()) {
            return true;
        }
        return false;
    }

    private boolean needToDisplayProgress() {
        TextExtractionHelper textExtractionHelper;
        if (!PocFeatures.CLIP_PARALLEL || !Mode.EXTRACT_BY_LONG_PRESS.equals(this.mMode) || !DeepSkyHelper.isObjectCaptureSupported() || (textExtractionHelper = this.mTextHelper) == null || textExtractionHelper.isExtracted()) {
            return false;
        }
        return true;
    }

    private void runInternal() {
        long j2;
        if (DeepSkyHelper.isTextExtractionReleased()) {
            Log.d("TextExtractionTask", this.mMode.name() + " task skipped, vision text is already released");
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        MediaItem mediaItem = this.mClipInfo.getMediaItem();
        TextExtractionHelper textExtractionHelper = this.mTextHelper;
        if (!(textExtractionHelper == null || mediaItem == null)) {
            textExtractionHelper.setMediaItem(mediaItem);
            int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$gallery$module$clip$textextraction$TextExtractionTask$Mode[this.mMode.ordinal()];
            if (i2 == 1) {
                handleDetect();
            } else if (i2 == 2) {
                handleExtract(false, mediaItem);
            } else if (i2 == 3) {
                handleExtract(true, mediaItem);
            }
        }
        StringBuilder sb2 = new StringBuilder("task#run");
        String name = this.mMode.name();
        if (mediaItem != null) {
            j2 = mediaItem.getFileId();
        } else {
            j2 = 0;
        }
        a.A(new Object[]{name, Long.valueOf(j2), Long.valueOf(currentTimeMillis)}, sb2, "TextExtractionTask");
        OnCompleteListener onCompleteListener = this.mListener;
        if (onCompleteListener != null) {
            onCompleteListener.onComplete();
        }
    }

    private void showDialog() {
        if (needToDisplayDialog()) {
            ThreadUtil.postOnUiThread(new e(this, 0));
        } else if (needToDisplayProgress()) {
            this.mIsProgressVisible = true;
            this.mClipInfo.requestCaptureProgress(true);
        }
    }

    public void run() {
        Trace.beginSection("TextExtractionTask run ");
        showDialog();
        runInternal();
        dismissDialog();
        Trace.endSection();
    }

    public String toString() {
        return "OR[" + this.mMode + "]";
    }
}
