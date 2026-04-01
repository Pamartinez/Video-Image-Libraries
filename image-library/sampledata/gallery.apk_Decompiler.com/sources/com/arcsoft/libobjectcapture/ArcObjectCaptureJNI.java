package com.arcsoft.libobjectcapture;

import android.graphics.Bitmap;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.arcsoft.libobjectcapture.parameters.ARC_SD_OBJECT_BOX;
import com.arcsoft.libobjectcapture.parameters.SD_RESULT;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ArcObjectCaptureJNI {
    public static final int DEVICE_DEFAULT = 0;
    public static final int DEVICE_LSI = 4;
    public static final int DEVICE_LSI_A56 = 6;
    public static final int DEVICE_LSI_S22 = 2;
    public static final int DEVICE_MTK = 3;
    public static final int DEVICE_MTK_DX3 = 5;
    public static final int DEVICE_QC = 1;
    private static final String TAG = "ArcSoft_ArcObjectCaptureJNI_1.0.3";
    private static VER_CODE verCode;
    private long m_ArcEng = 0;

    /* renamed from: com.arcsoft.libobjectcapture.ArcObjectCaptureJNI$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$arcsoft$libobjectcapture$ArcObjectCaptureJNI$VER_CODE;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.arcsoft.libobjectcapture.ArcObjectCaptureJNI$VER_CODE[] r0 = com.arcsoft.libobjectcapture.ArcObjectCaptureJNI.VER_CODE.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$arcsoft$libobjectcapture$ArcObjectCaptureJNI$VER_CODE = r0
                com.arcsoft.libobjectcapture.ArcObjectCaptureJNI$VER_CODE r1 = com.arcsoft.libobjectcapture.ArcObjectCaptureJNI.VER_CODE.V103     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$arcsoft$libobjectcapture$ArcObjectCaptureJNI$VER_CODE     // Catch:{ NoSuchFieldError -> 0x001d }
                com.arcsoft.libobjectcapture.ArcObjectCaptureJNI$VER_CODE r1 = com.arcsoft.libobjectcapture.ArcObjectCaptureJNI.VER_CODE.V102     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$arcsoft$libobjectcapture$ArcObjectCaptureJNI$VER_CODE     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.arcsoft.libobjectcapture.ArcObjectCaptureJNI$VER_CODE r1 = com.arcsoft.libobjectcapture.ArcObjectCaptureJNI.VER_CODE.V101     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$arcsoft$libobjectcapture$ArcObjectCaptureJNI$VER_CODE     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.arcsoft.libobjectcapture.ArcObjectCaptureJNI$VER_CODE r1 = com.arcsoft.libobjectcapture.ArcObjectCaptureJNI.VER_CODE.UNSUPPORT     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arcsoft.libobjectcapture.ArcObjectCaptureJNI.AnonymousClass1.<clinit>():void");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum VER_CODE {
        UNSUPPORT,
        V101,
        V102,
        V103
    }

    static {
        System.loadLibrary("objectcapture_jni.arcsoft");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0041, code lost:
        if (r5.equals("1.0.3") == false) goto L_0x0039;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void getNativeVerCode() {
        /*
            r5 = this;
            java.lang.String r5 = r5.native_ARC_OBJECT_CAPTURE_GetVersion()
            java.lang.String r0 = "_"
            java.lang.String[] r5 = r5.split(r0)
            r0 = 2
            r5 = r5[r0]
            r1 = 5
            r2 = 0
            java.lang.String r5 = r5.substring(r2, r1)
            java.lang.String r1 = TAG
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "JAR ver:"
            r3.<init>(r4)
            r3.append(r1)
            java.lang.String r4 = ", JNI vNum: "
            r3.append(r4)
            r3.append(r5)
            java.lang.String r3 = r3.toString()
            com.arcsoft.libarccommon.utils.ArcCommonLog.i(r1, r3)
            r5.getClass()
            int r1 = r5.hashCode()
            r3 = -1
            switch(r1) {
                case 46670518: goto L_0x004f;
                case 46670519: goto L_0x0044;
                case 46670520: goto L_0x003b;
                default: goto L_0x0039;
            }
        L_0x0039:
            r0 = r3
            goto L_0x0059
        L_0x003b:
            java.lang.String r1 = "1.0.3"
            boolean r5 = r5.equals(r1)
            if (r5 != 0) goto L_0x0059
            goto L_0x0039
        L_0x0044:
            java.lang.String r0 = "1.0.2"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x004d
            goto L_0x0039
        L_0x004d:
            r0 = 1
            goto L_0x0059
        L_0x004f:
            java.lang.String r0 = "1.0.1"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x0058
            goto L_0x0039
        L_0x0058:
            r0 = r2
        L_0x0059:
            switch(r0) {
                case 0: goto L_0x006b;
                case 1: goto L_0x0066;
                case 2: goto L_0x0061;
                default: goto L_0x005c;
            }
        L_0x005c:
            com.arcsoft.libobjectcapture.ArcObjectCaptureJNI$VER_CODE r5 = com.arcsoft.libobjectcapture.ArcObjectCaptureJNI.VER_CODE.UNSUPPORT
            verCode = r5
            return
        L_0x0061:
            com.arcsoft.libobjectcapture.ArcObjectCaptureJNI$VER_CODE r5 = com.arcsoft.libobjectcapture.ArcObjectCaptureJNI.VER_CODE.V103
            verCode = r5
            return
        L_0x0066:
            com.arcsoft.libobjectcapture.ArcObjectCaptureJNI$VER_CODE r5 = com.arcsoft.libobjectcapture.ArcObjectCaptureJNI.VER_CODE.V102
            verCode = r5
            return
        L_0x006b:
            com.arcsoft.libobjectcapture.ArcObjectCaptureJNI$VER_CODE r5 = com.arcsoft.libobjectcapture.ArcObjectCaptureJNI.VER_CODE.V101
            verCode = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arcsoft.libobjectcapture.ArcObjectCaptureJNI.getNativeVerCode():void");
    }

    private native String native_ARC_OBJECT_CAPTURE_GetVersion();

    private native int native_ARC_OBJECT_CAPTURE_Init();

    private native int native_ARC_OBJECT_CAPTURE_Init(int i2);

    private native int native_ARC_OBJECT_CAPTURE_Process(long j2, Bitmap bitmap, SD_RESULT sd_result);

    private native int native_ARC_OBJECT_CAPTURE_Uninit(long j2);

    private void processResult(Bitmap bitmap, SD_RESULT sd_result) {
        if (sd_result.mSalientNum > 0) {
            int i2 = AnonymousClass1.$SwitchMap$com$arcsoft$libobjectcapture$ArcObjectCaptureJNI$VER_CODE[verCode.ordinal()];
            int i7 = 0;
            if (i2 == 1) {
                Bitmap bitmap2 = sd_result.mSingleBitmap;
                if (bitmap2 != null) {
                    bitmap2.setHasAlpha(true);
                }
                if (sd_result.mObjectsBitmap != null) {
                    while (i7 < sd_result.mSalientNum) {
                        Bitmap bitmap3 = sd_result.mObjectsBitmap[i7];
                        if (bitmap3 != null) {
                            bitmap3.setHasAlpha(true);
                        }
                        i7++;
                    }
                }
            } else if (i2 == 2) {
                ARC_SD_OBJECT_BOX arc_sd_object_box = sd_result.mSingleRect;
                int i8 = (int) (arc_sd_object_box.mRightBottomX - arc_sd_object_box.mLeftTopX);
                int i10 = (int) (arc_sd_object_box.mRightBottomY - arc_sd_object_box.mLeftTopY);
                int[] iArr = new int[(i8 * i10)];
                sd_result.mSingleBitmap.getPixels(iArr, 0, i8, 0, 0, i8, i10);
                Bitmap createBitmap = Bitmap.createBitmap(iArr, i8, i10, bitmap.getConfig());
                sd_result.mSingleBitmap = createBitmap;
                createBitmap.setHasAlpha(true);
                while (i7 < sd_result.mSalientNum) {
                    ARC_SD_OBJECT_BOX arc_sd_object_box2 = sd_result.mObjectsRect[i7];
                    int i11 = (int) (arc_sd_object_box2.mRightBottomX - arc_sd_object_box2.mLeftTopX);
                    int i12 = (int) (arc_sd_object_box2.mRightBottomY - arc_sd_object_box2.mLeftTopY);
                    int[] iArr2 = new int[(i11 * i12)];
                    sd_result.mObjectsBitmap[i7].getPixels(iArr2, 0, i11, 0, 0, i11, i12);
                    sd_result.mObjectsBitmap[i7] = Bitmap.createBitmap(iArr2, i11, i12, bitmap.getConfig());
                    sd_result.mObjectsBitmap[i7].setHasAlpha(true);
                    i7++;
                }
            } else if (i2 == 3) {
                ARC_SD_OBJECT_BOX arc_sd_object_box3 = sd_result.mSingleRect;
                long j2 = arc_sd_object_box3.mLeftTopX;
                long j3 = arc_sd_object_box3.mLeftTopY;
                int i13 = (int) (arc_sd_object_box3.mRightBottomX - j2);
                int i14 = (int) (arc_sd_object_box3.mRightBottomY - j3);
                int[] iArr3 = new int[(i13 * i14)];
                sd_result.mSingleBitmap.getPixels(iArr3, 0, i13, (int) j2, (int) j3, i13, i14);
                Bitmap createBitmap2 = Bitmap.createBitmap(iArr3, i13, i14, bitmap.getConfig());
                sd_result.mSingleBitmap = createBitmap2;
                createBitmap2.setHasAlpha(true);
                while (i7 < sd_result.mSalientNum) {
                    ARC_SD_OBJECT_BOX arc_sd_object_box4 = sd_result.mObjectsRect[i7];
                    int i15 = (int) (arc_sd_object_box4.mRightBottomX - arc_sd_object_box4.mLeftTopX);
                    int i16 = (int) (arc_sd_object_box4.mRightBottomY - arc_sd_object_box4.mLeftTopY);
                    int[] iArr4 = new int[(i15 * i16)];
                    sd_result.mObjectsBitmap[i7].getPixels(iArr4, 0, i15, 0, 0, i15, i16);
                    sd_result.mObjectsBitmap[i7] = Bitmap.createBitmap(iArr4, i15, i16, bitmap.getConfig());
                    sd_result.mObjectsBitmap[i7].setHasAlpha(true);
                    i7++;
                }
            } else if (i2 != 4) {
                ArcCommonLog.e(TAG, "Unknown version.");
            } else {
                ArcCommonLog.e(TAG, "Not supported! Please update .SO to the latest version.");
            }
        } else {
            ArcCommonLog.d(TAG, "SDResult.mSalientNum = 0, no object detected!");
        }
    }

    public String OBJECT_CAPTURE_GetVersion() {
        return native_ARC_OBJECT_CAPTURE_GetVersion();
    }

    public int OBJECT_CAPTURE_Init(int i2) {
        int i7;
        getNativeVerCode();
        if (verCode == VER_CODE.UNSUPPORT) {
            ArcCommonLog.e(TAG, "Not supported! Please update .SO to latest version.");
            return 7;
        }
        if (verCode.ordinal() > VER_CODE.V101.ordinal()) {
            i7 = native_ARC_OBJECT_CAPTURE_Init(i2);
        } else {
            i7 = native_ARC_OBJECT_CAPTURE_Init();
        }
        if (i7 != 0) {
            String str = TAG;
            ArcCommonLog.e(str, "native_ARC_OBJECT_CAPTURE_Init error, res = " + i7);
        }
        return i7;
    }

    public int OBJECT_CAPTURE_Process(Bitmap bitmap, SD_RESULT sd_result) {
        getNativeVerCode();
        if (verCode == VER_CODE.UNSUPPORT) {
            ArcCommonLog.e(TAG, "Not supported! Please update .SO to latest version.");
            return 7;
        } else if (bitmap == null || sd_result == null) {
            ArcCommonLog.e(TAG, "input error, res = 2");
            return 2;
        } else {
            sd_result.errorCode = 0;
            sd_result.mSalientNum = 0;
            sd_result.solutionInfo = "";
            sd_result.mObjectsBitmap = null;
            sd_result.mSingleBitmap = null;
            sd_result.mObjectsRect = null;
            sd_result.mSingleRect = null;
            long j2 = this.m_ArcEng;
            if (0 != j2) {
                int native_ARC_OBJECT_CAPTURE_Process = native_ARC_OBJECT_CAPTURE_Process(j2, bitmap, sd_result);
                if (native_ARC_OBJECT_CAPTURE_Process != 0) {
                    String str = TAG;
                    ArcCommonLog.e(str, "native_ARC_OBJECT_CAPTURE_Process error, res = " + native_ARC_OBJECT_CAPTURE_Process);
                    return native_ARC_OBJECT_CAPTURE_Process;
                }
                processResult(bitmap, sd_result);
                return native_ARC_OBJECT_CAPTURE_Process;
            }
            ArcCommonLog.e(TAG, "native_ARC_OBJECT_CAPTURE_Uninit without init, res = 2");
            return 2;
        }
    }

    public int OBJECT_CAPTURE_Uninit() {
        long j2 = this.m_ArcEng;
        if (0 != j2) {
            int native_ARC_OBJECT_CAPTURE_Uninit = native_ARC_OBJECT_CAPTURE_Uninit(j2);
            this.m_ArcEng = 0;
            if (native_ARC_OBJECT_CAPTURE_Uninit != 0) {
                String str = TAG;
                ArcCommonLog.e(str, "native_ARC_OBJECT_CAPTURE_Uninit error, res = " + native_ARC_OBJECT_CAPTURE_Uninit);
            }
            return native_ARC_OBJECT_CAPTURE_Uninit;
        }
        ArcCommonLog.e(TAG, "native_ARC_OBJECT_CAPTURE_Uninit without init, res = 2");
        return 2;
    }
}
