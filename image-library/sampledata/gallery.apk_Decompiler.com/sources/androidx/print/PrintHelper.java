package androidx.print;

import K4.a;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import android.print.PrintManager;
import android.util.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PrintHelper {
    static final boolean IS_MIN_MARGINS_HANDLING_CORRECT = true;
    static final boolean PRINT_ACTIVITY_RESPECTS_ORIENTATION = true;
    int mColorMode = 2;
    final Context mContext;
    BitmapFactory.Options mDecodeOptions = null;
    final Object mLock = new Object();
    int mOrientation = 1;
    int mScaleMode = 2;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnPrintFinishCallback {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class PrintBitmapAdapter extends PrintDocumentAdapter {
        private PrintAttributes mAttributes;
        private final Bitmap mBitmap;
        private final OnPrintFinishCallback mCallback;
        private final int mFittingMode;
        private final String mJobName;

        public PrintBitmapAdapter(String str, int i2, Bitmap bitmap, OnPrintFinishCallback onPrintFinishCallback) {
            this.mJobName = str;
            this.mFittingMode = i2;
            this.mBitmap = bitmap;
            this.mCallback = onPrintFinishCallback;
        }

        public void onFinish() {
            OnPrintFinishCallback onPrintFinishCallback = this.mCallback;
            if (onPrintFinishCallback != null) {
                ((a) onPrintFinishCallback).a();
            }
        }

        public void onLayout(PrintAttributes printAttributes, PrintAttributes printAttributes2, CancellationSignal cancellationSignal, PrintDocumentAdapter.LayoutResultCallback layoutResultCallback, Bundle bundle) {
            this.mAttributes = printAttributes2;
            layoutResultCallback.onLayoutFinished(new PrintDocumentInfo.Builder(this.mJobName).setContentType(1).setPageCount(1).build(), true ^ printAttributes2.equals(printAttributes));
        }

        public void onWrite(PageRange[] pageRangeArr, ParcelFileDescriptor parcelFileDescriptor, CancellationSignal cancellationSignal, PrintDocumentAdapter.WriteResultCallback writeResultCallback) {
            PrintHelper.this.writeBitmap(this.mAttributes, this.mFittingMode, this.mBitmap, parcelFileDescriptor, cancellationSignal, writeResultCallback);
        }
    }

    public PrintHelper(Context context) {
        this.mContext = context;
    }

    public static Bitmap convertBitmapForColorMode(Bitmap bitmap, int i2) {
        if (i2 != 1) {
            return bitmap;
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0.0f);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        canvas.setBitmap((Bitmap) null);
        return createBitmap;
    }

    private static PrintAttributes.Builder copyAttributes(PrintAttributes printAttributes) {
        PrintAttributes.Builder minMargins = new PrintAttributes.Builder().setMediaSize(printAttributes.getMediaSize()).setResolution(printAttributes.getResolution()).setMinMargins(printAttributes.getMinMargins());
        if (printAttributes.getColorMode() != 0) {
            minMargins.setColorMode(printAttributes.getColorMode());
        }
        if (printAttributes.getDuplexMode() != 0) {
            minMargins.setDuplexMode(printAttributes.getDuplexMode());
        }
        return minMargins;
    }

    public static Matrix getMatrix(int i2, int i7, RectF rectF, int i8) {
        float f;
        Matrix matrix = new Matrix();
        float f5 = (float) i2;
        float width = rectF.width() / f5;
        if (i8 == 2) {
            f = Math.max(width, rectF.height() / ((float) i7));
        } else {
            f = Math.min(width, rectF.height() / ((float) i7));
        }
        matrix.postScale(f, f);
        matrix.postTranslate((rectF.width() - (f5 * f)) / 2.0f, (rectF.height() - (((float) i7) * f)) / 2.0f);
        return matrix;
    }

    public static boolean isPortrait(Bitmap bitmap) {
        if (bitmap.getWidth() <= bitmap.getHeight()) {
            return true;
        }
        return false;
    }

    public void printBitmap(String str, Bitmap bitmap) {
        printBitmap(str, bitmap, (OnPrintFinishCallback) null);
    }

    public void setScaleMode(int i2) {
        this.mScaleMode = i2;
    }

    public void writeBitmap(PrintAttributes printAttributes, int i2, Bitmap bitmap, ParcelFileDescriptor parcelFileDescriptor, CancellationSignal cancellationSignal, PrintDocumentAdapter.WriteResultCallback writeResultCallback) {
        final PrintAttributes printAttributes2;
        if (IS_MIN_MARGINS_HANDLING_CORRECT) {
            printAttributes2 = printAttributes;
        } else {
            printAttributes2 = copyAttributes(printAttributes).setMinMargins(new PrintAttributes.Margins(0, 0, 0, 0)).build();
        }
        final PrintAttributes printAttributes3 = printAttributes;
        final int i7 = i2;
        final Bitmap bitmap2 = bitmap;
        final ParcelFileDescriptor parcelFileDescriptor2 = parcelFileDescriptor;
        final CancellationSignal cancellationSignal2 = cancellationSignal;
        final PrintDocumentAdapter.WriteResultCallback writeResultCallback2 = writeResultCallback;
        new AsyncTask<Void, Void, Throwable>() {
            /* JADX WARNING: Code restructure failed: missing block: B:50:0x00d8, code lost:
                r1.recycle();
             */
            /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
            /* JADX WARNING: Missing exception handler attribute for start block: B:26:0x00a2 */
            /* JADX WARNING: Missing exception handler attribute for start block: B:38:0x00c2 */
            /* JADX WARNING: Missing exception handler attribute for start block: B:47:0x00d4 */
            /* JADX WARNING: Removed duplicated region for block: B:29:0x00a6 A[Catch:{ all -> 0x0041, all -> 0x00dc }] */
            /* JADX WARNING: Removed duplicated region for block: B:41:0x00c6 A[Catch:{ all -> 0x0041, all -> 0x00dc }] */
            /* JADX WARNING: Removed duplicated region for block: B:50:0x00d8 A[Catch:{ all -> 0x0041, all -> 0x00dc }] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.lang.Throwable doInBackground(java.lang.Void... r9) {
                /*
                    r8 = this;
                    android.os.CancellationSignal r9 = r4     // Catch:{ all -> 0x00dc }
                    boolean r9 = r9.isCanceled()     // Catch:{ all -> 0x00dc }
                    r0 = 0
                    if (r9 == 0) goto L_0x000a
                    return r0
                L_0x000a:
                    android.print.pdf.PrintedPdfDocument r9 = new android.print.pdf.PrintedPdfDocument     // Catch:{ all -> 0x00dc }
                    androidx.print.PrintHelper r1 = androidx.print.PrintHelper.this     // Catch:{ all -> 0x00dc }
                    android.content.Context r1 = r1.mContext     // Catch:{ all -> 0x00dc }
                    android.print.PrintAttributes r2 = r5     // Catch:{ all -> 0x00dc }
                    r9.<init>(r1, r2)     // Catch:{ all -> 0x00dc }
                    android.graphics.Bitmap r1 = r6     // Catch:{ all -> 0x00dc }
                    android.print.PrintAttributes r2 = r5     // Catch:{ all -> 0x00dc }
                    int r2 = r2.getColorMode()     // Catch:{ all -> 0x00dc }
                    android.graphics.Bitmap r1 = androidx.print.PrintHelper.convertBitmapForColorMode(r1, r2)     // Catch:{ all -> 0x00dc }
                    android.os.CancellationSignal r2 = r4     // Catch:{ all -> 0x00dc }
                    boolean r2 = r2.isCanceled()     // Catch:{ all -> 0x00dc }
                    if (r2 == 0) goto L_0x002a
                    return r0
                L_0x002a:
                    r2 = 1
                    android.graphics.pdf.PdfDocument$Page r3 = r9.startPage(r2)     // Catch:{ all -> 0x0041 }
                    boolean r4 = androidx.print.PrintHelper.IS_MIN_MARGINS_HANDLING_CORRECT     // Catch:{ all -> 0x0041 }
                    if (r4 == 0) goto L_0x0044
                    android.graphics.RectF r2 = new android.graphics.RectF     // Catch:{ all -> 0x0041 }
                    android.graphics.pdf.PdfDocument$PageInfo r5 = r3.getInfo()     // Catch:{ all -> 0x0041 }
                    android.graphics.Rect r5 = r5.getContentRect()     // Catch:{ all -> 0x0041 }
                    r2.<init>(r5)     // Catch:{ all -> 0x0041 }
                    goto L_0x0067
                L_0x0041:
                    r0 = move-exception
                    goto L_0x00ca
                L_0x0044:
                    android.print.pdf.PrintedPdfDocument r5 = new android.print.pdf.PrintedPdfDocument     // Catch:{ all -> 0x0041 }
                    androidx.print.PrintHelper r6 = androidx.print.PrintHelper.this     // Catch:{ all -> 0x0041 }
                    android.content.Context r6 = r6.mContext     // Catch:{ all -> 0x0041 }
                    android.print.PrintAttributes r7 = r7     // Catch:{ all -> 0x0041 }
                    r5.<init>(r6, r7)     // Catch:{ all -> 0x0041 }
                    android.graphics.pdf.PdfDocument$Page r2 = r5.startPage(r2)     // Catch:{ all -> 0x0041 }
                    android.graphics.RectF r6 = new android.graphics.RectF     // Catch:{ all -> 0x0041 }
                    android.graphics.pdf.PdfDocument$PageInfo r7 = r2.getInfo()     // Catch:{ all -> 0x0041 }
                    android.graphics.Rect r7 = r7.getContentRect()     // Catch:{ all -> 0x0041 }
                    r6.<init>(r7)     // Catch:{ all -> 0x0041 }
                    r5.finishPage(r2)     // Catch:{ all -> 0x0041 }
                    r5.close()     // Catch:{ all -> 0x0041 }
                    r2 = r6
                L_0x0067:
                    int r5 = r1.getWidth()     // Catch:{ all -> 0x0041 }
                    int r6 = r1.getHeight()     // Catch:{ all -> 0x0041 }
                    int r7 = r8     // Catch:{ all -> 0x0041 }
                    android.graphics.Matrix r5 = androidx.print.PrintHelper.getMatrix(r5, r6, r2, r7)     // Catch:{ all -> 0x0041 }
                    if (r4 == 0) goto L_0x0078
                    goto L_0x0086
                L_0x0078:
                    float r4 = r2.left     // Catch:{ all -> 0x0041 }
                    float r6 = r2.top     // Catch:{ all -> 0x0041 }
                    r5.postTranslate(r4, r6)     // Catch:{ all -> 0x0041 }
                    android.graphics.Canvas r4 = r3.getCanvas()     // Catch:{ all -> 0x0041 }
                    r4.clipRect(r2)     // Catch:{ all -> 0x0041 }
                L_0x0086:
                    android.graphics.Canvas r2 = r3.getCanvas()     // Catch:{ all -> 0x0041 }
                    r2.drawBitmap(r1, r5, r0)     // Catch:{ all -> 0x0041 }
                    r9.finishPage(r3)     // Catch:{ all -> 0x0041 }
                    android.os.CancellationSignal r2 = r4     // Catch:{ all -> 0x0041 }
                    boolean r2 = r2.isCanceled()     // Catch:{ all -> 0x0041 }
                    if (r2 == 0) goto L_0x00aa
                    r9.close()     // Catch:{ all -> 0x00dc }
                    android.os.ParcelFileDescriptor r9 = r9     // Catch:{ all -> 0x00dc }
                    if (r9 == 0) goto L_0x00a2
                    r9.close()     // Catch:{ IOException -> 0x00a2 }
                L_0x00a2:
                    android.graphics.Bitmap r8 = r6     // Catch:{ all -> 0x00dc }
                    if (r1 == r8) goto L_0x00a9
                    r1.recycle()     // Catch:{ all -> 0x00dc }
                L_0x00a9:
                    return r0
                L_0x00aa:
                    java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ all -> 0x0041 }
                    android.os.ParcelFileDescriptor r3 = r9     // Catch:{ all -> 0x0041 }
                    java.io.FileDescriptor r3 = r3.getFileDescriptor()     // Catch:{ all -> 0x0041 }
                    r2.<init>(r3)     // Catch:{ all -> 0x0041 }
                    r9.writeTo(r2)     // Catch:{ all -> 0x0041 }
                    r9.close()     // Catch:{ all -> 0x00dc }
                    android.os.ParcelFileDescriptor r9 = r9     // Catch:{ all -> 0x00dc }
                    if (r9 == 0) goto L_0x00c2
                    r9.close()     // Catch:{ IOException -> 0x00c2 }
                L_0x00c2:
                    android.graphics.Bitmap r8 = r6     // Catch:{ all -> 0x00dc }
                    if (r1 == r8) goto L_0x00c9
                    r1.recycle()     // Catch:{ all -> 0x00dc }
                L_0x00c9:
                    return r0
                L_0x00ca:
                    r9.close()     // Catch:{ all -> 0x00dc }
                    android.os.ParcelFileDescriptor r9 = r9     // Catch:{ all -> 0x00dc }
                    if (r9 == 0) goto L_0x00d4
                    r9.close()     // Catch:{ IOException -> 0x00d4 }
                L_0x00d4:
                    android.graphics.Bitmap r8 = r6     // Catch:{ all -> 0x00dc }
                    if (r1 == r8) goto L_0x00db
                    r1.recycle()     // Catch:{ all -> 0x00dc }
                L_0x00db:
                    throw r0     // Catch:{ all -> 0x00dc }
                L_0x00dc:
                    r8 = move-exception
                    return r8
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.print.PrintHelper.AnonymousClass1.doInBackground(java.lang.Void[]):java.lang.Throwable");
            }

            public void onPostExecute(Throwable th) {
                if (cancellationSignal2.isCanceled()) {
                    writeResultCallback2.onWriteCancelled();
                } else if (th == null) {
                    writeResultCallback2.onWriteFinished(new PageRange[]{PageRange.ALL_PAGES});
                } else {
                    Log.e("PrintHelper", "Error writing printed content", th);
                    writeResultCallback2.onWriteFailed((CharSequence) null);
                }
            }
        }.execute(new Void[0]);
    }

    public void printBitmap(String str, Bitmap bitmap, OnPrintFinishCallback onPrintFinishCallback) {
        PrintAttributes.MediaSize mediaSize;
        if (bitmap != null) {
            PrintManager printManager = (PrintManager) this.mContext.getSystemService("print");
            if (isPortrait(bitmap)) {
                mediaSize = PrintAttributes.MediaSize.UNKNOWN_PORTRAIT;
            } else {
                mediaSize = PrintAttributes.MediaSize.UNKNOWN_LANDSCAPE;
            }
            String str2 = str;
            printManager.print(str2, new PrintBitmapAdapter(str2, this.mScaleMode, bitmap, onPrintFinishCallback), new PrintAttributes.Builder().setMediaSize(mediaSize).setColorMode(this.mColorMode).build());
        }
    }
}
