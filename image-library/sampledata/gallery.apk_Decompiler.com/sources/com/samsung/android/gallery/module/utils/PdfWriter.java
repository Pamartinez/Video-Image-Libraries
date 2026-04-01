package com.samsung.android.gallery.module.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.StorageInfo;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PdfWriter {
    private Paint mBackgroundPaint;
    PdfDocument mDocument = new PdfDocument();
    int mPage = 1;

    public PdfWriter() {
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

    public File write(String str) {
        FileOutputStream fileOutputStream;
        String str2 = StorageInfo.getDefault().documentScans + File.separator + str;
        FileUtils.makeParentIfAbsent(str2);
        File file = new File(str2);
        try {
            fileOutputStream = new FileOutputStream(file);
            this.mDocument.writeTo(fileOutputStream);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        this.mDocument.close();
        return file;
        throw th;
    }
}
