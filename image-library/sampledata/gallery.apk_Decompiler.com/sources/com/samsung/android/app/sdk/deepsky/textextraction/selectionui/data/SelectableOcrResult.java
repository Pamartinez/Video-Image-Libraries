package com.samsung.android.app.sdk.deepsky.textextraction.selectionui.data;

import A.a;
import Tf.n;
import android.graphics.Point;
import com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType;
import com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger;
import com.samsung.android.app.sdk.deepsky.textextraction.result.EntityData;
import com.samsung.android.app.sdk.deepsky.textextraction.result.OcrData;
import com.samsung.android.app.sdk.deepsky.textextraction.util.PointUtil;
import com.samsung.android.app.sdk.deepsky.textextraction.util.VectorUtil;
import com.samsung.android.gallery.support.utils.MapUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.i;
import ne.C1194l;
import ne.C1196n;
import ne.C1202t;

@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0012\b\u0000\u0018\u0000 A2\u00020\u0001:\u0001AB\t\b\u0016¢\u0006\u0004\b\u0002\u0010\u0003B!\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\u0002\u0010\nJ#\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u000b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J#\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u000b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0002¢\u0006\u0004\b\u0012\u0010\u0010J#\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u000b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0002¢\u0006\u0004\b\u0014\u0010\u0010J-\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH\u0002¢\u0006\u0004\b\u0017\u0010\u0018J7\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\u001cH\u0002¢\u0006\u0004\b\u001e\u0010\u001fJ;\u0010$\u001a\b\u0012\u0004\u0012\u00020#0\u000b2\u0006\u0010!\u001a\u00020 2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00130\u000bH\u0002¢\u0006\u0004\b$\u0010%J\u000f\u0010'\u001a\u00020&H\u0002¢\u0006\u0004\b'\u0010\u0003J\u000f\u0010(\u001a\u00020&H\u0002¢\u0006\u0004\b(\u0010\u0003J\u001f\u0010+\u001a\u00020\u001c2\u0006\u0010)\u001a\u00020\u000e2\u0006\u0010*\u001a\u00020\u000eH\u0002¢\u0006\u0004\b+\u0010,J\u000f\u0010-\u001a\u00020&H\u0002¢\u0006\u0004\b-\u0010\u0003J\u000f\u0010.\u001a\u00020&H\u0002¢\u0006\u0004\b.\u0010\u0003J\u000f\u0010/\u001a\u00020&H\u0002¢\u0006\u0004\b/\u0010\u0003J\r\u00101\u001a\u000200¢\u0006\u0004\b1\u00102J\u0015\u00104\u001a\u00020\u000e2\u0006\u00103\u001a\u00020\u0013¢\u0006\u0004\b4\u00105J\u0015\u00106\u001a\u00020\u00112\u0006\u00103\u001a\u00020\u0013¢\u0006\u0004\b6\u00107R\u001d\u00108\u001a\b\u0012\u0004\u0012\u00020\f0\u000b8\u0006¢\u0006\f\n\u0004\b8\u00109\u001a\u0004\b:\u0010;R\u001d\u0010<\u001a\b\u0012\u0004\u0012\u00020\u000e0\u000b8\u0006¢\u0006\f\n\u0004\b<\u00109\u001a\u0004\b\u000f\u0010;R\u001d\u0010=\u001a\b\u0012\u0004\u0012\u00020\u00110\u000b8\u0006¢\u0006\f\n\u0004\b=\u00109\u001a\u0004\b\u0012\u0010;R\u001d\u0010>\u001a\b\u0012\u0004\u0012\u00020\u00130\u000b8\u0006¢\u0006\f\n\u0004\b>\u00109\u001a\u0004\b\u0014\u0010;R\u001d\u0010?\u001a\b\u0012\u0004\u0012\u00020#0\u000b8\u0006¢\u0006\f\n\u0004\b?\u00109\u001a\u0004\b@\u0010;¨\u0006B"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableOcrResult;", "", "<init>", "()V", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/TextData;", "textData", "", "ratio", "Landroid/graphics/Point;", "centerOffset", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/result/TextData;FLandroid/graphics/Point;)V", "", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableBlock;", "blocks", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableLine;", "getSelectableLines", "(Ljava/util/List;)Ljava/util/List;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableWord;", "getSelectableWords", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableCharacter;", "getSelectableCharacters", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData;", "ocrData", "createSelectableBlocks", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData;FLandroid/graphics/Point;)Ljava/util/List;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData$WordInfo;", "word", "nextWord", "", "isLineVertical", "createEmptySpaceBetweenWords", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData$WordInfo;Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData$WordInfo;FLandroid/graphics/Point;Z)Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableWord;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/EntityData;", "entityData", "selectableChars", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableEntity;", "createSelectableEntities", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/result/EntityData;FLandroid/graphics/Point;Ljava/util/List;)Ljava/util/List;", "Lme/x;", "adjustSelectableRegions", "adjustSelectableLines", "line0", "line1", "isLineParallel", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableLine;Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableLine;)Z", "adjustSelectableRegionOfWordsAndChars", "adjustSelectableRegionsOfEntities", "adjustSelectableEntityDrawInfo", "", "getContentDescription", "()Ljava/lang/String;", "char", "getLineContainingCharacter", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableCharacter;)Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableLine;", "getWordContainingCharacter", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableCharacter;)Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableWord;", "selectableBlocks", "Ljava/util/List;", "getSelectableBlocks", "()Ljava/util/List;", "selectableLines", "selectableWords", "selectableCharacters", "selectableEntities", "getSelectableEntities", "Companion", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SelectableOcrResult {
    public static final Companion Companion = new Companion((e) null);
    /* access modifiers changed from: private */
    public static final SelectableCharacter EMPTY_CHARACTER = new SelectableCharacter("", new Point[]{new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0)}, false);
    private final List<SelectableBlock> selectableBlocks;
    private final List<SelectableCharacter> selectableCharacters;
    private final List<SelectableEntity> selectableEntities;
    private final List<SelectableLine> selectableLines;
    private final List<SelectableWord> selectableWords;

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableOcrResult$Companion;", "", "<init>", "()V", "TAG", "", "ADJUST_LINE_TILT_THRESHOLD", "", "ADJUST_LINE_ANGLE_THRESHOLD", "", "ADJUST_LINE_HEIGHT_THRESHOLD", "", "EMPTY_CHARACTER", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableCharacter;", "getEMPTY_CHARACTER", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableCharacter;", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final SelectableCharacter getEMPTY_CHARACTER() {
            return SelectableOcrResult.EMPTY_CHARACTER;
        }

        private Companion() {
        }
    }

    public SelectableOcrResult() {
        C1202t tVar = C1202t.d;
        this.selectableBlocks = tVar;
        this.selectableLines = tVar;
        this.selectableWords = tVar;
        this.selectableCharacters = tVar;
        this.selectableEntities = tVar;
    }

    private final void adjustSelectableEntityDrawInfo() {
        for (SelectableEntity next : this.selectableEntities) {
            Iterator it = C1194l.r1(next.getUnderlineList(), next.getOverlappingLines()).iterator();
            while (it.hasNext()) {
                i iVar = (i) it.next();
                Underline underline = (Underline) iVar.d;
                SelectableLine selectableLine = (SelectableLine) iVar.e;
                Point point = selectableLine.getPoly()[3];
                Point point2 = selectableLine.getPoly()[2];
                if (!(underline.getTo().x == underline.getFrom().x || point2.x == point.x)) {
                    if (underline.getFrom().y >= underline.getTo().y) {
                        underline.getFrom().y = ((-((underline.getTo().x - underline.getFrom().x) * (point2.y - point.y))) / (point2.x - point.x)) + underline.getTo().y;
                    } else {
                        underline.getTo().y = (((underline.getTo().x - underline.getFrom().x) * (point2.y - point.y)) / (point2.x - point.x)) + underline.getFrom().y;
                    }
                }
            }
        }
    }

    private final void adjustSelectableLines() {
        for (SelectableBlock next : this.selectableBlocks) {
            int i2 = 0;
            for (SelectableLine selectableLine : next.getSelectableLines()) {
                int i7 = i2 + 1;
                if (i2 != next.getSelectableLines().size() - 1) {
                    SelectableLine selectableLine2 = next.getSelectableLines().get(i7);
                    if (isLineParallel(selectableLine, selectableLine2)) {
                        Point[] pointArr = {selectableLine.getPoly()[1], selectableLine.getPoly()[2]};
                        Point[] pointArr2 = {selectableLine.getPoly()[0], selectableLine.getPoly()[3]};
                        Point[] pointArr3 = {selectableLine2.getPoly()[0], selectableLine2.getPoly()[1]};
                        Point[] poly = selectableLine.getPoly();
                        VectorUtil vectorUtil = VectorUtil.INSTANCE;
                        Point calcIntersectionOfLines = vectorUtil.calcIntersectionOfLines(pointArr, pointArr3);
                        if (calcIntersectionOfLines == null) {
                            calcIntersectionOfLines = selectableLine.getPoly()[2];
                        }
                        poly[2] = calcIntersectionOfLines;
                        Point[] poly2 = selectableLine.getPoly();
                        Point calcIntersectionOfLines2 = vectorUtil.calcIntersectionOfLines(pointArr2, pointArr3);
                        if (calcIntersectionOfLines2 == null) {
                            calcIntersectionOfLines2 = selectableLine.getPoly()[3];
                        }
                        poly2[3] = calcIntersectionOfLines2;
                    }
                }
                i2 = i7;
            }
        }
    }

    private final void adjustSelectableRegionOfWordsAndChars() {
        for (SelectableBlock selectableLines2 : this.selectableBlocks) {
            for (SelectableLine next : selectableLines2.getSelectableLines()) {
                Point point = new Point(-1, -1);
                Point point2 = new Point(-1, -1);
                char c5 = 0;
                int i2 = 0;
                for (SelectableWord selectableWord : next.getSelectableWords()) {
                    int i7 = i2 + 1;
                    Point[] calcMinAreaPoly = VectorUtil.INSTANCE.calcMinAreaPoly(selectableWord.getPoly(), next.getPoly());
                    if (i2 != 0) {
                        Point[] poly = next.getSelectableWords().get(i2 - 1).getPoly();
                        calcMinAreaPoly[c5] = poly[1];
                        calcMinAreaPoly[3] = poly[2];
                    }
                    selectableWord.setPoly(calcMinAreaPoly);
                    for (SelectableCharacter next2 : selectableWord.getSelectableCharacters()) {
                        char c6 = c5;
                        Point[] calcMinAreaPoly2 = VectorUtil.INSTANCE.calcMinAreaPoly(next2.getPoly(), next.getPoly());
                        if (j.a(point, new Point(-1, -1)) && j.a(point2, new Point(-1, -1))) {
                            point = calcMinAreaPoly2[c6];
                            point2 = calcMinAreaPoly2[3];
                        }
                        calcMinAreaPoly2[c6] = point;
                        calcMinAreaPoly2[3] = point2;
                        next2.setPoly(calcMinAreaPoly2);
                        point = calcMinAreaPoly2[1];
                        point2 = calcMinAreaPoly2[2];
                        c5 = c6;
                    }
                    i2 = i7;
                }
            }
        }
    }

    private final void adjustSelectableRegions() {
        adjustSelectableRegionOfWordsAndChars();
        adjustSelectableRegionsOfEntities();
        adjustSelectableEntityDrawInfo();
    }

    private final void adjustSelectableRegionsOfEntities() {
        for (SelectableEntity next : this.selectableEntities) {
            ArrayList arrayList = new ArrayList();
            Iterable polyList = next.getPolyList();
            Iterable overlappingLines = next.getOverlappingLines();
            Iterator it = polyList.iterator();
            Iterator it2 = overlappingLines.iterator();
            ArrayList arrayList2 = new ArrayList(Math.min(C1196n.w0(polyList, 10), C1196n.w0(overlappingLines, 10)));
            while (it.hasNext() && it2.hasNext()) {
                Object next2 = it.next();
                arrayList2.add(Boolean.valueOf(arrayList.add(VectorUtil.INSTANCE.calcMinAreaPoly((Point[]) next2, ((SelectableLine) it2.next()).getPoly()))));
            }
            next.setPolyList(arrayList);
        }
    }

    private final SelectableWord createEmptySpaceBetweenWords(OcrData.WordInfo wordInfo, OcrData.WordInfo wordInfo2, float f, Point point, boolean z) {
        Point[] pointArr;
        PointUtil pointUtil = PointUtil.INSTANCE;
        Point[] transformedPoly = pointUtil.getTransformedPoly(wordInfo.getPoly(), f, point);
        Point[] transformedPoly2 = pointUtil.getTransformedPoly(wordInfo2.getPoly(), f, point);
        if (pointUtil.isPolyOverlapsPoly(transformedPoly, transformedPoly2, MapUtil.INVALID_LOCATION)) {
            Point point2 = transformedPoly[1];
            pointArr = new Point[]{point2, point2, transformedPoly[2], transformedPoly[2]};
        } else {
            pointArr = new Point[]{transformedPoly[1], transformedPoly2[0], transformedPoly2[3], transformedPoly[2]};
        }
        SelectableWord selectableWord = new SelectableWord(" ", pointArr);
        selectableWord.getSelectableCharacters().add(new SelectableCharacter(" ", pointArr, z));
        return selectableWord;
    }

    private final List<SelectableBlock> createSelectableBlocks(OcrData ocrData, float f, Point point) {
        float f5 = f;
        Point point2 = point;
        ArrayList arrayList = new ArrayList();
        for (OcrData.BlockInfo next : ocrData.getBlockList()) {
            SelectableBlock selectableBlock = new SelectableBlock(next.toString(), PointUtil.INSTANCE.getTransformedPoly(next.getPoly(), f5, point2));
            arrayList.add(selectableBlock);
            for (OcrData.LineInfo next2 : next.getLineList()) {
                PointUtil pointUtil = PointUtil.INSTANCE;
                Point[] transformedPoly = pointUtil.getTransformedPoly(next2.getPoly(), f5, point2);
                boolean isVertical = pointUtil.isVertical(transformedPoly);
                SelectableLine selectableLine = new SelectableLine(next2.toString(), transformedPoly, isVertical);
                selectableBlock.getSelectableLines().add(selectableLine);
                int i2 = 0;
                for (OcrData.WordInfo wordInfo : next2.getWordList()) {
                    int i7 = i2 + 1;
                    SelectableWord selectableWord = new SelectableWord(wordInfo.toString(), PointUtil.INSTANCE.getTransformedPoly(wordInfo.getPoly(), f5, point2));
                    selectableLine.getSelectableWords().add(selectableWord);
                    Iterator<OcrData.CharInfo> it = wordInfo.getCharList().iterator();
                    while (it.hasNext()) {
                        OcrData.CharInfo next3 = it.next();
                        OcrData.WordInfo wordInfo2 = wordInfo;
                        Iterator<OcrData.CharInfo> it2 = it;
                        selectableWord.getSelectableCharacters().add(new SelectableCharacter(next3.getText(), PointUtil.INSTANCE.getTransformedPoly(next3.getPoly(), f5, point2), isVertical));
                        wordInfo = wordInfo2;
                        it = it2;
                    }
                    OcrData.WordInfo wordInfo3 = wordInfo;
                    if (i2 != next2.getWordList().size() - 1) {
                        selectableLine.getSelectableWords().add(createEmptySpaceBetweenWords(wordInfo3, next2.getWordList().get(i7), f5, point2, isVertical));
                    }
                    f5 = f;
                    point2 = point;
                    i2 = i7;
                }
                f5 = f;
                point2 = point;
            }
            f5 = f;
            point2 = point;
        }
        return arrayList;
    }

    private final List<SelectableEntity> createSelectableEntities(EntityData entityData, float f, Point point, List<SelectableCharacter> list) {
        ArrayList arrayList = new ArrayList();
        for (EntityData.EntityInfo next : entityData.getEntityList()) {
            ArrayList<Point[]> arrayList2 = new ArrayList<>();
            for (Point[] next2 : next.getPolyList()) {
                if (next2.length == 4) {
                    arrayList2.add(PointUtil.INSTANCE.getTransformedPoly(next2, f, point));
                }
            }
            float f5 = f;
            Point point2 = point;
            String text = next.getText();
            EntityType entityType = EntityType.Companion.toEntityType(next.getType());
            ArrayList arrayList3 = new ArrayList(C1196n.w0(arrayList2, 10));
            for (Point[] pointArr : arrayList2) {
                arrayList3.add(new Underline(new Point(pointArr[3]), new Point(pointArr[2])));
            }
            SelectableEntity selectableEntity = new SelectableEntity(text, entityType, arrayList2, arrayList3);
            for (Point[] next3 : selectableEntity.getPolyList()) {
                for (SelectableCharacter next4 : list) {
                    if (PointUtil.isPolyOverlapsPoly$default(PointUtil.INSTANCE, next3, next4.getPoly(), MapUtil.INVALID_LOCATION, 4, (Object) null)) {
                        SelectableLine lineContainingCharacter = getLineContainingCharacter(next4);
                        if (!selectableEntity.getOverlappingLines().contains(lineContainingCharacter)) {
                            selectableEntity.getOverlappingLines().add(lineContainingCharacter);
                        }
                        selectableEntity.getSelectableCharacters().add(next4);
                    }
                }
            }
            if (selectableEntity.getSelectableCharacters().isEmpty()) {
                String text2 = next.getText();
                LibLogger.i("SelectableOcrResult", "Selectable characters not detected, entity " + text2 + " is skipped");
            } else if (selectableEntity.getPolyList().size() != selectableEntity.getOverlappingLines().size()) {
                int size = selectableEntity.getPolyList().size();
                int size2 = selectableEntity.getOverlappingLines().size();
                String text3 = next.getText();
                StringBuilder h5 = a.h(size, size2, "size of polyList(", ") and overlappingLines(", ") does not match, entity ");
                h5.append(text3);
                h5.append(" is skipped");
                LibLogger.w("SelectableOcrResult", h5.toString());
            } else {
                arrayList.add(selectableEntity);
            }
        }
        return arrayList;
    }

    private final List<SelectableLine> getSelectableLines(List<SelectableBlock> list) {
        ArrayList arrayList = new ArrayList();
        for (SelectableBlock selectableLines2 : list) {
            arrayList.addAll(selectableLines2.getSelectableLines());
        }
        return arrayList;
    }

    private final List<SelectableWord> getSelectableWords(List<SelectableBlock> list) {
        ArrayList arrayList = new ArrayList();
        for (SelectableBlock selectableLines2 : list) {
            for (SelectableLine selectableWords2 : selectableLines2.getSelectableLines()) {
                arrayList.addAll(selectableWords2.getSelectableWords());
            }
        }
        return arrayList;
    }

    private final boolean isLineParallel(SelectableLine selectableLine, SelectableLine selectableLine2) {
        Point[] pointArr = {selectableLine.getPoly()[3], selectableLine.getPoly()[2]};
        Point[] pointArr2 = {selectableLine2.getPoly()[0], selectableLine2.getPoly()[1]};
        VectorUtil vectorUtil = VectorUtil.INSTANCE;
        int calcDistanceFromPointToPoint = vectorUtil.calcDistanceFromPointToPoint(selectableLine.getPoly()[0], selectableLine.getPoly()[3]);
        int calcDistanceFromPointToPoint2 = vectorUtil.calcDistanceFromPointToPoint(selectableLine.getPoly()[1], selectableLine.getPoly()[2]);
        if (vectorUtil.isTilted(selectableLine.getPoly(), 5.0f) || vectorUtil.calcAngleBetweenLines(pointArr, pointArr2) >= 5 || ((double) Math.abs(pointArr[0].y - pointArr2[0].y)) >= ((double) calcDistanceFromPointToPoint) * 0.3d || ((double) Math.abs(pointArr[1].y - pointArr2[1].y)) >= ((double) calcDistanceFromPointToPoint2) * 0.3d) {
            return false;
        }
        return true;
    }

    public final String getContentDescription() {
        StringBuilder sb2 = new StringBuilder();
        for (SelectableWord data : this.selectableWords) {
            sb2.append(data.getData());
        }
        String sb3 = sb2.toString();
        j.d(sb3, "toString(...)");
        return n.R0(sb3).toString();
    }

    public final SelectableLine getLineContainingCharacter(SelectableCharacter selectableCharacter) {
        j.e(selectableCharacter, "char");
        for (SelectableLine next : this.selectableLines) {
            if (next.getSelectableCharacters().contains(selectableCharacter)) {
                return next;
            }
        }
        return (SelectableLine) C1194l.L0(this.selectableLines);
    }

    public final List<SelectableBlock> getSelectableBlocks() {
        return this.selectableBlocks;
    }

    public final List<SelectableCharacter> getSelectableCharacters() {
        return this.selectableCharacters;
    }

    public final List<SelectableEntity> getSelectableEntities() {
        return this.selectableEntities;
    }

    public final SelectableWord getWordContainingCharacter(SelectableCharacter selectableCharacter) {
        j.e(selectableCharacter, "char");
        for (SelectableWord next : this.selectableWords) {
            if (next.getSelectableCharacters().contains(selectableCharacter)) {
                return next;
            }
        }
        return (SelectableWord) C1194l.L0(this.selectableWords);
    }

    private final List<SelectableCharacter> getSelectableCharacters(List<SelectableBlock> list) {
        ArrayList arrayList = new ArrayList();
        for (SelectableBlock selectableLines2 : list) {
            for (SelectableLine selectableWords2 : selectableLines2.getSelectableLines()) {
                for (SelectableWord selectableCharacters2 : selectableWords2.getSelectableWords()) {
                    arrayList.addAll(selectableCharacters2.getSelectableCharacters());
                }
            }
        }
        return arrayList;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0033, code lost:
        r4 = createSelectableEntities(r4, r5, r6, r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SelectableOcrResult(com.samsung.android.app.sdk.deepsky.textextraction.result.TextData r4, float r5, android.graphics.Point r6) {
        /*
            r3 = this;
            java.lang.String r0 = "textData"
            kotlin.jvm.internal.j.e(r4, r0)
            java.lang.String r0 = "centerOffset"
            kotlin.jvm.internal.j.e(r6, r0)
            r3.<init>()
            com.samsung.android.app.sdk.deepsky.textextraction.result.OcrData r0 = r4.getOcrData()
            java.util.List r0 = r3.createSelectableBlocks(r0, r5, r6)
            r3.selectableBlocks = r0
            java.util.List r1 = r3.getSelectableLines(r0)
            r3.selectableLines = r1
            java.util.List r1 = r3.getSelectableWords(r0)
            r3.selectableWords = r1
            java.util.List r0 = r3.getSelectableCharacters(r0)
            r3.selectableCharacters = r0
            r3.adjustSelectableLines()
            com.samsung.android.app.sdk.deepsky.textextraction.result.EntityData r4 = r4.getEntityData()
            if (r4 == 0) goto L_0x0039
            java.util.List r4 = r3.createSelectableEntities(r4, r5, r6, r0)
            if (r4 != 0) goto L_0x003b
        L_0x0039:
            ne.t r4 = ne.C1202t.d
        L_0x003b:
            r3.selectableEntities = r4
            r3.adjustSelectableRegions()
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.Iterator r4 = r1.iterator()
            r5 = 0
            r6 = r5
        L_0x0048:
            boolean r0 = r4.hasNext()
            r1 = 0
            if (r0 == 0) goto L_0x0062
            java.lang.Object r0 = r4.next()
            int r2 = r6 + 1
            if (r6 < 0) goto L_0x005e
            com.samsung.android.app.sdk.deepsky.textextraction.selectionui.data.SelectableWord r0 = (com.samsung.android.app.sdk.deepsky.textextraction.selectionui.data.SelectableWord) r0
            r0.setIndex(r6)
            r6 = r2
            goto L_0x0048
        L_0x005e:
            ne.C1195m.v0()
            throw r1
        L_0x0062:
            java.util.List<com.samsung.android.app.sdk.deepsky.textextraction.selectionui.data.SelectableCharacter> r3 = r3.selectableCharacters
            java.lang.Iterable r3 = (java.lang.Iterable) r3
            java.util.Iterator r3 = r3.iterator()
        L_0x006a:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x0083
            java.lang.Object r4 = r3.next()
            int r6 = r5 + 1
            if (r5 < 0) goto L_0x007f
            com.samsung.android.app.sdk.deepsky.textextraction.selectionui.data.SelectableCharacter r4 = (com.samsung.android.app.sdk.deepsky.textextraction.selectionui.data.SelectableCharacter) r4
            r4.setIndex(r5)
            r5 = r6
            goto L_0x006a
        L_0x007f:
            ne.C1195m.v0()
            throw r1
        L_0x0083:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.textextraction.selectionui.data.SelectableOcrResult.<init>(com.samsung.android.app.sdk.deepsky.textextraction.result.TextData, float, android.graphics.Point):void");
    }
}
