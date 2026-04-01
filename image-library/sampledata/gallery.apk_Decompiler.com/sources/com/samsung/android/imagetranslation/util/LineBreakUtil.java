package com.samsung.android.imagetranslation.util;

import android.content.Context;
import com.samsung.android.imagetranslation.common.LTTLogger;
import com.samsung.android.imagetranslation.data.LttOcrResult;
import java.util.List;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LineBreakUtil {
    private static final String TAG = "LineBreakUtil";
    private LttOcrResult ocrResult;

    public LineBreakUtil(LttOcrResult lttOcrResult) {
        this.ocrResult = lttOcrResult;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$initResultWithSourceText$0(LineBreaker lineBreaker, LineBreaker lineBreaker2, LttOcrResult.BlockInfo blockInfo) {
        if (Discriminator.isParagraph(blockInfo)) {
            return lineBreaker.getLinedString(blockInfo);
        }
        return lineBreaker2.getLinedString(blockInfo);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$initResultWithSourceText$1(LineBreaker lineBreaker, Context context, LineBreaker lineBreaker2, LttOcrResult.BlockInfo blockInfo) {
        if (Discriminator.isParagraph(blockInfo)) {
            return lineBreaker.getLinedString(blockInfo);
        }
        if (Discriminator.isShortSentence(context, blockInfo)) {
            return blockInfo.getString();
        }
        return lineBreaker2.getLinedString(blockInfo);
    }

    @Deprecated
    public List<String> initResultWithSourceText(List<LttOcrResult.BlockInfo> list) {
        LTTLogger.d(TAG, "initResultWithSourceText");
        return (List) list.stream().map(new e(new ParagraphRenderLineBreaker(), new LineRenderLineBreaker())).collect(Collectors.toList());
    }

    /* JADX WARNING: type inference failed for: r4v2, types: [java.util.function.Consumer, java.lang.Object] */
    public static List<String> initResultWithSourceText(Context context, List<LttOcrResult.BlockInfo> list) {
        LTTLogger.d(TAG, "initResultWithSourceText with Context");
        return (List) list.stream().map(new f(new ParagraphRenderLineBreaker(), context, new LineRenderLineBreaker())).peek(new Object()).collect(Collectors.toList());
    }
}
