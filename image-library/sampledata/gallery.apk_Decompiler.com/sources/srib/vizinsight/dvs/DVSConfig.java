package srib.vizinsight.dvs;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DVSConfig {
    public static final String UNIFIED_CLIPPER_MODE = "unifiedclipper";
    int detectThreshold = 35;
    public String detectorDLAModelPath = "/vendor/etc/saiv/image_understanding/db/dvs/dvs_od_cnn.dla";
    public String detectorDLCModelPath = "/vendor/etc/saiv/image_understanding/db/dvs/dvs_od_cnn.dlc";
    public String detectorNNCModelPath = "/vendor/etc/saiv/image_understanding/db/dvs/dvs_od_cnn.nnc";
    public String detectorTfliteModelPath = "/vendor/etc/saiv/image_understanding/db/dvs/dvs_od_cnn.tflite";
    public Boolean isPamir = Boolean.FALSE;
    int maxPass;
    String odModelPath = "";
    int qualityThreshold = 65;
    public String refinerDLAModelPath = "/vendor/etc/saiv/image_understanding/db/dvs/dis_refiner_cnn.dla";
    public String refinerDLCModelPath = "/vendor/etc/saiv/image_understanding/db/dvs/dis_refiner_cnn.dlc";
    String refinerModelPath = "";
    public String refinerNNCModelPath = "/vendor/etc/saiv/image_understanding/db/dvs/dis_refiner_cnn.nnc";
    public String refinerTfliteModelPath = "/vendor/etc/saiv/image_understanding/db/dvs/dis_refiner_cnn.tflite";
    int segmentThreshold = 40;
    public String segmenterDLAModelPath = "/vendor/etc/saiv/image_understanding/db/dvs/dvs_segmenter_cnn.dla";
    public String segmenterDLCModelPath = "/vendor/etc/saiv/image_understanding/db/dvs/dvs_segmenter_cnn.dlc";
    String segmenterModelPath = "";
    public String segmenterNNCModelPath = "/vendor/etc/saiv/image_understanding/db/dvs/dvs_segmenter_cnn.nnc";
    public String segmenterTfliteModelPath = "/vendor/etc/saiv/image_understanding/db/dvs/dvs_segmenter_cnn.tflite";

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x00a2, code lost:
        if (r2.equals("GPU") == false) goto L_0x008f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DVSConfig() {
        /*
            r9 = this;
            r9.<init>()
            java.lang.String r0 = "/vendor/etc/saiv/image_understanding/db/dvs/dvs_segmenter_cnn.dlc"
            r9.segmenterDLCModelPath = r0
            java.lang.String r0 = "/vendor/etc/saiv/image_understanding/db/dvs/dvs_segmenter_cnn.tflite"
            r9.segmenterTfliteModelPath = r0
            java.lang.String r0 = "/vendor/etc/saiv/image_understanding/db/dvs/dvs_segmenter_cnn.nnc"
            r9.segmenterNNCModelPath = r0
            java.lang.String r0 = "/vendor/etc/saiv/image_understanding/db/dvs/dvs_segmenter_cnn.dla"
            r9.segmenterDLAModelPath = r0
            java.lang.String r0 = "/vendor/etc/saiv/image_understanding/db/dvs/dvs_od_cnn.dlc"
            r9.detectorDLCModelPath = r0
            java.lang.String r0 = "/vendor/etc/saiv/image_understanding/db/dvs/dvs_od_cnn.tflite"
            r9.detectorTfliteModelPath = r0
            java.lang.String r0 = "/vendor/etc/saiv/image_understanding/db/dvs/dvs_od_cnn.nnc"
            r9.detectorNNCModelPath = r0
            java.lang.String r0 = "/vendor/etc/saiv/image_understanding/db/dvs/dvs_od_cnn.dla"
            r9.detectorDLAModelPath = r0
            java.lang.String r0 = "/vendor/etc/saiv/image_understanding/db/dvs/dis_refiner_cnn.dlc"
            r9.refinerDLCModelPath = r0
            java.lang.String r0 = "/vendor/etc/saiv/image_understanding/db/dvs/dis_refiner_cnn.tflite"
            r9.refinerTfliteModelPath = r0
            java.lang.String r0 = "/vendor/etc/saiv/image_understanding/db/dvs/dis_refiner_cnn.nnc"
            r9.refinerNNCModelPath = r0
            java.lang.String r0 = "/vendor/etc/saiv/image_understanding/db/dvs/dis_refiner_cnn.dla"
            r9.refinerDLAModelPath = r0
            java.lang.Boolean r0 = java.lang.Boolean.FALSE
            r9.isPamir = r0
            java.lang.String r0 = ""
            r9.odModelPath = r0
            r9.segmenterModelPath = r0
            r9.refinerModelPath = r0
            r1 = 35
            r9.detectThreshold = r1
            r1 = 65
            r9.qualityThreshold = r1
            r1 = 40
            r9.segmentThreshold = r1
            r1 = 3
            r9.maxPass = r1
            com.samsung.android.feature.SemFloatingFeature r2 = com.samsung.android.feature.SemFloatingFeature.getInstance()
            if (r2 == 0) goto L_0x0123
            java.lang.String r3 = "SEC_FLOATING_FEATURE_VIDEO_CONFIG_VIDEO_CLIPPING_MODE"
            java.lang.String r2 = r2.getString(r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Model type from floating feature SEC_FLOATING_FEATURE_VIDEO_CONFIG_VIDEO_CLIPPING_MODE: "
            r3.<init>(r4)
            r3.append(r2)
            java.lang.String r3 = r3.toString()
            java.lang.String r4 = "DVSConfig"
            android.util.Log.d(r4, r3)
            java.lang.String r3 = ","
            boolean r4 = r2.contains(r3)
            r5 = 1
            r6 = 0
            if (r4 == 0) goto L_0x0083
            java.lang.String[] r2 = r2.split(r3)
            r3 = r2[r6]
            r2 = r2[r5]
            r8 = r3
            r3 = r2
            r2 = r8
            goto L_0x0084
        L_0x0083:
            r3 = r0
        L_0x0084:
            r2.getClass()
            int r4 = r2.hashCode()
            r7 = -1
            switch(r4) {
                case -1417419722: goto L_0x00bb;
                case -1417418728: goto L_0x00b0;
                case -632555975: goto L_0x00a5;
                case 70796: goto L_0x009c;
                case 77523: goto L_0x0091;
                default: goto L_0x008f;
            }
        L_0x008f:
            r1 = r7
            goto L_0x00c5
        L_0x0091:
            java.lang.String r1 = "NPU"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L_0x009a
            goto L_0x008f
        L_0x009a:
            r1 = 4
            goto L_0x00c5
        L_0x009c:
            java.lang.String r4 = "GPU"
            boolean r2 = r2.equals(r4)
            if (r2 != 0) goto L_0x00c5
            goto L_0x008f
        L_0x00a5:
            java.lang.String r1 = "NPU_PAMIR"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L_0x00ae
            goto L_0x008f
        L_0x00ae:
            r1 = 2
            goto L_0x00c5
        L_0x00b0:
            java.lang.String r1 = "NPU_MTK"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L_0x00b9
            goto L_0x008f
        L_0x00b9:
            r1 = r5
            goto L_0x00c5
        L_0x00bb:
            java.lang.String r1 = "NPU_LSI"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L_0x00c4
            goto L_0x008f
        L_0x00c4:
            r1 = r6
        L_0x00c5:
            switch(r1) {
                case 0: goto L_0x010d;
                case 1: goto L_0x0100;
                case 2: goto L_0x00ef;
                case 3: goto L_0x00e2;
                case 4: goto L_0x00d5;
                default: goto L_0x00c8;
            }
        L_0x00c8:
            java.lang.String r1 = r9.detectorDLCModelPath
            r9.odModelPath = r1
            java.lang.String r1 = r9.segmenterDLCModelPath
            r9.segmenterModelPath = r1
            java.lang.String r1 = r9.refinerDLCModelPath
            r9.refinerModelPath = r1
            goto L_0x0119
        L_0x00d5:
            java.lang.String r1 = r9.detectorDLCModelPath
            r9.odModelPath = r1
            java.lang.String r1 = r9.segmenterDLCModelPath
            r9.segmenterModelPath = r1
            java.lang.String r1 = r9.refinerDLCModelPath
            r9.refinerModelPath = r1
            goto L_0x0119
        L_0x00e2:
            java.lang.String r1 = r9.detectorTfliteModelPath
            r9.odModelPath = r1
            java.lang.String r1 = r9.segmenterTfliteModelPath
            r9.segmenterModelPath = r1
            java.lang.String r1 = r9.refinerTfliteModelPath
            r9.refinerModelPath = r1
            goto L_0x0119
        L_0x00ef:
            java.lang.String r1 = r9.detectorNNCModelPath
            r9.odModelPath = r1
            java.lang.String r1 = r9.segmenterNNCModelPath
            r9.segmenterModelPath = r1
            java.lang.String r1 = r9.refinerNNCModelPath
            r9.refinerModelPath = r1
            java.lang.Boolean r1 = java.lang.Boolean.TRUE
            r9.isPamir = r1
            goto L_0x0119
        L_0x0100:
            java.lang.String r1 = r9.detectorDLAModelPath
            r9.odModelPath = r1
            java.lang.String r1 = r9.segmenterDLAModelPath
            r9.segmenterModelPath = r1
            java.lang.String r1 = r9.refinerDLAModelPath
            r9.refinerModelPath = r1
            goto L_0x0119
        L_0x010d:
            java.lang.String r1 = r9.detectorNNCModelPath
            r9.odModelPath = r1
            java.lang.String r1 = r9.segmenterNNCModelPath
            r9.segmenterModelPath = r1
            java.lang.String r1 = r9.refinerNNCModelPath
            r9.refinerModelPath = r1
        L_0x0119:
            java.lang.String r1 = "unifiedclipper"
            boolean r1 = r3.equals(r1)
            if (r1 != 0) goto L_0x0123
            r9.refinerModelPath = r0
        L_0x0123:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: srib.vizinsight.dvs.DVSConfig.<init>():void");
    }

    public int getDetectThreshold() {
        return this.detectThreshold;
    }

    public String getDetectorDLCModelPath() {
        return this.detectorDLCModelPath;
    }

    public String getDetectorTfliteModelPath() {
        return this.detectorTfliteModelPath;
    }

    public int getMaxPass() {
        return this.maxPass;
    }

    public String getOdModelPath() {
        return this.odModelPath;
    }

    public int getQualityThreshold() {
        return this.qualityThreshold;
    }

    public String getRefinerModelPath() {
        return this.refinerModelPath;
    }

    public int getSegmentThreshold() {
        return this.segmentThreshold;
    }

    public String getSegmenterDLCModelPath() {
        return this.segmenterDLCModelPath;
    }

    public String getSegmenterModelPath() {
        return this.segmenterModelPath;
    }

    public String getSegmenterTfliteModelPath() {
        return this.segmenterTfliteModelPath;
    }

    public void setDetectThreshold(int i2) {
        this.detectThreshold = i2;
    }

    public void setDetectorDLCModelPath(String str) {
        this.detectorDLCModelPath = str;
    }

    public void setDetectorTfliteModelPath(String str) {
        this.detectorTfliteModelPath = str;
    }

    public void setMaxPass(int i2) {
        this.maxPass = i2;
    }

    public void setOdModelPath(String str) {
        this.odModelPath = str;
    }

    public void setQualityThreshold(int i2) {
        this.qualityThreshold = i2;
    }

    public void setRefinerModelPath(String str) {
        this.refinerModelPath = str;
    }

    public void setSegmentThreshold(int i2) {
        this.segmentThreshold = i2;
    }

    public void setSegmenterDLCModelPath(String str) {
        this.segmenterDLCModelPath = str;
    }

    public void setSegmenterModelPath(String str) {
        this.segmenterModelPath = str;
    }

    public void setSegmenterTfliteModelPath(String str) {
        this.segmenterTfliteModelPath = str;
    }
}
