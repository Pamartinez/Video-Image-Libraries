package com.samsung.android.gallery.app.controller.externals;

import K4.a;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import android.print.PrintManager;
import android.text.TextUtils;
import androidx.print.PrintHelper;
import com.samsung.android.gallery.app.controller.BaseSelectedCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.graphics.BitmapOptionsFactory;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.utils.PdfPrinter;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.support.utils.Utils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StartPrintCmd extends BaseSelectedCommand {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class PrintBitmapAdapter extends PrintDocumentAdapter {
        private PrintAttributes mAttributes;
        private final Bitmap[] mBitmapList;
        private final PrintHelper.OnPrintFinishCallback mCallback;
        private final int mFittingMode;
        private final String mJobName;

        public PrintBitmapAdapter(String str, int i2, Bitmap[] bitmapArr, PrintHelper.OnPrintFinishCallback onPrintFinishCallback) {
            this.mJobName = str;
            this.mFittingMode = i2;
            this.mBitmapList = bitmapArr;
            this.mCallback = onPrintFinishCallback;
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onWrite$0(CancellationSignal cancellationSignal, PrintDocumentAdapter.WriteResultCallback writeResultCallback, ParcelFileDescriptor parcelFileDescriptor) {
            Log.d(StartPrintCmd.this.TAG, "onWrite > ");
            try {
                PdfPrinter pdfPrinter = new PdfPrinter(this.mAttributes);
                Bitmap[] bitmapArr = this.mBitmapList;
                int length = bitmapArr.length;
                int i2 = 0;
                while (i2 < length) {
                    Bitmap bitmap = bitmapArr[i2];
                    if (cancellationSignal.isCanceled()) {
                        writeResultCallback.onWriteCancelled();
                        return;
                    } else {
                        pdfPrinter.addPage(bitmap);
                        i2++;
                    }
                }
                if (cancellationSignal.isCanceled()) {
                    writeResultCallback.onWriteCancelled();
                    return;
                }
                Log.d(StartPrintCmd.this.TAG, "< onWrite.add ");
                pdfPrinter.print(parcelFileDescriptor);
                Log.d(StartPrintCmd.this.TAG, "< onWrite.print ");
                writeResultCallback.onWriteFinished(new PageRange[]{PageRange.ALL_PAGES});
                Log.d(StartPrintCmd.this.TAG, "< onWrite");
            } catch (Exception unused) {
                writeResultCallback.onWriteFailed((CharSequence) null);
            }
        }

        public void onFinish() {
            PrintHelper.OnPrintFinishCallback onPrintFinishCallback = this.mCallback;
            if (onPrintFinishCallback != null) {
                ((a) onPrintFinishCallback).a();
            }
        }

        public void onLayout(PrintAttributes printAttributes, PrintAttributes printAttributes2, CancellationSignal cancellationSignal, PrintDocumentAdapter.LayoutResultCallback layoutResultCallback, Bundle bundle) {
            Log.d(StartPrintCmd.this.TAG, "onLayout > ");
            this.mAttributes = printAttributes2;
            layoutResultCallback.onLayoutFinished(new PrintDocumentInfo.Builder(this.mJobName).setContentType(1).setPageCount(this.mBitmapList.length).build(), !printAttributes2.equals(printAttributes));
            Log.d(StartPrintCmd.this.TAG, "< onLayout>");
        }

        public void onWrite(PageRange[] pageRangeArr, ParcelFileDescriptor parcelFileDescriptor, CancellationSignal cancellationSignal, PrintDocumentAdapter.WriteResultCallback writeResultCallback) {
            SimpleThreadPool.getInstance().execute(new c(this, cancellationSignal, writeResultCallback, parcelFileDescriptor));
        }
    }

    private Bitmap getBitmapToPrint(MediaItem mediaItem) {
        Bitmap bitmap;
        if (!PocFeatures.DUAL_PHOTO_PREVIEW || !mediaItem.isStream()) {
            bitmap = null;
        } else {
            byte[] bArr = (byte[]) mediaItem.getTag("data-stream");
            bitmap = BitmapUtils.decodeByteArray(bArr, BitmapOptionsFactory.parse(bArr, 0, bArr.length).withQuramCodec(mediaItem.isQuramDecodable()).adjustInSampleSizeSmallerThan(3500), mediaItem.getOrientation());
        }
        if (bitmap == null) {
            return BitmapUtils.getDecodedBitmap(mediaItem.getPath(), mediaItem.getOrientation(), 3500, mediaItem.isQuramDecodable());
        }
        return bitmap;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$1() {
        hideProgress();
        getBlackboard().postEvent(EventMessage.obtain(1003));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$2(MediaItem[] mediaItemArr) {
        Bitmap bitmap;
        Bitmap[] bitmapArr = new Bitmap[mediaItemArr.length];
        A.a.w(new StringBuilder("startGetBitmaps : "), mediaItemArr.length, this.TAG);
        int length = mediaItemArr.length;
        int i2 = 0;
        int i7 = 0;
        while (i2 < length) {
            MediaItem mediaItem = mediaItemArr[i2];
            int i8 = i7 + 1;
            if (mediaItem.isVideo()) {
                bitmap = ThumbnailLoader.getInstance().loadThumbnailSync(mediaItem, ThumbKind.MEDIUM_KIND);
            } else {
                bitmap = getBitmapToPrint(mediaItem);
            }
            bitmapArr[i7] = bitmap;
            i2++;
            i7 = i8;
        }
        Log.d(this.TAG, "endGetBitmaps");
        printMultiple(this.TAG, bitmapArr, new a(14, this));
    }

    private void print(Context context, String str, Bitmap bitmap) {
        if (context != null) {
            try {
                PrintHelper printHelper = new PrintHelper(context);
                printHelper.setScaleMode(1);
                printHelper.printBitmap(str, bitmap);
            } catch (Exception e) {
                Log.e((CharSequence) this.TAG, "print failed", (Throwable) e);
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: printSingle */
    public void lambda$onExecute$0(MediaItem mediaItem) {
        Bitmap bitmapToPrint = getBitmapToPrint(mediaItem);
        String str = this.TAG;
        Log.d(str, "print " + Logger.toString(bitmapToPrint));
        if (bitmapToPrint != null) {
            String title = mediaItem.getTitle();
            if (TextUtils.isEmpty(title)) {
                title = FileUtils.getBaseName(mediaItem.getPath());
            }
            if (TextUtils.isEmpty(title)) {
                title = TimeUtil.getIsoLocalDateTime(System.currentTimeMillis());
            }
            print(getContext(), title, bitmapToPrint);
        }
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_DETAIL_VIEW_PRINT.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        MediaItem mediaItem = objArr[0];
        if (mediaItem instanceof MediaItem) {
            SimpleThreadPool.getInstance().execute(new M5.a(8, this, mediaItem));
        } else if (mediaItem instanceof MediaItem[]) {
            MediaItem[] mediaItemArr = (MediaItem[]) mediaItem;
            if (mediaItemArr.length > 100) {
                Utils.showToast((Context) getActivity(), getActivity().getString(R.string.file_limit_exceed, new Object[]{100}));
                return;
            }
            showProgress(false);
            SimpleThreadPool.getInstance().execute(new M5.a(9, this, mediaItemArr));
        } else {
            Log.e(this.TAG, "item is null");
        }
    }

    public void printMultiple(String str, Bitmap[] bitmapArr, PrintHelper.OnPrintFinishCallback onPrintFinishCallback) {
        if (bitmapArr != null) {
            PrintAttributes build = new PrintAttributes.Builder().setMediaSize(PrintAttributes.MediaSize.UNKNOWN_PORTRAIT).setColorMode(2).build();
            String str2 = this.TAG;
            Log.d(str2, "startPrint : " + bitmapArr.length);
            String str3 = str;
            ((PrintManager) getActivity().getSystemService("print")).print(str3, new PrintBitmapAdapter(str3, 1, bitmapArr, onPrintFinishCallback), build);
            Log.d(this.TAG, "endPrint");
        }
    }
}
