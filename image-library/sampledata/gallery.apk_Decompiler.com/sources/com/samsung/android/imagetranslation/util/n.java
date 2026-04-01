package com.samsung.android.imagetranslation.util;

import com.samsung.android.imagetranslation.data.LttOcrResult;
import java.util.function.ToIntFunction;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class n implements ToIntFunction {
    public final int applyAsInt(Object obj) {
        return ((LttOcrResult.LineInfo) obj).getRect().height();
    }
}
