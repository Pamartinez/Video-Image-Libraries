package com.samsung.android.gallery.module.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.ParcelFileDescriptor;
import android.print.PrintAttributes;
import android.print.pdf.PrintedPdfDocument;
import com.samsung.android.gallery.support.utils.AppResources;
import java.io.FileOutputStream;
import java.io.IOException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PdfPrinter {
    private Paint mBackgroundPaint;
    PdfDocument mDocument;
    int mPage = 1;

    public PdfPrinter(PrintAttributes printAttributes) {
        this.mDocument = new PrintedPdfDocument(AppResources.getAppContext(), printAttributes);
        Paint paint = new Paint();
        this.mBackgroundPaint = paint;
        paint.setColor(-1);
    }

    private void addBitmap(Bitmap bitmap, int i2) {
        PdfDocument.Page startPage = this.mDocument.startPage(new PdfDocument.PageInfo.Builder(bitmap.getWidth(), bitmap.getHeight(), i2).create());
        Canvas canvas = startPage.getCanvas();
        canvas.drawPaint(this.mBackgroundPaint);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        this.mDocument.finishPage(startPage);
    }

    public void addPage(Bitmap bitmap) {
        int i2 = this.mPage;
        this.mPage = i2 + 1;
        addBitmap(bitmap, i2);
    }

    public void print(ParcelFileDescriptor parcelFileDescriptor) {
        try {
            this.mDocument.writeTo(new FileOutputStream(parcelFileDescriptor.getFileDescriptor()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.mDocument.close();
    }
}
