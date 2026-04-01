package com.samsung.android.livetranslation.util;

import com.samsung.android.imagetranslation.data.LttOcrResult;
import com.samsung.android.imagetranslation.util.a;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class LineRenderLineBreaker implements LineBreaker {
    public String getLinedString(LttOcrResult.BlockInfo blockInfo) {
        return (String) blockInfo.getLineInfo().stream().map(new a(2)).collect(Collectors.joining("\n"));
    }
}
