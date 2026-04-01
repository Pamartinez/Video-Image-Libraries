package com.samsung.android.vexfwk.param;

import com.samsung.android.vexfwk.param.VexFwkOcrResult;
import com.samsung.android.vexfwk.param.VexFwkOcrResultMeta;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1196n;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"toResult", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResult;", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResultMeta;", "VexFrameworkSDK_forInternalRelease"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkOcrResultMetaKt {
    public static final VexFwkOcrResult toResult(VexFwkOcrResultMeta vexFwkOcrResultMeta) {
        j.e(vexFwkOcrResultMeta, "<this>");
        Iterable<VexFwkOcrResultMeta.BlockInfo> blockInfoList = vexFwkOcrResultMeta.getBlockInfoList();
        ArrayList arrayList = new ArrayList(C1196n.w0(blockInfoList, 10));
        for (VexFwkOcrResultMeta.BlockInfo blockInfo : blockInfoList) {
            Iterable<VexFwkOcrResultMeta.LineInfo> lineInfo = blockInfo.getLineInfo();
            ArrayList arrayList2 = new ArrayList(C1196n.w0(lineInfo, 10));
            for (VexFwkOcrResultMeta.LineInfo lineInfo2 : lineInfo) {
                Iterable<VexFwkOcrResultMeta.WordInfo> wordInfo = lineInfo2.getWordInfo();
                ArrayList arrayList3 = new ArrayList(C1196n.w0(wordInfo, 10));
                for (VexFwkOcrResultMeta.WordInfo wordInfo2 : wordInfo) {
                    Iterable<VexFwkOcrResultMeta.CharInfo> charInfo = wordInfo2.getCharInfo();
                    ArrayList arrayList4 = new ArrayList(C1196n.w0(charInfo, 10));
                    for (VexFwkOcrResultMeta.CharInfo charInfo2 : charInfo) {
                        arrayList4.add(new VexFwkOcrResult.CharInfo(charInfo2.getString(), charInfo2.getRect(), charInfo2.getPoly()));
                    }
                    arrayList3.add(new VexFwkOcrResult.WordInfo(arrayList4, wordInfo2.getRect(), wordInfo2.getPoly()));
                }
                arrayList2.add(new VexFwkOcrResult.LineInfo(arrayList3, lineInfo2.getRect(), lineInfo2.getPoly(), false, 8, (e) null));
            }
            arrayList.add(new VexFwkOcrResult.BlockInfo(arrayList2, blockInfo.getRect(), blockInfo.getPoly(), blockInfo.isTabular(), blockInfo.getTranslatedText()));
        }
        return new VexFwkOcrResult(arrayList, (List) null, false, 6, (e) null);
    }
}
