package com.samsung.android.vexfwk.bitmap;

import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import android.hardware.HardwareBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u00002\u00060\u0001j\u0002`\u0002B)\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\t\u0010\nJ\r\u0010\u000b\u001a\u00020\u0007¢\u0006\u0004\b\u000b\u0010\fJ5\u0010\u0014\u001a\u00020\u00132\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0017\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u0017\u0010\u0018R\u0017\u0010\u001a\u001a\u00020\u00198\u0006¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\u00138\u0006X\u0004¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!R\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\"\u001a\u0004\b#\u0010$R\u0017\u0010\u0005\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0005\u0010\"\u001a\u0004\b%\u0010$¨\u0006&"}, d2 = {"Lcom/samsung/android/vexfwk/bitmap/VexFwkBitmapHardware;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "", "width", "height", "format", "", "pixels", "<init>", "(III[I)V", "toIntArray", "()[I", "left", "top", "right", "bottom", "Landroid/graphics/Bitmap$Config;", "config", "Landroid/graphics/Bitmap;", "toBitmapCropped", "(IIIILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;", "Lme/x;", "close", "()V", "Landroid/hardware/HardwareBuffer;", "hardwareBuffer", "Landroid/hardware/HardwareBuffer;", "getHardwareBuffer", "()Landroid/hardware/HardwareBuffer;", "bitmap", "Landroid/graphics/Bitmap;", "getBitmap", "()Landroid/graphics/Bitmap;", "I", "getWidth", "()I", "getHeight", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkBitmapHardware implements AutoCloseable {
    private final Bitmap bitmap;
    private final HardwareBuffer hardwareBuffer;
    private final int height;
    private final int width;

    public VexFwkBitmapHardware(int i2, int i7, int i8, int[] iArr) {
        HardwareBuffer create = HardwareBuffer.create(i2, i7, i8, 1, 307);
        j.d(create, "create(...)");
        this.hardwareBuffer = create;
        Bitmap wrapHardwareBuffer = Bitmap.wrapHardwareBuffer(create, (ColorSpace) null);
        j.b(wrapHardwareBuffer);
        this.bitmap = wrapHardwareBuffer;
        this.width = wrapHardwareBuffer.getWidth();
        this.height = wrapHardwareBuffer.getHeight();
        if (iArr != null) {
            VexFwkBitmap.Companion.copyBitmap(iArr, wrapHardwareBuffer);
        }
    }

    public void close() {
        this.bitmap.recycle();
        this.hardwareBuffer.close();
    }

    public final Bitmap getBitmap() {
        return this.bitmap;
    }

    public final HardwareBuffer getHardwareBuffer() {
        return this.hardwareBuffer;
    }

    public final int getHeight() {
        return this.height;
    }

    public final int getWidth() {
        return this.width;
    }

    public final Bitmap toBitmapCropped(int i2, int i7, int i8, int i10, Bitmap.Config config) {
        j.e(config, "config");
        Bitmap createBitmap = Bitmap.createBitmap(i8 - i2, i10 - i7, config);
        j.d(createBitmap, "createBitmap(...)");
        VexFwkBitmap.Companion.copyBitmap(this.bitmap, createBitmap, i2, i7, i8, i10);
        return createBitmap;
    }

    public final int[] toIntArray() {
        int[] iArr = new int[(this.width * this.height)];
        VexFwkBitmap.Companion.copyBitmap(this.bitmap, iArr);
        return iArr;
    }
}
