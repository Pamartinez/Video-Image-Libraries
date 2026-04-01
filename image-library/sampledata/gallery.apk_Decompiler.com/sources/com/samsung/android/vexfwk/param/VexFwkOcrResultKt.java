package com.samsung.android.vexfwk.param;

import com.samsung.android.vexfwk.param.VexFwkOcrAdditionalMeta;
import com.samsung.android.vexfwk.param.VexFwkOcrResult;
import com.samsung.android.vexfwk.param.VexFwkOcrResultMeta;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import ne.C1196n;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0011\u0010\u0002\u001a\u00020\u0001*\u00020\u0000¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u0019\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004*\u00020\u0000H\u0002¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u0011\u0010\t\u001a\u00020\b*\u00020\u0000¢\u0006\u0004\b\t\u0010\n\u001a\u0019\u0010\r\u001a\u00020\f*\u00020\u00002\u0006\u0010\u000b\u001a\u00020\b¢\u0006\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkOcrResult;", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResultMeta;", "toResultMeta", "(Lcom/samsung/android/vexfwk/param/VexFwkOcrResult;)Lcom/samsung/android/vexfwk/param/VexFwkOcrResultMeta;", "", "", "getCurvedList", "(Lcom/samsung/android/vexfwk/param/VexFwkOcrResult;)Ljava/util/List;", "Lcom/samsung/android/vexfwk/param/VexFwkOcrAdditionalMeta;", "toAdditionalMeta", "(Lcom/samsung/android/vexfwk/param/VexFwkOcrResult;)Lcom/samsung/android/vexfwk/param/VexFwkOcrAdditionalMeta;", "additionalMeta", "Lme/x;", "updateAdditionalMeta", "(Lcom/samsung/android/vexfwk/param/VexFwkOcrResult;Lcom/samsung/android/vexfwk/param/VexFwkOcrAdditionalMeta;)V", "VexFrameworkSDK_forInternalRelease"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkOcrResultKt {
    private static final List<Boolean> getCurvedList(VexFwkOcrResult vexFwkOcrResult) {
        ArrayList arrayList = new ArrayList();
        for (VexFwkOcrResult.BlockInfo lineInfo : vexFwkOcrResult.getBlockInfoList()) {
            for (VexFwkOcrResult.LineInfo isCurved : lineInfo.getLineInfo()) {
                arrayList.add(Boolean.valueOf(isCurved.isCurved()));
            }
        }
        return arrayList;
    }

    public static final VexFwkOcrAdditionalMeta toAdditionalMeta(VexFwkOcrResult vexFwkOcrResult) {
        j.e(vexFwkOcrResult, "<this>");
        Iterable<VexFwkOcrResult.TableInfo> tableInfoList = vexFwkOcrResult.getTableInfoList();
        ArrayList arrayList = new ArrayList(C1196n.w0(tableInfoList, 10));
        for (VexFwkOcrResult.TableInfo tableInfo : tableInfoList) {
            Iterable<VexFwkOcrResult.TableRowInfo> tableRowInfo = tableInfo.getTableRowInfo();
            ArrayList arrayList2 = new ArrayList(C1196n.w0(tableRowInfo, 10));
            for (VexFwkOcrResult.TableRowInfo tableCellInfo : tableRowInfo) {
                Iterable<VexFwkOcrResult.TableCellInfo> tableCellInfo2 = tableCellInfo.getTableCellInfo();
                ArrayList arrayList3 = new ArrayList(C1196n.w0(tableCellInfo2, 10));
                for (VexFwkOcrResult.TableCellInfo tableCellInfo3 : tableCellInfo2) {
                    arrayList3.add(new VexFwkOcrAdditionalMeta.TableCellInfo(tableCellInfo3.getCellBoundary(), tableCellInfo3.getCellText()));
                }
                arrayList2.add(new VexFwkOcrAdditionalMeta.TableRowInfo(arrayList3));
            }
            arrayList.add(new VexFwkOcrAdditionalMeta.TableInfo(arrayList2, tableInfo.getTableBoundary()));
        }
        return new VexFwkOcrAdditionalMeta(arrayList, getCurvedList(vexFwkOcrResult), vexFwkOcrResult.isHandwrittenResult());
    }

    public static final VexFwkOcrResultMeta toResultMeta(VexFwkOcrResult vexFwkOcrResult) {
        j.e(vexFwkOcrResult, "<this>");
        Iterable<VexFwkOcrResult.BlockInfo> blockInfoList = vexFwkOcrResult.getBlockInfoList();
        ArrayList arrayList = new ArrayList(C1196n.w0(blockInfoList, 10));
        for (VexFwkOcrResult.BlockInfo blockInfo : blockInfoList) {
            Iterable<VexFwkOcrResult.LineInfo> lineInfo = blockInfo.getLineInfo();
            ArrayList arrayList2 = new ArrayList(C1196n.w0(lineInfo, 10));
            for (VexFwkOcrResult.LineInfo lineInfo2 : lineInfo) {
                Iterable<VexFwkOcrResult.WordInfo> wordInfo = lineInfo2.getWordInfo();
                ArrayList arrayList3 = new ArrayList(C1196n.w0(wordInfo, 10));
                for (VexFwkOcrResult.WordInfo wordInfo2 : wordInfo) {
                    Iterable<VexFwkOcrResult.CharInfo> charInfo = wordInfo2.getCharInfo();
                    ArrayList arrayList4 = new ArrayList(C1196n.w0(charInfo, 10));
                    for (VexFwkOcrResult.CharInfo charInfo2 : charInfo) {
                        arrayList4.add(new VexFwkOcrResultMeta.CharInfo(charInfo2.getString(), charInfo2.getRect(), charInfo2.getPoly()));
                    }
                    arrayList3.add(new VexFwkOcrResultMeta.WordInfo(arrayList4, wordInfo2.getRect(), wordInfo2.getPoly()));
                }
                arrayList2.add(new VexFwkOcrResultMeta.LineInfo(arrayList3, lineInfo2.getRect(), lineInfo2.getPoly()));
            }
            arrayList.add(new VexFwkOcrResultMeta.BlockInfo(arrayList2, blockInfo.getRect(), blockInfo.getPoly(), blockInfo.isTabular(), blockInfo.getTranslatedText()));
        }
        return new VexFwkOcrResultMeta(arrayList);
    }

    public static final void updateAdditionalMeta(VexFwkOcrResult vexFwkOcrResult, VexFwkOcrAdditionalMeta vexFwkOcrAdditionalMeta) {
        j.e(vexFwkOcrResult, "<this>");
        j.e(vexFwkOcrAdditionalMeta, "additionalMeta");
        Iterable<VexFwkOcrAdditionalMeta.TableInfo> tableInfoList = vexFwkOcrAdditionalMeta.getTableInfoList();
        ArrayList arrayList = new ArrayList(C1196n.w0(tableInfoList, 10));
        for (VexFwkOcrAdditionalMeta.TableInfo tableInfo : tableInfoList) {
            Iterable<VexFwkOcrAdditionalMeta.TableRowInfo> tableRowInfo = tableInfo.getTableRowInfo();
            ArrayList arrayList2 = new ArrayList(C1196n.w0(tableRowInfo, 10));
            for (VexFwkOcrAdditionalMeta.TableRowInfo tableCellInfo : tableRowInfo) {
                Iterable<VexFwkOcrAdditionalMeta.TableCellInfo> tableCellInfo2 = tableCellInfo.getTableCellInfo();
                ArrayList arrayList3 = new ArrayList(C1196n.w0(tableCellInfo2, 10));
                for (VexFwkOcrAdditionalMeta.TableCellInfo tableCellInfo3 : tableCellInfo2) {
                    arrayList3.add(new VexFwkOcrResult.TableCellInfo(tableCellInfo3.getCellBoundary(), tableCellInfo3.getCellText()));
                }
                arrayList2.add(new VexFwkOcrResult.TableRowInfo(arrayList3));
            }
            arrayList.add(new VexFwkOcrResult.TableInfo(arrayList2, tableInfo.getTableBoundary()));
        }
        vexFwkOcrResult.setTableInfoList(arrayList);
        Iterator<Boolean> it = vexFwkOcrAdditionalMeta.getLineCurveInfo().iterator();
        for (VexFwkOcrResult.BlockInfo lineInfo : vexFwkOcrResult.getBlockInfoList()) {
            for (VexFwkOcrResult.LineInfo curved : lineInfo.getLineInfo()) {
                curved.setCurved(it.next().booleanValue());
            }
        }
    }
}
