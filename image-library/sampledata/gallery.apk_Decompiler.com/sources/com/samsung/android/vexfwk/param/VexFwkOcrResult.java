package com.samsung.android.vexfwk.param;

import Tf.n;
import android.graphics.Point;
import android.graphics.Rect;
import com.samsung.android.vexfwk.param.VexFwkOcrAdditionalMeta;
import com.samsung.android.vexfwk.param.VexFwkOcrResultMeta;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1196n;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001:\u0007%&'()*+B1\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nB\u0011\b\u0016\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\t\u0010\rB\u0019\b\u0016\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0004\b\t\u0010\u0010J\u0013\u0010\u001a\u001a\u00020\b2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001H\u0002R \u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R \u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0012\"\u0004\b\u0016\u0010\u0014R\u001a\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u00038F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u0012R\u0017\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\u00038F¢\u0006\u0006\u001a\u0004\b!\u0010\u0012R\u0017\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\u00038F¢\u0006\u0006\u001a\u0004\b$\u0010\u0012¨\u0006,"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkOcrResult;", "", "blockInfoList", "", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResult$BlockInfo;", "tableInfoList", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResult$TableInfo;", "isHandwrittenResult", "", "<init>", "(Ljava/util/List;Ljava/util/List;Z)V", "ocrResultMeta", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResultMeta;", "(Lcom/samsung/android/vexfwk/param/VexFwkOcrResultMeta;)V", "additionalMeta", "Lcom/samsung/android/vexfwk/param/VexFwkOcrAdditionalMeta;", "(Lcom/samsung/android/vexfwk/param/VexFwkOcrResultMeta;Lcom/samsung/android/vexfwk/param/VexFwkOcrAdditionalMeta;)V", "getBlockInfoList", "()Ljava/util/List;", "setBlockInfoList", "(Ljava/util/List;)V", "getTableInfoList", "setTableInfoList", "()Z", "setHandwrittenResult", "(Z)V", "equals", "other", "charInfoList", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResult$CharInfo;", "getCharInfoList", "wordInfoList", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResult$WordInfo;", "getWordInfoList", "lineInfoList", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResult$LineInfo;", "getLineInfoList", "CharInfo", "WordInfo", "LineInfo", "BlockInfo", "TableCellInfo", "TableRowInfo", "TableInfo", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkOcrResult {
    private List<BlockInfo> blockInfoList;
    private boolean isHandwrittenResult;
    private List<TableInfo> tableInfoList;

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B'\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\t\u0010\nJ\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u0002R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0019\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0015"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkOcrResult$CharInfo;", "", "string", "", "rect", "Landroid/graphics/Rect;", "poly", "", "Landroid/graphics/Point;", "<init>", "(Ljava/lang/String;Landroid/graphics/Rect;[Landroid/graphics/Point;)V", "getString", "()Ljava/lang/String;", "getRect", "()Landroid/graphics/Rect;", "getPoly", "()[Landroid/graphics/Point;", "[Landroid/graphics/Point;", "equals", "", "other", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class CharInfo {
        private final Point[] poly;
        private final Rect rect;
        private final String string;

        public CharInfo(String str, Rect rect2, Point[] pointArr) {
            j.e(rect2, "rect");
            j.e(pointArr, "poly");
            this.string = str;
            this.rect = rect2;
            this.poly = pointArr;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof CharInfo)) {
                return false;
            }
            CharInfo charInfo = (CharInfo) obj;
            if (j.a(this.string, charInfo.string) && j.a(this.rect, charInfo.rect) && Arrays.equals(this.poly, charInfo.poly)) {
                return true;
            }
            return false;
        }

        public final Point[] getPoly() {
            return this.poly;
        }

        public final Rect getRect() {
            return this.rect;
        }

        public final String getString() {
            return this.string;
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001H\u0002R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0010"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkOcrResult$TableInfo;", "", "tableRowInfo", "", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResult$TableRowInfo;", "tableBoundary", "Landroid/graphics/Rect;", "<init>", "(Ljava/util/List;Landroid/graphics/Rect;)V", "getTableRowInfo", "()Ljava/util/List;", "getTableBoundary", "()Landroid/graphics/Rect;", "equals", "", "other", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class TableInfo {
        private final Rect tableBoundary;
        private final List<TableRowInfo> tableRowInfo;

        public TableInfo(List<TableRowInfo> list, Rect rect) {
            j.e(list, "tableRowInfo");
            j.e(rect, "tableBoundary");
            this.tableRowInfo = list;
            this.tableBoundary = rect;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if ((obj instanceof TableInfo) && this.tableRowInfo.size() == ((TableInfo) obj).tableRowInfo.size()) {
                return true;
            }
            return false;
        }

        public final Rect getTableBoundary() {
            return this.tableBoundary;
        }

        public final List<TableRowInfo> getTableRowInfo() {
            return this.tableRowInfo;
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001H\u0002R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\f"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkOcrResult$TableRowInfo;", "", "tableCellInfo", "", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResult$TableCellInfo;", "<init>", "(Ljava/util/List;)V", "getTableCellInfo", "()Ljava/util/List;", "equals", "", "other", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class TableRowInfo {
        private final List<TableCellInfo> tableCellInfo;

        public TableRowInfo(List<TableCellInfo> list) {
            j.e(list, "tableCellInfo");
            this.tableCellInfo = list;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if ((obj instanceof TableRowInfo) && this.tableCellInfo.size() == ((TableRowInfo) obj).tableCellInfo.size()) {
                return true;
            }
            return false;
        }

        public final List<TableCellInfo> getTableCellInfo() {
            return this.tableCellInfo;
        }
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B+\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0004\b\n\u0010\u000bJ\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001H\u0002R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0019\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0013\u001a\u00020\u00148F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u001a"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkOcrResult$WordInfo;", "", "charInfo", "", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResult$CharInfo;", "rect", "Landroid/graphics/Rect;", "poly", "", "Landroid/graphics/Point;", "<init>", "(Ljava/util/List;Landroid/graphics/Rect;[Landroid/graphics/Point;)V", "getCharInfo", "()Ljava/util/List;", "getRect", "()Landroid/graphics/Rect;", "getPoly", "()[Landroid/graphics/Point;", "[Landroid/graphics/Point;", "string", "", "getString", "()Ljava/lang/String;", "equals", "", "other", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class WordInfo {
        private final List<CharInfo> charInfo;
        private final Point[] poly;
        private final Rect rect;

        public WordInfo(List<CharInfo> list, Rect rect2, Point[] pointArr) {
            j.e(list, "charInfo");
            j.e(rect2, "rect");
            j.e(pointArr, "poly");
            this.charInfo = list;
            this.rect = rect2;
            this.poly = pointArr;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof WordInfo)) {
                return false;
            }
            WordInfo wordInfo = (WordInfo) obj;
            if (this.charInfo.size() != wordInfo.charInfo.size()) {
                return false;
            }
            int size = this.charInfo.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (!this.charInfo.get(i2).equals(wordInfo.charInfo.get(i2))) {
                    return false;
                }
            }
            if (j.a(this.rect, wordInfo.rect) && Arrays.equals(this.poly, wordInfo.poly)) {
                return true;
            }
            return false;
        }

        public final List<CharInfo> getCharInfo() {
            return this.charInfo;
        }

        public final Point[] getPoly() {
            return this.poly;
        }

        public final Rect getRect() {
            return this.rect;
        }

        public final String getString() {
            StringBuilder sb2 = new StringBuilder();
            for (CharInfo string : this.charInfo) {
                sb2.append(string.getString());
            }
            String sb3 = sb2.toString();
            j.d(sb3, "toString(...)");
            return n.R0(sb3).toString();
        }
    }

    public VexFwkOcrResult() {
        this((List) null, (List) null, false, 7, (e) null);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VexFwkOcrResult)) {
            return false;
        }
        VexFwkOcrResult vexFwkOcrResult = (VexFwkOcrResult) obj;
        if (this.blockInfoList.size() != vexFwkOcrResult.blockInfoList.size()) {
            return false;
        }
        int size = this.blockInfoList.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (!this.blockInfoList.get(i2).equals(vexFwkOcrResult.blockInfoList.get(i2))) {
                return false;
            }
        }
        return true;
    }

    public final List<BlockInfo> getBlockInfoList() {
        return this.blockInfoList;
    }

    public final List<CharInfo> getCharInfoList() {
        ArrayList arrayList = new ArrayList();
        for (BlockInfo lineInfo : this.blockInfoList) {
            for (LineInfo wordInfo : lineInfo.getLineInfo()) {
                for (WordInfo charInfo : wordInfo.getWordInfo()) {
                    arrayList.addAll(charInfo.getCharInfo());
                }
            }
        }
        return arrayList;
    }

    public final List<LineInfo> getLineInfoList() {
        ArrayList arrayList = new ArrayList();
        for (BlockInfo lineInfo : this.blockInfoList) {
            arrayList.addAll(lineInfo.getLineInfo());
        }
        return arrayList;
    }

    public final List<TableInfo> getTableInfoList() {
        return this.tableInfoList;
    }

    public final List<WordInfo> getWordInfoList() {
        ArrayList arrayList = new ArrayList();
        for (BlockInfo lineInfo : this.blockInfoList) {
            for (LineInfo wordInfo : lineInfo.getLineInfo()) {
                arrayList.addAll(wordInfo.getWordInfo());
            }
        }
        return arrayList;
    }

    public final boolean isHandwrittenResult() {
        return this.isHandwrittenResult;
    }

    public final void setBlockInfoList(List<BlockInfo> list) {
        j.e(list, "<set-?>");
        this.blockInfoList = list;
    }

    public final void setHandwrittenResult(boolean z) {
        this.isHandwrittenResult = z;
    }

    public final void setTableInfoList(List<TableInfo> list) {
        j.e(list, "<set-?>");
        this.tableInfoList = list;
    }

    public VexFwkOcrResult(List<BlockInfo> list, List<TableInfo> list2, boolean z) {
        j.e(list, "blockInfoList");
        j.e(list2, "tableInfoList");
        this.blockInfoList = list;
        this.tableInfoList = list2;
        this.isHandwrittenResult = z;
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u000f"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkOcrResult$TableCellInfo;", "", "cellBoundary", "Landroid/graphics/Rect;", "cellText", "", "<init>", "(Landroid/graphics/Rect;Ljava/lang/String;)V", "getCellBoundary", "()Landroid/graphics/Rect;", "getCellText", "()Ljava/lang/String;", "equals", "", "other", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class TableCellInfo {
        private final Rect cellBoundary;
        private final String cellText;

        public TableCellInfo(Rect rect, String str) {
            j.e(rect, "cellBoundary");
            j.e(str, "cellText");
            this.cellBoundary = rect;
            this.cellText = str;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof TableCellInfo)) {
                return false;
            }
            TableCellInfo tableCellInfo = (TableCellInfo) obj;
            if (j.a(this.cellText, tableCellInfo.cellText) && j.a(this.cellBoundary, tableCellInfo.cellBoundary)) {
                return true;
            }
            return false;
        }

        public final Rect getCellBoundary() {
            return this.cellBoundary;
        }

        public final String getCellText() {
            return this.cellText;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ TableCellInfo(Rect rect, String str, int i2, e eVar) {
            this(rect, (i2 & 2) != 0 ? "" : str);
        }
    }

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B5\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\u0013\u0010\u001c\u001a\u00020\u000b2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001H\u0002R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0019\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0018\u001a\u00020\u00198F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001b¨\u0006\u001e"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkOcrResult$LineInfo;", "", "wordInfo", "", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResult$WordInfo;", "rect", "Landroid/graphics/Rect;", "poly", "", "Landroid/graphics/Point;", "isCurved", "", "<init>", "(Ljava/util/List;Landroid/graphics/Rect;[Landroid/graphics/Point;Z)V", "getWordInfo", "()Ljava/util/List;", "getRect", "()Landroid/graphics/Rect;", "getPoly", "()[Landroid/graphics/Point;", "[Landroid/graphics/Point;", "()Z", "setCurved", "(Z)V", "string", "", "getString", "()Ljava/lang/String;", "equals", "other", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class LineInfo {
        private boolean isCurved;
        private final Point[] poly;
        private final Rect rect;
        private final List<WordInfo> wordInfo;

        public LineInfo(List<WordInfo> list, Rect rect2, Point[] pointArr, boolean z) {
            j.e(list, "wordInfo");
            j.e(rect2, "rect");
            j.e(pointArr, "poly");
            this.wordInfo = list;
            this.rect = rect2;
            this.poly = pointArr;
            this.isCurved = z;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof LineInfo)) {
                return false;
            }
            LineInfo lineInfo = (LineInfo) obj;
            if (this.wordInfo.size() != lineInfo.wordInfo.size()) {
                return false;
            }
            int size = this.wordInfo.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (!this.wordInfo.get(i2).equals(lineInfo.wordInfo.get(i2))) {
                    return false;
                }
            }
            if (j.a(this.rect, lineInfo.rect) && Arrays.equals(this.poly, lineInfo.poly)) {
                return true;
            }
            return false;
        }

        public final Point[] getPoly() {
            return this.poly;
        }

        public final Rect getRect() {
            return this.rect;
        }

        public final String getString() {
            StringBuilder sb2 = new StringBuilder();
            for (WordInfo string : this.wordInfo) {
                sb2.append(string.getString());
                sb2.append(" ");
            }
            String sb3 = sb2.toString();
            j.d(sb3, "toString(...)");
            return n.R0(sb3).toString();
        }

        public final List<WordInfo> getWordInfo() {
            return this.wordInfo;
        }

        public final boolean isCurved() {
            return this.isCurved;
        }

        public final void setCurved(boolean z) {
            this.isCurved = z;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ LineInfo(List list, Rect rect2, Point[] pointArr, boolean z, int i2, e eVar) {
            this(list, rect2, pointArr, (i2 & 8) != 0 ? false : z);
        }
    }

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B?\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001H\u0002R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0019\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0014\u0010\u0015R\u001a\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\f\u001a\u00020\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u0011\u0010\u001e\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u001b¨\u0006#"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkOcrResult$BlockInfo;", "", "lineInfo", "", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResult$LineInfo;", "rect", "Landroid/graphics/Rect;", "poly", "", "Landroid/graphics/Point;", "isTabular", "", "translatedText", "", "<init>", "(Ljava/util/List;Landroid/graphics/Rect;[Landroid/graphics/Point;ILjava/lang/String;)V", "getLineInfo", "()Ljava/util/List;", "getRect", "()Landroid/graphics/Rect;", "getPoly", "()[Landroid/graphics/Point;", "[Landroid/graphics/Point;", "()I", "setTabular", "(I)V", "getTranslatedText", "()Ljava/lang/String;", "setTranslatedText", "(Ljava/lang/String;)V", "string", "getString", "equals", "", "other", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class BlockInfo {
        private int isTabular;
        private final List<LineInfo> lineInfo;
        private final Point[] poly;
        private final Rect rect;
        private String translatedText;

        public BlockInfo(List<LineInfo> list, Rect rect2, Point[] pointArr, int i2, String str) {
            j.e(list, "lineInfo");
            j.e(rect2, "rect");
            j.e(pointArr, "poly");
            j.e(str, "translatedText");
            this.lineInfo = list;
            this.rect = rect2;
            this.poly = pointArr;
            this.isTabular = i2;
            this.translatedText = str;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof BlockInfo)) {
                return false;
            }
            BlockInfo blockInfo = (BlockInfo) obj;
            if (this.lineInfo.size() != blockInfo.lineInfo.size()) {
                return false;
            }
            int size = this.lineInfo.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (!this.lineInfo.get(i2).equals(blockInfo.lineInfo.get(i2))) {
                    return false;
                }
            }
            if (j.a(this.rect, blockInfo.rect) && Arrays.equals(this.poly, blockInfo.poly) && this.isTabular == blockInfo.isTabular) {
                return true;
            }
            return false;
        }

        public final List<LineInfo> getLineInfo() {
            return this.lineInfo;
        }

        public final Point[] getPoly() {
            return this.poly;
        }

        public final Rect getRect() {
            return this.rect;
        }

        public final String getString() {
            StringBuilder sb2 = new StringBuilder();
            for (LineInfo string : this.lineInfo) {
                sb2.append(string.getString());
                sb2.append("\n");
            }
            String sb3 = sb2.toString();
            j.d(sb3, "toString(...)");
            return n.R0(sb3).toString();
        }

        public final String getTranslatedText() {
            return this.translatedText;
        }

        public final int isTabular() {
            return this.isTabular;
        }

        public final void setTabular(int i2) {
            this.isTabular = i2;
        }

        public final void setTranslatedText(String str) {
            j.e(str, "<set-?>");
            this.translatedText = str;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ BlockInfo(List list, Rect rect2, Point[] pointArr, int i2, String str, int i7, e eVar) {
            this(list, rect2, pointArr, (i7 & 8) != 0 ? 0 : i2, (i7 & 16) != 0 ? "" : str);
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ VexFwkOcrResult(java.util.List r2, java.util.List r3, boolean r4, int r5, kotlin.jvm.internal.e r6) {
        /*
            r1 = this;
            r6 = r5 & 1
            ne.t r0 = ne.C1202t.d
            if (r6 == 0) goto L_0x0007
            r2 = r0
        L_0x0007:
            r6 = r5 & 2
            if (r6 == 0) goto L_0x000c
            r3 = r0
        L_0x000c:
            r5 = r5 & 4
            if (r5 == 0) goto L_0x0011
            r4 = 0
        L_0x0011:
            r1.<init>(r2, r3, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.param.VexFwkOcrResult.<init>(java.util.List, java.util.List, boolean, int, kotlin.jvm.internal.e):void");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public VexFwkOcrResult(VexFwkOcrResultMeta vexFwkOcrResultMeta) {
        this((List) null, (List) null, false, 7, (e) null);
        VexFwkOcrResultMeta vexFwkOcrResultMeta2 = vexFwkOcrResultMeta;
        j.e(vexFwkOcrResultMeta2, "ocrResultMeta");
        Iterable<VexFwkOcrResultMeta.BlockInfo> blockInfoList2 = vexFwkOcrResultMeta2.getBlockInfoList();
        ArrayList arrayList = new ArrayList(C1196n.w0(blockInfoList2, 10));
        for (VexFwkOcrResultMeta.BlockInfo blockInfo : blockInfoList2) {
            Iterable<VexFwkOcrResultMeta.LineInfo> lineInfo = blockInfo.getLineInfo();
            ArrayList arrayList2 = new ArrayList(C1196n.w0(lineInfo, 10));
            for (VexFwkOcrResultMeta.LineInfo lineInfo2 : lineInfo) {
                Iterable<VexFwkOcrResultMeta.WordInfo> wordInfo = lineInfo2.getWordInfo();
                ArrayList arrayList3 = new ArrayList(C1196n.w0(wordInfo, 10));
                for (VexFwkOcrResultMeta.WordInfo wordInfo2 : wordInfo) {
                    Iterable<VexFwkOcrResultMeta.CharInfo> charInfo = wordInfo2.getCharInfo();
                    ArrayList arrayList4 = new ArrayList(C1196n.w0(charInfo, 10));
                    for (VexFwkOcrResultMeta.CharInfo charInfo2 : charInfo) {
                        arrayList4.add(new CharInfo(charInfo2.getString(), charInfo2.getRect(), charInfo2.getPoly()));
                    }
                    arrayList3.add(new WordInfo(arrayList4, wordInfo2.getRect(), wordInfo2.getPoly()));
                }
                arrayList2.add(new LineInfo(arrayList3, lineInfo2.getRect(), lineInfo2.getPoly(), false));
            }
            arrayList.add(new BlockInfo(arrayList2, blockInfo.getRect(), blockInfo.getPoly(), blockInfo.isTabular(), blockInfo.getTranslatedText()));
        }
        this.blockInfoList = arrayList;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public VexFwkOcrResult(VexFwkOcrResultMeta vexFwkOcrResultMeta, VexFwkOcrAdditionalMeta vexFwkOcrAdditionalMeta) {
        this((List) null, (List) null, false, 7, (e) null);
        VexFwkOcrResultMeta vexFwkOcrResultMeta2 = vexFwkOcrResultMeta;
        j.e(vexFwkOcrResultMeta2, "ocrResultMeta");
        VexFwkOcrAdditionalMeta vexFwkOcrAdditionalMeta2 = vexFwkOcrAdditionalMeta;
        j.e(vexFwkOcrAdditionalMeta2, "additionalMeta");
        Iterator<Boolean> it = vexFwkOcrAdditionalMeta2.getLineCurveInfo().iterator();
        Iterable blockInfoList2 = vexFwkOcrResultMeta2.getBlockInfoList();
        int i2 = 10;
        ArrayList arrayList = new ArrayList(C1196n.w0(blockInfoList2, 10));
        Iterator it2 = blockInfoList2.iterator();
        while (it2.hasNext()) {
            VexFwkOcrResultMeta.BlockInfo blockInfo = (VexFwkOcrResultMeta.BlockInfo) it2.next();
            Iterable<VexFwkOcrResultMeta.LineInfo> lineInfo = blockInfo.getLineInfo();
            ArrayList arrayList2 = new ArrayList(C1196n.w0(lineInfo, i2));
            for (VexFwkOcrResultMeta.LineInfo lineInfo2 : lineInfo) {
                Iterable<VexFwkOcrResultMeta.WordInfo> wordInfo = lineInfo2.getWordInfo();
                ArrayList arrayList3 = new ArrayList(C1196n.w0(wordInfo, i2));
                for (VexFwkOcrResultMeta.WordInfo wordInfo2 : wordInfo) {
                    Iterable<VexFwkOcrResultMeta.CharInfo> charInfo = wordInfo2.getCharInfo();
                    ArrayList arrayList4 = new ArrayList(C1196n.w0(charInfo, i2));
                    for (VexFwkOcrResultMeta.CharInfo charInfo2 : charInfo) {
                        arrayList4.add(new CharInfo(charInfo2.getString(), charInfo2.getRect(), charInfo2.getPoly()));
                        it = it;
                        it2 = it2;
                    }
                    arrayList3.add(new WordInfo(arrayList4, wordInfo2.getRect(), wordInfo2.getPoly()));
                    it = it;
                    it2 = it2;
                    i2 = 10;
                }
                Iterator<Boolean> it3 = it;
                arrayList2.add(new LineInfo(arrayList3, lineInfo2.getRect(), lineInfo2.getPoly(), it3.next().booleanValue()));
                it = it3;
                it2 = it2;
                i2 = 10;
            }
            Iterator<Boolean> it4 = it;
            Iterator it5 = it2;
            arrayList.add(new BlockInfo(arrayList2, blockInfo.getRect(), blockInfo.getPoly(), blockInfo.isTabular(), blockInfo.getTranslatedText()));
            i2 = 10;
        }
        this.blockInfoList = arrayList;
        Iterable<VexFwkOcrAdditionalMeta.TableInfo> tableInfoList2 = vexFwkOcrAdditionalMeta2.getTableInfoList();
        ArrayList arrayList5 = new ArrayList(C1196n.w0(tableInfoList2, 10));
        for (VexFwkOcrAdditionalMeta.TableInfo tableInfo : tableInfoList2) {
            Iterable<VexFwkOcrAdditionalMeta.TableRowInfo> tableRowInfo = tableInfo.getTableRowInfo();
            ArrayList arrayList6 = new ArrayList(C1196n.w0(tableRowInfo, 10));
            for (VexFwkOcrAdditionalMeta.TableRowInfo tableCellInfo : tableRowInfo) {
                Iterable<VexFwkOcrAdditionalMeta.TableCellInfo> tableCellInfo2 = tableCellInfo.getTableCellInfo();
                ArrayList arrayList7 = new ArrayList(C1196n.w0(tableCellInfo2, 10));
                for (VexFwkOcrAdditionalMeta.TableCellInfo tableCellInfo3 : tableCellInfo2) {
                    arrayList7.add(new TableCellInfo(tableCellInfo3.getCellBoundary(), tableCellInfo3.getCellText()));
                }
                arrayList6.add(new TableRowInfo(arrayList7));
            }
            arrayList5.add(new TableInfo(arrayList6, tableInfo.getTableBoundary()));
        }
        this.tableInfoList = arrayList5;
        this.isHandwrittenResult = vexFwkOcrAdditionalMeta2.isHandWritten();
    }
}
