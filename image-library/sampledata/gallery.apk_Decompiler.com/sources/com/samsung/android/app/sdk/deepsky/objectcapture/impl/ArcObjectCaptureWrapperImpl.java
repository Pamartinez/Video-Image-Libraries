package com.samsung.android.app.sdk.deepsky.objectcapture.impl;

import L2.a;
import Tf.n;
import android.graphics.Bitmap;
import android.os.SemSystemProperties;
import com.arcsoft.libobjectcapture.ArcObjectCaptureJNI;
import com.arcsoft.libobjectcapture.parameters.SD_RESULT;
import com.samsung.android.app.sdk.deepsky.objectcapture.logger.LibLogger;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.k;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\u0007\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0007\u0010\u0006J\u001f\u0010\r\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u000f\u0010\u0006J\u000f\u0010\u0011\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0014\u001a\u00020\u00138\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015¨\u0006\u0017"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/impl/ArcObjectCaptureWrapperImpl;", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/impl/ArcObjectCaptureWrapper;", "<init>", "()V", "", "getPlatformId", "()I", "init", "Landroid/graphics/Bitmap;", "bitmap", "Lcom/arcsoft/libobjectcapture/parameters/SD_RESULT;", "result", "Lme/x;", "capture", "(Landroid/graphics/Bitmap;Lcom/arcsoft/libobjectcapture/parameters/SD_RESULT;)V", "release", "", "getVersion", "()Ljava/lang/String;", "Lcom/arcsoft/libobjectcapture/ArcObjectCaptureJNI;", "instance", "Lcom/arcsoft/libobjectcapture/ArcObjectCaptureJNI;", "Companion", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ArcObjectCaptureWrapperImpl implements ArcObjectCaptureWrapper {
    private static final String AP_MEDIA_TEK = "mt";
    private static final String AP_QUALCOMM = "qcom";
    public static final Companion Companion = new Companion((e) null);
    private static final String MODEL_A56 = "a56";
    private static final String MODEL_RAINBOW = "r0";
    private static final String MODEL_RAINBOW_PLUS = "g0";
    private static final String MODEL_RAINBOW_ULTRA = "b0";
    private static final String TAG = "ArcObjectCaptureWrapperImpl";
    private final ArcObjectCaptureJNI instance = new ArcObjectCaptureJNI();

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/impl/ArcObjectCaptureWrapperImpl$Companion;", "", "<init>", "()V", "TAG", "", "AP_QUALCOMM", "AP_MEDIA_TEK", "MODEL_RAINBOW", "MODEL_RAINBOW_PLUS", "MODEL_RAINBOW_ULTRA", "MODEL_A56", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    private final int getPlatformId() {
        Object obj;
        int i2;
        String str = SemSystemProperties.get("ro.hardware");
        j.d(str, "get(...)");
        LibLogger.d(TAG, "getPlatformId, current hardware name: ".concat(str));
        try {
            if (n.u0(str, AP_QUALCOMM)) {
                LibLogger.d(TAG, "getPlatformId, DEVICE = qcom");
                i2 = 1;
            } else if (n.u0(str, AP_MEDIA_TEK)) {
                LibLogger.d(TAG, "getPlatformId, DEVICE = mt");
                i2 = 3;
            } else {
                String str2 = SemSystemProperties.get("ro.product.device");
                j.d(str2, "get(...)");
                LibLogger.d(TAG, "getPlatformId, current device name: ".concat(str2));
                if ((n.u0(str2, MODEL_RAINBOW) | n.u0(str2, MODEL_RAINBOW_PLUS)) || n.u0(str2, MODEL_RAINBOW_ULTRA)) {
                    LibLogger.d(TAG, "getPlatformId, DEVICE = S22 S.LSI");
                    i2 = 2;
                } else if (n.u0(str2, MODEL_A56)) {
                    LibLogger.d(TAG, "getPlatformId, DEVICE = A56 S.LSI");
                    i2 = 6;
                } else {
                    LibLogger.d(TAG, "getPlatformId, DEVICE = S.LSI");
                    i2 = 4;
                }
            }
            obj = Integer.valueOf(i2);
        } catch (Throwable th) {
            obj = a.l(th);
        }
        Throwable a7 = k.a(obj);
        if (a7 != null) {
            LibLogger.e(TAG, "getPlatformId, onFailure, " + a7);
        }
        if (obj instanceof me.j) {
            obj = null;
        }
        Integer num = (Integer) obj;
        if (num != null) {
            return num.intValue();
        }
        return 0;
    }

    public void capture(Bitmap bitmap, SD_RESULT sd_result) {
        j.e(bitmap, "bitmap");
        j.e(sd_result, "result");
        LibLogger.i(TAG, "capture, start");
        int OBJECT_CAPTURE_Process = this.instance.OBJECT_CAPTURE_Process(bitmap, sd_result);
        int i2 = sd_result.mSalientNum;
        LibLogger.i(TAG, "capture, done, ret: " + OBJECT_CAPTURE_Process + ", mSalientNum: " + i2);
        try {
            LibLogger.i(TAG, "capture, recycleObjectsBitmap, done");
        } catch (Throwable th) {
            a.l(th);
        }
    }

    public String getVersion() {
        String OBJECT_CAPTURE_GetVersion = this.instance.OBJECT_CAPTURE_GetVersion();
        j.d(OBJECT_CAPTURE_GetVersion, "OBJECT_CAPTURE_GetVersion(...)");
        return OBJECT_CAPTURE_GetVersion;
    }

    public int init() {
        LibLogger.i(TAG, "init, start");
        int platformId = getPlatformId();
        LibLogger.i(TAG, "init, platformId: " + platformId);
        int OBJECT_CAPTURE_Init = this.instance.OBJECT_CAPTURE_Init(platformId);
        LibLogger.i(TAG, "init, done, ret: " + OBJECT_CAPTURE_Init);
        return OBJECT_CAPTURE_Init;
    }

    public int release() {
        LibLogger.i(TAG, "release, start");
        int OBJECT_CAPTURE_Uninit = this.instance.OBJECT_CAPTURE_Uninit();
        LibLogger.i(TAG, "release, done, ret: " + OBJECT_CAPTURE_Uninit);
        return OBJECT_CAPTURE_Uninit;
    }
}
