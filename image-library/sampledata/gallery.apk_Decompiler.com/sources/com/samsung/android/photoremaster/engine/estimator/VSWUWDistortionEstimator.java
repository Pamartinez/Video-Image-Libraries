package com.samsung.android.photoremaster.engine.estimator;

import android.content.Context;
import android.media.ExifInterface;
import android.util.Log;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.photoremaster.engine.nativeInterface.VSWEngineNativeInterface;
import com.samsung.android.photoremaster.sdk.estimator.EstimatorResult;
import com.samsung.android.photoremaster.sdk.estimator.IEstimator;
import com.samsung.android.photoremaster.sdk.ip.BgrBuffer;
import com.samsung.android.photoremaster.sdk.ip.PrSize;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sivs.ai.sdkcommon.tts.TextToSpeechConst;
import java.io.IOException;
import t1.C0280e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VSWUWDistortionEstimator implements IEstimator {
    private static final int SEF_DATA_TYPE_FRONT_CAM_SELFIE_INFO = 2320;
    private static final String SEF_KEY_NAME_FRONT_CAM_SELFIE_INFO = "Front_Cam_Selfie_Info";
    private static final String SEF_KEY_NAME_GDC = "Gallery_DC_Data";
    private static final String SEF_KEY_NAME_UW_PE_DATA = "UltraWide_PhotoEditor_Data";
    private static final String TAG = "Remaster-VSWUWDistortionEstimator";

    public static boolean checkEXIFPrecondition(String str) {
        String str2 = TAG;
        Log.d(str2, "checkEXIFPrecondition - E");
        boolean z = false;
        try {
            ExifInterface exifInterface = new ExifInterface(str);
            if (exifInterface.hasAttribute("DigitalZoomRatio")) {
                String attribute = exifInterface.getAttribute("DigitalZoomRatio");
                Log.d(str2, "TAG_DIGITAL_ZOOM_RATIO: " + attribute);
                if (attribute != null && attribute.length() > 1) {
                    try {
                        if (Float.parseFloat(attribute) == 1.0f) {
                            z = true;
                        }
                    } catch (NumberFormatException e) {
                        Log.e(TAG, "checkEXIFPrecondition: " + e.toString());
                    }
                }
            } else {
                Log.d(str2, "No TAG_DIGITAL_ZOOM_RATIO");
            }
        } catch (IOException e7) {
            Log.e(TAG, "checkEXIFPrecondition: " + e7.toString());
        }
        Log.d(TAG, "checkEXIFPrecondition - X");
        return z;
    }

    public static boolean[] checkPrecondition(String str) {
        boolean[] zArr = {false, false};
        zArr[0] = checkSEFPrecondition(str);
        zArr[1] = checkEXIFPrecondition(str);
        return zArr;
    }

    public static boolean checkSEFPrecondition(String str) {
        String str2 = TAG;
        Log.d(str2, "checkSEFPrecondition - E");
        String sEFParam = getSEFParam(str);
        boolean z = false;
        if (sEFParam != null && sEFParam.indexOf(GlobalPostProcInternalPPInterface.SPLIT_REGEX) >= 0) {
            String[] split = sEFParam.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            if (split.length > 0 && Integer.parseInt(split[0]) > 0) {
                z = true;
            }
        }
        Log.d(str2, "checkSEFPrecondition - X");
        return z;
    }

    public static boolean estimate(byte[] bArr, boolean[] zArr, byte[] bArr2, int i2, int i7) {
        String str = TAG;
        Log.i(str, "estimate - E");
        VSWEngineNativeInterface.printLibraryVersion();
        boolean runDistortionEstimator = VSWEngineNativeInterface.runDistortionEstimator(bArr, bArr2, zArr, i2, i7);
        Log.i(str, "result : " + runDistortionEstimator);
        Log.i(str, "estimate - X");
        return runDistortionEstimator;
    }

    /* JADX WARNING: Removed duplicated region for block: B:91:0x01d7  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x01f6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getSEFParam(java.lang.String r18) {
        /*
            r0 = r18
            java.lang.String r1 = TAG
            java.lang.String r2 = "getSEFParam - E"
            android.util.Log.d(r1, r2)
            r2 = 3
            java.lang.String[] r2 = new java.lang.String[r2]
            r3 = 0
            java.lang.String r4 = ""
            r2[r3] = r4
            r5 = 1
            r2[r5] = r4
            r6 = 2
            r2[r6] = r4
            int r7 = com.samsung.android.photoremaster.engine.util.SefUtil.getSEFDataCount((java.lang.String) r0)
            java.lang.String r8 = "1"
            java.lang.String r9 = ","
            if (r7 <= 0) goto L_0x01c2
            java.lang.String r7 = "Gallery_DC_Data"
            boolean r10 = com.samsung.android.photoremaster.engine.util.SefUtil.hasSEFData((java.lang.String) r0, (java.lang.String) r7)
            java.lang.String r11 = "UltraWide_PhotoEditor_Data"
            boolean r12 = com.samsung.android.photoremaster.engine.util.SefUtil.hasSEFData((java.lang.String) r0, (java.lang.String) r11)
            java.lang.String r13 = "Front_Cam_Selfie_Info"
            boolean r14 = com.samsung.android.photoremaster.engine.util.SefUtil.hasSEFData((java.lang.String) r0, (java.lang.String) r13)
            java.util.Locale.getDefault()
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r16 = r3
            java.lang.String r3 = "Sef-1: "
            r15.<init>(r3)
            r15.append(r10)
            java.lang.String r3 = ", Sef-2: "
            r15.append(r3)
            r15.append(r12)
            java.lang.String r3 = ", Sef-3: "
            r15.append(r3)
            r15.append(r14)
            java.lang.String r3 = r15.toString()
            android.util.Log.i(r1, r3)
            java.lang.String r3 = "0"
            if (r14 == 0) goto L_0x006d
            int r0 = com.samsung.android.photoremaster.engine.util.SefUtil.getSEFDataType((java.lang.String) r0, (java.lang.String) r13)
            r7 = 2320(0x910, float:3.251E-42)
            if (r0 != r7) goto L_0x006a
            java.lang.String r0 = "result : false, sef, selfie image"
            android.util.Log.d(r1, r0)
        L_0x006a:
            r8 = r3
            goto L_0x01c9
        L_0x006d:
            java.lang.String r13 = ";"
            if (r12 == 0) goto L_0x00eb
            java.lang.String r12 = "Cam distortion off image"
            android.util.Log.i(r1, r12)
            byte[] r11 = com.samsung.android.photoremaster.engine.util.SefUtil.getSEFData((java.lang.String) r0, (java.lang.String) r11)
            java.lang.String r12 = new java.lang.String
            r12.<init>(r11)
            java.lang.String r11 = "strSefData : "
            java.lang.String r11 = r11.concat(r12)
            android.util.Log.i(r1, r11)
            int r11 = r12.length()
            java.lang.String r14 = "Invalid SEF data (UW_PE_DATA)"
            if (r11 <= 0) goto L_0x00e8
            int r11 = r12.indexOf(r9)
            if (r11 < 0) goto L_0x0099
            r11 = r5
            goto L_0x009b
        L_0x0099:
            r11 = r16
        L_0x009b:
            int r15 = r12.indexOf(r13)
            if (r15 < 0) goto L_0x00a3
            r15 = r5
            goto L_0x00a5
        L_0x00a3:
            r15 = r16
        L_0x00a5:
            if (r11 == 0) goto L_0x00a9
            r11 = r9
            goto L_0x00aa
        L_0x00a9:
            r11 = r4
        L_0x00aa:
            if (r15 == 0) goto L_0x00ad
            r11 = r13
        L_0x00ad:
            java.lang.String[] r11 = r12.split(r11)     // Catch:{ ArrayIndexOutOfBoundsException -> 0x00e2 }
            if (r11 == 0) goto L_0x00de
            int r12 = r11.length     // Catch:{ ArrayIndexOutOfBoundsException -> 0x00e2 }
            if (r12 <= r6) goto L_0x00de
            r1 = r11[r16]     // Catch:{ ArrayIndexOutOfBoundsException -> 0x00e2 }
            r12 = r11[r5]     // Catch:{ ArrayIndexOutOfBoundsException -> 0x00e2 }
            r11 = r11[r6]     // Catch:{ ArrayIndexOutOfBoundsException -> 0x00e2 }
            if (r1 == 0) goto L_0x00c6
            int r11 = r1.length()     // Catch:{ ArrayIndexOutOfBoundsException -> 0x00e2 }
            if (r11 <= 0) goto L_0x00c6
            r11 = r1
            goto L_0x00c7
        L_0x00c6:
            r11 = r4
        L_0x00c7:
            if (r12 == 0) goto L_0x00eb
            int r12 = r12.length()     // Catch:{ ArrayIndexOutOfBoundsException -> 0x00e2 }
            if (r12 <= 0) goto L_0x00eb
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ ArrayIndexOutOfBoundsException -> 0x00e2 }
            r12.<init>()     // Catch:{ ArrayIndexOutOfBoundsException -> 0x00e2 }
            r12.append(r11)     // Catch:{ ArrayIndexOutOfBoundsException -> 0x00e2 }
            r12.append(r9)     // Catch:{ ArrayIndexOutOfBoundsException -> 0x00e2 }
            r12.append(r1)     // Catch:{ ArrayIndexOutOfBoundsException -> 0x00e2 }
            goto L_0x00eb
        L_0x00de:
            android.util.Log.i(r1, r14)     // Catch:{ ArrayIndexOutOfBoundsException -> 0x00e2 }
            goto L_0x00eb
        L_0x00e2:
            java.lang.String r1 = TAG
            android.util.Log.e(r1, r14)
            goto L_0x00eb
        L_0x00e8:
            android.util.Log.i(r1, r14)
        L_0x00eb:
            if (r10 == 0) goto L_0x01ba
            byte[] r0 = com.samsung.android.photoremaster.engine.util.SefUtil.getSEFData((java.lang.String) r0, (java.lang.String) r7)
            java.lang.String r1 = new java.lang.String
            r1.<init>(r0)
            java.lang.String r0 = TAG
            java.lang.String r7 = " : "
            java.lang.String r7 = r7.concat(r1)
            android.util.Log.d(r0, r7)
            int r7 = r1.length()
            java.lang.String r8 = "Error"
            java.lang.String r10 = "-1"
            if (r7 <= r6) goto L_0x01b4
            int r7 = r1.indexOf(r13)
            if (r7 < 0) goto L_0x0113
            r7 = r5
            goto L_0x0115
        L_0x0113:
            r7 = r16
        L_0x0115:
            if (r7 == 0) goto L_0x01ae
            java.lang.String[] r1 = r1.split(r13)
            if (r1 == 0) goto L_0x01aa
            int r7 = r1.length
            if (r7 <= 0) goto L_0x01aa
            r7 = r1[r16]
            r8 = r1[r5]
            r1 = r1[r6]
            int r10 = r7.indexOf(r9)
            if (r10 < 0) goto L_0x012d
            r13 = r9
        L_0x012d:
            java.lang.String[] r7 = r7.split(r13)
            r10 = r7[r6]
            int r10 = java.lang.Integer.parseInt(r10)
            r11 = 6
            r11 = r7[r11]
            int r11 = java.lang.Integer.parseInt(r11)
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            java.lang.String r13 = "uwdcVer : "
            r12.<init>(r13)
            r12.append(r10)
            java.lang.String r10 = " , gdcVer : "
            r12.append(r10)
            r12.append(r11)
            java.lang.String r10 = r12.toString()
            android.util.Log.i(r0, r10)
            if (r11 != 0) goto L_0x01a1
            java.lang.String r3 = "result : true, sef, gdc not applied"
            android.util.Log.d(r0, r3)
            r0 = r16
            r3 = r0
        L_0x0162:
            int r10 = r7.length
            if (r0 >= r10) goto L_0x017c
            int r3 = r3 + 1
            if (r0 != 0) goto L_0x016c
            r4 = r7[r0]
            goto L_0x0179
        L_0x016c:
            java.lang.StringBuilder r4 = i.C0212a.t(r4, r9)
            r10 = r7[r0]
            r4.append(r10)
            java.lang.String r4 = r4.toString()
        L_0x0179:
            int r0 = r0 + 1
            goto L_0x0162
        L_0x017c:
            if (r8 == 0) goto L_0x018b
            int r0 = r8.length()
            if (r0 <= 0) goto L_0x018b
            int r3 = r3 + 1
            java.lang.String r0 = i.C0212a.B(r4, r9, r8)
            r4 = r0
        L_0x018b:
            java.lang.String r0 = "3"
            if (r1 == 0) goto L_0x019b
            int r7 = r1.length()
            if (r7 <= 0) goto L_0x019b
            int r3 = r3 + 1
            java.lang.String r4 = i.C0212a.B(r4, r9, r1)
        L_0x019b:
            r17 = r3
            r3 = r0
            r0 = r17
            goto L_0x01a8
        L_0x01a1:
            java.lang.String r1 = "result : false, sef, gdc applied"
            android.util.Log.d(r0, r1)
            r0 = r16
        L_0x01a8:
            r10 = r3
            goto L_0x01b2
        L_0x01aa:
            android.util.Log.d(r0, r8)
            goto L_0x01b7
        L_0x01ae:
            android.util.Log.d(r0, r8)
            goto L_0x01b7
        L_0x01b2:
            r8 = r10
            goto L_0x01cb
        L_0x01b4:
            android.util.Log.d(r0, r8)
        L_0x01b7:
            r0 = r16
            goto L_0x01b2
        L_0x01ba:
            java.lang.String r0 = TAG
            java.lang.String r1 = "result : true, sef, default gdc"
            android.util.Log.d(r0, r1)
            goto L_0x01c9
        L_0x01c2:
            r16 = r3
            java.lang.String r0 = "result : true, no sef, default gdc"
            android.util.Log.d(r1, r0)
        L_0x01c9:
            r0 = r16
        L_0x01cb:
            r2[r16] = r8
            java.lang.String r1 = java.lang.Integer.toString(r0)
            r2[r5] = r1
            r2[r6] = r4
            if (r0 <= 0) goto L_0x01f6
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r1 = r2[r16]
            r0.append(r1)
            r0.append(r9)
            r1 = r2[r5]
            r0.append(r1)
            r0.append(r9)
            r1 = r2[r6]
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            goto L_0x020c
        L_0x01f6:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r1 = r2[r16]
            r0.append(r1)
            r0.append(r9)
            r1 = r2[r5]
            r0.append(r1)
            java.lang.String r0 = r0.toString()
        L_0x020c:
            java.lang.String r1 = TAG
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "strParam : "
            r2.<init>(r3)
            r2.append(r0)
            java.lang.String r2 = r2.toString()
            android.util.Log.d(r1, r2)
            java.lang.String r2 = "getSEFParam - X"
            android.util.Log.d(r1, r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.photoremaster.engine.estimator.VSWUWDistortionEstimator.getSEFParam(java.lang.String):java.lang.String");
    }

    public boolean allowDownscaledInput(Context context) {
        return false;
    }

    public PrSize getRecommendedInputSize(Context context) {
        return new PrSize(TextToSpeechConst.MAX_SPEECH_INPUT, 3000);
    }

    @Deprecated
    public static boolean estimate(byte[] bArr, int i2, int i7) {
        String str = TAG;
        Log.d(str, "estimate - E");
        VSWEngineNativeInterface.printLibraryVersion();
        byte[] makeByteArray = VSWEngineNativeInterface.makeByteArray(153600);
        boolean runUWDistortionEstimator = VSWEngineNativeInterface.runUWDistortionEstimator(bArr, makeByteArray, i2, i7);
        C0280e.g("result : ", str, str, "estimate - X", runUWDistortionEstimator);
        VSWEngineNativeInterface.releaseByteArray(makeByteArray);
        return runUWDistortionEstimator;
    }

    public void releaseResources() {
    }

    public void stop() {
    }

    @Deprecated
    public static boolean estimate(byte[] bArr, byte[] bArr2, int i2, int i7) {
        String str = TAG;
        Log.d(str, "estimate - E");
        VSWEngineNativeInterface.printLibraryVersion();
        boolean runUWDistortionEstimator = VSWEngineNativeInterface.runUWDistortionEstimator(bArr, bArr2, i2, i7);
        C0280e.g("result : ", str, str, "estimate - X", runUWDistortionEstimator);
        return runUWDistortionEstimator;
    }

    @Deprecated
    public static boolean estimate(byte[] bArr, boolean[] zArr, int i2, int i7) {
        String str = TAG;
        Log.i(str, "estimate - E");
        VSWEngineNativeInterface.printLibraryVersion();
        byte[] makeByteArray = VSWEngineNativeInterface.makeByteArray(153600);
        boolean runDistortionEstimator = VSWEngineNativeInterface.runDistortionEstimator(bArr, makeByteArray, zArr, i2, i7);
        Log.i(str, "result : " + runDistortionEstimator);
        Log.i(str, "estimate - X");
        VSWEngineNativeInterface.releaseByteArray(makeByteArray);
        return runDistortionEstimator;
    }

    public EstimatorResult estimate(Context context, BgrBuffer bgrBuffer, int i2, String str) {
        boolean estimate = estimate(bgrBuffer.getData(), checkPrecondition(bgrBuffer.getFilePath()), bgrBuffer.getSize().getWidth(), bgrBuffer.getSize().getHeight());
        return new EstimatorResult(estimate, 1.0d, estimate ? 1.0d : MapUtil.INVALID_LOCATION);
    }
}
