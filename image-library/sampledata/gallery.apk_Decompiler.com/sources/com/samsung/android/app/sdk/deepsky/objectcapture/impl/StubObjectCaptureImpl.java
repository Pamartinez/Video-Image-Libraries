package com.samsung.android.app.sdk.deepsky.objectcapture.impl;

import L2.a;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Looper;
import com.samsung.android.app.sdk.deepsky.objectcapture.base.MaskedObjectResult;
import com.samsung.android.app.sdk.deepsky.objectcapture.base.ObjectCapture;
import com.samsung.android.app.sdk.deepsky.objectcapture.base.ObjectCaptureDrawHelper;
import com.samsung.android.app.sdk.deepsky.objectcapture.base.ObjectInfo;
import com.samsung.android.app.sdk.deepsky.objectcapture.base.ObjectResult;
import com.samsung.android.app.sdk.deepsky.objectcapture.logger.LibLogger;
import com.samsung.android.app.sdk.deepsky.objectcapture.ui.ObjectCaptureDrawHelperImpl;
import com.samsung.android.gallery.database.dbtype.ScreenShotFilterType;
import com.samsung.android.vexfwk.sdk.objeraser.VexFwkImageObjectRemover;
import com.samsung.android.vexfwk.sdk.objeraser.VexFwkVideoObjectRemover;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1202t;
import o1.C0246a;

