package com.samsung.android.app.sdk.deepsky.textextraction.ocrwrapper;

import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityExtractionResult;
import com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityItem;
import com.samsung.android.app.sdk.deepsky.textextraction.result.EntityData;
import com.samsung.android.app.sdk.deepsky.textextraction.result.OcrData;
import com.samsung.android.imagetranslation.data.LttOcrResult;
import com.samsung.android.sdk.ocr.OCRResult;
import com.samsung.android.vexfwk.param.VexFwkOcrResultV2;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import ne.C1196n;
import ne.C1202t;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\b\u001a\u00020\u0007*\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0005H\u0002¢\u0006\u0004\b\b\u0010\tJ\u001b\u0010\f\u001a\u00020\u000b*\u00020\n2\u0006\u0010\u0006\u001a\u00020\u0005H\u0002¢\u0006\u0004\b\f\u0010\rJ\u001b\u0010\u0010\u001a\u00020\u000f*\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u0005H\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u001b\u0010\u0014\u001a\u00020\u0013*\u00020\u00122\u0006\u0010\u0006\u001a\u00020\u0005H\u0002¢\u0006\u0004\b\u0014\u0010\u0015J%\u0010\u0019\u001a\u00020\u00052\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\u0006\u0010\u0006\u001a\u00020\u0005H\u0002¢\u0006\u0004\b\u0019\u0010\u001aJ+\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\u0006\u0010\u0006\u001a\u00020\u0005H\u0002¢\u0006\u0004\b\u001b\u0010\u001cJ\u0013\u0010\u001e\u001a\u00020\u001d*\u00020\u0007H\u0002¢\u0006\u0004\b\u001e\u0010\u001fJ\u0013\u0010!\u001a\u00020 *\u00020\u000bH\u0002¢\u0006\u0004\b!\u0010\"J\u0013\u0010$\u001a\u00020#*\u00020\u000fH\u0002¢\u0006\u0004\b$\u0010%J\u0013\u0010'\u001a\u00020&*\u00020\u0013H\u0002¢\u0006\u0004\b'\u0010(J\u0019\u0010+\u001a\u00020**\u00020)2\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b+\u0010,J\u0019\u0010/\u001a\u00020.*\u00020-2\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b/\u00100J\u0011\u00102\u001a\u000201*\u00020*¢\u0006\u0004\b2\u00103J\u0011\u00105\u001a\u000204*\u00020*¢\u0006\u0004\b5\u00106¨\u00067"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/ocrwrapper/OcrDataConverter;", "", "<init>", "()V", "Lcom/samsung/android/sdk/ocr/OCRResult$BlockData;", "Landroid/graphics/Rect;", "validRect", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData$BlockInfo;", "toOcrDataBlockInfo", "(Lcom/samsung/android/sdk/ocr/OCRResult$BlockData;Landroid/graphics/Rect;)Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData$BlockInfo;", "Lcom/samsung/android/sdk/ocr/OCRResult$LineData;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData$LineInfo;", "toOcrDataLineInfo", "(Lcom/samsung/android/sdk/ocr/OCRResult$LineData;Landroid/graphics/Rect;)Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData$LineInfo;", "Lcom/samsung/android/sdk/ocr/OCRResult$WordData;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData$WordInfo;", "toOcrDataWordInfo", "(Lcom/samsung/android/sdk/ocr/OCRResult$WordData;Landroid/graphics/Rect;)Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData$WordInfo;", "Lcom/samsung/android/sdk/ocr/OCRResult$CharData;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData$CharInfo;", "toOcrDataCharInfo", "(Lcom/samsung/android/sdk/ocr/OCRResult$CharData;Landroid/graphics/Rect;)Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData$CharInfo;", "", "Landroid/graphics/Point;", "pointArray", "getTranslatedRect", "([Landroid/graphics/Point;Landroid/graphics/Rect;)Landroid/graphics/Rect;", "getTranslatedPoly", "([Landroid/graphics/Point;Landroid/graphics/Rect;)[Landroid/graphics/Point;", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResultV2$BlockInfo;", "toVexFwkOcrResultV2BlockInfo", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData$BlockInfo;)Lcom/samsung/android/vexfwk/param/VexFwkOcrResultV2$BlockInfo;", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResultV2$LineInfo;", "toVexFwkOcrResultV2LineInfo", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData$LineInfo;)Lcom/samsung/android/vexfwk/param/VexFwkOcrResultV2$LineInfo;", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResultV2$WordInfo;", "toVexFwkOcrResultV2WordInfo", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData$WordInfo;)Lcom/samsung/android/vexfwk/param/VexFwkOcrResultV2$WordInfo;", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResultV2$CharInfo;", "toVexFwkOcrResultV2CharInfo", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData$CharInfo;)Lcom/samsung/android/vexfwk/param/VexFwkOcrResultV2$CharInfo;", "Lcom/samsung/android/sdk/ocr/OCRResult;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData;", "toOcrData", "(Lcom/samsung/android/sdk/ocr/OCRResult;Landroid/graphics/Rect;)Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/entity/EntityExtractionResult;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/EntityData;", "toEntityData", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/entity/EntityExtractionResult;Landroid/graphics/Rect;)Lcom/samsung/android/app/sdk/deepsky/textextraction/result/EntityData;", "Lcom/samsung/android/imagetranslation/data/LttOcrResult;", "toLttOcrResult", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData;)Lcom/samsung/android/imagetranslation/data/LttOcrResult;", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResultV2;", "toVexFwkOcrResultV2", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData;)Lcom/samsung/android/vexfwk/param/VexFwkOcrResultV2;", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class OcrDataConverter {
    public static final OcrDataConverter INSTANCE = new OcrDataConverter();

    private OcrDataConverter() {
    }

    private final Point[] getTranslatedPoly(Point[] pointArr, Rect rect) {
        ArrayList arrayList = new ArrayList(pointArr.length);
        for (Point point : pointArr) {
            arrayList.add(new Point(point.x + rect.left, point.y + rect.top));
        }
        return (Point[]) arrayList.toArray(new Point[0]);
    }

    private final Rect getTranslatedRect(Point[] pointArr, Rect rect) {
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
            int i11 = i2 + rect.left;
            if (pointArr.length != 0) {
                int i12 = pointArr[0].y;
                int length2 = pointArr.length - 1;
                if (1 <= length2) {
                    int i13 = 1;
                    while (true) {
                        int i14 = pointArr[i13].y;
                        if (i12 > i14) {
                            i12 = i14;
                        }
                        if (i13 == length2) {
                            break;
                        }
                        i13++;
                    }
                }
                int i15 = i12 + rect.top;
                if (pointArr.length != 0) {
                    int i16 = pointArr[0].x;
                    int length3 = pointArr.length - 1;
                    if (1 <= length3) {
                        int i17 = 1;
                        while (true) {
                            int i18 = pointArr[i17].x;
                            if (i16 < i18) {
                                i16 = i18;
                            }
                            if (i17 == length3) {
                                break;
                            }
                            i17++;
                        }
                    }
                    int i19 = i16 + rect.left;
                    if (pointArr.length != 0) {
                        int i20 = pointArr[0].y;
                        int length4 = pointArr.length - 1;
                        if (1 <= length4) {
                            while (true) {
                                int i21 = pointArr[i7].y;
                                if (i20 < i21) {
                                    i20 = i21;
                                }
                                if (i7 == length4) {
                                    break;
                                }
                                i7++;
                            }
                        }
                        return new Rect(i11, i15, i19, i20 + rect.top);
                    }
                    throw new NoSuchElementException();
                }
                throw new NoSuchElementException();
            }
            throw new NoSuchElementException();
        }
        throw new NoSuchElementException();
    }

    private final OcrData.BlockInfo toOcrDataBlockInfo(OCRResult.BlockData blockData, Rect rect) {
        ArrayList<OCRResult.LineData> lineDataList = blockData.getLineDataList();
        j.d(lineDataList, "getLineDataList(...)");
        ArrayList arrayList = new ArrayList(C1196n.w0(lineDataList, 10));
        for (OCRResult.LineData lineData : lineDataList) {
            OcrDataConverter ocrDataConverter = INSTANCE;
            j.b(lineData);
            arrayList.add(ocrDataConverter.toOcrDataLineInfo(lineData, rect));
        }
        Point[] rect2 = blockData.getRect();
        j.d(rect2, "getRect(...)");
        Rect translatedRect = getTranslatedRect(rect2, rect);
        Point[] rect3 = blockData.getRect();
        j.d(rect3, "getRect(...)");
        return new OcrData.BlockInfo(arrayList, translatedRect, getTranslatedPoly(rect3, rect), blockData.isTabular());
    }

    private final OcrData.CharInfo toOcrDataCharInfo(OCRResult.CharData charData, Rect rect) {
        String text = charData.getText();
        j.d(text, "getText(...)");
        Point[] rect2 = charData.getRect();
        j.d(rect2, "getRect(...)");
        Rect translatedRect = getTranslatedRect(rect2, rect);
        Point[] rect3 = charData.getRect();
        j.d(rect3, "getRect(...)");
        return new OcrData.CharInfo(text, translatedRect, getTranslatedPoly(rect3, rect));
    }

    private final OcrData.LineInfo toOcrDataLineInfo(OCRResult.LineData lineData, Rect rect) {
        ArrayList<OCRResult.WordData> wordDataList = lineData.getWordDataList();
        j.d(wordDataList, "getWordDataList(...)");
        ArrayList arrayList = new ArrayList(C1196n.w0(wordDataList, 10));
        for (OCRResult.WordData wordData : wordDataList) {
            OcrDataConverter ocrDataConverter = INSTANCE;
            j.b(wordData);
            arrayList.add(ocrDataConverter.toOcrDataWordInfo(wordData, rect));
        }
        Point[] rect2 = lineData.getRect();
        j.d(rect2, "getRect(...)");
        Rect translatedRect = getTranslatedRect(rect2, rect);
        Point[] rect3 = lineData.getRect();
        j.d(rect3, "getRect(...)");
        return new OcrData.LineInfo(arrayList, translatedRect, getTranslatedPoly(rect3, rect), lineData.isCurved());
    }

    private final OcrData.WordInfo toOcrDataWordInfo(OCRResult.WordData wordData, Rect rect) {
        ArrayList<OCRResult.CharData> charDataList = wordData.getCharDataList();
        j.d(charDataList, "getCharDataList(...)");
        ArrayList arrayList = new ArrayList(C1196n.w0(charDataList, 10));
        for (OCRResult.CharData charData : charDataList) {
            OcrDataConverter ocrDataConverter = INSTANCE;
            j.b(charData);
            arrayList.add(ocrDataConverter.toOcrDataCharInfo(charData, rect));
        }
        Point[] rect2 = wordData.getRect();
        j.d(rect2, "getRect(...)");
        Rect translatedRect = getTranslatedRect(rect2, rect);
        Point[] rect3 = wordData.getRect();
        j.d(rect3, "getRect(...)");
        Point[] translatedPoly = getTranslatedPoly(rect3, rect);
        Point[] poly = wordData.getPoly();
        j.d(poly, "getPoly(...)");
        return new OcrData.WordInfo(arrayList, translatedRect, translatedPoly, getTranslatedPoly(poly, rect));
    }

    private final VexFwkOcrResultV2.BlockInfo toVexFwkOcrResultV2BlockInfo(OcrData.BlockInfo blockInfo) {
        Iterable<OcrData.LineInfo> lineList = blockInfo.getLineList();
        ArrayList arrayList = new ArrayList(C1196n.w0(lineList, 10));
        for (OcrData.LineInfo vexFwkOcrResultV2LineInfo : lineList) {
            arrayList.add(INSTANCE.toVexFwkOcrResultV2LineInfo(vexFwkOcrResultV2LineInfo));
        }
        return new VexFwkOcrResultV2.BlockInfo(arrayList, blockInfo.getPoly(), blockInfo.isTabular(), 0.0f);
    }

    private final VexFwkOcrResultV2.CharInfo toVexFwkOcrResultV2CharInfo(OcrData.CharInfo charInfo) {
        return new VexFwkOcrResultV2.CharInfo(charInfo.getText(), charInfo.getPoly(), 0.0f);
    }

    private final VexFwkOcrResultV2.LineInfo toVexFwkOcrResultV2LineInfo(OcrData.LineInfo lineInfo) {
        Iterable<OcrData.WordInfo> wordList = lineInfo.getWordList();
        ArrayList arrayList = new ArrayList(C1196n.w0(wordList, 10));
        for (OcrData.WordInfo vexFwkOcrResultV2WordInfo : wordList) {
            arrayList.add(INSTANCE.toVexFwkOcrResultV2WordInfo(vexFwkOcrResultV2WordInfo));
        }
        return new VexFwkOcrResultV2.LineInfo(arrayList, lineInfo.getPoly(), lineInfo.isCurved(), 0.0f);
    }

    private final VexFwkOcrResultV2.WordInfo toVexFwkOcrResultV2WordInfo(OcrData.WordInfo wordInfo) {
        Iterable<OcrData.CharInfo> charList = wordInfo.getCharList();
        ArrayList arrayList = new ArrayList(C1196n.w0(charList, 10));
        for (OcrData.CharInfo vexFwkOcrResultV2CharInfo : charList) {
            arrayList.add(INSTANCE.toVexFwkOcrResultV2CharInfo(vexFwkOcrResultV2CharInfo));
        }
        return new VexFwkOcrResultV2.WordInfo(arrayList, wordInfo.getPoly(), wordInfo.getCurvedPoly(), 0.0f);
    }

    public final EntityData toEntityData(EntityExtractionResult entityExtractionResult, Rect rect) {
        j.e(entityExtractionResult, "<this>");
        j.e(rect, "validRect");
        Iterable<EntityItem> items = entityExtractionResult.getItems();
        ArrayList arrayList = new ArrayList(C1196n.w0(items, 10));
        for (EntityItem entityItem : items) {
            String text = entityItem.getText();
            String typeId = entityItem.getType().getTypeId();
            List<Rect> rectList = entityItem.getRectList();
            List<Point[]> polyList = entityItem.getPolyList();
            Iterable<Point[]> polyList2 = entityItem.getPolyList();
            ArrayList arrayList2 = new ArrayList(C1196n.w0(polyList2, 10));
            for (Point[] pointArr : polyList2) {
                arrayList2.add(new EntityData.UnderlineInfo(new PointF(pointArr[3]), new PointF(pointArr[2])));
            }
            arrayList.add(new EntityData.EntityInfo(text, typeId, rectList, polyList, arrayList2, entityItem.getScore(), entityItem.getAction(), entityItem.getActionId()));
        }
        return new EntityData(arrayList, rect);
    }

    public final LttOcrResult toLttOcrResult(OcrData ocrData) {
        j.e(ocrData, "<this>");
        ArrayList arrayList = new ArrayList();
        for (OcrData.BlockInfo blockInfo : ocrData.getBlockList()) {
            ArrayList arrayList2 = new ArrayList();
            for (OcrData.LineInfo lineInfo : blockInfo.getLineList()) {
                ArrayList arrayList3 = new ArrayList();
                for (OcrData.WordInfo wordInfo : lineInfo.getWordList()) {
                    ArrayList arrayList4 = new ArrayList();
                    for (OcrData.CharInfo next : wordInfo.getCharList()) {
                        arrayList4.add(new LttOcrResult.CharInfo(next.getText(), next.getRect(), next.getPoly()));
                    }
                    arrayList4.add(new LttOcrResult.CharInfo(" ", (Rect) null, (Point[]) null));
                    arrayList3.add(new LttOcrResult.WordInfo(arrayList4, wordInfo.getRect(), wordInfo.getPoly(), ""));
                }
                arrayList2.add(new LttOcrResult.LineInfo(arrayList3, lineInfo.getRect(), lineInfo.getPoly(), ""));
            }
            arrayList.add(new LttOcrResult.BlockInfo(arrayList2, blockInfo.getRect(), blockInfo.getPoly(), "", blockInfo.isTabular()));
        }
        return new LttOcrResult(arrayList, C1202t.d);
    }

    public final OcrData toOcrData(OCRResult oCRResult, Rect rect) {
        j.e(oCRResult, "<this>");
        j.e(rect, "validRect");
        ArrayList<OCRResult.BlockData> blockDataList = oCRResult.getBlockDataList();
        j.d(blockDataList, "getBlockDataList(...)");
        ArrayList arrayList = new ArrayList(C1196n.w0(blockDataList, 10));
        for (OCRResult.BlockData blockData : blockDataList) {
            OcrDataConverter ocrDataConverter = INSTANCE;
            j.b(blockData);
            arrayList.add(ocrDataConverter.toOcrDataBlockInfo(blockData, rect));
        }
        return new OcrData("1.0", arrayList, rect);
    }

    public final VexFwkOcrResultV2 toVexFwkOcrResultV2(OcrData ocrData) {
        j.e(ocrData, "<this>");
        Iterable<OcrData.BlockInfo> blockList = ocrData.getBlockList();
        ArrayList arrayList = new ArrayList(C1196n.w0(blockList, 10));
        for (OcrData.BlockInfo vexFwkOcrResultV2BlockInfo : blockList) {
            arrayList.add(INSTANCE.toVexFwkOcrResultV2BlockInfo(vexFwkOcrResultV2BlockInfo));
        }
        return new VexFwkOcrResultV2(arrayList, C1202t.d, false);
    }
}
