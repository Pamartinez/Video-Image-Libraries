package com.samsung.android.app.sdk.deepsky.textextraction.selectionui.textselection;

import Tf.n;
import Tf.w;
import Z8.c;
import android.graphics.Point;
import android.graphics.Rect;
import com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger;
import com.samsung.android.app.sdk.deepsky.textextraction.result.TextData;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.data.SelectableBlock;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.data.SelectableCharacter;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.data.SelectableEntity;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.data.SelectableLine;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.data.SelectableOcrResult;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.data.SelectableWord;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.helper.VibrationHelper;
import com.samsung.android.app.sdk.deepsky.textextraction.util.PointUtil;
import com.samsung.android.app.sdk.deepsky.textextraction.util.VectorUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1194l;
import ne.C1195m;
import ne.C1196n;
import ne.C1202t;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0000\u0018\u0000 s2\u00020\u0001:\u0001sB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J%\u0010\r\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\r\u0010\u000eJ\r\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0010\u0010\u0011J\r\u0010\u0012\u001a\u00020\u000f¢\u0006\u0004\b\u0012\u0010\u0011J\r\u0010\u0013\u001a\u00020\f¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0016\u001a\u0004\u0018\u00010\u0015¢\u0006\u0004\b\u0016\u0010\u0017J\u001d\u0010\u001b\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u0018¢\u0006\u0004\b\u001b\u0010\u001cJ\u0013\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00180\u001d¢\u0006\u0004\b\u001e\u0010\u001fJ\r\u0010!\u001a\u00020 ¢\u0006\u0004\b!\u0010\"J\r\u0010#\u001a\u00020\u0018¢\u0006\u0004\b#\u0010$J\r\u0010%\u001a\u00020\u0018¢\u0006\u0004\b%\u0010$J\r\u0010'\u001a\u00020&¢\u0006\u0004\b'\u0010(J\u0015\u0010*\u001a\u00020\f2\u0006\u0010)\u001a\u00020\u000f¢\u0006\u0004\b*\u0010+J\u0019\u0010-\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0,0\u001d¢\u0006\u0004\b-\u0010\u001fJ\r\u0010.\u001a\u00020\f¢\u0006\u0004\b.\u0010\u0014J\r\u0010/\u001a\u00020\f¢\u0006\u0004\b/\u0010\u0014J\r\u00100\u001a\u00020&¢\u0006\u0004\b0\u0010(J\r\u00101\u001a\u00020&¢\u0006\u0004\b1\u0010(J\r\u00102\u001a\u00020&¢\u0006\u0004\b2\u0010(J\r\u00103\u001a\u00020&¢\u0006\u0004\b3\u0010(J\u001d\u00107\u001a\u00020\u000f2\u0006\u00105\u001a\u0002042\u0006\u00106\u001a\u000204¢\u0006\u0004\b7\u00108J\u0017\u0010:\u001a\u0004\u0018\u00010\u00182\u0006\u00109\u001a\u00020\n¢\u0006\u0004\b:\u0010;J\u0017\u0010<\u001a\u0004\u0018\u00010\u00182\u0006\u00109\u001a\u00020\n¢\u0006\u0004\b<\u0010;J\u001f\u0010\u001b\u001a\u00020\f2\u0006\u0010=\u001a\u0002042\u0006\u0010>\u001a\u000204H\u0002¢\u0006\u0004\b\u001b\u0010?J\u0013\u0010\u001b\u001a\u00020\f*\u00020@H\u0002¢\u0006\u0004\b\u001b\u0010AJ\u0013\u0010\u001b\u001a\u00020\f*\u00020BH\u0002¢\u0006\u0004\b\u001b\u0010CJ\u000f\u0010D\u001a\u00020&H\u0002¢\u0006\u0004\bD\u0010(J\u001f\u0010G\u001a\u00020&2\u0006\u0010E\u001a\u00020&2\u0006\u0010F\u001a\u00020&H\u0002¢\u0006\u0004\bG\u0010HJ\u001f\u0010I\u001a\u00020\u000f2\u0006\u00105\u001a\u0002042\u0006\u00106\u001a\u000204H\u0002¢\u0006\u0004\bI\u00108J\u001f\u0010J\u001a\u00020\u000f2\u0006\u00105\u001a\u0002042\u0006\u00106\u001a\u000204H\u0002¢\u0006\u0004\bJ\u00108J\u0019\u0010K\u001a\u0004\u0018\u00010@2\u0006\u00109\u001a\u00020\nH\u0002¢\u0006\u0004\bK\u0010LJ\u0019\u0010M\u001a\u0004\u0018\u00010B2\u0006\u00109\u001a\u00020\nH\u0002¢\u0006\u0004\bM\u0010NJ\u0019\u0010O\u001a\u0004\u0018\u00010B2\u0006\u00109\u001a\u00020\nH\u0002¢\u0006\u0004\bO\u0010NJ\u0019\u0010Q\u001a\u0004\u0018\u00010P2\u0006\u00109\u001a\u00020\nH\u0002¢\u0006\u0004\bQ\u0010RJ!\u0010T\u001a\u0004\u0018\u00010\u00152\u0006\u0010S\u001a\u00020P2\u0006\u00109\u001a\u00020\nH\u0002¢\u0006\u0004\bT\u0010UJ!\u0010V\u001a\u0004\u0018\u00010\u00152\u0006\u0010S\u001a\u00020P2\u0006\u00109\u001a\u00020\nH\u0002¢\u0006\u0004\bV\u0010UJ!\u0010X\u001a\u0004\u0018\u00010B2\u0006\u0010W\u001a\u00020\u00152\u0006\u00109\u001a\u00020\nH\u0002¢\u0006\u0004\bX\u0010YJ!\u0010[\u001a\u0004\u0018\u00010\u00182\u0006\u0010Z\u001a\u00020B2\u0006\u00109\u001a\u00020\nH\u0002¢\u0006\u0004\b[\u0010\\R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010]R\u001c\u0010^\u001a\b\u0012\u0004\u0012\u00020\u00180\u001d8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b^\u0010_R$\u0010`\u001a\u0004\u0018\u00010B8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b`\u0010a\u001a\u0004\bb\u0010c\"\u0004\bd\u0010CR$\u0010g\u001a\u00020e2\u0006\u0010f\u001a\u00020e8\u0006@BX\u000e¢\u0006\f\n\u0004\bg\u0010h\u001a\u0004\bi\u0010jR$\u0010k\u001a\u0004\u0018\u00010\u00188\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\bk\u0010l\u001a\u0004\bm\u0010$\"\u0004\bn\u0010oR$\u0010p\u001a\u00020&2\u0006\u0010f\u001a\u00020&8\u0006@BX\u000e¢\u0006\f\n\u0004\bp\u0010q\u001a\u0004\br\u0010(¨\u0006t"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/textselection/TextSelectionHelper;", "", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/helper/VibrationHelper;", "vibrationHelper", "<init>", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/helper/VibrationHelper;)V", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/TextData;", "textData", "", "ratio", "Landroid/graphics/Point;", "centerOffset", "Lme/x;", "updateSelectableData", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/result/TextData;FLandroid/graphics/Point;)V", "", "isTextSelected", "()Z", "isAllTextSelected", "selectAll", "()V", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableLine;", "getLastSelectedLine", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableLine;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableCharacter;", "from", "to", "selectCharacters", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableCharacter;Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableCharacter;)V", "", "getSelectedCharacters", "()Ljava/util/List;", "Landroid/graphics/Rect;", "getBlockBoundingRect", "()Landroid/graphics/Rect;", "findStartCharFromSelection", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableCharacter;", "findEndCharFromSelection", "", "getContentDescription", "()Ljava/lang/String;", "isStartHandle", "updateLastSelectedChar", "(Z)V", "", "makeHighlightPolyPerBlock", "clearAllSelection", "updateSelectedTextData", "getSelectedTextForTextClassification", "getSelectedTextForActionMode", "getLeftAdjacentStringFromSelection", "getRightAdjacentStringFromSelection", "", "x", "y", "startNewTextSelection", "(II)Z", "point", "findTouchedCharacter", "(Landroid/graphics/Point;)Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableCharacter;", "findNearestCharacter", "startIndexInclusive", "endIndexExclusive", "(II)V", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableEntity;", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableEntity;)V", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableWord;", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableWord;)V", "getSelectedTextForTextSelection", "lineSeparator", "blockSeparator", "getSelectedTextWithSeparator", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "setNewSelectedEntity", "setNewSelectedWord", "findTouchedEntity", "(Landroid/graphics/Point;)Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableEntity;", "findTouchedWord", "(Landroid/graphics/Point;)Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableWord;", "findNearestWord", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableBlock;", "findNearestBlock", "(Landroid/graphics/Point;)Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableBlock;", "block", "findNearestLineInBlock", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableBlock;Landroid/graphics/Point;)Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableLine;", "findNearestLineInBlockLineByLine", "line", "findNearestWordInLine", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableLine;Landroid/graphics/Point;)Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableWord;", "word", "findNearestCharacterInWord", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableWord;Landroid/graphics/Point;)Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableCharacter;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/helper/VibrationHelper;", "selectableChars", "Ljava/util/List;", "selectedWord", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableWord;", "getSelectedWord", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableWord;", "setSelectedWord", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableOcrResult;", "value", "selectableOcrResult", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableOcrResult;", "getSelectableOcrResult", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableOcrResult;", "lastSelectedChar", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableCharacter;", "getLastSelectedChar", "setLastSelectedChar", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableCharacter;)V", "selectedText", "Ljava/lang/String;", "getSelectedText", "Companion", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TextSelectionHelper {
    public static final Companion Companion = new Companion((e) null);
    private SelectableCharacter lastSelectedChar;
    private List<SelectableCharacter> selectableChars = C1202t.d;
    private SelectableOcrResult selectableOcrResult = new SelectableOcrResult();
    private String selectedText = "";
    private SelectableWord selectedWord;
    private final VibrationHelper vibrationHelper;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/textselection/TextSelectionHelper$Companion;", "", "<init>", "()V", "TAG", "", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public TextSelectionHelper(VibrationHelper vibrationHelper2) {
        j.e(vibrationHelper2, "vibrationHelper");
        this.vibrationHelper = vibrationHelper2;
    }

    private final SelectableBlock findNearestBlock(Point point) {
        float f = Float.MAX_VALUE;
        SelectableBlock selectableBlock = null;
        for (SelectableBlock next : this.selectableOcrResult.getSelectableBlocks()) {
            float calcDistanceFromPointToPoly = VectorUtil.INSTANCE.calcDistanceFromPointToPoly(point, next.getPoly());
            if (calcDistanceFromPointToPoly < f) {
                selectableBlock = next;
                f = calcDistanceFromPointToPoly;
            }
        }
        return selectableBlock;
    }

    private final SelectableCharacter findNearestCharacterInWord(SelectableWord selectableWord, Point point) {
        float f = Float.MAX_VALUE;
        SelectableCharacter selectableCharacter = null;
        for (SelectableCharacter next : selectableWord.getSelectableCharacters()) {
            float calcDistanceFromPointToPoly = VectorUtil.INSTANCE.calcDistanceFromPointToPoly(point, next.getPoly());
            if (calcDistanceFromPointToPoly < f) {
                selectableCharacter = next;
                f = calcDistanceFromPointToPoly;
            }
        }
        return selectableCharacter;
    }

    private final SelectableLine findNearestLineInBlock(SelectableBlock selectableBlock, Point point) {
        float f = Float.MAX_VALUE;
        SelectableLine selectableLine = null;
        for (SelectableLine next : selectableBlock.getSelectableLines()) {
            float calcDistanceFromPointToPoly = VectorUtil.INSTANCE.calcDistanceFromPointToPoly(point, next.getPoly());
            if (calcDistanceFromPointToPoly < f) {
                selectableLine = next;
                f = calcDistanceFromPointToPoly;
            }
        }
        return selectableLine;
    }

    private final SelectableLine findNearestLineInBlockLineByLine(SelectableBlock selectableBlock, Point point) {
        return (SelectableLine) C1194l.N0(C1194l.g1(selectableBlock.getSelectableLines(), new c(7, new w(6, point))));
    }

    /* access modifiers changed from: private */
    public static final int findNearestLineInBlockLineByLine$lambda$30(Point point, SelectableLine selectableLine, SelectableLine selectableLine2) {
        VectorUtil vectorUtil = VectorUtil.INSTANCE;
        float calcVerticalDistanceFromPointToPoly = vectorUtil.calcVerticalDistanceFromPointToPoly(point, selectableLine.getPoly());
        float calcVerticalDistanceFromPointToPoly2 = vectorUtil.calcVerticalDistanceFromPointToPoly(point, selectableLine2.getPoly());
        if (calcVerticalDistanceFromPointToPoly == calcVerticalDistanceFromPointToPoly2) {
            return Float.compare(vectorUtil.calcHorizontalDistanceFromPointToPoly(point, selectableLine.getPoly()), vectorUtil.calcHorizontalDistanceFromPointToPoly(point, selectableLine2.getPoly()));
        }
        return Float.compare(calcVerticalDistanceFromPointToPoly, calcVerticalDistanceFromPointToPoly2);
    }

    /* access modifiers changed from: private */
    public static final int findNearestLineInBlockLineByLine$lambda$31(Ae.c cVar, Object obj, Object obj2) {
        return ((Number) cVar.invoke(obj, obj2)).intValue();
    }

    private final SelectableWord findNearestWord(Point point) {
        SelectableLine selectableLine;
        SelectableBlock findNearestBlock = findNearestBlock(point);
        if (findNearestBlock != null) {
            selectableLine = findNearestLineInBlockLineByLine(findNearestBlock, point);
        } else {
            selectableLine = null;
        }
        if (selectableLine != null) {
            return findNearestWordInLine(selectableLine, point);
        }
        return null;
    }

    private final SelectableWord findNearestWordInLine(SelectableLine selectableLine, Point point) {
        float f = Float.MAX_VALUE;
        SelectableWord selectableWord = null;
        for (SelectableWord next : selectableLine.getSelectableWords()) {
            float calcDistanceFromPointToPoly = VectorUtil.INSTANCE.calcDistanceFromPointToPoly(point, next.getPoly());
            if (calcDistanceFromPointToPoly < f && n.R0(next.getData()).toString().length() > 0) {
                selectableWord = next;
                f = calcDistanceFromPointToPoly;
            }
        }
        return selectableWord;
    }

    private final SelectableEntity findTouchedEntity(Point point) {
        for (SelectableEntity next : this.selectableOcrResult.getSelectableEntities()) {
            Iterator<Point[]> it = next.getPolyList().iterator();
            while (true) {
                if (it.hasNext()) {
                    if (PointUtil.INSTANCE.isPointInsidePoly(point, it.next())) {
                        return next;
                    }
                }
            }
        }
        return null;
    }

    private final SelectableWord findTouchedWord(Point point) {
        SelectableLine selectableLine;
        SelectableBlock findNearestBlock = findNearestBlock(point);
        if (findNearestBlock != null) {
            selectableLine = findNearestLineInBlock(findNearestBlock, point);
        } else {
            selectableLine = null;
        }
        if (selectableLine != null) {
            for (SelectableWord next : selectableLine.getSelectableWords()) {
                if (PointUtil.INSTANCE.isPointInsidePoly(point, next.getPoly())) {
                    if (n.R0(next.getData()).toString().length() == 0) {
                        return findNearestWord(point);
                    }
                    return next;
                }
            }
        }
        return null;
    }

    private final String getSelectedTextForTextSelection() {
        return getSelectedTextWithSeparator("\n", "\n");
    }

    private final String getSelectedTextWithSeparator(String str, String str2) {
        StringBuilder sb2 = new StringBuilder();
        boolean z = false;
        for (SelectableBlock selectableBlock : this.selectableOcrResult.getSelectableBlocks()) {
            int i2 = 0;
            for (Object next : selectableBlock.getSelectableLines()) {
                int i7 = i2 + 1;
                if (i2 >= 0) {
                    for (SelectableWord selectableCharacters : ((SelectableLine) next).getSelectableWords()) {
                        for (SelectableCharacter selectableCharacter : selectableCharacters.getSelectableCharacters()) {
                            if (selectableCharacter.isSelected()) {
                                if (!z) {
                                    z = true;
                                }
                                sb2.append(selectableCharacter.getData());
                            }
                            if (!selectableCharacter.isSelected() && z) {
                                z = false;
                            }
                        }
                    }
                    if (z && i2 < selectableBlock.getSelectableLines().size() - 1) {
                        sb2.append(str);
                    }
                    i2 = i7;
                } else {
                    C1195m.v0();
                    throw null;
                }
            }
            if (z) {
                sb2.append(str2);
            }
        }
        String sb3 = sb2.toString();
        j.d(sb3, "toString(...)");
        return n.R0(sb3).toString();
    }

    private final void selectCharacters(int i2, int i7) {
        while (i2 < i7) {
            this.selectableChars.get(i2).setSelected(true);
            i2++;
        }
    }

    private final boolean setNewSelectedEntity(int i2, int i7) {
        if (this.selectableOcrResult.getSelectableBlocks().isEmpty()) {
            LibLogger.w("TextSelectionHelper", "setNewSelectedEntity - no selectable blocks");
            return false;
        }
        SelectableEntity findTouchedEntity = findTouchedEntity(new Point(i2, i7));
        SelectableWord selectableWord = null;
        if (findTouchedEntity != null) {
            clearAllSelection();
            selectCharacters(findTouchedEntity);
            this.lastSelectedChar = (SelectableCharacter) C1194l.L0(findTouchedEntity.getSelectableCharacters());
        } else {
            findTouchedEntity = null;
        }
        if (findTouchedEntity != null) {
            selectableWord = this.selectableOcrResult.getWordContainingCharacter((SelectableCharacter) C1194l.L0(findTouchedEntity.getSelectableCharacters()));
        }
        this.selectedWord = selectableWord;
        updateSelectedTextData();
        if (findTouchedEntity != null) {
            return true;
        }
        return false;
    }

    private final boolean setNewSelectedWord(int i2, int i7) {
        if (this.selectableOcrResult.getSelectableBlocks().isEmpty()) {
            LibLogger.w("TextSelectionHelper", "setNewSelectedWord - no selectable blocks");
            return false;
        }
        SelectableWord findTouchedWord = findTouchedWord(new Point(i2, i7));
        if (findTouchedWord != null) {
            clearAllSelection();
            selectCharacters(findTouchedWord);
            this.lastSelectedChar = (SelectableCharacter) C1194l.L0(findTouchedWord.getSelectableCharacters());
        } else {
            findTouchedWord = null;
        }
        this.selectedWord = findTouchedWord;
        updateSelectedTextData();
        if (findTouchedWord != null) {
            return true;
        }
        return false;
    }

    public final void clearAllSelection() {
        for (SelectableCharacter selected : this.selectableChars) {
            selected.setSelected(false);
        }
    }

    public final SelectableCharacter findEndCharFromSelection() {
        SelectableCharacter selectableCharacter;
        List<SelectableCharacter> list = this.selectableChars;
        ListIterator<SelectableCharacter> listIterator = list.listIterator(list.size());
        while (true) {
            if (!listIterator.hasPrevious()) {
                selectableCharacter = null;
                break;
            }
            selectableCharacter = listIterator.previous();
            if (selectableCharacter.isSelected()) {
                break;
            }
        }
        SelectableCharacter selectableCharacter2 = selectableCharacter;
        if (selectableCharacter2 == null) {
            return SelectableOcrResult.Companion.getEMPTY_CHARACTER();
        }
        return selectableCharacter2;
    }

    public final SelectableCharacter findNearestCharacter(Point point) {
        j.e(point, "point");
        SelectableWord findNearestWord = findNearestWord(point);
        if (findNearestWord != null) {
            return findNearestCharacterInWord(findNearestWord, point);
        }
        return null;
    }

    public final SelectableCharacter findStartCharFromSelection() {
        Object obj;
        Iterator it = this.selectableChars.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (((SelectableCharacter) obj).isSelected()) {
                break;
            }
        }
        SelectableCharacter selectableCharacter = (SelectableCharacter) obj;
        if (selectableCharacter == null) {
            return SelectableOcrResult.Companion.getEMPTY_CHARACTER();
        }
        return selectableCharacter;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: com.samsung.android.app.sdk.deepsky.textextraction.selectionui.data.SelectableCharacter} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: com.samsung.android.app.sdk.deepsky.textextraction.selectionui.data.SelectableCharacter} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v0, resolved type: com.samsung.android.app.sdk.deepsky.textextraction.selectionui.data.SelectableCharacter} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: com.samsung.android.app.sdk.deepsky.textextraction.selectionui.data.SelectableCharacter} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.samsung.android.app.sdk.deepsky.textextraction.selectionui.data.SelectableCharacter findTouchedCharacter(android.graphics.Point r5) {
        /*
            r4 = this;
            java.lang.String r0 = "point"
            kotlin.jvm.internal.j.e(r5, r0)
            com.samsung.android.app.sdk.deepsky.textextraction.selectionui.data.SelectableWord r4 = r4.findTouchedWord(r5)
            r0 = 0
            if (r4 == 0) goto L_0x0032
            java.util.List r4 = r4.getSelectableCharacters()
            java.lang.Iterable r4 = (java.lang.Iterable) r4
            java.util.Iterator r4 = r4.iterator()
        L_0x0016:
            boolean r1 = r4.hasNext()
            if (r1 == 0) goto L_0x0030
            java.lang.Object r1 = r4.next()
            r2 = r1
            com.samsung.android.app.sdk.deepsky.textextraction.selectionui.data.SelectableCharacter r2 = (com.samsung.android.app.sdk.deepsky.textextraction.selectionui.data.SelectableCharacter) r2
            com.samsung.android.app.sdk.deepsky.textextraction.util.PointUtil r3 = com.samsung.android.app.sdk.deepsky.textextraction.util.PointUtil.INSTANCE
            android.graphics.Point[] r2 = r2.getPoly()
            boolean r2 = r3.isPointInsidePoly(r5, r2)
            if (r2 == 0) goto L_0x0016
            r0 = r1
        L_0x0030:
            com.samsung.android.app.sdk.deepsky.textextraction.selectionui.data.SelectableCharacter r0 = (com.samsung.android.app.sdk.deepsky.textextraction.selectionui.data.SelectableCharacter) r0
        L_0x0032:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.textextraction.selectionui.textselection.TextSelectionHelper.findTouchedCharacter(android.graphics.Point):com.samsung.android.app.sdk.deepsky.textextraction.selectionui.data.SelectableCharacter");
    }

    public final Rect getBlockBoundingRect() {
        VectorUtil vectorUtil = VectorUtil.INSTANCE;
        Iterable<SelectableBlock> selectableBlocks = this.selectableOcrResult.getSelectableBlocks();
        ArrayList arrayList = new ArrayList(C1196n.w0(selectableBlocks, 10));
        for (SelectableBlock poly : selectableBlocks) {
            arrayList.add(poly.getPoly());
        }
        return vectorUtil.calcBoundingRect(arrayList);
    }

    public final String getContentDescription() {
        return this.selectableOcrResult.getContentDescription();
    }

    public final SelectableLine getLastSelectedLine() {
        SelectableCharacter selectableCharacter = this.lastSelectedChar;
        if (selectableCharacter != null) {
            return this.selectableOcrResult.getLineContainingCharacter(selectableCharacter);
        }
        return null;
    }

    public final String getLeftAdjacentStringFromSelection() {
        if (!isTextSelected()) {
            return "";
        }
        StringBuilder sb2 = new StringBuilder();
        int index = findStartCharFromSelection().getIndex();
        for (int i2 = 0; i2 < index; i2++) {
            sb2.append(this.selectableChars.get(i2).getData());
        }
        String sb3 = sb2.toString();
        j.d(sb3, "toString(...)");
        return n.R0(sb3).toString();
    }

    public final String getRightAdjacentStringFromSelection() {
        if (!isTextSelected()) {
            return "";
        }
        StringBuilder sb2 = new StringBuilder();
        int size = this.selectableChars.size();
        for (int index = findEndCharFromSelection().getIndex() + 1; index < size; index++) {
            sb2.append(this.selectableChars.get(index).getData());
        }
        String sb3 = sb2.toString();
        j.d(sb3, "toString(...)");
        return n.R0(sb3).toString();
    }

    public final SelectableOcrResult getSelectableOcrResult() {
        return this.selectableOcrResult;
    }

    public final List<SelectableCharacter> getSelectedCharacters() {
        ArrayList arrayList = new ArrayList();
        for (Object next : this.selectableChars) {
            if (((SelectableCharacter) next).isSelected()) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public final String getSelectedText() {
        return this.selectedText;
    }

    public final String getSelectedTextForActionMode() {
        return getSelectedTextWithSeparator(" ", "\n");
    }

    public final String getSelectedTextForTextClassification() {
        return getSelectedTextWithSeparator("\n", "\n\n");
    }

    public final SelectableWord getSelectedWord() {
        return this.selectedWord;
    }

    public final boolean isAllTextSelected() {
        Iterable<SelectableCharacter> iterable = this.selectableChars;
        if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
            return true;
        }
        for (SelectableCharacter isSelected : iterable) {
            if (!isSelected.isSelected()) {
                return false;
            }
        }
        return true;
    }

    public final boolean isTextSelected() {
        Iterable<SelectableCharacter> iterable = this.selectableChars;
        if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
            return false;
        }
        for (SelectableCharacter isSelected : iterable) {
            if (isSelected.isSelected()) {
                return true;
            }
        }
        return false;
    }

    public final List<Point[]> makeHighlightPolyPerBlock() {
        Iterable<SelectableBlock> selectableBlocks = this.selectableOcrResult.getSelectableBlocks();
        ArrayList arrayList = new ArrayList(C1196n.w0(selectableBlocks, 10));
        for (SelectableBlock poly : selectableBlocks) {
            arrayList.add(poly.getPoly());
        }
        return arrayList;
    }

    public final void selectAll() {
        for (SelectableCharacter selected : this.selectableChars) {
            selected.setSelected(true);
        }
        updateLastSelectedChar(true);
    }

    public final void setLastSelectedChar(SelectableCharacter selectableCharacter) {
        this.lastSelectedChar = selectableCharacter;
    }

    public final boolean startNewTextSelection(int i2, int i7) {
        if (setNewSelectedEntity(i2, i7) || setNewSelectedWord(i2, i7)) {
            return true;
        }
        return false;
    }

    public final void updateLastSelectedChar(boolean z) {
        SelectableCharacter selectableCharacter;
        if (z) {
            selectableCharacter = findStartCharFromSelection();
        } else {
            selectableCharacter = findEndCharFromSelection();
        }
        this.lastSelectedChar = selectableCharacter;
    }

    public final void updateSelectableData(TextData textData, float f, Point point) {
        j.e(textData, "textData");
        j.e(point, "centerOffset");
        SelectableOcrResult selectableOcrResult2 = new SelectableOcrResult(textData, f, point);
        this.selectableOcrResult = selectableOcrResult2;
        this.selectableChars = selectableOcrResult2.getSelectableCharacters();
    }

    public final void updateSelectedTextData() {
        String str = this.selectedText;
        String selectedTextForTextSelection = getSelectedTextForTextSelection();
        this.selectedText = selectedTextForTextSelection;
        if (!j.a(str, selectedTextForTextSelection) && str.length() > 0 && this.selectedText.length() > 0) {
            this.vibrationHelper.hapticFeedback();
        }
    }

    public final void selectCharacters(SelectableCharacter selectableCharacter, SelectableCharacter selectableCharacter2) {
        j.e(selectableCharacter, "from");
        j.e(selectableCharacter2, "to");
        selectCharacters(selectableCharacter.getIndex(), selectableCharacter2.getIndex() + 1);
    }

    private final void selectCharacters(SelectableEntity selectableEntity) {
        selectCharacters((SelectableCharacter) C1194l.L0(selectableEntity.getSelectableCharacters()), (SelectableCharacter) C1194l.T0(selectableEntity.getSelectableCharacters()));
    }

    private final void selectCharacters(SelectableWord selectableWord) {
        selectCharacters((SelectableCharacter) C1194l.L0(selectableWord.getSelectableCharacters()), (SelectableCharacter) C1194l.T0(selectableWord.getSelectableCharacters()));
    }
}
