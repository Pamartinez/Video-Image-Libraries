package com.samsung.android.app.sdk.deepsky.textextraction.result;

import Tf.n;
import android.graphics.Point;
import android.graphics.Rect;
import bd.h;
import com.google.gson.annotations.SerializedName;
import com.samsung.android.app.sdk.deepsky.textextraction.result.OcrResult;
import com.samsung.android.vexfwk.sdk.docscan.b;
import i.C0212a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1194l;
import ne.C1196n;
import p3.a;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0010\b\b\u0018\u0000 (2\u00020\u0001:\u0005)*+,(B+\b\u0007\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u000b\u0010\fJ3\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00020\u00042\b\b\u0002\u0010\r\u001a\u00020\u00022\b\b\u0002\u0010\u000e\u001a\u00020\u00022\b\b\u0002\u0010\u000f\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\u0010\u0010\u0011J7\u0010\u0016\u001a\u00020\u00022\b\b\u0002\u0010\u0013\u001a\u00020\u00022\b\b\u0002\u0010\r\u001a\u00020\u00022\b\b\u0002\u0010\u000e\u001a\u00020\u00022\b\b\u0002\u0010\u000f\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\u0014\u0010\u0015J4\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\b\b\u0002\u0010\b\u001a\u00020\u0007HÆ\u0001¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u001a\u001a\u00020\u0019HÖ\u0001¢\u0006\u0004\b\u001a\u0010\u001bJ\u001a\u0010\u001e\u001a\u00020\u001d2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u001e\u0010\u001fR\u001a\u0010\u0003\u001a\u00020\u00028\u0006X\u0004¢\u0006\f\n\u0004\b\u0003\u0010 \u001a\u0004\b!\u0010\fR \u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006X\u0004¢\u0006\f\n\u0004\b\u0006\u0010\"\u001a\u0004\b#\u0010$R\u001a\u0010\b\u001a\u00020\u00078\u0006X\u0004¢\u0006\f\n\u0004\b\b\u0010%\u001a\u0004\b&\u0010'¨\u0006-"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData;", "", "", "version", "", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData$BlockInfo;", "blockList", "Landroid/graphics/Rect;", "validRect", "<init>", "(Ljava/lang/String;Ljava/util/List;Landroid/graphics/Rect;)V", "toString", "()Ljava/lang/String;", "lineSeparator", "wordSeparator", "charSeparator", "toBlockStringList$deepsky_sdk_textextraction_8_5_30_release", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/util/List;", "toBlockStringList", "blockSeparator", "joinToStringWithSeparator$deepsky_sdk_textextraction_8_5_30_release", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "joinToStringWithSeparator", "copy", "(Ljava/lang/String;Ljava/util/List;Landroid/graphics/Rect;)Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getVersion", "Ljava/util/List;", "getBlockList", "()Ljava/util/List;", "Landroid/graphics/Rect;", "getValidRect", "()Landroid/graphics/Rect;", "Companion", "BlockInfo", "LineInfo", "WordInfo", "CharInfo", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class OcrData {
    public static final Companion Companion = new Companion((e) null);
    @SerializedName("block_list")
    private final List<BlockInfo> blockList;
    @SerializedName("valid_rect")
    private final Rect validRect;
    @SerializedName("version")
    private final String version;

    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\r\b\b\u0018\u00002\u00020\u0001B7\b\u0007\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u0011\u001a\u00020\u000eH\u0000¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0013\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0015\u001a\u00020\nHÖ\u0001¢\u0006\u0004\b\u0015\u0010\u0016J\u001a\u0010\u0019\u001a\u00020\u00182\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0019\u0010\u001aR \u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006X\u0004¢\u0006\f\n\u0004\b\u0004\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u001a\u0010\u0006\u001a\u00020\u00058\u0006X\u0004¢\u0006\f\n\u0004\b\u0006\u0010\u001e\u001a\u0004\b\u001f\u0010 R \u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0006X\u0004¢\u0006\f\n\u0004\b\t\u0010!\u001a\u0004\b\"\u0010#R\u001a\u0010\u000b\u001a\u00020\n8\u0006X\u0004¢\u0006\f\n\u0004\b\u000b\u0010$\u001a\u0004\b\u000b\u0010\u0016¨\u0006%"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData$BlockInfo;", "", "", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData$LineInfo;", "lineList", "Landroid/graphics/Rect;", "rect", "", "Landroid/graphics/Point;", "poly", "", "isTabular", "<init>", "(Ljava/util/List;Landroid/graphics/Rect;[Landroid/graphics/Point;I)V", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrResult$BlockInfo;", "toOcrResultBlockInfo$deepsky_sdk_textextraction_8_5_30_release", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrResult$BlockInfo;", "toOcrResultBlockInfo", "", "toString", "()Ljava/lang/String;", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/util/List;", "getLineList", "()Ljava/util/List;", "Landroid/graphics/Rect;", "getRect", "()Landroid/graphics/Rect;", "[Landroid/graphics/Point;", "getPoly", "()[Landroid/graphics/Point;", "I", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class BlockInfo {
        @SerializedName("is_tabular")
        private final int isTabular;
        @SerializedName("line_list")
        private final List<LineInfo> lineList;
        @SerializedName("poly")
        private final Point[] poly;
        @SerializedName("rect")
        private final Rect rect;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public BlockInfo(List<LineInfo> list, Rect rect2, Point[] pointArr) {
            this(list, rect2, pointArr, 0, 8, (e) null);
            j.e(list, "lineList");
            j.e(rect2, "rect");
            j.e(pointArr, "poly");
        }

        /* access modifiers changed from: private */
        public static final CharSequence toString$lambda$1(LineInfo lineInfo) {
            j.e(lineInfo, "it");
            return lineInfo.toString();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof BlockInfo)) {
                return false;
            }
            BlockInfo blockInfo = (BlockInfo) obj;
            if (j.a(this.lineList, blockInfo.lineList) && j.a(this.rect, blockInfo.rect) && j.a(this.poly, blockInfo.poly) && this.isTabular == blockInfo.isTabular) {
                return true;
            }
            return false;
        }

        public final List<LineInfo> getLineList() {
            return this.lineList;
        }

        public final Point[] getPoly() {
            return this.poly;
        }

        public final Rect getRect() {
            return this.rect;
        }

        public int hashCode() {
            int hashCode = this.rect.hashCode();
            return Integer.hashCode(this.isTabular) + ((((hashCode + (this.lineList.hashCode() * 31)) * 31) + Arrays.hashCode(this.poly)) * 31);
        }

        public final int isTabular() {
            return this.isTabular;
        }

        public final OcrResult.BlockInfo toOcrResultBlockInfo$deepsky_sdk_textextraction_8_5_30_release() {
            Iterable<LineInfo> iterable = this.lineList;
            ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
            for (LineInfo ocrResultLineInfo$deepsky_sdk_textextraction_8_5_30_release : iterable) {
                arrayList.add(ocrResultLineInfo$deepsky_sdk_textextraction_8_5_30_release.toOcrResultLineInfo$deepsky_sdk_textextraction_8_5_30_release());
            }
            return new OcrResult.BlockInfo(arrayList, this.rect, this.poly);
        }

        public String toString() {
            return n.R0(C1194l.R0(this.lineList, " ", (String) null, (String) null, new b(13), 30)).toString();
        }

        public BlockInfo(List<LineInfo> list, Rect rect2, Point[] pointArr, int i2) {
            j.e(list, "lineList");
            j.e(rect2, "rect");
            j.e(pointArr, "poly");
            this.lineList = list;
            this.rect = rect2;
            this.poly = pointArr;
            this.isTabular = i2;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ BlockInfo(List list, Rect rect2, Point[] pointArr, int i2, int i7, e eVar) {
            this(list, rect2, pointArr, (i7 & 8) != 0 ? 0 : i2);
        }
    }

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000b\b\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000e\u001a\u00020\u000bH\u0000¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000f\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0012\u001a\u00020\u0011HÖ\u0001¢\u0006\u0004\b\u0012\u0010\u0013J\u001a\u0010\u0016\u001a\u00020\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0003\u001a\u00020\u00028\u0006X\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0018\u001a\u0004\b\u0019\u0010\u0010R\u001a\u0010\u0005\u001a\u00020\u00048\u0006X\u0004¢\u0006\f\n\u0004\b\u0005\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR \u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0006X\u0004¢\u0006\f\n\u0004\b\b\u0010\u001d\u001a\u0004\b\u001e\u0010\u001f¨\u0006 "}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData$CharInfo;", "", "", "text", "Landroid/graphics/Rect;", "rect", "", "Landroid/graphics/Point;", "poly", "<init>", "(Ljava/lang/String;Landroid/graphics/Rect;[Landroid/graphics/Point;)V", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrResult$CharInfo;", "toOcrResultCharInfo$deepsky_sdk_textextraction_8_5_30_release", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrResult$CharInfo;", "toOcrResultCharInfo", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getText", "Landroid/graphics/Rect;", "getRect", "()Landroid/graphics/Rect;", "[Landroid/graphics/Point;", "getPoly", "()[Landroid/graphics/Point;", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class CharInfo {
        @SerializedName("poly")
        private final Point[] poly;
        @SerializedName("rect")
        private final Rect rect;
        @SerializedName("text")
        private final String text;

        public CharInfo(String str, Rect rect2, Point[] pointArr) {
            j.e(str, "text");
            j.e(rect2, "rect");
            j.e(pointArr, "poly");
            this.text = str;
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
            if (j.a(this.text, charInfo.text) && j.a(this.rect, charInfo.rect) && j.a(this.poly, charInfo.poly)) {
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

        public final String getText() {
            return this.text;
        }

        public int hashCode() {
            return ((this.rect.hashCode() + (this.text.hashCode() * 31)) * 31) + Arrays.hashCode(this.poly);
        }

        public final OcrResult.CharInfo toOcrResultCharInfo$deepsky_sdk_textextraction_8_5_30_release() {
            return new OcrResult.CharInfo(this.text, this.rect, this.poly);
        }

        public String toString() {
            return this.text;
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0006XT¢\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048\u0002XT¢\u0006\u0006\n\u0004\b\u0007\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00048\u0002XT¢\u0006\u0006\n\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u00048\u0002XT¢\u0006\u0006\n\u0004\b\t\u0010\u0006R\u0014\u0010\n\u001a\u00020\u00048\u0002XT¢\u0006\u0006\n\u0004\b\n\u0010\u0006¨\u0006\u000b"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData$Companion;", "", "<init>", "()V", "", "OCR_DATA_VERSION", "Ljava/lang/String;", "BLOCK_SEPARATOR", "LINE_SEPARATOR", "WORD_SEPARATOR", "CHAR_SEPARATOR", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0011\b\b\u0018\u00002\u00020\u0001B7\b\u0007\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u0011\u001a\u00020\u000eH\u0000¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0013\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0016\u001a\u00020\u0015HÖ\u0001¢\u0006\u0004\b\u0016\u0010\u0017J\u001a\u0010\u0019\u001a\u00020\n2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0019\u0010\u001aR \u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006X\u0004¢\u0006\f\n\u0004\b\u0004\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u001a\u0010\u0006\u001a\u00020\u00058\u0006X\u0004¢\u0006\f\n\u0004\b\u0006\u0010\u001e\u001a\u0004\b\u001f\u0010 R \u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0006X\u0004¢\u0006\f\n\u0004\b\t\u0010!\u001a\u0004\b\"\u0010#R\u001a\u0010\u000b\u001a\u00020\n8\u0006X\u0004¢\u0006\f\n\u0004\b\u000b\u0010$\u001a\u0004\b\u000b\u0010%¨\u0006&"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData$LineInfo;", "", "", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData$WordInfo;", "wordList", "Landroid/graphics/Rect;", "rect", "", "Landroid/graphics/Point;", "poly", "", "isCurved", "<init>", "(Ljava/util/List;Landroid/graphics/Rect;[Landroid/graphics/Point;Z)V", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrResult$LineInfo;", "toOcrResultLineInfo$deepsky_sdk_textextraction_8_5_30_release", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrResult$LineInfo;", "toOcrResultLineInfo", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "equals", "(Ljava/lang/Object;)Z", "Ljava/util/List;", "getWordList", "()Ljava/util/List;", "Landroid/graphics/Rect;", "getRect", "()Landroid/graphics/Rect;", "[Landroid/graphics/Point;", "getPoly", "()[Landroid/graphics/Point;", "Z", "()Z", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class LineInfo {
        @SerializedName("is_curved")
        private final boolean isCurved;
        @SerializedName("poly")
        private final Point[] poly;
        @SerializedName("rect")
        private final Rect rect;
        @SerializedName("word_list")
        private final List<WordInfo> wordList;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public LineInfo(List<WordInfo> list, Rect rect2, Point[] pointArr) {
            this(list, rect2, pointArr, false, 8, (e) null);
            j.e(list, "wordList");
            j.e(rect2, "rect");
            j.e(pointArr, "poly");
        }

        /* access modifiers changed from: private */
        public static final CharSequence toString$lambda$1(WordInfo wordInfo) {
            j.e(wordInfo, "it");
            return wordInfo.toString();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof LineInfo)) {
                return false;
            }
            LineInfo lineInfo = (LineInfo) obj;
            if (j.a(this.wordList, lineInfo.wordList) && j.a(this.rect, lineInfo.rect) && j.a(this.poly, lineInfo.poly) && this.isCurved == lineInfo.isCurved) {
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

        public final List<WordInfo> getWordList() {
            return this.wordList;
        }

        public int hashCode() {
            int hashCode = this.rect.hashCode();
            return Boolean.hashCode(this.isCurved) + ((((hashCode + (this.wordList.hashCode() * 31)) * 31) + Arrays.hashCode(this.poly)) * 31);
        }

        public final boolean isCurved() {
            return this.isCurved;
        }

        public final OcrResult.LineInfo toOcrResultLineInfo$deepsky_sdk_textextraction_8_5_30_release() {
            Iterable<WordInfo> iterable = this.wordList;
            ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
            for (WordInfo ocrResultWordInfo$deepsky_sdk_textextraction_8_5_30_release : iterable) {
                arrayList.add(ocrResultWordInfo$deepsky_sdk_textextraction_8_5_30_release.toOcrResultWordInfo$deepsky_sdk_textextraction_8_5_30_release());
            }
            return new OcrResult.LineInfo(arrayList, this.rect, this.poly);
        }

        public String toString() {
            return n.R0(C1194l.R0(this.wordList, " ", (String) null, (String) null, new b(14), 30)).toString();
        }

        public LineInfo(List<WordInfo> list, Rect rect2, Point[] pointArr, boolean z) {
            j.e(list, "wordList");
            j.e(rect2, "rect");
            j.e(pointArr, "poly");
            this.wordList = list;
            this.rect = rect2;
            this.poly = pointArr;
            this.isCurved = z;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ LineInfo(List list, Rect rect2, Point[] pointArr, boolean z, int i2, e eVar) {
            this(list, rect2, pointArr, (i2 & 8) != 0 ? false : z);
        }
    }

    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\r\b\b\u0018\u00002\u00020\u0001B=\b\u0007\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u0010\u001a\u00020\rH\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0012\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0015\u001a\u00020\u0014HÖ\u0001¢\u0006\u0004\b\u0015\u0010\u0016J\u001a\u0010\u0019\u001a\u00020\u00182\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0019\u0010\u001aR \u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006X\u0004¢\u0006\f\n\u0004\b\u0004\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u001a\u0010\u0006\u001a\u00020\u00058\u0006X\u0004¢\u0006\f\n\u0004\b\u0006\u0010\u001e\u001a\u0004\b\u001f\u0010 R \u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0006X\u0004¢\u0006\f\n\u0004\b\t\u0010!\u001a\u0004\b\"\u0010#R \u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0006X\u0004¢\u0006\f\n\u0004\b\n\u0010!\u001a\u0004\b$\u0010#¨\u0006%"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData$WordInfo;", "", "", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData$CharInfo;", "charList", "Landroid/graphics/Rect;", "rect", "", "Landroid/graphics/Point;", "poly", "curvedPoly", "<init>", "(Ljava/util/List;Landroid/graphics/Rect;[Landroid/graphics/Point;[Landroid/graphics/Point;)V", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrResult$WordInfo;", "toOcrResultWordInfo$deepsky_sdk_textextraction_8_5_30_release", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrResult$WordInfo;", "toOcrResultWordInfo", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/util/List;", "getCharList", "()Ljava/util/List;", "Landroid/graphics/Rect;", "getRect", "()Landroid/graphics/Rect;", "[Landroid/graphics/Point;", "getPoly", "()[Landroid/graphics/Point;", "getCurvedPoly", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class WordInfo {
        @SerializedName("char_list")
        private final List<CharInfo> charList;
        @SerializedName("curved_poly")
        private final Point[] curvedPoly;
        @SerializedName("poly")
        private final Point[] poly;
        @SerializedName("rect")
        private final Rect rect;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public WordInfo(List<CharInfo> list, Rect rect2, Point[] pointArr) {
            this(list, rect2, pointArr, (Point[]) null, 8, (e) null);
            j.e(list, "charList");
            j.e(rect2, "rect");
            j.e(pointArr, "poly");
        }

        /* access modifiers changed from: private */
        public static final CharSequence toString$lambda$1(CharInfo charInfo) {
            j.e(charInfo, "it");
            return charInfo.toString();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof WordInfo)) {
                return false;
            }
            WordInfo wordInfo = (WordInfo) obj;
            if (j.a(this.charList, wordInfo.charList) && j.a(this.rect, wordInfo.rect) && j.a(this.poly, wordInfo.poly) && j.a(this.curvedPoly, wordInfo.curvedPoly)) {
                return true;
            }
            return false;
        }

        public final List<CharInfo> getCharList() {
            return this.charList;
        }

        public final Point[] getCurvedPoly() {
            return this.curvedPoly;
        }

        public final Point[] getPoly() {
            return this.poly;
        }

        public final Rect getRect() {
            return this.rect;
        }

        public int hashCode() {
            return ((((this.rect.hashCode() + (this.charList.hashCode() * 31)) * 31) + Arrays.hashCode(this.poly)) * 31) + Arrays.hashCode(this.curvedPoly);
        }

        public final OcrResult.WordInfo toOcrResultWordInfo$deepsky_sdk_textextraction_8_5_30_release() {
            Iterable<CharInfo> iterable = this.charList;
            ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
            for (CharInfo ocrResultCharInfo$deepsky_sdk_textextraction_8_5_30_release : iterable) {
                arrayList.add(ocrResultCharInfo$deepsky_sdk_textextraction_8_5_30_release.toOcrResultCharInfo$deepsky_sdk_textextraction_8_5_30_release());
            }
            return new OcrResult.WordInfo(arrayList, this.rect, this.poly);
        }

        public String toString() {
            return n.R0(C1194l.R0(this.charList, "", (String) null, (String) null, new b(15), 30)).toString();
        }

        public WordInfo(List<CharInfo> list, Rect rect2, Point[] pointArr, Point[] pointArr2) {
            j.e(list, "charList");
            j.e(rect2, "rect");
            j.e(pointArr, "poly");
            j.e(pointArr2, "curvedPoly");
            this.charList = list;
            this.rect = rect2;
            this.poly = pointArr;
            this.curvedPoly = pointArr2;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ WordInfo(List list, Rect rect2, Point[] pointArr, Point[] pointArr2, int i2, e eVar) {
            this(list, rect2, pointArr, (i2 & 8) != 0 ? new Point[0] : pointArr2);
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public OcrData(List<BlockInfo> list) {
        this((String) null, list, (Rect) null, 5, (e) null);
        j.e(list, "blockList");
    }

    public static /* synthetic */ OcrData copy$default(OcrData ocrData, String str, List<BlockInfo> list, Rect rect, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = ocrData.version;
        }
        if ((i2 & 2) != 0) {
            list = ocrData.blockList;
        }
        if ((i2 & 4) != 0) {
            rect = ocrData.validRect;
        }
        return ocrData.copy(str, list, rect);
    }

    public static /* synthetic */ String joinToStringWithSeparator$deepsky_sdk_textextraction_8_5_30_release$default(OcrData ocrData, String str, String str2, String str3, String str4, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = "\n";
        }
        if ((i2 & 2) != 0) {
            str2 = " ";
        }
        if ((i2 & 4) != 0) {
            str3 = " ";
        }
        if ((i2 & 8) != 0) {
            str4 = "";
        }
        return ocrData.joinToStringWithSeparator$deepsky_sdk_textextraction_8_5_30_release(str, str2, str3, str4);
    }

    /* access modifiers changed from: private */
    public static final CharSequence joinToStringWithSeparator$lambda$9(String str, String str2, String str3, BlockInfo blockInfo) {
        j.e(blockInfo, "block");
        return n.R0(C1194l.R0(blockInfo.getLineList(), str, (String) null, (String) null, new a(str2, str3, 1), 30)).toString();
    }

    /* access modifiers changed from: private */
    public static final CharSequence joinToStringWithSeparator$lambda$9$lambda$8(String str, String str2, LineInfo lineInfo) {
        j.e(lineInfo, "line");
        return n.R0(C1194l.R0(lineInfo.getWordList(), str, (String) null, (String) null, new h3.a(str2, 4), 30)).toString();
    }

    /* access modifiers changed from: private */
    public static final CharSequence joinToStringWithSeparator$lambda$9$lambda$8$lambda$7(String str, WordInfo wordInfo) {
        j.e(wordInfo, "word");
        return C1194l.R0(wordInfo.getCharList(), str, (String) null, (String) null, new b(11), 30);
    }

    /* access modifiers changed from: private */
    public static final CharSequence joinToStringWithSeparator$lambda$9$lambda$8$lambda$7$lambda$6(CharInfo charInfo) {
        j.e(charInfo, "it");
        return charInfo.getText();
    }

    public static /* synthetic */ List toBlockStringList$deepsky_sdk_textextraction_8_5_30_release$default(OcrData ocrData, String str, String str2, String str3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = " ";
        }
        if ((i2 & 2) != 0) {
            str2 = " ";
        }
        if ((i2 & 4) != 0) {
            str3 = "";
        }
        return ocrData.toBlockStringList$deepsky_sdk_textextraction_8_5_30_release(str, str2, str3);
    }

    /* access modifiers changed from: private */
    public static final CharSequence toBlockStringList$lambda$5$lambda$4(String str, String str2, LineInfo lineInfo) {
        j.e(lineInfo, "line");
        return n.R0(C1194l.R0(lineInfo.getWordList(), str, (String) null, (String) null, new h3.a(str2, 3), 30)).toString();
    }

    /* access modifiers changed from: private */
    public static final CharSequence toBlockStringList$lambda$5$lambda$4$lambda$3(String str, WordInfo wordInfo) {
        j.e(wordInfo, "word");
        return n.R0(C1194l.R0(wordInfo.getCharList(), str, (String) null, (String) null, new b(12), 30)).toString();
    }

    /* access modifiers changed from: private */
    public static final CharSequence toBlockStringList$lambda$5$lambda$4$lambda$3$lambda$2(CharInfo charInfo) {
        j.e(charInfo, "it");
        return charInfo.getText();
    }

    public final OcrData copy(String str, List<BlockInfo> list, Rect rect) {
        j.e(str, "version");
        j.e(list, "blockList");
        j.e(rect, "validRect");
        return new OcrData(str, list, rect);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OcrData)) {
            return false;
        }
        OcrData ocrData = (OcrData) obj;
        if (j.a(this.version, ocrData.version) && j.a(this.blockList, ocrData.blockList) && j.a(this.validRect, ocrData.validRect)) {
            return true;
        }
        return false;
    }

    public final List<BlockInfo> getBlockList() {
        return this.blockList;
    }

    public final Rect getValidRect() {
        return this.validRect;
    }

    public int hashCode() {
        return this.validRect.hashCode() + C0212a.f(this.blockList, this.version.hashCode() * 31, 31);
    }

    public final String joinToStringWithSeparator$deepsky_sdk_textextraction_8_5_30_release(String str, String str2, String str3, String str4) {
        j.e(str, "blockSeparator");
        j.e(str2, "lineSeparator");
        j.e(str3, "wordSeparator");
        j.e(str4, "charSeparator");
        return n.R0(C1194l.R0(this.blockList, str, (String) null, (String) null, new h(str2, str3, str4, 5), 30)).toString();
    }

    public final List<String> toBlockStringList$deepsky_sdk_textextraction_8_5_30_release(String str, String str2, String str3) {
        j.e(str, "lineSeparator");
        j.e(str2, "wordSeparator");
        j.e(str3, "charSeparator");
        Iterable<BlockInfo> iterable = this.blockList;
        ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
        for (BlockInfo lineList : iterable) {
            String str4 = str;
            arrayList.add(n.R0(C1194l.R0(lineList.getLineList(), str4, (String) null, (String) null, new a(str2, str3, 0), 30)).toString());
            str = str4;
        }
        return arrayList;
    }

    public String toString() {
        return joinToStringWithSeparator$deepsky_sdk_textextraction_8_5_30_release$default(this, (String) null, (String) null, (String) null, (String) null, 15, (Object) null);
    }

    public OcrData(String str, List<BlockInfo> list, Rect rect) {
        j.e(str, "version");
        j.e(list, "blockList");
        j.e(rect, "validRect");
        this.version = str;
        this.blockList = list;
        this.validRect = rect;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ OcrData(java.lang.String r2, java.util.List r3, android.graphics.Rect r4, int r5, kotlin.jvm.internal.e r6) {
        /*
            r1 = this;
            r6 = r5 & 1
            if (r6 == 0) goto L_0x0006
            java.lang.String r2 = "1.0"
        L_0x0006:
            r5 = r5 & 4
            if (r5 == 0) goto L_0x0036
            com.samsung.android.app.sdk.deepsky.textextraction.util.OcrUtil r4 = com.samsung.android.app.sdk.deepsky.textextraction.util.OcrUtil.INSTANCE
            r5 = r3
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.ArrayList r6 = new java.util.ArrayList
            r0 = 10
            int r0 = ne.C1196n.w0(r5, r0)
            r6.<init>(r0)
            java.util.Iterator r5 = r5.iterator()
        L_0x001e:
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto L_0x0032
            java.lang.Object r0 = r5.next()
            com.samsung.android.app.sdk.deepsky.textextraction.result.OcrData$BlockInfo r0 = (com.samsung.android.app.sdk.deepsky.textextraction.result.OcrData.BlockInfo) r0
            android.graphics.Rect r0 = r0.getRect()
            r6.add(r0)
            goto L_0x001e
        L_0x0032:
            android.graphics.Rect r4 = r4.calculateBoundingRect(r6)
        L_0x0036:
            r1.<init>(r2, r3, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.textextraction.result.OcrData.<init>(java.lang.String, java.util.List, android.graphics.Rect, int, kotlin.jvm.internal.e):void");
    }
}