@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J)\u0010\f\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000f\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0007\u001a\u00020\u0006H\u0017¢\u0006\u0004\b\u0012\u0010\u0013J'\u0010\u0012\u001a\u00020\u00172\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0016¢\u0006\u0004\b\u0012\u0010\u0018J/\u0010!\u001a\u00020 2\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\u001eH\u0017¢\u0006\u0004\b!\u0010\"J'\u0010&\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010$\u001a\u00020#2\u0006\u0010%\u001a\u00020\bH\u0016¢\u0006\u0004\b&\u0010'J/\u0010&\u001a\u00020 2\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u00192\u0006\u0010)\u001a\u00020(2\u0006\u0010\u001f\u001a\u00020\u001eH\u0016¢\u0006\u0004\b&\u0010*J\u000f\u0010+\u001a\u00020\u000eH\u0017¢\u0006\u0004\b+\u0010\u0010J\u000f\u0010,\u001a\u00020\u000eH\u0017¢\u0006\u0004\b,\u0010\u0010J\u000f\u0010-\u001a\u00020\u001eH\u0016¢\u0006\u0004\b-\u0010.J\u000f\u0010/\u001a\u00020\u001eH\u0016¢\u0006\u0004\b/\u0010.J\u0017\u00101\u001a\u0002002\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b1\u00102R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u00103R\u0014\u00104\u001a\u00020\u00198\u0002XD¢\u0006\u0006\n\u0004\b4\u00105¨\u00066"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/impl/StubObjectCaptureImpl;", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectCapture;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Landroid/graphics/Bitmap;", "bitmap", "Landroid/graphics/Rect;", "boundRect", "Landroid/graphics/Path;", "objPath", "createCropBitmap", "(Landroid/graphics/Bitmap;Landroid/graphics/Rect;Landroid/graphics/Path;)Landroid/graphics/Bitmap;", "Lme/x;", "checkThread", "()V", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectResult;", "capture", "(Landroid/graphics/Bitmap;)Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectResult;", "", "x", "y", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/MaskedObjectResult;", "(Landroid/graphics/Bitmap;FF)Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/MaskedObjectResult;", "", "inputPath", "outputPath", "Lcom/samsung/android/vexfwk/sdk/objeraser/VexFwkVideoObjectRemover$ObjectMask;", "videoInputMask", "", "mediaScan", "", "removeVideoObject", "(Ljava/lang/String;Ljava/lang/String;Lcom/samsung/android/vexfwk/sdk/objeraser/VexFwkVideoObjectRemover$ObjectMask;Z)I", "", "mask", "roi", "removeImageObject", "(Landroid/graphics/Bitmap;[ILandroid/graphics/Rect;)Landroid/graphics/Bitmap;", "Lcom/samsung/android/vexfwk/sdk/objeraser/VexFwkImageObjectRemover$ObjectMask;", "imageInputMask", "(Ljava/lang/String;Ljava/lang/String;Lcom/samsung/android/vexfwk/sdk/objeraser/VexFwkImageObjectRemover$ObjectMask;Z)I", "init", "release", "isObjectCaptureSupported", "()Z", "isObjectRemoveSupported", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectCaptureDrawHelper;", "getObjectCaptureDrawHelper", "(Landroid/content/Context;)Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectCaptureDrawHelper;", "Landroid/content/Context;", "tag", "Ljava/lang/String;", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class StubObjectCaptureImpl implements ObjectCapture {
    private final Context context;
    private final String tag = "StubObjectCaptureImpl";

    public StubObjectCaptureImpl(Context context2) {
        j.e(context2, "context");
        this.context = context2;
    }

    private final void checkThread() {
        if (j.a(Thread.currentThread(), Looper.getMainLooper().getThread())) {
            throw new IllegalStateException("Should be called on worker thread");
        }
    }

    private final Bitmap createCropBitmap(Bitmap bitmap, Rect rect, Path path) {
        try {
            Rect rect2 = new Rect(0, 0, rect.width(), rect.height());
            Bitmap createBitmap = Bitmap.createBitmap(rect2.width(), rect2.height(), Bitmap.Config.ARGB_8888);
            j.d(createBitmap, "createBitmap(...)");
            Canvas canvas = new Canvas(createBitmap);
            canvas.drawARGB(0, 0, 0, 0);
            Paint paint = new Paint();
            paint.setColor(-7829368);
            paint.setStyle(Paint.Style.FILL);
            Path path2 = new Path(path);
            Matrix matrix = new Matrix();
            matrix.setTranslate(-((float) rect.left), -((float) rect.top));
            path2.transform(matrix);
            canvas.drawPath(path2, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(bitmap, rect, rect2, paint);
            return createBitmap;
        } catch (Throwable th) {
            a.l(th);
            return null;
        }
    }

    public ObjectResult capture(Bitmap bitmap) {
        j.e(bitmap, "bitmap");
        LibLogger.w(this.tag, ScreenShotFilterType.CAPTURE);
        checkThread();
        if (bitmap.getWidth() == 0) {
            throw new IllegalStateException("invalid bitmap width");
        } else if (bitmap.getHeight() != 0) {
            float min = ((float) Math.min(bitmap.getWidth(), bitmap.getHeight())) / 4.0f;
            String str = this.tag;
            LibLogger.w(str, "capture, radius: " + min);
            Rect rect = new Rect();
            Path path = new Path();
            path.addCircle(((float) bitmap.getWidth()) / 2.0f, ((float) bitmap.getHeight()) / 2.0f, min, Path.Direction.CW);
            RectF rectF = new RectF();
            path.computeBounds(rectF, true);
            rect.set((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom);
            Bitmap createCropBitmap = createCropBitmap(bitmap, rect, path);
            if (createCropBitmap == null) {
                return new ObjectResult(false, new ObjectInfo(bitmap, new Rect()), new ObjectInfo(bitmap, new Rect()), C1202t.d, 0, "deepsky-sdk-stub", 16, (e) null);
            }
            return new ObjectResult(true, new ObjectInfo(createCropBitmap, rect), new ObjectInfo(createCropBitmap, rect), C0246a.e0(new ObjectInfo(createCropBitmap, rect)), 0, "deepsky-sdk-stub", 16, (e) null);
        } else {
            throw new IllegalStateException("invalid height height");
        }
    }

    public ObjectCaptureDrawHelper getObjectCaptureDrawHelper(Context context2) {
        j.e(context2, "context");
        return new ObjectCaptureDrawHelperImpl(context2);
    }

    public void init() {
        LibLogger.w(this.tag, "init");
        checkThread();
    }

    public boolean isObjectCaptureSupported() {
        LibLogger.w(this.tag, "isObjectCaptureSupported, true");
        return true;
    }

    public boolean isObjectRemoveSupported() {
        LibLogger.w(this.tag, "isObjectRemoveSupported, false");
        return false;
    }

    public void release() {
        LibLogger.w(this.tag, "release");
        checkThread();
    }

    public int removeImageObject(String str, String str2, VexFwkImageObjectRemover.ObjectMask objectMask, boolean z) {
        j.e(str, "inputPath");
        j.e(str2, "outputPath");
        j.e(objectMask, "imageInputMask");
        return -1;
    }

    public int removeVideoObject(String str, String str2, VexFwkVideoObjectRemover.ObjectMask objectMask, boolean z) {
        j.e(str, "inputPath");
        j.e(str2, "outputPath");
        j.e(objectMask, "videoInputMask");
        return -1;
    }

    public Bitmap removeImageObject(Bitmap bitmap, int[] iArr, Rect rect) {
        j.e(bitmap, "bitmap");
        j.e(iArr, "mask");
        j.e(rect, "roi");
        Bitmap createBitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        j.d(createBitmap, "createBitmap(...)");
        return createBitmap;
    }

    public MaskedObjectResult capture(Bitmap bitmap, float f, float f5) {
        j.e(bitmap, "bitmap");
        return new MaskedObjectResult(false, (Bitmap) null, (int[]) null, (Rect) null, 0, (String) null, 48, (e) null);
    }
}
