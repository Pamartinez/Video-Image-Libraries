package com.samsung.android.app.sdk.deepsky.objectcapture.impl;

import L2.a;
import android.graphics.Bitmap;
import com.samsung.android.app.sdk.deepsky.objectcapture.logger.LibLogger;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import srib.vizinsight.dvs.UNF_Image_Clipper_Interface;
import srib.vizinsight.dvs.UNF_Object_Segment_Info;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0005\u0010\u0006J\u001f\u0010\f\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u000e\u0010\u0006J\u000f\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0014\u001a\n \u0013*\u0004\u0018\u00010\u00120\u00128\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015¨\u0006\u0017"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/impl/SribCaptureWrapperImpl;", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/impl/SribCaptureWrapper;", "<init>", "()V", "", "init", "()I", "Landroid/graphics/Bitmap;", "bitmap", "Lsrib/vizinsight/dvs/UNF_Object_Segment_Info;", "result", "Lme/x;", "capture", "(Landroid/graphics/Bitmap;Lsrib/vizinsight/dvs/UNF_Object_Segment_Info;)V", "release", "", "getVersion", "()Ljava/lang/String;", "Lsrib/vizinsight/dvs/UNF_Image_Clipper_Interface;", "kotlin.jvm.PlatformType", "instance", "Lsrib/vizinsight/dvs/UNF_Image_Clipper_Interface;", "Companion", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SribCaptureWrapperImpl implements SribCaptureWrapper {
    public static final Companion Companion = new Companion((e) null);
    public static final String SUPPORTED_MODEL_B7Q7 = "unifiedclipper";
    private static final String TAG = "SribCaptureWrapperImpl";
    private final UNF_Image_Clipper_Interface instance = UNF_Image_Clipper_Interface.getInstance();

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/impl/SribCaptureWrapperImpl$Companion;", "", "<init>", "()V", "TAG", "", "SUPPORTED_MODEL_B7Q7", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public void capture(Bitmap bitmap, UNF_Object_Segment_Info uNF_Object_Segment_Info) {
        j.e(bitmap, "bitmap");
        j.e(uNF_Object_Segment_Info, "result");
        LibLogger.i(TAG, "capture, start");
        int Unified_Image_Clipper_Execute = this.instance.Unified_Image_Clipper_Execute(bitmap, uNF_Object_Segment_Info);
        int i2 = uNF_Object_Segment_Info.mSalientNum;
        LibLogger.i(TAG, "capture, done, ret: " + Unified_Image_Clipper_Execute + ", mSalientNum: " + i2);
        try {
            LibLogger.i(TAG, "capture, recycleObjectsBitmap, done");
        } catch (Throwable th) {
            a.l(th);
        }
    }

    public String getVersion() {
        String Unified_Image_Clipper_Get_Version = this.instance.Unified_Image_Clipper_Get_Version();
        j.d(Unified_Image_Clipper_Get_Version, "Unified_Image_Clipper_Get_Version(...)");
        return Unified_Image_Clipper_Get_Version;
    }

    public int init() {
        LibLogger.i(TAG, "init, start");
        int Unified_Image_Clipper_Init = this.instance.Unified_Image_Clipper_Init();
        LibLogger.i(TAG, "init, done, ret: " + Unified_Image_Clipper_Init);
        return Unified_Image_Clipper_Init;
    }

    public int release() {
        LibLogger.i(TAG, "release, start");
        int Unified_Image_Clipper_DeInit = this.instance.Unified_Image_Clipper_DeInit();
        LibLogger.i(TAG, "release, done, ret: " + Unified_Image_Clipper_DeInit);
        return Unified_Image_Clipper_DeInit;
    }
}
