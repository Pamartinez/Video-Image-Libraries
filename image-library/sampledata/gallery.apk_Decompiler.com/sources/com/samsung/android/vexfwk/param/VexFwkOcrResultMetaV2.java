package com.samsung.android.vexfwk.param;

import A.a;
import Tf.n;
import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.vexfwk.extensions.RectKt;
import com.samsung.android.vexfwk.param.VexFwkOcrAdditionalMeta;
import com.samsung.android.vexfwk.param.VexFwkOcrResultMeta;
import com.samsung.android.vexfwk.param.VexFwkOcrResultV2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ld.b;
import ne.C1196n;
import ne.C1202t;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\b\u0007\u0018\u0000 &2\u00020\u0001:\b&'()*+,-B+\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0002\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nB\u001b\b\u0016\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\t\u0010\u000fJ\u001a\u0010\u0012\u001a\u00020\u00072\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\r\u0010\u0015\u001a\u00020\u0014¢\u0006\u0004\b\u0015\u0010\u0016J\u001d\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u0019¢\u0006\u0004\b\u001c\u0010\u001dJ\r\u0010\u001e\u001a\u00020\u0019¢\u0006\u0004\b\u001e\u0010\u001fR\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010 \u001a\u0004\b!\u0010\"R\u001d\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00028\u0006¢\u0006\f\n\u0004\b\u0006\u0010 \u001a\u0004\b#\u0010\"R\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010$\u001a\u0004\b\b\u0010%¨\u0006."}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkOcrResultMetaV2;", "Landroid/os/Parcelable;", "", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResultMetaV2$BlockInfo;", "blockInfoList", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResultMetaV2$TableInfo;", "tableInfoList", "", "isHandwrittenResult", "<init>", "(Ljava/util/List;Ljava/util/List;Z)V", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResultMeta;", "ocrResultV1", "Lcom/samsung/android/vexfwk/param/VexFwkOcrAdditionalMeta;", "ocrAdditionalResult", "(Lcom/samsung/android/vexfwk/param/VexFwkOcrResultMeta;Lcom/samsung/android/vexfwk/param/VexFwkOcrAdditionalMeta;)V", "", "other", "equals", "(Ljava/lang/Object;)Z", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResultV2;", "toResult", "()Lcom/samsung/android/vexfwk/param/VexFwkOcrResultV2;", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/util/List;", "getBlockInfoList", "()Ljava/util/List;", "getTableInfoList", "Z", "()Z", "Companion", "CharInfo", "WordInfo", "LineInfo", "BlockInfo", "TableCellInfo", "TableRowInfo", "TableInfo", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkOcrResultMetaV2 implements Parcelable {
    public static final Parcelable.Creator<VexFwkOcrResultMetaV2> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private final List<BlockInfo> blockInfoList;
    private final boolean isHandwrittenResult;
    private final List<TableInfo> tableInfoList;

    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0007\u0018\u00002\u00020\u0001B'\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\u001a\u0010\u000e\u001a\u00020\r2\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u001d\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0012¢\u0006\u0004\b\u0015\u0010\u0016J\r\u0010\u0017\u001a\u00020\u0012¢\u0006\u0004\b\u0017\u0010\u0018R\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u001d\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010\u001f\u001a\u0004\b \u0010!¨\u0006\""}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkOcrResultMetaV2$CharInfo;", "Landroid/os/Parcelable;", "", "string", "", "Landroid/graphics/Point;", "rect", "", "angle", "<init>", "(Ljava/lang/String;[Landroid/graphics/Point;F)V", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/lang/String;", "getString", "()Ljava/lang/String;", "[Landroid/graphics/Point;", "getRect", "()[Landroid/graphics/Point;", "F", "getAngle", "()F", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class CharInfo implements Parcelable {
        public static final Parcelable.Creator<CharInfo> CREATOR = new Creator();
        private final float angle;
        private final Point[] rect;
        private final String string;

        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Creator implements Parcelable.Creator<CharInfo> {
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: android.graphics.Point[]} */
            /* JADX WARNING: Multi-variable type inference failed */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public final com.samsung.android.vexfwk.param.VexFwkOcrResultMetaV2.CharInfo createFromParcel(android.os.Parcel r5) {
                /*
                    r4 = this;
                    java.lang.String r4 = "parcel"
                    kotlin.jvm.internal.j.e(r5, r4)
                    java.lang.String r4 = r5.readString()
                    int r0 = r5.readInt()
                    android.graphics.Point[] r1 = new android.graphics.Point[r0]
                    r2 = 0
                L_0x0010:
                    if (r2 == r0) goto L_0x0021
                    java.lang.Class<com.samsung.android.vexfwk.param.VexFwkOcrResultMetaV2$CharInfo> r3 = com.samsung.android.vexfwk.param.VexFwkOcrResultMetaV2.CharInfo.class
                    java.lang.ClassLoader r3 = r3.getClassLoader()
                    android.os.Parcelable r3 = r5.readParcelable(r3)
                    r1[r2] = r3
                    int r2 = r2 + 1
                    goto L_0x0010
                L_0x0021:
                    float r5 = r5.readFloat()
                    com.samsung.android.vexfwk.param.VexFwkOcrResultMetaV2$CharInfo r0 = new com.samsung.android.vexfwk.param.VexFwkOcrResultMetaV2$CharInfo
                    r0.<init>(r4, r1, r5)
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.param.VexFwkOcrResultMetaV2.CharInfo.Creator.createFromParcel(android.os.Parcel):com.samsung.android.vexfwk.param.VexFwkOcrResultMetaV2$CharInfo");
            }

            public final CharInfo[] newArray(int i2) {
                return new CharInfo[i2];
            }
        }

        public CharInfo(String str, Point[] pointArr, float f) {
            j.e(pointArr, "rect");
            this.string = str;
            this.rect = pointArr;
            this.angle = f;
        }

        public final int describeContents() {
            return 0;
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

        public final void writeToParcel(Parcel parcel, int i2) {
            j.e(parcel, "dest");
            parcel.writeString(this.string);
            Point[] pointArr = this.rect;
            int length = pointArr.length;
            parcel.writeInt(length);
            for (int i7 = 0; i7 != length; i7++) {
                parcel.writeParcelable(pointArr[i7], i2);
            }
            parcel.writeFloat(this.angle);
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkOcrResultMetaV2$Companion;", "Lcom/samsung/android/vexfwk/param/VexFwkParamBaseParcelable;", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResultMetaV2;", "<init>", "()V", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion extends VexFwkParamBaseParcelable<VexFwkOcrResultMetaV2> {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        /* access modifiers changed from: private */
        public static final Parcelable.Creator _init_$lambda$0() {
            return VexFwkOcrResultMetaV2.CREATOR;
        }

        private Companion() {
            super(new b(4));
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<VexFwkOcrResultMetaV2> {
        public final VexFwkOcrResultMetaV2 createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            int readInt = parcel.readInt();
            ArrayList arrayList = new ArrayList(readInt);
            boolean z = false;
            int i2 = 0;
            while (i2 != readInt) {
                i2 = a.a(BlockInfo.CREATOR, parcel, arrayList, i2, 1);
            }
            int readInt2 = parcel.readInt();
            ArrayList arrayList2 = new ArrayList(readInt2);
            int i7 = 0;
            while (i7 != readInt2) {
                i7 = a.a(TableInfo.CREATOR, parcel, arrayList2, i7, 1);
            }
            if (parcel.readInt() != 0) {
                z = true;
            }
            return new VexFwkOcrResultMetaV2(arrayList, arrayList2, z);
        }

        public final VexFwkOcrResultMetaV2[] newArray(int i2) {
            return new VexFwkOcrResultMetaV2[i2];
        }
    }

    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B3\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rJ\u001a\u0010\u0010\u001a\u00020\b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u001d\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0014¢\u0006\u0004\b\u0017\u0010\u0018J\r\u0010\u0019\u001a\u00020\u0014¢\u0006\u0004\b\u0019\u0010\u001aR\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u001d\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u0017\u0010\t\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\t\u0010!\u001a\u0004\b\t\u0010\"R\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010#\u001a\u0004\b$\u0010%R\u0011\u0010)\u001a\u00020&8F¢\u0006\u0006\u001a\u0004\b'\u0010(¨\u0006*"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkOcrResultMetaV2$LineInfo;", "Landroid/os/Parcelable;", "", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResultMetaV2$WordInfo;", "wordInfoList", "", "Landroid/graphics/Point;", "rect", "", "isCurved", "", "angle", "<init>", "(Ljava/util/List;[Landroid/graphics/Point;ZF)V", "", "other", "equals", "(Ljava/lang/Object;)Z", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/util/List;", "getWordInfoList", "()Ljava/util/List;", "[Landroid/graphics/Point;", "getRect", "()[Landroid/graphics/Point;", "Z", "()Z", "F", "getAngle", "()F", "", "getString", "()Ljava/lang/String;", "string", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class LineInfo implements Parcelable {
        public static final Parcelable.Creator<LineInfo> CREATOR = new Creator();
        private final float angle;
        private final boolean isCurved;
        private final Point[] rect;
        private final List<WordInfo> wordInfoList;

        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Creator implements Parcelable.Creator<LineInfo> {
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: android.graphics.Point[]} */
            /* JADX WARNING: Multi-variable type inference failed */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public final com.samsung.android.vexfwk.param.VexFwkOcrResultMetaV2.LineInfo createFromParcel(android.os.Parcel r7) {
                /*
                    r6 = this;
                    java.lang.String r6 = "parcel"
                    kotlin.jvm.internal.j.e(r7, r6)
                    int r6 = r7.readInt()
                    java.util.ArrayList r0 = new java.util.ArrayList
                    r0.<init>(r6)
                    r1 = 0
                    r2 = r1
                L_0x0010:
                    r3 = 1
                    if (r2 == r6) goto L_0x001a
                    android.os.Parcelable$Creator<com.samsung.android.vexfwk.param.VexFwkOcrResultMetaV2$WordInfo> r4 = com.samsung.android.vexfwk.param.VexFwkOcrResultMetaV2.WordInfo.CREATOR
                    int r2 = A.a.a(r4, r7, r0, r2, r3)
                    goto L_0x0010
                L_0x001a:
                    int r6 = r7.readInt()
                    android.graphics.Point[] r2 = new android.graphics.Point[r6]
                    r4 = r1
                L_0x0021:
                    if (r4 == r6) goto L_0x0032
                    java.lang.Class<com.samsung.android.vexfwk.param.VexFwkOcrResultMetaV2$LineInfo> r5 = com.samsung.android.vexfwk.param.VexFwkOcrResultMetaV2.LineInfo.class
                    java.lang.ClassLoader r5 = r5.getClassLoader()
                    android.os.Parcelable r5 = r7.readParcelable(r5)
                    r2[r4] = r5
                    int r4 = r4 + 1
                    goto L_0x0021
                L_0x0032:
                    int r6 = r7.readInt()
                    if (r6 == 0) goto L_0x0039
                    r1 = r3
                L_0x0039:
                    float r6 = r7.readFloat()
                    com.samsung.android.vexfwk.param.VexFwkOcrResultMetaV2$LineInfo r7 = new com.samsung.android.vexfwk.param.VexFwkOcrResultMetaV2$LineInfo
                    r7.<init>(r0, r2, r1, r6)
                    return r7
                */
                throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.param.VexFwkOcrResultMetaV2.LineInfo.Creator.createFromParcel(android.os.Parcel):com.samsung.android.vexfwk.param.VexFwkOcrResultMetaV2$LineInfo");
            }

            public final LineInfo[] newArray(int i2) {
                return new LineInfo[i2];
            }
        }

        public LineInfo(List<WordInfo> list, Point[] pointArr, boolean z, float f) {
            j.e(list, "wordInfoList");
            j.e(pointArr, "rect");
            this.wordInfoList = list;
            this.rect = pointArr;
            this.isCurved = z;
            this.angle = f;
        }

        public final int describeContents() {
            return 0;
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
                sb2.append("     ");
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

        public final void writeToParcel(Parcel parcel, int i2) {
            j.e(parcel, "dest");
            Iterator j2 = a.j(parcel, this.wordInfoList);
            while (j2.hasNext()) {
                ((WordInfo) j2.next()).writeToParcel(parcel, i2);
            }
            Point[] pointArr = this.rect;
            int length = pointArr.length;
            parcel.writeInt(length);
            for (int i7 = 0; i7 != length; i7++) {
                parcel.writeParcelable(pointArr[i7], i2);
            }
            parcel.writeInt(this.isCurved ? 1 : 0);
            parcel.writeFloat(this.angle);
        }
    }

    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0007\u0018\u00002\u00020\u0001B%\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\u001a\u0010\u000e\u001a\u00020\r2\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u001d\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0012¢\u0006\u0004\b\u0015\u0010\u0016J\r\u0010\u0017\u001a\u00020\u0012¢\u0006\u0004\b\u0017\u0010\u0018R\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010\u001f\u001a\u0004\b \u0010!¨\u0006\""}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkOcrResultMetaV2$TableCellInfo;", "Landroid/os/Parcelable;", "", "Landroid/graphics/Point;", "cellBoundary", "", "cellText", "", "angle", "<init>", "([Landroid/graphics/Point;Ljava/lang/String;F)V", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "[Landroid/graphics/Point;", "getCellBoundary", "()[Landroid/graphics/Point;", "Ljava/lang/String;", "getCellText", "()Ljava/lang/String;", "F", "getAngle", "()F", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class TableCellInfo implements Parcelable {
        public static final Parcelable.Creator<TableCellInfo> CREATOR = new Creator();
        private final float angle;
        private final Point[] cellBoundary;
        private final String cellText;

        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Creator implements Parcelable.Creator<TableCellInfo> {
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v0, resolved type: android.graphics.Point[]} */
            /* JADX WARNING: Multi-variable type inference failed */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public final com.samsung.android.vexfwk.param.VexFwkOcrResultMetaV2.TableCellInfo createFromParcel(android.os.Parcel r4) {
                /*
                    r3 = this;
                    java.lang.String r3 = "parcel"
                    kotlin.jvm.internal.j.e(r4, r3)
                    int r3 = r4.readInt()
                    android.graphics.Point[] r0 = new android.graphics.Point[r3]
                    r1 = 0
                L_0x000c:
                    if (r1 == r3) goto L_0x001d
                    java.lang.Class<com.samsung.android.vexfwk.param.VexFwkOcrResultMetaV2$TableCellInfo> r2 = com.samsung.android.vexfwk.param.VexFwkOcrResultMetaV2.TableCellInfo.class
                    java.lang.ClassLoader r2 = r2.getClassLoader()
                    android.os.Parcelable r2 = r4.readParcelable(r2)
                    r0[r1] = r2
                    int r1 = r1 + 1
                    goto L_0x000c
                L_0x001d:
                    java.lang.String r3 = r4.readString()
                    float r4 = r4.readFloat()
                    com.samsung.android.vexfwk.param.VexFwkOcrResultMetaV2$TableCellInfo r1 = new com.samsung.android.vexfwk.param.VexFwkOcrResultMetaV2$TableCellInfo
                    r1.<init>(r0, r3, r4)
                    return r1
                */
                throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.param.VexFwkOcrResultMetaV2.TableCellInfo.Creator.createFromParcel(android.os.Parcel):com.samsung.android.vexfwk.param.VexFwkOcrResultMetaV2$TableCellInfo");
            }

            public final TableCellInfo[] newArray(int i2) {
                return new TableCellInfo[i2];
            }
        }

        public TableCellInfo(Point[] pointArr, String str, float f) {
            j.e(pointArr, "cellBoundary");
            j.e(str, "cellText");
            this.cellBoundary = pointArr;
            this.cellText = str;
            this.angle = f;
        }

        public final int describeContents() {
            return 0;
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

        public final void writeToParcel(Parcel parcel, int i2) {
            j.e(parcel, "dest");
            Point[] pointArr = this.cellBoundary;
            int length = pointArr.length;
            parcel.writeInt(length);
            for (int i7 = 0; i7 != length; i7++) {
                parcel.writeParcelable(pointArr[i7], i2);
            }
            parcel.writeString(this.cellText);
            parcel.writeFloat(this.angle);
        }
    }

    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0007\u0018\u00002\u00020\u0001B+\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ\u001a\u0010\u000f\u001a\u00020\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u001d\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0013¢\u0006\u0004\b\u0016\u0010\u0017J\r\u0010\u0018\u001a\u00020\u0013¢\u0006\u0004\b\u0018\u0010\u0019R\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u001d\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR\u0017\u0010\t\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\t\u0010 \u001a\u0004\b!\u0010\"¨\u0006#"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkOcrResultMetaV2$TableInfo;", "Landroid/os/Parcelable;", "", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResultMetaV2$TableRowInfo;", "tableRowInfoList", "", "Landroid/graphics/Point;", "tableBoundary", "", "angle", "<init>", "(Ljava/util/List;[Landroid/graphics/Point;F)V", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/util/List;", "getTableRowInfoList", "()Ljava/util/List;", "[Landroid/graphics/Point;", "getTableBoundary", "()[Landroid/graphics/Point;", "F", "getAngle", "()F", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class TableInfo implements Parcelable {
        public static final Parcelable.Creator<TableInfo> CREATOR = new Creator();
        private final float angle;
        private final Point[] tableBoundary;
        private final List<TableRowInfo> tableRowInfoList;

        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Creator implements Parcelable.Creator<TableInfo> {
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: android.graphics.Point[]} */
            /* JADX WARNING: Multi-variable type inference failed */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public final com.samsung.android.vexfwk.param.VexFwkOcrResultMetaV2.TableInfo createFromParcel(android.os.Parcel r6) {
                /*
                    r5 = this;
                    java.lang.String r5 = "parcel"
                    kotlin.jvm.internal.j.e(r6, r5)
                    int r5 = r6.readInt()
                    java.util.ArrayList r0 = new java.util.ArrayList
                    r0.<init>(r5)
                    r1 = 0
                    r2 = r1
                L_0x0010:
                    if (r2 == r5) goto L_0x001a
                    android.os.Parcelable$Creator<com.samsung.android.vexfwk.param.VexFwkOcrResultMetaV2$TableRowInfo> r3 = com.samsung.android.vexfwk.param.VexFwkOcrResultMetaV2.TableRowInfo.CREATOR
                    r4 = 1
                    int r2 = A.a.a(r3, r6, r0, r2, r4)
                    goto L_0x0010
                L_0x001a:
                    int r5 = r6.readInt()
                    android.graphics.Point[] r2 = new android.graphics.Point[r5]
                L_0x0020:
                    if (r1 == r5) goto L_0x0031
                    java.lang.Class<com.samsung.android.vexfwk.param.VexFwkOcrResultMetaV2$TableInfo> r3 = com.samsung.android.vexfwk.param.VexFwkOcrResultMetaV2.TableInfo.class
                    java.lang.ClassLoader r3 = r3.getClassLoader()
                    android.os.Parcelable r3 = r6.readParcelable(r3)
                    r2[r1] = r3
                    int r1 = r1 + 1
                    goto L_0x0020
                L_0x0031:
                    float r5 = r6.readFloat()
                    com.samsung.android.vexfwk.param.VexFwkOcrResultMetaV2$TableInfo r6 = new com.samsung.android.vexfwk.param.VexFwkOcrResultMetaV2$TableInfo
                    r6.<init>(r0, r2, r5)
                    return r6
                */
                throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.param.VexFwkOcrResultMetaV2.TableInfo.Creator.createFromParcel(android.os.Parcel):com.samsung.android.vexfwk.param.VexFwkOcrResultMetaV2$TableInfo");
            }

            public final TableInfo[] newArray(int i2) {
                return new TableInfo[i2];
            }
        }

        public TableInfo(List<TableRowInfo> list, Point[] pointArr, float f) {
            j.e(list, "tableRowInfoList");
            j.e(pointArr, "tableBoundary");
            this.tableRowInfoList = list;
            this.tableBoundary = pointArr;
            this.angle = f;
        }

        public final int describeContents() {
            return 0;
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

        public final void writeToParcel(Parcel parcel, int i2) {
            j.e(parcel, "dest");
            Iterator j2 = a.j(parcel, this.tableRowInfoList);
            while (j2.hasNext()) {
                ((TableRowInfo) j2.next()).writeToParcel(parcel, i2);
            }
            Point[] pointArr = this.tableBoundary;
            int length = pointArr.length;
            parcel.writeInt(length);
            for (int i7 = 0; i7 != length; i7++) {
                parcel.writeParcelable(pointArr[i7], i2);
            }
            parcel.writeFloat(this.angle);
        }
    }

    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0007\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u001a\u0010\f\u001a\u00020\u000b2\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0002¢\u0006\u0004\b\f\u0010\rJ\u001d\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0013\u0010\u0014J\r\u0010\u0015\u001a\u00020\u0010¢\u0006\u0004\b\u0015\u0010\u0016R\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001a\u001a\u0004\b\u001b\u0010\u001c¨\u0006\u001d"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkOcrResultMetaV2$TableRowInfo;", "Landroid/os/Parcelable;", "", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResultMetaV2$TableCellInfo;", "tableCellInfoList", "", "angle", "<init>", "(Ljava/util/List;F)V", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/util/List;", "getTableCellInfoList", "()Ljava/util/List;", "F", "getAngle", "()F", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class TableRowInfo implements Parcelable {
        public static final Parcelable.Creator<TableRowInfo> CREATOR = new Creator();
        private final float angle;
        private final List<TableCellInfo> tableCellInfoList;

        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Creator implements Parcelable.Creator<TableRowInfo> {
            public final TableRowInfo createFromParcel(Parcel parcel) {
                j.e(parcel, "parcel");
                int readInt = parcel.readInt();
                ArrayList arrayList = new ArrayList(readInt);
                int i2 = 0;
                while (i2 != readInt) {
                    i2 = a.a(TableCellInfo.CREATOR, parcel, arrayList, i2, 1);
                }
                return new TableRowInfo(arrayList, parcel.readFloat());
            }

            public final TableRowInfo[] newArray(int i2) {
                return new TableRowInfo[i2];
            }
        }

        public TableRowInfo(List<TableCellInfo> list, float f) {
            j.e(list, "tableCellInfoList");
            this.tableCellInfoList = list;
            this.angle = f;
        }

        public final int describeContents() {
            return 0;
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

        public final void writeToParcel(Parcel parcel, int i2) {
            j.e(parcel, "dest");
            Iterator j2 = a.j(parcel, this.tableCellInfoList);
            while (j2.hasNext()) {
                ((TableCellInfo) j2.next()).writeToParcel(parcel, i2);
            }
            parcel.writeFloat(this.angle);
        }
    }

    @Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B9\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\fJ\u001a\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u001d\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0014¢\u0006\u0004\b\u0017\u0010\u0018J\r\u0010\u0019\u001a\u00020\u0014¢\u0006\u0004\b\u0019\u0010\u001aR\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u001d\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u001d\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006¢\u0006\f\n\u0004\b\b\u0010\u001e\u001a\u0004\b!\u0010 R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\"\u001a\u0004\b#\u0010$R\u0011\u0010(\u001a\u00020%8F¢\u0006\u0006\u001a\u0004\b&\u0010'¨\u0006)"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkOcrResultMetaV2$WordInfo;", "Landroid/os/Parcelable;", "", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResultMetaV2$CharInfo;", "charInfoList", "", "Landroid/graphics/Point;", "rect", "poly", "", "angle", "<init>", "(Ljava/util/List;[Landroid/graphics/Point;[Landroid/graphics/Point;F)V", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/util/List;", "getCharInfoList", "()Ljava/util/List;", "[Landroid/graphics/Point;", "getRect", "()[Landroid/graphics/Point;", "getPoly", "F", "getAngle", "()F", "", "getString", "()Ljava/lang/String;", "string", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class WordInfo implements Parcelable {
        public static final Parcelable.Creator<WordInfo> CREATOR = new Creator();
        private final float angle;
        private final List<CharInfo> charInfoList;
        private final Point[] poly;
        private final Point[] rect;

        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Creator implements Parcelable.Creator<WordInfo> {
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: android.graphics.Point[]} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: android.graphics.Point[]} */
            /* JADX WARNING: Multi-variable type inference failed */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public final com.samsung.android.vexfwk.param.VexFwkOcrResultMetaV2.WordInfo createFromParcel(android.os.Parcel r7) {
                /*
                    r6 = this;
                    java.lang.String r6 = "parcel"
                    kotlin.jvm.internal.j.e(r7, r6)
                    int r6 = r7.readInt()
                    java.util.ArrayList r0 = new java.util.ArrayList
                    r0.<init>(r6)
                    r1 = 0
                    r2 = r1
                L_0x0010:
                    if (r2 == r6) goto L_0x001a
                    android.os.Parcelable$Creator<com.samsung.android.vexfwk.param.VexFwkOcrResultMetaV2$CharInfo> r3 = com.samsung.android.vexfwk.param.VexFwkOcrResultMetaV2.CharInfo.CREATOR
                    r4 = 1
                    int r2 = A.a.a(r3, r7, r0, r2, r4)
                    goto L_0x0010
                L_0x001a:
                    int r6 = r7.readInt()
                    android.graphics.Point[] r2 = new android.graphics.Point[r6]
                    r3 = r1
                L_0x0021:
                    java.lang.Class<com.samsung.android.vexfwk.param.VexFwkOcrResultMetaV2$WordInfo> r4 = com.samsung.android.vexfwk.param.VexFwkOcrResultMetaV2.WordInfo.class
                    if (r3 == r6) goto L_0x0032
                    java.lang.ClassLoader r4 = r4.getClassLoader()
                    android.os.Parcelable r4 = r7.readParcelable(r4)
                    r2[r3] = r4
                    int r3 = r3 + 1
                    goto L_0x0021
                L_0x0032:
                    int r6 = r7.readInt()
                    android.graphics.Point[] r3 = new android.graphics.Point[r6]
                L_0x0038:
                    if (r1 == r6) goto L_0x0047
                    java.lang.ClassLoader r5 = r4.getClassLoader()
                    android.os.Parcelable r5 = r7.readParcelable(r5)
                    r3[r1] = r5
                    int r1 = r1 + 1
                    goto L_0x0038
                L_0x0047:
                    float r6 = r7.readFloat()
                    com.samsung.android.vexfwk.param.VexFwkOcrResultMetaV2$WordInfo r7 = new com.samsung.android.vexfwk.param.VexFwkOcrResultMetaV2$WordInfo
                    r7.<init>(r0, r2, r3, r6)
                    return r7
                */
                throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.param.VexFwkOcrResultMetaV2.WordInfo.Creator.createFromParcel(android.os.Parcel):com.samsung.android.vexfwk.param.VexFwkOcrResultMetaV2$WordInfo");
            }

            public final WordInfo[] newArray(int i2) {
                return new WordInfo[i2];
            }
        }

        public WordInfo(List<CharInfo> list, Point[] pointArr, Point[] pointArr2, float f) {
            j.e(list, "charInfoList");
            j.e(pointArr, "rect");
            j.e(pointArr2, "poly");
            this.charInfoList = list;
            this.rect = pointArr;
            this.poly = pointArr2;
            this.angle = f;
        }

        public final int describeContents() {
            return 0;
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

        public final void writeToParcel(Parcel parcel, int i2) {
            j.e(parcel, "dest");
            Iterator j2 = a.j(parcel, this.charInfoList);
            while (j2.hasNext()) {
                ((CharInfo) j2.next()).writeToParcel(parcel, i2);
            }
            Point[] pointArr = this.rect;
            int length = pointArr.length;
            parcel.writeInt(length);
            for (int i7 = 0; i7 != length; i7++) {
                parcel.writeParcelable(pointArr[i7], i2);
            }
            Point[] pointArr2 = this.poly;
            int length2 = pointArr2.length;
            parcel.writeInt(length2);
            for (int i8 = 0; i8 != length2; i8++) {
                parcel.writeParcelable(pointArr2[i8], i2);
            }
            parcel.writeFloat(this.angle);
        }
    }

    public VexFwkOcrResultMetaV2(List<BlockInfo> list, List<TableInfo> list2, boolean z) {
        j.e(list, "blockInfoList");
        j.e(list2, "tableInfoList");
        this.blockInfoList = list;
        this.tableInfoList = list2;
        this.isHandwrittenResult = z;
    }

    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VexFwkOcrResultMetaV2)) {
            return false;
        }
        VexFwkOcrResultMetaV2 vexFwkOcrResultMetaV2 = (VexFwkOcrResultMetaV2) obj;
        if (this.isHandwrittenResult != vexFwkOcrResultMetaV2.isHandwrittenResult || this.blockInfoList.size() != vexFwkOcrResultMetaV2.blockInfoList.size() || this.tableInfoList.size() != vexFwkOcrResultMetaV2.tableInfoList.size()) {
            return false;
        }
        int size = this.blockInfoList.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (!this.blockInfoList.get(i2).equals(vexFwkOcrResultMetaV2.blockInfoList.get(i2))) {
                return false;
            }
        }
        int size2 = this.tableInfoList.size();
        for (int i7 = 0; i7 < size2; i7++) {
            if (!this.tableInfoList.get(i7).equals(vexFwkOcrResultMetaV2.tableInfoList.get(i7))) {
                return false;
            }
        }
        return true;
    }

    public final List<BlockInfo> getBlockInfoList() {
        return this.blockInfoList;
    }

    public final List<TableInfo> getTableInfoList() {
        return this.tableInfoList;
    }

    public final boolean isHandwrittenResult() {
        return this.isHandwrittenResult;
    }

    public final VexFwkOcrResultV2 toResult() {
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
                        arrayList4.add(new VexFwkOcrResultV2.CharInfo(charInfo.getString(), charInfo.getRect(), charInfo.getAngle()));
                    }
                    arrayList3.add(new VexFwkOcrResultV2.WordInfo(arrayList4, wordInfo.getRect(), wordInfo.getPoly(), wordInfo.getAngle()));
                    i2 = 10;
                }
                arrayList2.add(new VexFwkOcrResultV2.LineInfo(arrayList3, lineInfo.getRect(), lineInfo.isCurved(), lineInfo.getAngle()));
                i2 = 10;
            }
            arrayList.add(new VexFwkOcrResultV2.BlockInfo(arrayList2, blockInfo.getRect(), blockInfo.isTabular(), blockInfo.getAngle()));
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
                    arrayList7.add(new VexFwkOcrResultV2.TableCellInfo(tableCellInfo.getCellBoundary(), tableCellInfo.getCellText(), tableCellInfo.getAngle()));
                }
                arrayList6.add(new VexFwkOcrResultV2.TableRowInfo(arrayList7, tableRowInfo.getAngle()));
            }
            arrayList5.add(new VexFwkOcrResultV2.TableInfo(arrayList6, tableInfo.getTableBoundary(), tableInfo.getAngle()));
        }
        return new VexFwkOcrResultV2(arrayList, arrayList5, this.isHandwrittenResult);
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        Iterator j2 = a.j(parcel, this.blockInfoList);
        while (j2.hasNext()) {
            ((BlockInfo) j2.next()).writeToParcel(parcel, i2);
        }
        Iterator j3 = a.j(parcel, this.tableInfoList);
        while (j3.hasNext()) {
            ((TableInfo) j3.next()).writeToParcel(parcel, i2);
        }
        parcel.writeInt(this.isHandwrittenResult ? 1 : 0);
    }

    @Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B5\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rJ\u001a\u0010\u0011\u001a\u00020\u00102\b\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\u001d\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\b¢\u0006\u0004\b\u0017\u0010\u0018J\r\u0010\u0019\u001a\u00020\b¢\u0006\u0004\b\u0019\u0010\u001aR\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u001d\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u001e\u001a\u0004\b\u001f\u0010 R\"\u0010\t\u001a\u00020\b8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\t\u0010!\u001a\u0004\b\t\u0010\u001a\"\u0004\b\"\u0010#R\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010$\u001a\u0004\b%\u0010&R\u0011\u0010*\u001a\u00020'8F¢\u0006\u0006\u001a\u0004\b(\u0010)¨\u0006+"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkOcrResultMetaV2$BlockInfo;", "Landroid/os/Parcelable;", "", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResultMetaV2$LineInfo;", "lineInfoList", "", "Landroid/graphics/Point;", "rect", "", "isTabular", "", "angle", "<init>", "(Ljava/util/List;[Landroid/graphics/Point;IF)V", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Landroid/os/Parcel;", "dest", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/util/List;", "getLineInfoList", "()Ljava/util/List;", "[Landroid/graphics/Point;", "getRect", "()[Landroid/graphics/Point;", "I", "setTabular", "(I)V", "F", "getAngle", "()F", "", "getString", "()Ljava/lang/String;", "string", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class BlockInfo implements Parcelable {
        public static final Parcelable.Creator<BlockInfo> CREATOR = new Creator();
        private final float angle;
        private int isTabular;
        private final List<LineInfo> lineInfoList;
        private final Point[] rect;

        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Creator implements Parcelable.Creator<BlockInfo> {
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: android.graphics.Point[]} */
            /* JADX WARNING: Multi-variable type inference failed */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public final com.samsung.android.vexfwk.param.VexFwkOcrResultMetaV2.BlockInfo createFromParcel(android.os.Parcel r6) {
                /*
                    r5 = this;
                    java.lang.String r5 = "parcel"
                    kotlin.jvm.internal.j.e(r6, r5)
                    int r5 = r6.readInt()
                    java.util.ArrayList r0 = new java.util.ArrayList
                    r0.<init>(r5)
                    r1 = 0
                    r2 = r1
                L_0x0010:
                    if (r2 == r5) goto L_0x001a
                    android.os.Parcelable$Creator<com.samsung.android.vexfwk.param.VexFwkOcrResultMetaV2$LineInfo> r3 = com.samsung.android.vexfwk.param.VexFwkOcrResultMetaV2.LineInfo.CREATOR
                    r4 = 1
                    int r2 = A.a.a(r3, r6, r0, r2, r4)
                    goto L_0x0010
                L_0x001a:
                    int r5 = r6.readInt()
                    android.graphics.Point[] r2 = new android.graphics.Point[r5]
                L_0x0020:
                    if (r1 == r5) goto L_0x0031
                    java.lang.Class<com.samsung.android.vexfwk.param.VexFwkOcrResultMetaV2$BlockInfo> r3 = com.samsung.android.vexfwk.param.VexFwkOcrResultMetaV2.BlockInfo.class
                    java.lang.ClassLoader r3 = r3.getClassLoader()
                    android.os.Parcelable r3 = r6.readParcelable(r3)
                    r2[r1] = r3
                    int r1 = r1 + 1
                    goto L_0x0020
                L_0x0031:
                    int r5 = r6.readInt()
                    float r6 = r6.readFloat()
                    com.samsung.android.vexfwk.param.VexFwkOcrResultMetaV2$BlockInfo r1 = new com.samsung.android.vexfwk.param.VexFwkOcrResultMetaV2$BlockInfo
                    r1.<init>(r0, r2, r5, r6)
                    return r1
                */
                throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.param.VexFwkOcrResultMetaV2.BlockInfo.Creator.createFromParcel(android.os.Parcel):com.samsung.android.vexfwk.param.VexFwkOcrResultMetaV2$BlockInfo");
            }

            public final BlockInfo[] newArray(int i2) {
                return new BlockInfo[i2];
            }
        }

        public BlockInfo(List<LineInfo> list, Point[] pointArr, int i2, float f) {
            j.e(list, "lineInfoList");
            j.e(pointArr, "rect");
            this.lineInfoList = list;
            this.rect = pointArr;
            this.isTabular = i2;
            this.angle = f;
        }

        public final int describeContents() {
            return 0;
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

        public final void writeToParcel(Parcel parcel, int i2) {
            j.e(parcel, "dest");
            Iterator j2 = a.j(parcel, this.lineInfoList);
            while (j2.hasNext()) {
                ((LineInfo) j2.next()).writeToParcel(parcel, i2);
            }
            Point[] pointArr = this.rect;
            int length = pointArr.length;
            parcel.writeInt(length);
            for (int i7 = 0; i7 != length; i7++) {
                parcel.writeParcelable(pointArr[i7], i2);
            }
            parcel.writeInt(this.isTabular);
            parcel.writeFloat(this.angle);
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ BlockInfo(List list, Point[] pointArr, int i2, float f, int i7, e eVar) {
            this(list, pointArr, (i7 & 4) != 0 ? 0 : i2, f);
        }
    }

    public VexFwkOcrResultMetaV2(VexFwkOcrResultMeta vexFwkOcrResultMeta, VexFwkOcrAdditionalMeta vexFwkOcrAdditionalMeta) {
        boolean z;
        List list;
        VexFwkOcrResultMetaV2 vexFwkOcrResultMetaV2;
        List<VexFwkOcrAdditionalMeta.TableInfo> tableInfoList2;
        VexFwkOcrResultMeta vexFwkOcrResultMeta2 = vexFwkOcrResultMeta;
        j.e(vexFwkOcrResultMeta2, "ocrResultV1");
        Iterable blockInfoList2 = vexFwkOcrResultMeta2.getBlockInfoList();
        int i2 = 10;
        ArrayList arrayList = new ArrayList(C1196n.w0(blockInfoList2, 10));
        Iterator it = blockInfoList2.iterator();
        while (true) {
            z = false;
            if (!it.hasNext()) {
                break;
            }
            VexFwkOcrResultMeta.BlockInfo blockInfo = (VexFwkOcrResultMeta.BlockInfo) it.next();
            Iterable<VexFwkOcrResultMeta.LineInfo> lineInfo = blockInfo.getLineInfo();
            ArrayList arrayList2 = new ArrayList(C1196n.w0(lineInfo, i2));
            for (VexFwkOcrResultMeta.LineInfo lineInfo2 : lineInfo) {
                Iterable<VexFwkOcrResultMeta.WordInfo> wordInfo = lineInfo2.getWordInfo();
                ArrayList arrayList3 = new ArrayList(C1196n.w0(wordInfo, i2));
                for (VexFwkOcrResultMeta.WordInfo wordInfo2 : wordInfo) {
                    Iterable<VexFwkOcrResultMeta.CharInfo> charInfo = wordInfo2.getCharInfo();
                    ArrayList arrayList4 = new ArrayList(C1196n.w0(charInfo, i2));
                    for (VexFwkOcrResultMeta.CharInfo charInfo2 : charInfo) {
                        arrayList4.add(new CharInfo(charInfo2.getString(), RectKt.toArrayOfPoints(charInfo2.getRect()), 0.0f));
                    }
                    arrayList3.add(new WordInfo(arrayList4, RectKt.toArrayOfPoints(wordInfo2.getRect()), wordInfo2.getPoly(), 0.0f));
                    i2 = 10;
                }
                arrayList2.add(new LineInfo(arrayList3, RectKt.toArrayOfPoints(lineInfo2.getRect()), false, 0.0f));
                i2 = 10;
            }
            arrayList.add(new BlockInfo(arrayList2, RectKt.toArrayOfPoints(blockInfo.getRect()), blockInfo.isTabular(), 0.0f));
            i2 = 10;
        }
        if (vexFwkOcrAdditionalMeta == null || (tableInfoList2 = vexFwkOcrAdditionalMeta.getTableInfoList()) == null) {
            list = C1202t.d;
        } else {
            Iterable<VexFwkOcrAdditionalMeta.TableInfo> iterable = tableInfoList2;
            list = new ArrayList(C1196n.w0(iterable, 10));
            for (VexFwkOcrAdditionalMeta.TableInfo tableInfo : iterable) {
                Iterable<VexFwkOcrAdditionalMeta.TableRowInfo> tableRowInfo = tableInfo.getTableRowInfo();
                ArrayList arrayList5 = new ArrayList(C1196n.w0(tableRowInfo, 10));
                for (VexFwkOcrAdditionalMeta.TableRowInfo tableCellInfo : tableRowInfo) {
                    Iterable<VexFwkOcrAdditionalMeta.TableCellInfo> tableCellInfo2 = tableCellInfo.getTableCellInfo();
                    ArrayList arrayList6 = new ArrayList(C1196n.w0(tableCellInfo2, 10));
                    for (VexFwkOcrAdditionalMeta.TableCellInfo tableCellInfo3 : tableCellInfo2) {
                        arrayList6.add(new TableCellInfo(RectKt.toArrayOfPoints(tableCellInfo3.getCellBoundary()), tableCellInfo3.getCellText(), 0.0f));
                    }
                    arrayList5.add(new TableRowInfo(arrayList6, 0.0f));
                }
                list.add(new TableInfo(arrayList5, RectKt.toArrayOfPoints(tableInfo.getTableBoundary()), 0.0f));
            }
        }
        if (vexFwkOcrAdditionalMeta == null || !vexFwkOcrAdditionalMeta.isHandWritten()) {
            vexFwkOcrResultMetaV2 = this;
        } else {
            vexFwkOcrResultMetaV2 = this;
            z = true;
        }
        new VexFwkOcrResultMetaV2(arrayList, list, z);
    }
}
