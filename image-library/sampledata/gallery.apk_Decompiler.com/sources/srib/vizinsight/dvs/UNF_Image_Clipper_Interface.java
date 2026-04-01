package srib.vizinsight.dvs;

import android.graphics.Bitmap;
import android.util.Log;
import com.samsung.android.feature.SemFloatingFeature;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class UNF_Image_Clipper_Interface {
    private static final String TAG = "UNF_Image_Clipper_Interface";
    public static int UNF_ERR_DE_INIT_FAILED = 2;
    public static int UNF_ERR_INIT_CONFIG_FAILED = 1;
    public static int UNF_ERR_NONE = 0;
    public static int UNF_ERR_NO_OUTPUT = 3;
    private static volatile UNF_Image_Clipper_Interface instance;
    private final Object lock = new Object();
    private volatile DVS segmenter;

    private UNF_Image_Clipper_Interface() {
    }

    public static UNF_Image_Clipper_Interface getInstance() {
        if (instance == null) {
            synchronized (UNF_Image_Clipper_Interface.class) {
                try {
                    if (instance == null) {
                        instance = new UNF_Image_Clipper_Interface();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return instance;
    }

    public boolean Is_Support_Unified_Image_Clipper() {
        String str;
        String str2;
        SemFloatingFeature instance2 = SemFloatingFeature.getInstance();
        if (instance2 == null) {
            return false;
        }
        String string = instance2.getString("SEC_FLOATING_FEATURE_VIDEO_CONFIG_VIDEO_CLIPPING_MODE");
        String str3 = TAG;
        Log.d(str3, "sec config mode data :" + string);
        if (string.contains(GlobalPostProcInternalPPInterface.SPLIT_REGEX)) {
            String[] split = string.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            str2 = split[0];
            str = split[1];
        } else {
            str2 = string;
            str = "";
        }
        boolean equals = str.equals("unifiedclipper");
        Log.d(str3, "isModeSupported:" + equals + " modelType :" + str2);
        return equals;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0037, code lost:
        return r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int Unified_Image_Clipper_DeInit() {
        /*
            r5 = this;
            java.lang.String r0 = "De-init end : staus:"
            java.lang.String r1 = TAG
            java.lang.String r2 = "Unified_Image_Clipper_DeInit"
            android.util.Log.i(r1, r2)
            java.lang.Object r2 = r5.lock
            monitor-enter(r2)
            srib.vizinsight.dvs.DVS r3 = r5.segmenter     // Catch:{ all -> 0x0038 }
            if (r3 == 0) goto L_0x003a
            java.lang.String r3 = "De-init start:"
            android.util.Log.i(r1, r3)     // Catch:{ all -> 0x0038 }
            srib.vizinsight.dvs.DVS r3 = r5.segmenter     // Catch:{ all -> 0x0038 }
            boolean r3 = r3.DVSDeInit()     // Catch:{ all -> 0x0038 }
            srib.vizinsight.dvs.DVSegmenter.releaseUnifiedSegmenter()     // Catch:{ all -> 0x0038 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0038 }
            r4.<init>(r0)     // Catch:{ all -> 0x0038 }
            r4.append(r3)     // Catch:{ all -> 0x0038 }
            java.lang.String r0 = r4.toString()     // Catch:{ all -> 0x0038 }
            android.util.Log.i(r1, r0)     // Catch:{ all -> 0x0038 }
            r0 = 0
            r5.segmenter = r0     // Catch:{ all -> 0x0038 }
            if (r3 == 0) goto L_0x0034
            r5 = 0
            goto L_0x0036
        L_0x0034:
            int r5 = UNF_ERR_DE_INIT_FAILED     // Catch:{ all -> 0x0038 }
        L_0x0036:
            monitor-exit(r2)     // Catch:{ all -> 0x0038 }
            return r5
        L_0x0038:
            r5 = move-exception
            goto L_0x003e
        L_0x003a:
            int r5 = UNF_ERR_DE_INIT_FAILED     // Catch:{ all -> 0x0038 }
            monitor-exit(r2)     // Catch:{ all -> 0x0038 }
            return r5
        L_0x003e:
            monitor-exit(r2)     // Catch:{ all -> 0x0038 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: srib.vizinsight.dvs.UNF_Image_Clipper_Interface.Unified_Image_Clipper_DeInit():int");
    }

    public int Unified_Image_Clipper_Execute(Bitmap bitmap, UNF_Object_Segment_Info uNF_Object_Segment_Info) {
        UNF_Object_Segment_Info uNF_Object_Segment_Info2 = uNF_Object_Segment_Info;
        synchronized (this.lock) {
            try {
                if (this.segmenter == null) {
                    Log.e(TAG, "Segmenter not initialized,returning from execute");
                    int i2 = UNF_ERR_NO_OUTPUT;
                    return i2;
                }
                String str = TAG;
                Log.d(str, "Unified_Image_Clipper_Execute called: inputBitmap W=" + bitmap.getWidth() + " H=" + bitmap.getHeight());
                long currentTimeMillis = System.currentTimeMillis();
                Bitmap downSizedBitmap = Utils.getDownSizedBitmap(bitmap);
                long currentTimeMillis2 = System.currentTimeMillis();
                long j2 = currentTimeMillis;
                Log.d(str, "Time for downsizing: " + (currentTimeMillis2 - j2) + " ms");
                UNF_Object_Segment_Info segmentInfoJNI = this.segmenter.segmentInfoJNI(downSizedBitmap);
                uNF_Object_Segment_Info2.solutionInfo = segmentInfoJNI.solutionInfo;
                uNF_Object_Segment_Info2.errorCode = segmentInfoJNI.errorCode;
                uNF_Object_Segment_Info2.mObjectsBitmap = segmentInfoJNI.mObjectsBitmap;
                uNF_Object_Segment_Info2.mSingleBitmap = segmentInfoJNI.mSingleBitmap;
                uNF_Object_Segment_Info2.mSalientNum = segmentInfoJNI.mSalientNum;
                uNF_Object_Segment_Info2.mSingleRect = segmentInfoJNI.mSingleRect;
                uNF_Object_Segment_Info2.mObjectsRect = segmentInfoJNI.mObjectsRect;
                if (segmentInfoJNI.errorCode != 0) {
                    Log.e(str, "Invalid output from library, errorCode:" + segmentInfoJNI.errorCode);
                    int i7 = uNF_Object_Segment_Info2.errorCode;
                    return i7;
                }
                Log.d(str, "mSingleBitmap Width:" + uNF_Object_Segment_Info2.mSingleBitmap.getWidth() + " Height:" + uNF_Object_Segment_Info2.mSingleBitmap.getHeight());
                long currentTimeMillis3 = System.currentTimeMillis();
                int pow = (int) Math.pow(2.0d, (double) Utils.getScaleFactor());
                Log.d(str, "Utils.getScaleFactor():" + Utils.getScaleFactor() + " actualScaleFactor:" + pow);
                if (Utils.getScaleFactor() != 0) {
                    int width = uNF_Object_Segment_Info2.mSingleBitmap.getWidth() * pow;
                    int height = uNF_Object_Segment_Info2.mSingleBitmap.getHeight() * pow;
                    Log.d(str, "SingleRect scaled newWidth :" + width + "scaled newHeight:" + height);
                    UNF_Object_RoI_Info uNF_Object_RoI_Info = uNF_Object_Segment_Info2.mSingleRect;
                    UNF_Object_RoI_Info uNF_Object_RoI_Info2 = segmentInfoJNI.mSingleRect;
                    float f = (float) pow;
                    uNF_Object_RoI_Info.f5113x1 = (float) ((int) (uNF_Object_RoI_Info2.f5113x1 * f));
                    uNF_Object_RoI_Info.f5115y1 = (float) ((int) (uNF_Object_RoI_Info2.f5115y1 * f));
                    uNF_Object_RoI_Info.f5114x2 = (float) ((int) (uNF_Object_RoI_Info2.f5114x2 * f));
                    uNF_Object_RoI_Info.f5116y2 = (float) ((int) (uNF_Object_RoI_Info2.f5116y2 * f));
                    Log.d(str, " clipped_result.mSingleRect values   mSingleRect.x1:" + uNF_Object_Segment_Info2.mSingleRect.f5113x1 + "  mSingleRect.y1:" + uNF_Object_Segment_Info2.mSingleRect.f5115y1 + "  mSingleRect.x2:" + uNF_Object_Segment_Info2.mSingleRect.f5114x2 + "  mSingleRect.y2:" + uNF_Object_Segment_Info2.mSingleRect.f5116y2);
                    uNF_Object_Segment_Info2.mSingleBitmap = Bitmap.createScaledBitmap(uNF_Object_Segment_Info2.mSingleBitmap, width, height, true);
                    StringBuilder sb2 = new StringBuilder("Utils newWidth:");
                    sb2.append(width);
                    sb2.append(" newHeight:");
                    sb2.append(height);
                    Log.d(str, sb2.toString());
                    Log.d(str, "Utils mObjectsBitmap.length:" + uNF_Object_Segment_Info2.mObjectsBitmap.length);
                    if (uNF_Object_Segment_Info2.mObjectsBitmap.length > 1) {
                        int i8 = 0;
                        while (true) {
                            Bitmap[] bitmapArr = uNF_Object_Segment_Info2.mObjectsBitmap;
                            if (i8 >= bitmapArr.length) {
                                break;
                            }
                            int width2 = bitmapArr[i8].getWidth() * pow;
                            int height2 = uNF_Object_Segment_Info2.mObjectsBitmap[i8].getHeight() * pow;
                            UNF_Object_RoI_Info uNF_Object_RoI_Info3 = uNF_Object_Segment_Info2.mObjectsRect[i8];
                            UNF_Object_RoI_Info uNF_Object_RoI_Info4 = segmentInfoJNI.mObjectsRect[i8];
                            uNF_Object_RoI_Info3.f5113x1 = (float) ((int) (uNF_Object_RoI_Info4.f5113x1 * f));
                            uNF_Object_RoI_Info3.f5115y1 = (float) ((int) (uNF_Object_RoI_Info4.f5115y1 * f));
                            uNF_Object_RoI_Info3.f5114x2 = (float) ((int) (uNF_Object_RoI_Info4.f5114x2 * f));
                            uNF_Object_RoI_Info3.f5116y2 = (float) ((int) (uNF_Object_RoI_Info4.f5116y2 * f));
                            Bitmap[] bitmapArr2 = uNF_Object_Segment_Info2.mObjectsBitmap;
                            bitmapArr2[i8] = Bitmap.createScaledBitmap(bitmapArr2[i8], width2, height2, true);
                            String str2 = TAG;
                            Log.d(str2, "ObjectsRect newWidth: " + width2 + " newHeight:" + height2 + " object->" + i8);
                            Log.d(str2, "clipped_result.mObjectsRect[i] values   mObjectsRect[i].x1:" + uNF_Object_Segment_Info2.mObjectsRect[i8].f5113x1 + "  mObjectsRect[i].y1:" + uNF_Object_Segment_Info2.mObjectsRect[i8].f5115y1 + "  mObjectsRect[i].x2" + uNF_Object_Segment_Info2.mObjectsRect[i8].f5114x2 + "  mObjectsRect[i].y2:" + uNF_Object_Segment_Info2.mObjectsRect[i8].f5116y2);
                            i8++;
                        }
                    }
                }
                long currentTimeMillis4 = System.currentTimeMillis();
                String str3 = TAG;
                Log.d(str3, "Time for upsizing: " + (currentTimeMillis4 - currentTimeMillis3));
                Log.i(str3, "errorCode:" + uNF_Object_Segment_Info2.errorCode + " solutionInfo:" + uNF_Object_Segment_Info2.solutionInfo + " mSalientNum:" + uNF_Object_Segment_Info2.mSalientNum);
                int i10 = uNF_Object_Segment_Info2.errorCode;
                return i10;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public String Unified_Image_Clipper_Get_Version() {
        Log.i(TAG, "Unified_Image_Clipper_Get_Version :VideoClipperInterface_v2.0_Beta13.1");
        return "VideoClipperInterface_v2.0_Beta13.1";
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0053, code lost:
        return r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int Unified_Image_Clipper_Init() {
        /*
            r6 = this;
            java.lang.String r0 = "Unified_Image_Clipper_Init configStatus:"
            java.lang.String r1 = TAG
            java.lang.String r2 = "Unified_Image_Clipper_Init"
            android.util.Log.i(r1, r2)
            java.lang.Object r2 = r6.lock
            monitor-enter(r2)
            srib.vizinsight.dvs.DVS r3 = r6.segmenter     // Catch:{ all -> 0x0019 }
            if (r3 == 0) goto L_0x001b
            java.lang.String r6 = "Already initialized, skipping duplicate init"
            android.util.Log.i(r1, r6)     // Catch:{ all -> 0x0019 }
            int r6 = UNF_ERR_NONE     // Catch:{ all -> 0x0019 }
            monitor-exit(r2)     // Catch:{ all -> 0x0019 }
            return r6
        L_0x0019:
            r6 = move-exception
            goto L_0x0054
        L_0x001b:
            java.lang.String r3 = "init start"
            android.util.Log.i(r1, r3)     // Catch:{ all -> 0x0019 }
            srib.vizinsight.dvs.DVSConfig r3 = new srib.vizinsight.dvs.DVSConfig     // Catch:{ all -> 0x0019 }
            r3.<init>()     // Catch:{ all -> 0x0019 }
            srib.vizinsight.dvs.DVS r4 = new srib.vizinsight.dvs.DVS     // Catch:{ all -> 0x0019 }
            r4.<init>()     // Catch:{ all -> 0x0019 }
            r6.segmenter = r4     // Catch:{ all -> 0x0019 }
            srib.vizinsight.dvs.DVS r4 = r6.segmenter     // Catch:{ all -> 0x0019 }
            boolean r4 = r4.DVSCheckConfig(r3)     // Catch:{ all -> 0x0019 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0019 }
            r5.<init>(r0)     // Catch:{ all -> 0x0019 }
            r5.append(r4)     // Catch:{ all -> 0x0019 }
            java.lang.String r0 = r5.toString()     // Catch:{ all -> 0x0019 }
            android.util.Log.i(r1, r0)     // Catch:{ all -> 0x0019 }
            srib.vizinsight.dvs.DVS r0 = srib.vizinsight.dvs.DVSegmenter.getSegmenter(r3)     // Catch:{ all -> 0x0019 }
            r6.segmenter = r0     // Catch:{ all -> 0x0019 }
            java.lang.String r6 = "init end"
            android.util.Log.i(r1, r6)     // Catch:{ all -> 0x0019 }
            if (r4 == 0) goto L_0x0050
            r6 = 0
            goto L_0x0052
        L_0x0050:
            int r6 = UNF_ERR_INIT_CONFIG_FAILED     // Catch:{ all -> 0x0019 }
        L_0x0052:
            monitor-exit(r2)     // Catch:{ all -> 0x0019 }
            return r6
        L_0x0054:
            monitor-exit(r2)     // Catch:{ all -> 0x0019 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: srib.vizinsight.dvs.UNF_Image_Clipper_Interface.Unified_Image_Clipper_Init():int");
    }
}
