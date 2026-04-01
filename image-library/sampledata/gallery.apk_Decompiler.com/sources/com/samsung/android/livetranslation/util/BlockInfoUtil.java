package com.samsung.android.livetranslation.util;

import com.samsung.android.imagetranslation.data.LttOcrResult;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class BlockInfoUtil {
    /* JADX WARNING: type inference failed for: r0v1, types: [java.lang.Object, java.util.Comparator] */
    public static int getMaxLineWidth(LttOcrResult.BlockInfo blockInfo) {
        return blockInfo.getLineInfo().stream().map(new a(0)).max(new Object()).orElse(0).intValue();
    }
}
