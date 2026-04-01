package com.samsung.android.vexfwk.param;

import A.a;
import Tf.n;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ld.b;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\b\u0007\u0018\u0000 \u00182\u00020\u0001:\u0005\u0018\u0019\u001a\u001b\u001cB\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u001a\u0010\n\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u001d\u0010\u0011\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0011\u0010\u0012J\r\u0010\u0013\u001a\u00020\u000e¢\u0006\u0004\b\u0013\u0010\u0014R\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u001d"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkOcrResultMeta;", "Landroid/os/Parcelable;", "", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResultMeta$BlockInfo;", "blockInfoList", "<init>", "(Ljava/util/List;)V", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/util/List;", "getBlockInfoList", "()Ljava/util/List;", "Companion", "CharInfo", "WordInfo", "LineInfo", "BlockInfo", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkOcrResultMeta implements Parcelable {
    public static final Parcelable.Creator<VexFwkOcrResultMeta> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private final List<BlockInfo> blockInfoList;

    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0007\u0018\u00002\u00020\u0001B'\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0004\b\t\u0010\nJ\u001a\u0010\u000e\u001a\u00020\r2\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u001d\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0012¢\u0006\u0004\b\u0015\u0010\u0016J\r\u0010\u0017\u001a\u00020\u0012¢\u0006\u0004\b\u0017\u0010\u0018R\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u001d\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0006¢\u0006\f\n\u0004\b\b\u0010\u001f\u001a\u0004\b \u0010!¨\u0006\""}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkOcrResultMeta$CharInfo;", "Landroid/os/Parcelable;", "", "string", "Landroid/graphics/Rect;", "rect", "", "Landroid/graphics/Point;", "poly", "<init>", "(Ljava/lang/String;Landroid/graphics/Rect;[Landroid/graphics/Point;)V", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/lang/String;", "getString", "()Ljava/lang/String;", "Landroid/graphics/Rect;", "getRect", "()Landroid/graphics/Rect;", "[Landroid/graphics/Point;", "getPoly", "()[Landroid/graphics/Point;", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class CharInfo implements Parcelable {
        public static final Parcelable.Creator<CharInfo> CREATOR = new Creator();
        private final Point[] poly;
        private final Rect rect;
        private final String string;

        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Creator implements Parcelable.Creator<CharInfo> {
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: android.graphics.Point[]} */
            /* JADX WARNING: Multi-variable type inference failed */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public final com.samsung.android.vexfwk.param.VexFwkOcrResultMeta.CharInfo createFromParcel(android.os.Parcel r7) {
                /*
                    r6 = this;
                    java.lang.String r6 = "parcel"
                    kotlin.jvm.internal.j.e(r7, r6)
                    java.lang.String r6 = r7.readString()
                    java.lang.Class<com.samsung.android.vexfwk.param.VexFwkOcrResultMeta$CharInfo> r0 = com.samsung.android.vexfwk.param.VexFwkOcrResultMeta.CharInfo.class
                    java.lang.ClassLoader r1 = r0.getClassLoader()
                    android.os.Parcelable r1 = r7.readParcelable(r1)
                    android.graphics.Rect r1 = (android.graphics.Rect) r1
                    int r2 = r7.readInt()
                    android.graphics.Point[] r3 = new android.graphics.Point[r2]
                    r4 = 0
                L_0x001c:
                    if (r4 == r2) goto L_0x002b
                    java.lang.ClassLoader r5 = r0.getClassLoader()
                    android.os.Parcelable r5 = r7.readParcelable(r5)
                    r3[r4] = r5
                    int r4 = r4 + 1
                    goto L_0x001c
                L_0x002b:
                    com.samsung.android.vexfwk.param.VexFwkOcrResultMeta$CharInfo r7 = new com.samsung.android.vexfwk.param.VexFwkOcrResultMeta$CharInfo
                    r7.<init>(r6, r1, r3)
                    return r7
                */
                throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.param.VexFwkOcrResultMeta.CharInfo.Creator.createFromParcel(android.os.Parcel):com.samsung.android.vexfwk.param.VexFwkOcrResultMeta$CharInfo");
            }

            public final CharInfo[] newArray(int i2) {
                return new CharInfo[i2];
            }
        }

        public CharInfo(String str, Rect rect2, Point[] pointArr) {
            j.e(rect2, "rect");
            j.e(pointArr, "poly");
            this.string = str;
            this.rect = rect2;
            this.poly = pointArr;
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

        public final void writeToParcel(Parcel parcel, int i2) {
            j.e(parcel, "dest");
            parcel.writeString(this.string);
            parcel.writeParcelable(this.rect, i2);
            Point[] pointArr = this.poly;
            int length = pointArr.length;
            parcel.writeInt(length);
            for (int i7 = 0; i7 != length; i7++) {
                parcel.writeParcelable(pointArr[i7], i2);
            }
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkOcrResultMeta$Companion;", "Lcom/samsung/android/vexfwk/param/VexFwkParamBaseParcelable;", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResultMeta;", "<init>", "()V", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion extends VexFwkParamBaseParcelable<VexFwkOcrResultMeta> {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        /* access modifiers changed from: private */
        public static final Parcelable.Creator _init_$lambda$0() {
            return VexFwkOcrResultMeta.CREATOR;
        }

        private Companion() {
            super(new b(3));
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<VexFwkOcrResultMeta> {
        public final VexFwkOcrResultMeta createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            int readInt = parcel.readInt();
            ArrayList arrayList = new ArrayList(readInt);
            int i2 = 0;
            while (i2 != readInt) {
                i2 = a.a(BlockInfo.CREATOR, parcel, arrayList, i2, 1);
            }
            return new VexFwkOcrResultMeta(arrayList);
        }

        public final VexFwkOcrResultMeta[] newArray(int i2) {
            return new VexFwkOcrResultMeta[i2];
        }
    }

    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B+\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u001a\u0010\u000f\u001a\u00020\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u001d\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0013¢\u0006\u0004\b\u0016\u0010\u0017J\r\u0010\u0018\u001a\u00020\u0013¢\u0006\u0004\b\u0018\u0010\u0019R\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR\u001d\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0006¢\u0006\f\n\u0004\b\t\u0010 \u001a\u0004\b!\u0010\"R\u0011\u0010&\u001a\u00020#8F¢\u0006\u0006\u001a\u0004\b$\u0010%¨\u0006'"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkOcrResultMeta$LineInfo;", "Landroid/os/Parcelable;", "", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResultMeta$WordInfo;", "wordInfo", "Landroid/graphics/Rect;", "rect", "", "Landroid/graphics/Point;", "poly", "<init>", "(Ljava/util/List;Landroid/graphics/Rect;[Landroid/graphics/Point;)V", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/util/List;", "getWordInfo", "()Ljava/util/List;", "Landroid/graphics/Rect;", "getRect", "()Landroid/graphics/Rect;", "[Landroid/graphics/Point;", "getPoly", "()[Landroid/graphics/Point;", "", "getString", "()Ljava/lang/String;", "string", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class LineInfo implements Parcelable {
        public static final Parcelable.Creator<LineInfo> CREATOR = new Creator();
        private final Point[] poly;
        private final Rect rect;
        private final List<WordInfo> wordInfo;

        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Creator implements Parcelable.Creator<LineInfo> {
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v0, resolved type: android.graphics.Point[]} */
            /* JADX WARNING: Multi-variable type inference failed */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public final com.samsung.android.vexfwk.param.VexFwkOcrResultMeta.LineInfo createFromParcel(android.os.Parcel r7) {
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
                    android.os.Parcelable$Creator<com.samsung.android.vexfwk.param.VexFwkOcrResultMeta$WordInfo> r3 = com.samsung.android.vexfwk.param.VexFwkOcrResultMeta.WordInfo.CREATOR
                    r4 = 1
                    int r2 = A.a.a(r3, r7, r0, r2, r4)
                    goto L_0x0010
                L_0x001a:
                    java.lang.Class<com.samsung.android.vexfwk.param.VexFwkOcrResultMeta$LineInfo> r6 = com.samsung.android.vexfwk.param.VexFwkOcrResultMeta.LineInfo.class
                    java.lang.ClassLoader r2 = r6.getClassLoader()
                    android.os.Parcelable r2 = r7.readParcelable(r2)
                    android.graphics.Rect r2 = (android.graphics.Rect) r2
                    int r3 = r7.readInt()
                    android.graphics.Point[] r4 = new android.graphics.Point[r3]
                L_0x002c:
                    if (r1 == r3) goto L_0x003b
                    java.lang.ClassLoader r5 = r6.getClassLoader()
                    android.os.Parcelable r5 = r7.readParcelable(r5)
                    r4[r1] = r5
                    int r1 = r1 + 1
                    goto L_0x002c
                L_0x003b:
                    com.samsung.android.vexfwk.param.VexFwkOcrResultMeta$LineInfo r6 = new com.samsung.android.vexfwk.param.VexFwkOcrResultMeta$LineInfo
                    r6.<init>(r0, r2, r4)
                    return r6
                */
                throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.param.VexFwkOcrResultMeta.LineInfo.Creator.createFromParcel(android.os.Parcel):com.samsung.android.vexfwk.param.VexFwkOcrResultMeta$LineInfo");
            }

            public final LineInfo[] newArray(int i2) {
                return new LineInfo[i2];
            }
        }

        public LineInfo(List<WordInfo> list, Rect rect2, Point[] pointArr) {
            j.e(list, "wordInfo");
            j.e(rect2, "rect");
            j.e(pointArr, "poly");
            this.wordInfo = list;
            this.rect = rect2;
            this.poly = pointArr;
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

        public final void writeToParcel(Parcel parcel, int i2) {
            j.e(parcel, "dest");
            Iterator j2 = a.j(parcel, this.wordInfo);
            while (j2.hasNext()) {
                ((WordInfo) j2.next()).writeToParcel(parcel, i2);
            }
            parcel.writeParcelable(this.rect, i2);
            Point[] pointArr = this.poly;
            int length = pointArr.length;
            parcel.writeInt(length);
            for (int i7 = 0; i7 != length; i7++) {
                parcel.writeParcelable(pointArr[i7], i2);
            }
        }
    }

    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B+\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u001a\u0010\u000f\u001a\u00020\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u001d\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0013¢\u0006\u0004\b\u0016\u0010\u0017J\r\u0010\u0018\u001a\u00020\u0013¢\u0006\u0004\b\u0018\u0010\u0019R\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR\u001d\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0006¢\u0006\f\n\u0004\b\t\u0010 \u001a\u0004\b!\u0010\"R\u0011\u0010&\u001a\u00020#8F¢\u0006\u0006\u001a\u0004\b$\u0010%¨\u0006'"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkOcrResultMeta$WordInfo;", "Landroid/os/Parcelable;", "", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResultMeta$CharInfo;", "charInfo", "Landroid/graphics/Rect;", "rect", "", "Landroid/graphics/Point;", "poly", "<init>", "(Ljava/util/List;Landroid/graphics/Rect;[Landroid/graphics/Point;)V", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/util/List;", "getCharInfo", "()Ljava/util/List;", "Landroid/graphics/Rect;", "getRect", "()Landroid/graphics/Rect;", "[Landroid/graphics/Point;", "getPoly", "()[Landroid/graphics/Point;", "", "getString", "()Ljava/lang/String;", "string", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class WordInfo implements Parcelable {
        public static final Parcelable.Creator<WordInfo> CREATOR = new Creator();
        private final List<CharInfo> charInfo;
        private final Point[] poly;
        private final Rect rect;

        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Creator implements Parcelable.Creator<WordInfo> {
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v0, resolved type: android.graphics.Point[]} */
            /* JADX WARNING: Multi-variable type inference failed */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public final com.samsung.android.vexfwk.param.VexFwkOcrResultMeta.WordInfo createFromParcel(android.os.Parcel r7) {
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
                    android.os.Parcelable$Creator<com.samsung.android.vexfwk.param.VexFwkOcrResultMeta$CharInfo> r3 = com.samsung.android.vexfwk.param.VexFwkOcrResultMeta.CharInfo.CREATOR
                    r4 = 1
                    int r2 = A.a.a(r3, r7, r0, r2, r4)
                    goto L_0x0010
                L_0x001a:
                    java.lang.Class<com.samsung.android.vexfwk.param.VexFwkOcrResultMeta$WordInfo> r6 = com.samsung.android.vexfwk.param.VexFwkOcrResultMeta.WordInfo.class
                    java.lang.ClassLoader r2 = r6.getClassLoader()
                    android.os.Parcelable r2 = r7.readParcelable(r2)
                    android.graphics.Rect r2 = (android.graphics.Rect) r2
                    int r3 = r7.readInt()
                    android.graphics.Point[] r4 = new android.graphics.Point[r3]
                L_0x002c:
                    if (r1 == r3) goto L_0x003b
                    java.lang.ClassLoader r5 = r6.getClassLoader()
                    android.os.Parcelable r5 = r7.readParcelable(r5)
                    r4[r1] = r5
                    int r1 = r1 + 1
                    goto L_0x002c
                L_0x003b:
                    com.samsung.android.vexfwk.param.VexFwkOcrResultMeta$WordInfo r6 = new com.samsung.android.vexfwk.param.VexFwkOcrResultMeta$WordInfo
                    r6.<init>(r0, r2, r4)
                    return r6
                */
                throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.param.VexFwkOcrResultMeta.WordInfo.Creator.createFromParcel(android.os.Parcel):com.samsung.android.vexfwk.param.VexFwkOcrResultMeta$WordInfo");
            }

            public final WordInfo[] newArray(int i2) {
                return new WordInfo[i2];
            }
        }

        public WordInfo(List<CharInfo> list, Rect rect2, Point[] pointArr) {
            j.e(list, "charInfo");
            j.e(rect2, "rect");
            j.e(pointArr, "poly");
            this.charInfo = list;
            this.rect = rect2;
            this.poly = pointArr;
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

        public final void writeToParcel(Parcel parcel, int i2) {
            j.e(parcel, "dest");
            Iterator j2 = a.j(parcel, this.charInfo);
            while (j2.hasNext()) {
                ((CharInfo) j2.next()).writeToParcel(parcel, i2);
            }
            parcel.writeParcelable(this.rect, i2);
            Point[] pointArr = this.poly;
            int length = pointArr.length;
            parcel.writeInt(length);
            for (int i7 = 0; i7 != length; i7++) {
                parcel.writeParcelable(pointArr[i7], i2);
            }
        }
    }

    public VexFwkOcrResultMeta(List<BlockInfo> list) {
        j.e(list, "blockInfoList");
        this.blockInfoList = list;
    }

    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VexFwkOcrResultMeta)) {
            return false;
        }
        VexFwkOcrResultMeta vexFwkOcrResultMeta = (VexFwkOcrResultMeta) obj;
        if (this.blockInfoList.size() != vexFwkOcrResultMeta.blockInfoList.size()) {
            return false;
        }
        int size = this.blockInfoList.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (!this.blockInfoList.get(i2).equals(vexFwkOcrResultMeta.blockInfoList.get(i2))) {
                return false;
            }
        }
        return true;
    }

    public final List<BlockInfo> getBlockInfoList() {
        return this.blockInfoList;
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        Iterator j2 = a.j(parcel, this.blockInfoList);
        while (j2.hasNext()) {
            ((BlockInfo) j2.next()).writeToParcel(parcel, i2);
        }
    }

    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0018\b\u0007\u0018\u00002\u00020\u0001B?\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u0012\b\b\u0002\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000e\u0010\u000fJ\u001a\u0010\u0013\u001a\u00020\u00122\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u001d\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\n¢\u0006\u0004\b\u0019\u0010\u001aJ\r\u0010\u001b\u001a\u00020\n¢\u0006\u0004\b\u001b\u0010\u001cR\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010 \u001a\u0004\b!\u0010\"R\u001d\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0006¢\u0006\f\n\u0004\b\t\u0010#\u001a\u0004\b$\u0010%R\"\u0010\u000b\u001a\u00020\n8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010&\u001a\u0004\b\u000b\u0010\u001c\"\u0004\b'\u0010(R\"\u0010\r\u001a\u00020\f8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\r\u0010)\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u0011\u0010/\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\b.\u0010+¨\u00060"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkOcrResultMeta$BlockInfo;", "Landroid/os/Parcelable;", "", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResultMeta$LineInfo;", "lineInfo", "Landroid/graphics/Rect;", "rect", "", "Landroid/graphics/Point;", "poly", "", "isTabular", "", "translatedText", "<init>", "(Ljava/util/List;Landroid/graphics/Rect;[Landroid/graphics/Point;ILjava/lang/String;)V", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Landroid/os/Parcel;", "dest", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/util/List;", "getLineInfo", "()Ljava/util/List;", "Landroid/graphics/Rect;", "getRect", "()Landroid/graphics/Rect;", "[Landroid/graphics/Point;", "getPoly", "()[Landroid/graphics/Point;", "I", "setTabular", "(I)V", "Ljava/lang/String;", "getTranslatedText", "()Ljava/lang/String;", "setTranslatedText", "(Ljava/lang/String;)V", "getString", "string", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class BlockInfo implements Parcelable {
        public static final Parcelable.Creator<BlockInfo> CREATOR = new Creator();
        private int isTabular;
        private final List<LineInfo> lineInfo;
        private final Point[] poly;
        private final Rect rect;
        private String translatedText;

        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Creator implements Parcelable.Creator<BlockInfo> {
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: android.graphics.Point[]} */
            /* JADX WARNING: Multi-variable type inference failed */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public final com.samsung.android.vexfwk.param.VexFwkOcrResultMeta.BlockInfo createFromParcel(android.os.Parcel r7) {
                /*
                    r6 = this;
                    java.lang.String r6 = "parcel"
                    kotlin.jvm.internal.j.e(r7, r6)
                    int r6 = r7.readInt()
                    java.util.ArrayList r1 = new java.util.ArrayList
                    r1.<init>(r6)
                    r0 = 0
                    r2 = r0
                L_0x0010:
                    if (r2 == r6) goto L_0x001a
                    android.os.Parcelable$Creator<com.samsung.android.vexfwk.param.VexFwkOcrResultMeta$LineInfo> r3 = com.samsung.android.vexfwk.param.VexFwkOcrResultMeta.LineInfo.CREATOR
                    r4 = 1
                    int r2 = A.a.a(r3, r7, r1, r2, r4)
                    goto L_0x0010
                L_0x001a:
                    java.lang.Class<com.samsung.android.vexfwk.param.VexFwkOcrResultMeta$BlockInfo> r6 = com.samsung.android.vexfwk.param.VexFwkOcrResultMeta.BlockInfo.class
                    java.lang.ClassLoader r2 = r6.getClassLoader()
                    android.os.Parcelable r2 = r7.readParcelable(r2)
                    android.graphics.Rect r2 = (android.graphics.Rect) r2
                    int r3 = r7.readInt()
                    r4 = r3
                    android.graphics.Point[] r3 = new android.graphics.Point[r4]
                L_0x002d:
                    if (r0 == r4) goto L_0x003c
                    java.lang.ClassLoader r5 = r6.getClassLoader()
                    android.os.Parcelable r5 = r7.readParcelable(r5)
                    r3[r0] = r5
                    int r0 = r0 + 1
                    goto L_0x002d
                L_0x003c:
                    int r4 = r7.readInt()
                    java.lang.String r5 = r7.readString()
                    com.samsung.android.vexfwk.param.VexFwkOcrResultMeta$BlockInfo r0 = new com.samsung.android.vexfwk.param.VexFwkOcrResultMeta$BlockInfo
                    r0.<init>(r1, r2, r3, r4, r5)
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.param.VexFwkOcrResultMeta.BlockInfo.Creator.createFromParcel(android.os.Parcel):com.samsung.android.vexfwk.param.VexFwkOcrResultMeta$BlockInfo");
            }

            public final BlockInfo[] newArray(int i2) {
                return new BlockInfo[i2];
            }
        }

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

        public final void writeToParcel(Parcel parcel, int i2) {
            j.e(parcel, "dest");
            Iterator j2 = a.j(parcel, this.lineInfo);
            while (j2.hasNext()) {
                ((LineInfo) j2.next()).writeToParcel(parcel, i2);
            }
            parcel.writeParcelable(this.rect, i2);
            Point[] pointArr = this.poly;
            int length = pointArr.length;
            parcel.writeInt(length);
            for (int i7 = 0; i7 != length; i7++) {
                parcel.writeParcelable(pointArr[i7], i2);
            }
            parcel.writeInt(this.isTabular);
            parcel.writeString(this.translatedText);
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ BlockInfo(List list, Rect rect2, Point[] pointArr, int i2, String str, int i7, e eVar) {
            this(list, rect2, pointArr, (i7 & 8) != 0 ? 0 : i2, (i7 & 16) != 0 ? "" : str);
        }
    }
}
