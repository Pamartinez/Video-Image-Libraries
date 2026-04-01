package com.samsung.android.vexfwk.param;

import L1.d;
import Tf.n;
import android.graphics.Point;
import com.samsung.android.vexfwk.param.VexFwkOcrResultMetaV2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ld.c;
import me.f;
import ne.C1196n;
import ne.C1200r;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001:\u0007)*+,-./B+\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0002\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\u001a\u0010\f\u001a\u00020\u00072\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001H\u0002¢\u0006\u0004\b\f\u0010\rJ\r\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u000f\u0010\u0010R(\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0004\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R(\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00028\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0006\u0010\u0011\u001a\u0004\b\u0016\u0010\u0013\"\u0004\b\u0017\u0010\u0015R\"\u0010\b\u001a\u00020\u00078\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\b\u0010\u0018\u001a\u0004\b\b\u0010\u0019\"\u0004\b\u001a\u0010\u001bR!\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001c0\u00028FX\u0002¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010\u0013R!\u0010$\u001a\b\u0012\u0004\u0012\u00020!0\u00028FX\u0002¢\u0006\f\n\u0004\b\"\u0010\u001e\u001a\u0004\b#\u0010\u0013R!\u0010(\u001a\b\u0012\u0004\u0012\u00020%0\u00028FX\u0002¢\u0006\f\n\u0004\b&\u0010\u001e\u001a\u0004\b'\u0010\u0013¨\u00060"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkOcrResultV2;", "", "", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResultV2$BlockInfo;", "blockInfoList", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResultV2$TableInfo;", "tableInfoList", "", "isHandwrittenResult", "<init>", "(Ljava/util/List;Ljava/util/List;Z)V", "other", "equals", "(Ljava/lang/Object;)Z", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResultMetaV2;", "toResultMeta", "()Lcom/samsung/android/vexfwk/param/VexFwkOcrResultMetaV2;", "Ljava/util/List;", "getBlockInfoList", "()Ljava/util/List;", "setBlockInfoList", "(Ljava/util/List;)V", "getTableInfoList", "setTableInfoList", "Z", "()Z", "setHandwrittenResult", "(Z)V", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResultV2$CharInfo;", "charInfoList$delegate", "Lme/f;", "getCharInfoList", "charInfoList", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResultV2$WordInfo;", "wordInfoList$delegate", "getWordInfoList", "wordInfoList", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResultV2$LineInfo;", "lineInfoList$delegate", "getLineInfoList", "lineInfoList", "CharInfo", "WordInfo", "LineInfo", "BlockInfo", "TableCellInfo", "TableRowInfo", "TableInfo", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkOcrResultV2 {
    private List<BlockInfo> blockInfoList;
    private final f charInfoList$delegate = d.q(new c(this, 0));
    private boolean isHandwrittenResult;
    private final f lineInfoList$delegate = d.q(new c(this, 2));
    private List<TableInfo> tableInfoList;
    private final f wordInfoList$delegate = d.q(new c(this, 1));

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B'\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u0002R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0015"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkOcrResultV2$CharInfo;", "", "string", "", "rect", "", "Landroid/graphics/Point;", "angle", "", "<init>", "(Ljava/lang/String;[Landroid/graphics/Point;F)V", "getString", "()Ljava/lang/String;", "getRect", "()[Landroid/graphics/Point;", "[Landroid/graphics/Point;", "getAngle", "()F", "equals", "", "other", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class CharInfo {
        private final float angle;
        private final Point[] rect;
        private final String string;

        public CharInfo(String str, Point[] pointArr, float f) {
            j.e(pointArr, "rect");
            this.string = str;
            this.rect = pointArr;
            this.angle = f;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof CharInfo)) {
                return false;
            }
            CharInfo charInfo = (CharInfo) obj;
            if (j.a(this.string, charInfo.string) && Arrays.equals(this.rect, charInfo.rect)) {
                return true;
            }
            return false;
        }

        public final float getAngle() {
            return this.angle;
        }

        public final Point[] getRect() {
            return this.rect;
        }

        public final String getString() {
            return this.string;
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B%\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u0002R\u0019\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0015"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkOcrResultV2$TableCellInfo;", "", "cellBoundary", "", "Landroid/graphics/Point;", "cellText", "", "angle", "", "<init>", "([Landroid/graphics/Point;Ljava/lang/String;F)V", "getCellBoundary", "()[Landroid/graphics/Point;", "[Landroid/graphics/Point;", "getCellText", "()Ljava/lang/String;", "getAngle", "()F", "equals", "", "other", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class TableCellInfo {
        private final float angle;
        private final Point[] cellBoundary;
        private final String cellText;

        public TableCellInfo(Point[] pointArr, String str, float f) {
            j.e(pointArr, "cellBoundary");
            j.e(str, "cellText");
            this.cellBoundary = pointArr;
            this.cellText = str;
            this.angle = f;
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

        public final float getAngle() {
            return this.angle;
        }

        public final Point[] getCellBoundary() {
            return this.cellBoundary;
        }

        public final String getCellText() {
            return this.cellText;
        }
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B+\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u0002R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0019\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0016"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkOcrResultV2$TableInfo;", "", "tableRowInfoList", "", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResultV2$TableRowInfo;", "tableBoundary", "", "Landroid/graphics/Point;", "angle", "", "<init>", "(Ljava/util/List;[Landroid/graphics/Point;F)V", "getTableRowInfoList", "()Ljava/util/List;", "getTableBoundary", "()[Landroid/graphics/Point;", "[Landroid/graphics/Point;", "getAngle", "()F", "equals", "", "other", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class TableInfo {
        private final float angle;
        private final Point[] tableBoundary;
        private final List<TableRowInfo> tableRowInfoList;

        public TableInfo(List<TableRowInfo> list, Point[] pointArr, float f) {
            j.e(list, "tableRowInfoList");
            j.e(pointArr, "tableBoundary");
            this.tableRowInfoList = list;
            this.tableBoundary = pointArr;
            this.angle = f;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if ((obj instanceof TableInfo) && this.tableRowInfoList.size() == ((TableInfo) obj).tableRowInfoList.size()) {
                return true;
            }
            return false;
        }

        public final float getAngle() {
            return this.angle;
        }

        public final Point[] getTableBoundary() {
            return this.tableBoundary;
        }

        public final List<TableRowInfo> getTableRowInfoList() {
            return this.tableRowInfoList;
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001H\u0002R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0010"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkOcrResultV2$TableRowInfo;", "", "tableCellInfoList", "", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResultV2$TableCellInfo;", "angle", "", "<init>", "(Ljava/util/List;F)V", "getTableCellInfoList", "()Ljava/util/List;", "getAngle", "()F", "equals", "", "other", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class TableRowInfo {
        private final float angle;
        private final List<TableCellInfo> tableCellInfoList;

        public TableRowInfo(List<TableCellInfo> list, float f) {
            j.e(list, "tableCellInfoList");
            this.tableCellInfoList = list;
            this.angle = f;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if ((obj instanceof TableRowInfo) && this.tableCellInfoList.size() == ((TableRowInfo) obj).tableCellInfoList.size()) {
                return true;
            }
            return false;
        }

        public final float getAngle() {
            return this.angle;
        }

        public final List<TableCellInfo> getTableCellInfoList() {
            return this.tableCellInfoList;
        }
    }

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B9\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001H\u0002R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0019\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010R\u0019\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u0012\u0010\u0010R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0015\u001a\u00020\u00168F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u001c"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkOcrResultV2$WordInfo;", "", "charInfoList", "", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResultV2$CharInfo;", "rect", "", "Landroid/graphics/Point;", "poly", "angle", "", "<init>", "(Ljava/util/List;[Landroid/graphics/Point;[Landroid/graphics/Point;F)V", "getCharInfoList", "()Ljava/util/List;", "getRect", "()[Landroid/graphics/Point;", "[Landroid/graphics/Point;", "getPoly", "getAngle", "()F", "string", "", "getString", "()Ljava/lang/String;", "equals", "", "other", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class WordInfo {
        private final float angle;
        private final List<CharInfo> charInfoList;
        private final Point[] poly;
        private final Point[] rect;

        public WordInfo(List<CharInfo> list, Point[] pointArr, Point[] pointArr2, float f) {
            j.e(list, "charInfoList");
            j.e(pointArr, "rect");
            j.e(pointArr2, "poly");
            this.charInfoList = list;
            this.rect = pointArr;
            this.poly = pointArr2;
            this.angle = f;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof WordInfo)) {
                return false;
            }
            WordInfo wordInfo = (WordInfo) obj;
            if (this.charInfoList.size() != wordInfo.charInfoList.size()) {
                return false;
            }
            int size = this.charInfoList.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (!this.charInfoList.get(i2).equals(wordInfo.charInfoList.get(i2))) {
                    return false;
                }
            }
            if (Arrays.equals(this.rect, wordInfo.rect) && Arrays.equals(this.poly, wordInfo.poly)) {
                return true;
            }
            return false;
        }

        public final float getAngle() {
            return this.angle;
        }

        public final List<CharInfo> getCharInfoList() {
            return this.charInfoList;
        }

        public final Point[] getPoly() {
            return this.poly;
        }

        public final Point[] getRect() {
            return this.rect;
        }

        public final String getString() {
            StringBuilder sb2 = new StringBuilder();
            for (CharInfo string : this.charInfoList) {
                sb2.append(string.getString());
            }
            String sb3 = sb2.toString();
            j.d(sb3, "toString(...)");
            return n.R0(sb3).toString();
        }
    }

    public VexFwkOcrResultV2(List<BlockInfo> list, List<TableInfo> list2, boolean z) {
        j.e(list, "blockInfoList");
        j.e(list2, "tableInfoList");
        this.blockInfoList = list;
        this.tableInfoList = list2;
        this.isHandwrittenResult = z;
    }

    /* access modifiers changed from: private */
    public static final List charInfoList_delegate$lambda$1(VexFwkOcrResultV2 vexFwkOcrResultV2) {
        ArrayList arrayList = new ArrayList();
        for (WordInfo charInfoList : vexFwkOcrResultV2.getWordInfoList()) {
            C1200r.A0(charInfoList.getCharInfoList(), arrayList);
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public static final List lineInfoList_delegate$lambda$5(VexFwkOcrResultV2 vexFwkOcrResultV2) {
        ArrayList arrayList = new ArrayList();
        for (BlockInfo lineInfoList : vexFwkOcrResultV2.blockInfoList) {
            C1200r.A0(lineInfoList.getLineInfoList(), arrayList);
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public static final List wordInfoList_delegate$lambda$3(VexFwkOcrResultV2 vexFwkOcrResultV2) {
        ArrayList arrayList = new ArrayList();
        for (LineInfo wordInfoList : vexFwkOcrResultV2.getLineInfoList()) {
            C1200r.A0(wordInfoList.getWordInfoList(), arrayList);
        }
        return arrayList;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VexFwkOcrResultV2)) {
            return false;
        }
        VexFwkOcrResultV2 vexFwkOcrResultV2 = (VexFwkOcrResultV2) obj;
        if (this.blockInfoList.size() != vexFwkOcrResultV2.blockInfoList.size()) {
            return false;
        }
        int size = this.blockInfoList.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (!this.blockInfoList.get(i2).equals(vexFwkOcrResultV2.blockInfoList.get(i2))) {
                return false;
            }
        }
        return true;
    }

    public final List<BlockInfo> getBlockInfoList() {
        return this.blockInfoList;
    }

    public final List<CharInfo> getCharInfoList() {
        return (List) this.charInfoList$delegate.getValue();
    }

    public final List<LineInfo> getLineInfoList() {
        return (List) this.lineInfoList$delegate.getValue();
    }

    public final List<TableInfo> getTableInfoList() {
        return this.tableInfoList;
    }

    public final List<WordInfo> getWordInfoList() {
        return (List) this.wordInfoList$delegate.getValue();
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

    public final VexFwkOcrResultMetaV2 toResultMeta() {
        Iterable<BlockInfo> iterable = this.blockInfoList;
        int i2 = 10;
        ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
        for (BlockInfo blockInfo : iterable) {
            Iterable<LineInfo> lineInfoList = blockInfo.getLineInfoList();
            ArrayList arrayList2 = new ArrayList(C1196n.w0(lineInfoList, i2));
            for (LineInfo lineInfo : lineInfoList) {
                Iterable<WordInfo> wordInfoList = lineInfo.getWordInfoList();
                ArrayList arrayList3 = new ArrayList(C1196n.w0(wordInfoList, i2));
                for (WordInfo wordInfo : wordInfoList) {
                    Iterable<CharInfo> charInfoList = wordInfo.getCharInfoList();
                    ArrayList arrayList4 = new ArrayList(C1196n.w0(charInfoList, i2));
                    for (CharInfo charInfo : charInfoList) {
                        arrayList4.add(new VexFwkOcrResultMetaV2.CharInfo(charInfo.getString(), charInfo.getRect(), charInfo.getAngle()));
                    }
                    arrayList3.add(new VexFwkOcrResultMetaV2.WordInfo(arrayList4, wordInfo.getRect(), wordInfo.getPoly(), wordInfo.getAngle()));
                    i2 = 10;
                }
                arrayList2.add(new VexFwkOcrResultMetaV2.LineInfo(arrayList3, lineInfo.getRect(), lineInfo.isCurved(), lineInfo.getAngle()));
                i2 = 10;
            }
            arrayList.add(new VexFwkOcrResultMetaV2.BlockInfo(arrayList2, blockInfo.getRect(), blockInfo.isTabular(), blockInfo.getAngle()));
            i2 = 10;
        }
        Iterable<TableInfo> iterable2 = this.tableInfoList;
        ArrayList arrayList5 = new ArrayList(C1196n.w0(iterable2, 10));
        for (TableInfo tableInfo : iterable2) {
            Iterable<TableRowInfo> tableRowInfoList = tableInfo.getTableRowInfoList();
            ArrayList arrayList6 = new ArrayList(C1196n.w0(tableRowInfoList, 10));
            for (TableRowInfo tableRowInfo : tableRowInfoList) {
                Iterable<TableCellInfo> tableCellInfoList = tableRowInfo.getTableCellInfoList();
                ArrayList arrayList7 = new ArrayList(C1196n.w0(tableCellInfoList, 10));
                for (TableCellInfo tableCellInfo : tableCellInfoList) {
                    arrayList7.add(new VexFwkOcrResultMetaV2.TableCellInfo(tableCellInfo.getCellBoundary(), tableCellInfo.getCellText(), tableCellInfo.getAngle()));
                }
                arrayList6.add(new VexFwkOcrResultMetaV2.TableRowInfo(arrayList7, tableRowInfo.getAngle()));
            }
            arrayList5.add(new VexFwkOcrResultMetaV2.TableInfo(arrayList6, tableInfo.getTableBoundary(), tableInfo.getAngle()));
        }
        return new VexFwkOcrResultMetaV2(arrayList, arrayList5, this.isHandwrittenResult);
    }

    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\r\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B5\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001H\u0002R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0019\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\b\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0018\u001a\u00020\u00198F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001b¨\u0006\u001f"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkOcrResultV2$BlockInfo;", "", "lineInfoList", "", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResultV2$LineInfo;", "rect", "", "Landroid/graphics/Point;", "isTabular", "", "angle", "", "<init>", "(Ljava/util/List;[Landroid/graphics/Point;IF)V", "getLineInfoList", "()Ljava/util/List;", "getRect", "()[Landroid/graphics/Point;", "[Landroid/graphics/Point;", "()I", "setTabular", "(I)V", "getAngle", "()F", "string", "", "getString", "()Ljava/lang/String;", "equals", "", "other", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class BlockInfo {
        private final float angle;
        private int isTabular;
        private final List<LineInfo> lineInfoList;
        private final Point[] rect;

        public BlockInfo(List<LineInfo> list, Point[] pointArr, int i2, float f) {
            j.e(list, "lineInfoList");
            j.e(pointArr, "rect");
            this.lineInfoList = list;
            this.rect = pointArr;
            this.isTabular = i2;
            this.angle = f;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof BlockInfo)) {
                return false;
            }
            BlockInfo blockInfo = (BlockInfo) obj;
            if (this.lineInfoList.size() != blockInfo.lineInfoList.size()) {
                return false;
            }
            int size = this.lineInfoList.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (!this.lineInfoList.get(i2).equals(blockInfo.lineInfoList.get(i2))) {
                    return false;
                }
            }
            if (Arrays.equals(this.rect, blockInfo.rect) && this.isTabular == blockInfo.isTabular) {
                return true;
            }
            return false;
        }

        public final float getAngle() {
            return this.angle;
        }

        public final List<LineInfo> getLineInfoList() {
            return this.lineInfoList;
        }

        public final Point[] getRect() {
            return this.rect;
        }

        public final String getString() {
            StringBuilder sb2 = new StringBuilder();
            for (LineInfo string : this.lineInfoList) {
                sb2.append(string.getString());
                sb2.append("\n");
            }
            String sb3 = sb2.toString();
            j.d(sb3, "toString(...)");
            return n.R0(sb3).toString();
        }

        public final int isTabular() {
            return this.isTabular;
        }

        public final void setTabular(int i2) {
            this.isTabular = i2;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ BlockInfo(List list, Point[] pointArr, int i2, float f, int i7, e eVar) {
            this(list, pointArr, (i7 & 4) != 0 ? 0 : i2, f);
        }
    }

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\r\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B5\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\u0013\u0010\u001c\u001a\u00020\t2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001H\u0002R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0019\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\b\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0018\u001a\u00020\u00198F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001b¨\u0006\u001e"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkOcrResultV2$LineInfo;", "", "wordInfoList", "", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResultV2$WordInfo;", "rect", "", "Landroid/graphics/Point;", "isCurved", "", "angle", "", "<init>", "(Ljava/util/List;[Landroid/graphics/Point;ZF)V", "getWordInfoList", "()Ljava/util/List;", "getRect", "()[Landroid/graphics/Point;", "[Landroid/graphics/Point;", "()Z", "setCurved", "(Z)V", "getAngle", "()F", "string", "", "getString", "()Ljava/lang/String;", "equals", "other", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class LineInfo {
        private final float angle;
        private boolean isCurved;
        private final Point[] rect;
        private final List<WordInfo> wordInfoList;

        public LineInfo(List<WordInfo> list, Point[] pointArr, boolean z, float f) {
            j.e(list, "wordInfoList");
            j.e(pointArr, "rect");
            this.wordInfoList = list;
            this.rect = pointArr;
            this.isCurved = z;
            this.angle = f;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof LineInfo)) {
                return false;
            }
            LineInfo lineInfo = (LineInfo) obj;
            if (this.isCurved != lineInfo.isCurved || this.wordInfoList.size() != lineInfo.wordInfoList.size()) {
                return false;
            }
            int size = this.wordInfoList.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (!this.wordInfoList.get(i2).equals(lineInfo.wordInfoList.get(i2))) {
                    return false;
                }
            }
            return Arrays.equals(this.rect, lineInfo.rect);
        }

        public final float getAngle() {
            return this.angle;
        }

        public final Point[] getRect() {
            return this.rect;
        }

        public final String getString() {
            StringBuilder sb2 = new StringBuilder();
            for (WordInfo string : this.wordInfoList) {
                sb2.append(string.getString());
                sb2.append(" ");
            }
            String sb3 = sb2.toString();
            j.d(sb3, "toString(...)");
            return n.R0(sb3).toString();
        }

        public final List<WordInfo> getWordInfoList() {
            return this.wordInfoList;
        }

        public final boolean isCurved() {
            return this.isCurved;
        }

        public final void setCurved(boolean z) {
            this.isCurved = z;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ LineInfo(List list, Point[] pointArr, boolean z, float f, int i2, e eVar) {
            this(list, pointArr, (i2 & 4) != 0 ? false : z, f);
        }
    }
}
