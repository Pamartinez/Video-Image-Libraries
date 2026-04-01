package com.samsung.android.app.sdk.deepsky.objectcapture.base;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import com.samsung.android.vexfwk.sdk.objeraser.VexFwkImageObjectRemover;
import com.samsung.android.vexfwk.sdk.objeraser.VexFwkVideoObjectRemover;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H'¢\u0006\u0004\b\u0005\u0010\u0006J'\u0010\u0005\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H'¢\u0006\u0004\b\u0005\u0010\u000bJ1\u0010\u0014\u001a\u00020\u00132\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0012\u001a\u00020\u0011H'¢\u0006\u0004\b\u0014\u0010\u0015J1\u0010\u0018\u001a\u00020\u00132\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u0012\u001a\u00020\u0011H'¢\u0006\u0004\b\u0018\u0010\u0019J'\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u001cH'¢\u0006\u0004\b\u0018\u0010\u001eJ\u000f\u0010 \u001a\u00020\u001fH'¢\u0006\u0004\b \u0010!J\u000f\u0010\"\u001a\u00020\u001fH'¢\u0006\u0004\b\"\u0010!J\u000f\u0010#\u001a\u00020\u0011H&¢\u0006\u0004\b#\u0010$J\u000f\u0010%\u001a\u00020\u0011H&¢\u0006\u0004\b%\u0010$J\u0017\u0010)\u001a\u00020(2\u0006\u0010'\u001a\u00020&H&¢\u0006\u0004\b)\u0010*¨\u0006+"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectCapture;", "", "Landroid/graphics/Bitmap;", "bitmap", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectResult;", "capture", "(Landroid/graphics/Bitmap;)Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectResult;", "", "x", "y", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/MaskedObjectResult;", "(Landroid/graphics/Bitmap;FF)Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/MaskedObjectResult;", "", "inputPath", "outputPath", "Lcom/samsung/android/vexfwk/sdk/objeraser/VexFwkVideoObjectRemover$ObjectMask;", "videoInputMask", "", "mediaScan", "", "removeVideoObject", "(Ljava/lang/String;Ljava/lang/String;Lcom/samsung/android/vexfwk/sdk/objeraser/VexFwkVideoObjectRemover$ObjectMask;Z)I", "Lcom/samsung/android/vexfwk/sdk/objeraser/VexFwkImageObjectRemover$ObjectMask;", "imageInputMask", "removeImageObject", "(Ljava/lang/String;Ljava/lang/String;Lcom/samsung/android/vexfwk/sdk/objeraser/VexFwkImageObjectRemover$ObjectMask;Z)I", "", "mask", "Landroid/graphics/Rect;", "roi", "(Landroid/graphics/Bitmap;[ILandroid/graphics/Rect;)Landroid/graphics/Bitmap;", "Lme/x;", "init", "()V", "release", "isObjectCaptureSupported", "()Z", "isObjectRemoveSupported", "Landroid/content/Context;", "context", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectCaptureDrawHelper;", "getObjectCaptureDrawHelper", "(Landroid/content/Context;)Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectCaptureDrawHelper;", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ObjectCapture {

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class DefaultImpls {
        public static /* synthetic */ int removeImageObject$default(ObjectCapture objectCapture, String str, String str2, VexFwkImageObjectRemover.ObjectMask objectMask, boolean z, int i2, Object obj) {
            if (obj == null) {
                if ((i2 & 8) != 0) {
                    z = false;
                }
                return objectCapture.removeImageObject(str, str2, objectMask, z);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: removeImageObject");
        }

        public static /* synthetic */ int removeVideoObject$default(ObjectCapture objectCapture, String str, String str2, VexFwkVideoObjectRemover.ObjectMask objectMask, boolean z, int i2, Object obj) {
            if (obj == null) {
                if ((i2 & 8) != 0) {
                    z = false;
                }
                return objectCapture.removeVideoObject(str, str2, objectMask, z);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: removeVideoObject");
        }
    }

    MaskedObjectResult capture(Bitmap bitmap, float f, float f5);

    ObjectResult capture(Bitmap bitmap);

    ObjectCaptureDrawHelper getObjectCaptureDrawHelper(Context context);

    void init();

    boolean isObjectCaptureSupported();

    boolean isObjectRemoveSupported();

    void release();

    int removeImageObject(String str, String str2, VexFwkImageObjectRemover.ObjectMask objectMask, boolean z);

    Bitmap removeImageObject(Bitmap bitmap, int[] iArr, Rect rect);

    int removeVideoObject(String str, String str2, VexFwkVideoObjectRemover.ObjectMask objectMask, boolean z);
}
