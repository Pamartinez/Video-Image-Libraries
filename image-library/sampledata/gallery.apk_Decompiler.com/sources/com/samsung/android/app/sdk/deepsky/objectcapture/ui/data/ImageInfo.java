package com.samsung.android.app.sdk.deepsky.objectcapture.ui.data;

import android.graphics.Bitmap;
import android.graphics.RectF;
import i.C0212a;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B;\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0005HÆ\u0003J\t\u0010!\u001a\u00020\u0007HÆ\u0003J\t\u0010\"\u001a\u00020\u0007HÆ\u0003J\t\u0010#\u001a\u00020\nHÆ\u0003J=\u0010$\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0013\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010(\u001a\u00020\u0007HÖ\u0001J\t\u0010)\u001a\u00020*HÖ\u0001R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\b\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0016\"\u0004\b\u001a\u0010\u0018R\u001a\u0010\t\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001e¨\u0006+"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/data/ImageInfo;", "", "bitmap", "Landroid/graphics/Bitmap;", "bitmapRectInScreen", "Landroid/graphics/RectF;", "imageHeight", "", "imageWidth", "imageRatio", "", "<init>", "(Landroid/graphics/Bitmap;Landroid/graphics/RectF;IIF)V", "getBitmap", "()Landroid/graphics/Bitmap;", "setBitmap", "(Landroid/graphics/Bitmap;)V", "getBitmapRectInScreen", "()Landroid/graphics/RectF;", "setBitmapRectInScreen", "(Landroid/graphics/RectF;)V", "getImageHeight", "()I", "setImageHeight", "(I)V", "getImageWidth", "setImageWidth", "getImageRatio", "()F", "setImageRatio", "(F)V", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ImageInfo {
    private Bitmap bitmap;
    private RectF bitmapRectInScreen;
    private int imageHeight;
    private float imageRatio;
    private int imageWidth;

    public ImageInfo() {
        this((Bitmap) null, (RectF) null, 0, 0, 0.0f, 31, (e) null);
    }

    public static /* synthetic */ ImageInfo copy$default(ImageInfo imageInfo, Bitmap bitmap2, RectF rectF, int i2, int i7, float f, int i8, Object obj) {
        if ((i8 & 1) != 0) {
            bitmap2 = imageInfo.bitmap;
        }
        if ((i8 & 2) != 0) {
            rectF = imageInfo.bitmapRectInScreen;
        }
        if ((i8 & 4) != 0) {
            i2 = imageInfo.imageHeight;
        }
        if ((i8 & 8) != 0) {
            i7 = imageInfo.imageWidth;
        }
        if ((i8 & 16) != 0) {
            f = imageInfo.imageRatio;
        }
        int i10 = i7;
        float f5 = f;
        return imageInfo.copy(bitmap2, rectF, i2, i10, f5);
    }

    public final Bitmap component1() {
        return this.bitmap;
    }

    public final RectF component2() {
        return this.bitmapRectInScreen;
    }

    public final int component3() {
        return this.imageHeight;
    }

    public final int component4() {
        return this.imageWidth;
    }

    public final float component5() {
        return this.imageRatio;
    }

    public final ImageInfo copy(Bitmap bitmap2, RectF rectF, int i2, int i7, float f) {
        j.e(rectF, "bitmapRectInScreen");
        return new ImageInfo(bitmap2, rectF, i2, i7, f);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ImageInfo)) {
            return false;
        }
        ImageInfo imageInfo = (ImageInfo) obj;
        if (j.a(this.bitmap, imageInfo.bitmap) && j.a(this.bitmapRectInScreen, imageInfo.bitmapRectInScreen) && this.imageHeight == imageInfo.imageHeight && this.imageWidth == imageInfo.imageWidth && Float.compare(this.imageRatio, imageInfo.imageRatio) == 0) {
            return true;
        }
        return false;
    }

    public final Bitmap getBitmap() {
        return this.bitmap;
    }

    public final RectF getBitmapRectInScreen() {
        return this.bitmapRectInScreen;
    }

    public final int getImageHeight() {
        return this.imageHeight;
    }

    public final float getImageRatio() {
        return this.imageRatio;
    }

    public final int getImageWidth() {
        return this.imageWidth;
    }

    public int hashCode() {
        int i2;
        Bitmap bitmap2 = this.bitmap;
        if (bitmap2 == null) {
            i2 = 0;
        } else {
            i2 = bitmap2.hashCode();
        }
        int hashCode = this.bitmapRectInScreen.hashCode();
        return Float.hashCode(this.imageRatio) + C0212a.b(this.imageWidth, C0212a.b(this.imageHeight, (hashCode + (i2 * 31)) * 31, 31), 31);
    }

    public final void setBitmap(Bitmap bitmap2) {
        this.bitmap = bitmap2;
    }

    public final void setBitmapRectInScreen(RectF rectF) {
        j.e(rectF, "<set-?>");
        this.bitmapRectInScreen = rectF;
    }

    public final void setImageHeight(int i2) {
        this.imageHeight = i2;
    }

    public final void setImageRatio(float f) {
        this.imageRatio = f;
    }

    public final void setImageWidth(int i2) {
        this.imageWidth = i2;
    }

    public String toString() {
        Bitmap bitmap2 = this.bitmap;
        RectF rectF = this.bitmapRectInScreen;
        int i2 = this.imageHeight;
        int i7 = this.imageWidth;
        float f = this.imageRatio;
        StringBuilder sb2 = new StringBuilder("ImageInfo(bitmap=");
        sb2.append(bitmap2);
        sb2.append(", bitmapRectInScreen=");
        sb2.append(rectF);
        sb2.append(", imageHeight=");
        N2.j.x(sb2, i2, ", imageWidth=", i7, ", imageRatio=");
        sb2.append(f);
        sb2.append(")");
        return sb2.toString();
    }

    public ImageInfo(Bitmap bitmap2, RectF rectF, int i2, int i7, float f) {
        j.e(rectF, "bitmapRectInScreen");
        this.bitmap = bitmap2;
        this.bitmapRectInScreen = rectF;
        this.imageHeight = i2;
        this.imageWidth = i7;
        this.imageRatio = f;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ ImageInfo(android.graphics.Bitmap r2, android.graphics.RectF r3, int r4, int r5, float r6, int r7, kotlin.jvm.internal.e r8) {
        /*
            r1 = this;
            r8 = r7 & 1
            if (r8 == 0) goto L_0x0005
            r2 = 0
        L_0x0005:
            r8 = r7 & 2
            if (r8 == 0) goto L_0x000e
            android.graphics.RectF r3 = new android.graphics.RectF
            r3.<init>()
        L_0x000e:
            r8 = r7 & 4
            r0 = -1
            if (r8 == 0) goto L_0x0014
            r4 = r0
        L_0x0014:
            r8 = r7 & 8
            if (r8 == 0) goto L_0x0019
            r5 = r0
        L_0x0019:
            r7 = r7 & 16
            if (r7 == 0) goto L_0x001f
            r6 = 1065353216(0x3f800000, float:1.0)
        L_0x001f:
            r7 = r5
            r8 = r6
            r5 = r3
            r6 = r4
            r3 = r1
            r4 = r2
            r3.<init>(r4, r5, r6, r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.objectcapture.ui.data.ImageInfo.<init>(android.graphics.Bitmap, android.graphics.RectF, int, int, float, int, kotlin.jvm.internal.e):void");
    }
}
