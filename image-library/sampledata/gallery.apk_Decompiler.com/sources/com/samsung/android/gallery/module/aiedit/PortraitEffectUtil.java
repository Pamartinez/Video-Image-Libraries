package com.samsung.android.gallery.module.aiedit;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class PortraitEffectUtil {
    private static final boolean SUPPORT_DETECTION = PortraitDetector.SUPPORT;

    /* JADX WARNING: Removed duplicated region for block: B:28:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0074  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0076  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0090  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0092  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean supportPortraitEffect(com.samsung.android.gallery.module.data.MediaItem r8) {
        /*
            r0 = 0
            if (r8 == 0) goto L_0x00a0
            boolean r1 = com.samsung.android.gallery.support.utils.PreferenceFeatures.OneUi41.SUPPORT_PORTRAIT_CHANGE
            if (r1 != 0) goto L_0x0009
            goto L_0x00a0
        L_0x0009:
            boolean r1 = SUPPORT_DETECTION
            if (r1 == 0) goto L_0x0014
            boolean r2 = r8.isCloudOnly()
            if (r2 == 0) goto L_0x0014
            return r0
        L_0x0014:
            boolean r2 = r8.isJpeg()
            r3 = 1
            if (r2 != 0) goto L_0x0027
            boolean r2 = r8.isPng()
            if (r2 != 0) goto L_0x0027
            boolean r2 = r8.isHeif()
            if (r2 == 0) goto L_0x002f
        L_0x0027:
            boolean r2 = r8.is360Image()
            if (r2 != 0) goto L_0x002f
            r2 = r3
            goto L_0x0030
        L_0x002f:
            r2 = r0
        L_0x0030:
            if (r1 == 0) goto L_0x0043
            int r1 = r8.getWidth()
            int r4 = r8.getHeight()
            int r4 = r4 * r1
            r1 = 201000000(0xbfb0440, float:9.668808E-32)
            if (r4 > r1) goto L_0x0041
            goto L_0x0043
        L_0x0041:
            r1 = r0
            goto L_0x0044
        L_0x0043:
            r1 = r3
        L_0x0044:
            boolean r4 = com.samsung.android.gallery.support.utils.PreferenceFeatures.OneUi6x.SUPPORT_AI_EDIT_ABOVE_DETAILS
            if (r4 != 0) goto L_0x0051
            boolean r4 = r8.isRemasterSaved()
            if (r4 != 0) goto L_0x004f
            goto L_0x0051
        L_0x004f:
            r4 = r0
            goto L_0x0052
        L_0x0051:
            r4 = r3
        L_0x0052:
            boolean r5 = r8.isMotionPhoto()
            if (r5 == 0) goto L_0x0063
            com.samsung.android.gallery.support.config.SdkConfig$GED r5 = com.samsung.android.gallery.support.config.SdkConfig.GED.T
            boolean r5 = com.samsung.android.gallery.support.config.SdkConfig.atLeast((com.samsung.android.gallery.support.config.SdkConfig.GED) r5)
            if (r5 == 0) goto L_0x0061
            goto L_0x0063
        L_0x0061:
            r5 = r0
            goto L_0x0064
        L_0x0063:
            r5 = r3
        L_0x0064:
            r6 = 3105(0xc21, float:4.351E-42)
            boolean r6 = r8.hasSefFileTypes(r6)
            if (r6 != 0) goto L_0x0076
            r6 = 3552(0xde0, float:4.977E-42)
            boolean r6 = r8.hasSefFileTypes(r6)
            if (r6 != 0) goto L_0x0076
            r6 = r3
            goto L_0x0077
        L_0x0076:
            r6 = r0
        L_0x0077:
            java.lang.String r7 = r8.getShotMode()
            if (r7 == 0) goto L_0x0092
            int r7 = r8.getSefFileType()
            boolean r7 = com.samsung.android.gallery.support.type.ShotModeType.isLiveFocus(r7)
            if (r7 != 0) goto L_0x0090
            java.lang.String r7 = "panorama"
            boolean r8 = r8.isShotMode(r7)
            if (r8 != 0) goto L_0x0090
            goto L_0x0092
        L_0x0090:
            r8 = r0
            goto L_0x0093
        L_0x0092:
            r8 = r3
        L_0x0093:
            if (r2 == 0) goto L_0x00a0
            if (r1 == 0) goto L_0x00a0
            if (r5 == 0) goto L_0x00a0
            if (r4 == 0) goto L_0x00a0
            if (r6 == 0) goto L_0x00a0
            if (r8 == 0) goto L_0x00a0
            return r3
        L_0x00a0:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.aiedit.PortraitEffectUtil.supportPortraitEffect(com.samsung.android.gallery.module.data.MediaItem):boolean");
    }
}
