package com.samsung.android.vexfwk.param;

import android.graphics.Point;
import android.graphics.Rect;
import com.samsung.android.vexfwk.extensions.RectKt;
import com.samsung.android.vexfwk.param.VexFwkOcrResult;
import com.samsung.android.vexfwk.param.VexFwkOcrResultV2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import ne.C1196n;

@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0002*\u00020\u0001\u001a\u001b\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0002¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"toV2", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResultV2;", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResult;", "toV1", "convertPointsToRect", "Landroid/graphics/Rect;", "points", "", "Landroid/graphics/Point;", "([Landroid/graphics/Point;)Landroid/graphics/Rect;", "VexFrameworkSDK_forInternalRelease"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkOcrResultConverterKt {
    private static final Rect convertPointsToRect(Point[] pointArr) {
        if (pointArr.length == 0) {
            return new Rect();
        }
        if (pointArr.length != 0) {
            int i2 = pointArr[0].x;
            int i7 = 1;
            int length = pointArr.length - 1;
            if (1 <= length) {
                int i8 = 1;
                while (true) {
                    int i10 = pointArr[i8].x;
                    if (i2 > i10) {
                        i2 = i10;
                    }
                    if (i8 == length) {
                        break;
                    }
                    i8++;
                }
            }
            if (pointArr.length != 0) {
                int i11 = pointArr[0].x;
                int length2 = pointArr.length - 1;
                if (1 <= length2) {
                    int i12 = 1;
                    while (true) {
                        int i13 = pointArr[i12].x;
                        if (i11 < i13) {
                            i11 = i13;
                        }
                        if (i12 == length2) {
                            break;
                        }
                        i12++;
                    }
                }
                if (pointArr.length != 0) {
                    int i14 = pointArr[0].y;
                    int length3 = pointArr.length - 1;
                    if (1 <= length3) {
                        int i15 = 1;
                        while (true) {
                            int i16 = pointArr[i15].y;
                            if (i14 > i16) {
                                i14 = i16;
                            }
                            if (i15 == length3) {
                                break;
                            }
                            i15++;
                        }
                    }
                    if (pointArr.length != 0) {
                        int i17 = pointArr[0].y;
                        int length4 = pointArr.length - 1;
                        if (1 <= length4) {
                            while (true) {
                                int i18 = pointArr[i7].y;
                                if (i17 < i18) {
                                    i17 = i18;
                                }
                                if (i7 == length4) {
                                    break;
                                }
                                i7++;
                            }
                        }
                        return new Rect(i2, i14, i11, i17);
                    }
                    throw new NoSuchElementException();
                }
                throw new NoSuchElementException();
            }
            throw new NoSuchElementException();
        }
        throw new NoSuchElementException();
    }

    public static final VexFwkOcrResult toV1(VexFwkOcrResultV2 vexFwkOcrResultV2) {
        VexFwkOcrResultV2 vexFwkOcrResultV22 = vexFwkOcrResultV2;
        j.e(vexFwkOcrResultV22, "<this>");
        Iterable blockInfoList = vexFwkOcrResultV22.getBlockInfoList();
        int i2 = 10;
        ArrayList arrayList = new ArrayList(C1196n.w0(blockInfoList, 10));
        Iterator it = blockInfoList.iterator();
        while (it.hasNext()) {
            VexFwkOcrResultV2.BlockInfo blockInfo = (VexFwkOcrResultV2.BlockInfo) it.next();
            Iterable<VexFwkOcrResultV2.LineInfo> lineInfoList = blockInfo.getLineInfoList();
            ArrayList arrayList2 = new ArrayList(C1196n.w0(lineInfoList, i2));
            for (VexFwkOcrResultV2.LineInfo lineInfo : lineInfoList) {
                Iterable<VexFwkOcrResultV2.WordInfo> wordInfoList = lineInfo.getWordInfoList();
                ArrayList arrayList3 = new ArrayList(C1196n.w0(wordInfoList, i2));
                for (VexFwkOcrResultV2.WordInfo wordInfo : wordInfoList) {
                    Iterable<VexFwkOcrResultV2.CharInfo> charInfoList = wordInfo.getCharInfoList();
                    ArrayList arrayList4 = new ArrayList(C1196n.w0(charInfoList, i2));
                    for (VexFwkOcrResultV2.CharInfo charInfo : charInfoList) {
                        String string = charInfo.getString();
                        Iterator it2 = it;
                        Rect convertPointsToRect = convertPointsToRect(charInfo.getRect());
                        Point[] rect = charInfo.getRect();
                        Object[] copyOf = Arrays.copyOf(rect, rect.length);
                        j.d(copyOf, "copyOf(...)");
                        arrayList4.add(new VexFwkOcrResult.CharInfo(string, convertPointsToRect, (Point[]) copyOf));
                        VexFwkOcrResultV2 vexFwkOcrResultV23 = vexFwkOcrResultV2;
                        it = it2;
                    }
                    Rect convertPointsToRect2 = convertPointsToRect(wordInfo.getRect());
                    Point[] rect2 = wordInfo.getRect();
                    Object[] copyOf2 = Arrays.copyOf(rect2, rect2.length);
                    j.d(copyOf2, "copyOf(...)");
                    arrayList3.add(new VexFwkOcrResult.WordInfo(arrayList4, convertPointsToRect2, (Point[]) copyOf2));
                    i2 = 10;
                    VexFwkOcrResultV2 vexFwkOcrResultV24 = vexFwkOcrResultV2;
                    it = it;
                }
                Rect convertPointsToRect3 = convertPointsToRect(lineInfo.getRect());
                Point[] rect3 = lineInfo.getRect();
                Object[] copyOf3 = Arrays.copyOf(rect3, rect3.length);
                j.d(copyOf3, "copyOf(...)");
                arrayList2.add(new VexFwkOcrResult.LineInfo(arrayList3, convertPointsToRect3, (Point[]) copyOf3, lineInfo.isCurved()));
                i2 = 10;
                VexFwkOcrResultV2 vexFwkOcrResultV25 = vexFwkOcrResultV2;
                it = it;
            }
            Iterator it3 = it;
            Rect convertPointsToRect4 = convertPointsToRect(blockInfo.getRect());
            Point[] rect4 = blockInfo.getRect();
            Object[] copyOf4 = Arrays.copyOf(rect4, rect4.length);
            j.d(copyOf4, "copyOf(...)");
            arrayList.add(new VexFwkOcrResult.BlockInfo(arrayList2, convertPointsToRect4, (Point[]) copyOf4, blockInfo.isTabular(), ""));
            i2 = 10;
            VexFwkOcrResultV2 vexFwkOcrResultV26 = vexFwkOcrResultV2;
            it = it3;
        }
        Iterable<VexFwkOcrResultV2.TableInfo> tableInfoList = vexFwkOcrResultV2.getTableInfoList();
        ArrayList arrayList5 = new ArrayList(C1196n.w0(tableInfoList, 10));
        for (VexFwkOcrResultV2.TableInfo tableInfo : tableInfoList) {
            Iterable<VexFwkOcrResultV2.TableRowInfo> tableRowInfoList = tableInfo.getTableRowInfoList();
            ArrayList arrayList6 = new ArrayList(C1196n.w0(tableRowInfoList, 10));
            for (VexFwkOcrResultV2.TableRowInfo tableCellInfoList : tableRowInfoList) {
                Iterable<VexFwkOcrResultV2.TableCellInfo> tableCellInfoList2 = tableCellInfoList.getTableCellInfoList();
                ArrayList arrayList7 = new ArrayList(C1196n.w0(tableCellInfoList2, 10));
                for (VexFwkOcrResultV2.TableCellInfo tableCellInfo : tableCellInfoList2) {
                    arrayList7.add(new VexFwkOcrResult.TableCellInfo(convertPointsToRect(tableCellInfo.getCellBoundary()), tableCellInfo.getCellText()));
                }
                arrayList6.add(new VexFwkOcrResult.TableRowInfo(arrayList7));
            }
            arrayList5.add(new VexFwkOcrResult.TableInfo(arrayList6, convertPointsToRect(tableInfo.getTableBoundary())));
        }
        return new VexFwkOcrResult(arrayList, arrayList5, vexFwkOcrResultV2.isHandwrittenResult());
    }

    public static final VexFwkOcrResultV2 toV2(VexFwkOcrResult vexFwkOcrResult) {
        VexFwkOcrResult vexFwkOcrResult2 = vexFwkOcrResult;
        j.e(vexFwkOcrResult2, "<this>");
        Iterable<VexFwkOcrResult.BlockInfo> blockInfoList = vexFwkOcrResult2.getBlockInfoList();
        int i2 = 10;
        ArrayList arrayList = new ArrayList(C1196n.w0(blockInfoList, 10));
        for (VexFwkOcrResult.BlockInfo blockInfo : blockInfoList) {
            Iterable<VexFwkOcrResult.LineInfo> lineInfo = blockInfo.getLineInfo();
            ArrayList arrayList2 = new ArrayList(C1196n.w0(lineInfo, i2));
            for (VexFwkOcrResult.LineInfo lineInfo2 : lineInfo) {
                Iterable<VexFwkOcrResult.WordInfo> wordInfo = lineInfo2.getWordInfo();
                ArrayList arrayList3 = new ArrayList(C1196n.w0(wordInfo, i2));
                for (VexFwkOcrResult.WordInfo wordInfo2 : wordInfo) {
                    Iterable<VexFwkOcrResult.CharInfo> charInfo = wordInfo2.getCharInfo();
                    ArrayList arrayList4 = new ArrayList(C1196n.w0(charInfo, i2));
                    for (VexFwkOcrResult.CharInfo charInfo2 : charInfo) {
                        arrayList4.add(new VexFwkOcrResultV2.CharInfo(charInfo2.getString(), charInfo2.getPoly(), 0.0f));
                    }
                    arrayList3.add(new VexFwkOcrResultV2.WordInfo(arrayList4, wordInfo2.getPoly(), wordInfo2.getPoly(), 0.0f));
                    i2 = 10;
                }
                arrayList2.add(new VexFwkOcrResultV2.LineInfo(arrayList3, lineInfo2.getPoly(), lineInfo2.isCurved(), 0.0f));
                i2 = 10;
            }
            arrayList.add(new VexFwkOcrResultV2.BlockInfo(arrayList2, blockInfo.getPoly(), blockInfo.isTabular(), 0.0f));
            i2 = 10;
        }
        Iterable<VexFwkOcrResult.TableInfo> tableInfoList = vexFwkOcrResult2.getTableInfoList();
        ArrayList arrayList5 = new ArrayList(C1196n.w0(tableInfoList, 10));
        for (VexFwkOcrResult.TableInfo tableInfo : tableInfoList) {
            Iterable<VexFwkOcrResult.TableRowInfo> tableRowInfo = tableInfo.getTableRowInfo();
            ArrayList arrayList6 = new ArrayList(C1196n.w0(tableRowInfo, 10));
            for (VexFwkOcrResult.TableRowInfo tableCellInfo : tableRowInfo) {
                Iterable<VexFwkOcrResult.TableCellInfo> tableCellInfo2 = tableCellInfo.getTableCellInfo();
                ArrayList arrayList7 = new ArrayList(C1196n.w0(tableCellInfo2, 10));
                for (VexFwkOcrResult.TableCellInfo tableCellInfo3 : tableCellInfo2) {
                    arrayList7.add(new VexFwkOcrResultV2.TableCellInfo(RectKt.toArrayOfPoints(tableCellInfo3.getCellBoundary()), tableCellInfo3.getCellText(), 0.0f));
                }
                arrayList6.add(new VexFwkOcrResultV2.TableRowInfo(arrayList7, 0.0f));
            }
            arrayList5.add(new VexFwkOcrResultV2.TableInfo(arrayList6, RectKt.toArrayOfPoints(tableInfo.getTableBoundary()), 0.0f));
        }
        return new VexFwkOcrResultV2(arrayList, arrayList5, vexFwkOcrResult2.isHandwrittenResult());
    }
}
