package com.samsung.android.app.sdk.deepsky.textextraction.selectionui.data;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.RectF;
import i.C0212a;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b%\b\b\u0018\u00002\u00020\u0001Bm\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\u0006\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0006\u0012\b\b\u0002\u0010\f\u001a\u00020\u0006\u0012\b\b\u0002\u0010\r\u001a\u00020\t\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u0010\u001a\u00020\t¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0014\u001a\u00020\u0013HÖ\u0001¢\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010\u0016\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b\u0016\u0010\u0017J\u001a\u0010\u001a\u001a\u00020\u00192\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u001a\u0010\u001bR$\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u001c\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\"\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0005\u0010!\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\"\u0010\u0007\u001a\u00020\u00068\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0007\u0010&\u001a\u0004\b'\u0010\u0017\"\u0004\b(\u0010)R\"\u0010\b\u001a\u00020\u00068\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\b\u0010&\u001a\u0004\b*\u0010\u0017\"\u0004\b+\u0010)R\"\u0010\n\u001a\u00020\t8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\n\u0010,\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\"\u0010\u000b\u001a\u00020\u00068\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010&\u001a\u0004\b1\u0010\u0017\"\u0004\b2\u0010)R\"\u0010\f\u001a\u00020\u00068\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\f\u0010&\u001a\u0004\b3\u0010\u0017\"\u0004\b4\u0010)R\"\u0010\r\u001a\u00020\t8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\r\u0010,\u001a\u0004\b5\u0010.\"\u0004\b6\u00100R\"\u0010\u000f\u001a\u00020\u000e8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u000f\u00107\u001a\u0004\b8\u00109\"\u0004\b:\u0010;R\"\u0010\u0010\u001a\u00020\t8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0010\u0010,\u001a\u0004\b<\u0010.\"\u0004\b=\u00100¨\u0006>"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/ImageInfo;", "", "Landroid/graphics/Bitmap;", "bitmap", "Landroid/graphics/RectF;", "imageRect", "", "imageWidth", "imageHeight", "", "imageRatio", "barcodeImageWidth", "barcodeImageHeight", "barcodeImageRatio", "Landroid/graphics/Point;", "centerOffset", "scaleFactor", "<init>", "(Landroid/graphics/Bitmap;Landroid/graphics/RectF;IIFIIFLandroid/graphics/Point;F)V", "", "toString", "()Ljava/lang/String;", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Landroid/graphics/Bitmap;", "getBitmap", "()Landroid/graphics/Bitmap;", "setBitmap", "(Landroid/graphics/Bitmap;)V", "Landroid/graphics/RectF;", "getImageRect", "()Landroid/graphics/RectF;", "setImageRect", "(Landroid/graphics/RectF;)V", "I", "getImageWidth", "setImageWidth", "(I)V", "getImageHeight", "setImageHeight", "F", "getImageRatio", "()F", "setImageRatio", "(F)V", "getBarcodeImageWidth", "setBarcodeImageWidth", "getBarcodeImageHeight", "setBarcodeImageHeight", "getBarcodeImageRatio", "setBarcodeImageRatio", "Landroid/graphics/Point;", "getCenterOffset", "()Landroid/graphics/Point;", "setCenterOffset", "(Landroid/graphics/Point;)V", "getScaleFactor", "setScaleFactor", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ImageInfo {
    private int barcodeImageHeight;
    private float barcodeImageRatio;
    private int barcodeImageWidth;
    private Bitmap bitmap;
    private Point centerOffset;
    private int imageHeight;
    private float imageRatio;
    private RectF imageRect;
    private int imageWidth;
    private float scaleFactor;

    public ImageInfo(Bitmap bitmap2, RectF rectF, int i2, int i7, float f, int i8, int i10, float f5, Point point, float f8) {
        j.e(rectF, "imageRect");
        j.e(point, "centerOffset");
        this.bitmap = bitmap2;
        this.imageRect = rectF;
        this.imageWidth = i2;
        this.imageHeight = i7;
        this.imageRatio = f;
        this.barcodeImageWidth = i8;
        this.barcodeImageHeight = i10;
        this.barcodeImageRatio = f5;
        this.centerOffset = point;
        this.scaleFactor = f8;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ImageInfo)) {
            return false;
        }
        ImageInfo imageInfo = (ImageInfo) obj;
        if (j.a(this.bitmap, imageInfo.bitmap) && j.a(this.imageRect, imageInfo.imageRect) && this.imageWidth == imageInfo.imageWidth && this.imageHeight == imageInfo.imageHeight && Float.compare(this.imageRatio, imageInfo.imageRatio) == 0 && this.barcodeImageWidth == imageInfo.barcodeImageWidth && this.barcodeImageHeight == imageInfo.barcodeImageHeight && Float.compare(this.barcodeImageRatio, imageInfo.barcodeImageRatio) == 0 && j.a(this.centerOffset, imageInfo.centerOffset) && Float.compare(this.scaleFactor, imageInfo.scaleFactor) == 0) {
            return true;
        }
        return false;
    }

    public final int getBarcodeImageHeight() {
        return this.barcodeImageHeight;
    }

    public final float getBarcodeImageRatio() {
        return this.barcodeImageRatio;
    }

    public final int getBarcodeImageWidth() {
        return this.barcodeImageWidth;
    }

    public final Bitmap getBitmap() {
        return this.bitmap;
    }

    public final Point getCenterOffset() {
        return this.centerOffset;
    }

    public final int getImageHeight() {
        return this.imageHeight;
    }

    public final float getImageRatio() {
        return this.imageRatio;
    }

    public final RectF getImageRect() {
        return this.imageRect;
    }

    public final int getImageWidth() {
        return this.imageWidth;
    }

    public final float getScaleFactor() {
        return this.scaleFactor;
    }

    public int hashCode() {
        int i2;
        Bitmap bitmap2 = this.bitmap;
        if (bitmap2 == null) {
            i2 = 0;
        } else {
            i2 = bitmap2.hashCode();
        }
        int hashCode = this.imageRect.hashCode();
        int a7 = N2.j.a(this.barcodeImageRatio, C0212a.b(this.barcodeImageHeight, C0212a.b(this.barcodeImageWidth, N2.j.a(this.imageRatio, C0212a.b(this.imageHeight, C0212a.b(this.imageWidth, (hashCode + (i2 * 31)) * 31, 31), 31), 31), 31), 31), 31);
        return Float.hashCode(this.scaleFactor) + ((this.centerOffset.hashCode() + a7) * 31);
    }

    public final void setBarcodeImageHeight(int i2) {
        this.barcodeImageHeight = i2;
    }

    public final void setBarcodeImageRatio(float f) {
        this.barcodeImageRatio = f;
    }

    public final void setBarcodeImageWidth(int i2) {
        this.barcodeImageWidth = i2;
    }

    public final void setBitmap(Bitmap bitmap2) {
        this.bitmap = bitmap2;
    }

    public final void setCenterOffset(Point point) {
        j.e(point, "<set-?>");
        this.centerOffset = point;
    }

    public final void setImageHeight(int i2) {
        this.imageHeight = i2;
    }

    public final void setImageRatio(float f) {
        this.imageRatio = f;
    }

    public final void setImageRect(RectF rectF) {
        j.e(rectF, "<set-?>");
        this.imageRect = rectF;
    }

    public final void setImageWidth(int i2) {
        this.imageWidth = i2;
    }

    public final void setScaleFactor(float f) {
        this.scaleFactor = f;
    }

    public String toString() {
        Bitmap bitmap2 = this.bitmap;
        RectF rectF = this.imageRect;
        int i2 = this.imageWidth;
        int i7 = this.imageHeight;
        float f = this.imageRatio;
        int i8 = this.barcodeImageWidth;
        int i10 = this.barcodeImageHeight;
        float f5 = this.barcodeImageRatio;
        Point point = this.centerOffset;
        float f8 = this.scaleFactor;
        StringBuilder sb2 = new StringBuilder("ImageInfo(bitmap=");
        sb2.append(bitmap2);
        sb2.append(", imageRect=");
        sb2.append(rectF);
        sb2.append(", imageWidth=");
        N2.j.x(sb2, i2, ", imageHeight=", i7, ", imageRatio=");
        sb2.append(f);
        sb2.append(", barcodeImageWidth=");
        sb2.append(i8);
        sb2.append(", barcodeImageHeight=");
        sb2.append(i10);
        sb2.append(", barcodeImageRatio=");
        sb2.append(f5);
        sb2.append(", centerOffset=");
        sb2.append(point);
        sb2.append(", scaleFactor=");
        sb2.append(f8);
        sb2.append(")");
        return sb2.toString();
    }

    public /* synthetic */ ImageInfo(Bitmap bitmap2, RectF rectF, int i2, int i7, float f, int i8, int i10, float f5, Point point, float f8, int i11, e eVar) {
        float f10;
        Point point2;
        float f11;
        int i12;
        int i13;
        float f12;
        int i14;
        int i15;
        RectF rectF2;
        Bitmap bitmap3;
        ImageInfo imageInfo;
        bitmap2 = (i11 & 1) != 0 ? null : bitmap2;
        rectF = (i11 & 2) != 0 ? new RectF() : rectF;
        i2 = (i11 & 4) != 0 ? -1 : i2;
        i7 = (i11 & 8) != 0 ? -1 : i7;
        f = (i11 & 16) != 0 ? 1.0f : f;
        i8 = (i11 & 32) != 0 ? -1 : i8;
        i10 = (i11 & 64) != 0 ? -1 : i10;
        f5 = (i11 & 128) != 0 ? 1.0f : f5;
        point = (i11 & 256) != 0 ? new Point() : point;
        if ((i11 & 512) != 0) {
            f10 = 1.0f;
            f11 = f5;
            point2 = point;
            i13 = i8;
            i12 = i10;
            i14 = i7;
            f12 = f;
            rectF2 = rectF;
            i15 = i2;
            imageInfo = this;
            bitmap3 = bitmap2;
        } else {
            f10 = f8;
            point2 = point;
            i12 = i10;
            f11 = f5;
            f12 = f;
            i13 = i8;
            i15 = i2;
            i14 = i7;
            bitmap3 = bitmap2;
            rectF2 = rectF;
            imageInfo = this;
        }
        new ImageInfo(bitmap3, rectF2, i15, i14, f12, i13, i12, f11, point2, f10);
    }
}
